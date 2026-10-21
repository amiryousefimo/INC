package ir.tamin.incomeBank.dao.announcement.setad;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.announcement.setad.AnnouncementEdareKol;
import ir.tamin.incomeBank.model.announcement.setad.AnnouncementEdareKolView;
import ir.tamin.incomeBank.model.announcement.setad.AnnouncementEdareKolSummery;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnouncementEdareDAOService extends BaseDAOService<AnnouncementEdareKol> {


    @Inject
    private EntityManager em;


    public Map<String, Object> getSetdaByDateRange(String startDate1, String endDate1, String edareCode1) {
        Map<String, Object> map = new HashMap<>();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<AnnouncementEdareKol> root = criteriaQuery.from(AnnouncementEdareKol.class);

        Expression<String> edareCode = root.get("edareCode");
        Expression<String> ordDate = root.get("letterDate");
        Expression<BigDecimal> sumEmployees = root.get("employees");
        Expression<BigDecimal> sumEmployeesCount = root.get("employeesCount");
        Expression<BigDecimal> sumPeyman = root.get("peyman");
        Expression<BigDecimal> sumPeymanCount = root.get("peymanCount");
        Expression<BigDecimal> sumAmt = root.get("sumAmount");
        Expression<BigDecimal> sumCount = root.get("sumCount");

        Predicate datePredicate = criteriaBuilder.between(ordDate, startDate1, endDate1);

        criteriaQuery.multiselect(
                root.get("edareCode"),
                root.get("edareName"),
                criteriaBuilder.literal(startDate1),
                criteriaBuilder.literal(endDate1),
                criteriaBuilder.sum(sumEmployees),
                criteriaBuilder.sum(sumEmployeesCount),
                criteriaBuilder.sum(sumPeyman),
                criteriaBuilder.sum(sumPeymanCount),
                criteriaBuilder.sum(sumAmt),
                criteriaBuilder.sum(sumCount)
        );
        if (edareCode1.equals("-1")) {
            Predicate edareCdePredict = criteriaBuilder.like(edareCode, "%%");
            criteriaQuery.where(datePredicate,edareCdePredict).groupBy(root.get("edareCode"), root.get("edareName"));
        }
        if (!edareCode1.equals("-1")) {
            Predicate edareCdePredict = criteriaBuilder.equal(edareCode, edareCode1);
            criteriaQuery.where(datePredicate,edareCdePredict).groupBy(root.get("edareCode"), root.get("edareName"));
        }
        

        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
        List<Object[]> results = query.getResultList();
        List<AnnouncementEdareKolView> list = new ArrayList<>();

        for (Object[] result : results) {
          
            String edareCode2 = (String) result[0];
            String edareName = (String) result[1];
            String startDate = (String) result[2];
            String endDate = (String) result[3];
            BigDecimal sumE = ((BigDecimal) result[4]);
            BigDecimal sumEC = ((BigDecimal) result[5]);
            BigDecimal sumP = ((BigDecimal) result[6]);
            BigDecimal sumPC = ((BigDecimal) result[7]);
            BigDecimal sumAmount = ((BigDecimal) result[8]);
            BigDecimal sumCountO = ((BigDecimal) result[9]);


            AnnouncementEdareKolView setadView = new AnnouncementEdareKolView();
            setadView.setPeyman(sumP);
            setadView.setPeymanCount(sumPC);
            setadView.setEmployees(sumE);
            setadView.setEmployeesCount(sumEC);
            setadView.setEdareCode(edareCode2);
            setadView.setEdareName(edareName);
            setadView.setLetterDate1(startDate);
            setadView.setLetterDate2(endDate);
            setadView.setSumAmount(sumAmount);
            setadView.setSumCount(sumCountO);
            list.add(setadView);
        }
        BigDecimal sumSumValue = BigDecimal.valueOf(0);
        BigDecimal sumCountValue = BigDecimal.valueOf(0);
        BigDecimal sumEmployeeValue = BigDecimal.valueOf(0);
        BigDecimal sumEmployeeCountValue = BigDecimal.valueOf(0);
        BigDecimal sumPeymanValue = BigDecimal.valueOf(0);
        BigDecimal sumPeymanCountValue = BigDecimal.valueOf(0);

        for (AnnouncementEdareKolView setadView : list) {
            BigDecimal sumAmount2 = setadView.getSumAmount();
            BigDecimal sumCount2 = setadView.getSumCount();
            BigDecimal sumPeyman2 = setadView.getPeyman();
            BigDecimal sumPeymanCount2 = setadView.getPeymanCount();
            BigDecimal sumEmployees2 = setadView.getEmployees();
            BigDecimal sumEmployeesCount2 = setadView.getEmployeesCount();

            sumSumValue = sumSumValue.add(sumAmount2);
            sumCountValue = sumCountValue.add(sumCount2);
            sumEmployeeValue = sumEmployeeValue.add(sumEmployees2);
            sumEmployeeCountValue = sumEmployeeCountValue.add(sumEmployeesCount2);
            sumPeymanValue = sumPeymanValue.add(sumPeyman2);
            sumPeymanCountValue = sumPeymanCountValue.add(sumPeymanCount2);
        }
        BigDecimal total = BigDecimal.valueOf(list.size());
        AnnouncementEdareKolSummery summery = new AnnouncementEdareKolSummery(sumEmployeeValue, sumEmployeeCountValue, sumPeymanValue, sumPeymanCountValue, sumSumValue, sumCountValue);
        map.put("total",total);
        map.put("data",list);
        map.put("sum",summery);
        return map;

    }



    /******************************/

    public AnnouncementEdareKolSummery getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<AnnouncementEdareKol> announcementBranchRoot = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, announcementBranchRoot, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(AnnouncementEdareKolSummery.class,
                criteriaBuilder.sum(announcementBranchRoot.<BigDecimal>get("employees")),
                criteriaBuilder.sum(announcementBranchRoot.<BigDecimal>get("employeesCount")),
                criteriaBuilder.sum(announcementBranchRoot.<BigDecimal>get("peyman")),
                criteriaBuilder.sum(announcementBranchRoot.<BigDecimal>get("peymanCount")),
                criteriaBuilder.sum(announcementBranchRoot.<BigDecimal>get("sumAmount")),
                criteriaBuilder.sum(announcementBranchRoot.<BigDecimal>get("sumCount"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        AnnouncementEdareKolSummery summery = (AnnouncementEdareKolSummery) createQuery.getSingleResult();
        return summery;
    }

    @Override
    public Class<AnnouncementEdareKol> getClazz() {
        return AnnouncementEdareKol.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
