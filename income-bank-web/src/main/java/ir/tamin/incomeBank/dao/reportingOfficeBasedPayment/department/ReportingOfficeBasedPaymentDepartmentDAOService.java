package ir.tamin.incomeBank.dao.reportingOfficeBasedPayment.department;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.reportingOfficeBasedPayment.department.ReportingOfficeBasedPaymentDepartment;
import ir.tamin.incomeBank.model.reportingOfficeBasedPayment.department.ReportingOfficeBasedPaymentDepartmentSummary;
import ir.tamin.incomeBank.model.reportingOfficeBasedPayment.department.ReportingOfficeBasedPaymentDepartmentView;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportingOfficeBasedPaymentDepartmentDAOService extends BaseDAOService<ReportingOfficeBasedPaymentDepartment> {


    @Inject
    private EntityManager em;


    public Map<String, Object> getBranchByDateRange(String startDate1, String endDate1, String edareCode1) {
        Map<String, Object> map = new HashMap<>();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ReportingOfficeBasedPaymentDepartment> root = criteriaQuery.from(ReportingOfficeBasedPaymentDepartment.class);

        Expression<String> edareCode = root.get("edareCode");
        Expression<String> ordDate = root.get("ordDate");
        Expression<BigDecimal> sumAmt = root.get("sumAmount");
        Expression<BigDecimal> sumCountOrder = root.get("countOrderNumber");

        Predicate datePredicate = criteriaBuilder.between(ordDate, startDate1, endDate1);

        criteriaQuery.multiselect(
                root.get("edareCode"),
                root.get("edareName"),
                criteriaBuilder.literal(startDate1),
                criteriaBuilder.literal(endDate1),
                criteriaBuilder.sum(sumAmt),
                criteriaBuilder.sum(sumCountOrder)
        );
        if (edareCode1.equals("-1")) {
            Predicate edareCdePredict = criteriaBuilder.like(edareCode, "%%");
            criteriaQuery.where(datePredicate,edareCdePredict).groupBy(root.get("edareCode"), root.get("edareName"));
        }
        if (!edareCode1.equals("-1")) {
            Predicate edareCdePredict = criteriaBuilder.equal(edareCode, edareCode1);
            criteriaQuery.where(datePredicate,edareCdePredict).groupBy(root.get("edareCode"), root.get("edareName"));
        }
//        criteriaQuery.groupBy(root.get("edareCode"), root.get("edareName"));

        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);

        List<Object[]> results = query.getResultList();
        List<ReportingOfficeBasedPaymentDepartmentView> list = new ArrayList<>();

        for (Object[] result : results) {
            String edareCode2 = (String) result[0];
            String edareName = (String) result[1];
            String startDate = (String) result[2];
            String endDate = (String) result[3];
            BigDecimal sumAmount = ((BigDecimal) result[4]);
            BigDecimal sumCountOrderNumber = ((BigDecimal) result[5]);

            ReportingOfficeBasedPaymentDepartmentView departmentView = new ReportingOfficeBasedPaymentDepartmentView();

            departmentView.setEdareCode(edareCode2);
            departmentView.setEdareName(edareName);
            departmentView.setOrdDate1(startDate);
            departmentView.setOrdDate2(endDate);
            departmentView.setSumAmount(sumAmount);
            departmentView.setCountOrderNumber(sumCountOrderNumber);
            list.add(departmentView);
        }
        BigDecimal sumSumValue = BigDecimal.valueOf(0);
        BigDecimal sumCountValue = BigDecimal.valueOf(0);

        for (ReportingOfficeBasedPaymentDepartmentView branchView : list) {
            BigDecimal sumAmount2 = branchView.getSumAmount();
            BigDecimal sumCountOrder2 = branchView.getCountOrderNumber();

            sumSumValue = sumSumValue.add(sumAmount2);
            sumCountValue = sumCountValue.add(sumCountOrder2);
        }

        BigDecimal total = BigDecimal.valueOf(list.size());
        ReportingOfficeBasedPaymentDepartmentSummary summery = new ReportingOfficeBasedPaymentDepartmentSummary(sumSumValue, sumCountValue);
        map.put("total", total);
        map.put("data", list);
        map.put("sum", summery);
        return map;
    }


    /******************************/

    public ReportingOfficeBasedPaymentDepartmentSummary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<ReportingOfficeBasedPaymentDepartment> root = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(ReportingOfficeBasedPaymentDepartmentSummary.class,
                criteriaBuilder.sum(root.<BigDecimal>get("sumAmount")),
                criteriaBuilder.sum(root.<BigDecimal>get("countOrderNumber"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        ReportingOfficeBasedPaymentDepartmentSummary summery = (ReportingOfficeBasedPaymentDepartmentSummary) createQuery.getSingleResult();
        return summery;
    }

    @Override
    public Class<ReportingOfficeBasedPaymentDepartment> getClazz() {
        return ReportingOfficeBasedPaymentDepartment.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
