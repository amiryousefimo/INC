package ir.tamin.incomeBank.dao.announcement.branch;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.announcement.branch.AnnouncementBranch;
import ir.tamin.incomeBank.model.announcement.branch.AnnouncementBranchSummery;
import ir.tamin.incomeBank.model.announcement.branch.AnnouncementBranchView;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author A_Jamali
 */
public class AnnouncementBranchDAOService extends BaseDAOService<AnnouncementBranch> {


    @Inject
    private EntityManager em;


    public Map<String, Object> getBranchByDateRange(String startDate1, String endDate1, String edareCode1, String brchCode1) {
        Map<String, Object> map = new HashMap<>();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<AnnouncementBranch> root = criteriaQuery.from(AnnouncementBranch.class);

        Expression<String> edareCode = root.get("edareCode");
        Expression<String> brchCode = root.get("brchCode");
        Expression<String> ordDate = root.get("letterDate");
        Expression<BigDecimal> sumEmployees = root.get("employees");
        Expression<BigDecimal> sumEmployeesCount = root.get("employeesCount");
        Expression<BigDecimal> sumPeyman = root.get("peyman");
        Expression<BigDecimal> sumPeymanCount = root.get("peymanCount");
        Expression<BigDecimal> sumAmt = root.get("sumAmount");
        Expression<BigDecimal> sumCount = root.get("sumCount");

        Predicate datePredicate = criteriaBuilder.between(ordDate, startDate1, endDate1);
        Predicate edareCdePredictLike = criteriaBuilder.like(edareCode, "%%");
        Predicate edareCdePredictEqual = criteriaBuilder.equal(edareCode, edareCode1);
        Predicate brchPredicateLike = criteriaBuilder.like(brchCode, "%%");
        Predicate brchPredicateEqual = criteriaBuilder.equal(brchCode, brchCode1);

        criteriaQuery.multiselect(
                root.get("edareCode"),
                root.get("edareName"),
                root.get("brchCode"),
                root.get("brchName"),
                criteriaBuilder.literal(startDate1),
                criteriaBuilder.literal(endDate1),
                criteriaBuilder.sum(sumEmployees),
                criteriaBuilder.sum(sumEmployeesCount),
                criteriaBuilder.sum(sumPeyman),
                criteriaBuilder.sum(sumPeymanCount),
                criteriaBuilder.sum(sumAmt),
                criteriaBuilder.sum(sumCount)
        );

        if ((edareCode1.equals("-1") && brchCode1.equals("-1"))) {
            criteriaQuery.where(datePredicate, edareCdePredictLike, brchPredicateLike).groupBy(root.get("edareCode"), root.get("edareName"), root.get("brchCode"), root.get("brchName"));
        } else if (edareCode1.equals("-1") || brchCode1.equals("-1")) {
            if (edareCode1.equals("-1")) {
                criteriaQuery.where(datePredicate, edareCdePredictLike, brchPredicateEqual).groupBy(root.get("edareCode"), root.get("edareName"), root.get("brchCode"), root.get("brchName"));
            } else {
                criteriaQuery.where(datePredicate, edareCdePredictEqual, brchPredicateLike).groupBy(root.get("edareCode"), root.get("edareName"), root.get("brchCode"), root.get("brchName"));
            }
        } else {
            criteriaQuery.where(datePredicate, edareCdePredictEqual, brchPredicateEqual).groupBy(root.get("edareCode"), root.get("edareName"), root.get("brchCode"), root.get("brchName"));
        }
//        criteriaQuery.groupBy(root.get("edareCode"), root.get("edareName"), root.get("brchCode"), root.get("brchName"));

        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
        List<Object[]> results = query.getResultList();
        List<AnnouncementBranchView> list = new ArrayList<>();

        for (Object[] result : results) {
            String edareCode2 = (String) result[0];
            String edareName = (String) result[1];
            String branchCode = (String) result[2];
            String branchName = (String) result[3];
            String startDate = (String) result[4];
            String endDate = (String) result[5];
            BigDecimal sumE = ((BigDecimal) result[6]);
            BigDecimal sumEC = ((BigDecimal) result[7]);
            BigDecimal sumP = ((BigDecimal) result[8]);
            BigDecimal sumPC = ((BigDecimal) result[9]);
            BigDecimal sumAmount = ((BigDecimal) result[10]);
            BigDecimal sumCountO = ((BigDecimal) result[11]);


            AnnouncementBranchView branchView = new AnnouncementBranchView();
            branchView.setPeyman(sumP);
            branchView.setPeymanCount(sumPC);
            branchView.setEmployees(sumE);
            branchView.setEmployeesCount(sumEC);
            branchView.setBrchCode(branchCode);
            branchView.setBrchName(branchName);
            branchView.setEdareCode(edareCode2);
            branchView.setEdareName(edareName);
            branchView.setLetterDate1(startDate);
            branchView.setLetterDate2(endDate);
            branchView.setSumAmount(sumAmount);
            branchView.setSumCount(sumCountO);
            list.add(branchView);
        }
        BigDecimal sumSumValue = BigDecimal.valueOf(0);
        BigDecimal sumCountValue = BigDecimal.valueOf(0);
        BigDecimal sumEmployeeValue = BigDecimal.valueOf(0);
        BigDecimal sumEmployeeCountValue = BigDecimal.valueOf(0);
        BigDecimal sumPeymanValue = BigDecimal.valueOf(0);
        BigDecimal sumPeymanCountValue = BigDecimal.valueOf(0);

        for (AnnouncementBranchView branchView : list) {
            BigDecimal sumAmount2 = branchView.getSumAmount();
            BigDecimal sumCount2 = branchView.getSumCount();
            BigDecimal sumPeyman2 = branchView.getPeyman();
            BigDecimal sumPeymanCount2 = branchView.getPeymanCount();
            BigDecimal sumEmployees2 = branchView.getEmployees();
            BigDecimal sumEmployeesCount2 = branchView.getEmployeesCount();

            sumSumValue = sumSumValue.add(sumAmount2);
            sumCountValue = sumCountValue.add(sumCount2);
            sumEmployeeValue = sumEmployeeValue.add(sumEmployees2);
            sumEmployeeCountValue = sumEmployeeCountValue.add(sumEmployeesCount2);
            sumPeymanValue = sumPeymanValue.add(sumPeyman2);
            sumPeymanCountValue = sumPeymanCountValue.add(sumPeymanCount2);
        }
        BigDecimal total = BigDecimal.valueOf(list.size());
        AnnouncementBranchSummery summery = new AnnouncementBranchSummery(sumEmployeeValue, sumEmployeeCountValue, sumPeymanValue, sumPeymanCountValue, sumSumValue, sumCountValue);
        map.put("total",total);
        map.put("data",list);
        map.put("sum",summery);
        return map;

    }


    public AnnouncementBranchSummery getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<AnnouncementBranch> workshopDebitReportFrom = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, workshopDebitReportFrom, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(AnnouncementBranchSummery.class,
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("employees")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("employeesCount")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("peyman")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("peymanCount")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("sumAmount")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("sumCount"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        AnnouncementBranchSummery summery = (AnnouncementBranchSummery) createQuery.getSingleResult();
        return summery;
    }

    @Override
    public Class<AnnouncementBranch> getClazz() {
        return AnnouncementBranch.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
