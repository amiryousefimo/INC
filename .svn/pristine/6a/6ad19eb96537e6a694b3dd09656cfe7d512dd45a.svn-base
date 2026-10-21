package ir.tamin.incomeBank.dao.reportingOfficeBasedPayment.branch;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.reportingOfficeBasedPayment.branch.ReportingOfficeBasedPaymentBranch;
import ir.tamin.incomeBank.model.reportingOfficeBasedPayment.branch.ReportingOfficeBasedPaymentBranchSummary;
import ir.tamin.incomeBank.model.reportingOfficeBasedPayment.branch.ReportingOfficeBasedPaymentBranchView;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportingOfficeBasedPaymentBranchDAOService extends BaseDAOService<ReportingOfficeBasedPaymentBranch> {


    @Inject
    private EntityManager em;





    public Map<String, Object> getBranchByDateRange(String startDate1, String endDate1, String edareCode1, String brchCode1) {
        Map<String, Object> map = new HashMap<>();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ReportingOfficeBasedPaymentBranch> root = criteriaQuery.from(ReportingOfficeBasedPaymentBranch.class);

        Expression<String> edareCode = root.get("edareCode");
        Expression<String> brchCode = root.get("brchCode");
        Expression<String> ordDate = root.get("ordDate");
        Expression<BigDecimal> sumAmt = root.get("sumAmount");
        Expression<BigDecimal> sumCountOrder = root.get("countOrderNumber");

        Predicate datePredicate = criteriaBuilder.between(ordDate, startDate1, endDate1);

        criteriaQuery.multiselect(
                root.get("edareCode"),
                root.get("edareName"),
                root.get("brchCode"),
                root.get("brchName"),
                root.get("payKind"),
                root.get("payKindDSC"),
                criteriaBuilder.literal(startDate1),
                criteriaBuilder.literal(endDate1),
                criteriaBuilder.sum(sumAmt),
                criteriaBuilder.sum(sumCountOrder)
        );
        Predicate edareCdePredictLike = criteriaBuilder.like(edareCode, "%%");
        Predicate edareCdePredictEqual = criteriaBuilder.equal(edareCode, edareCode1);
        Predicate brchPredicateLike = criteriaBuilder.like(brchCode, "%%");
        Predicate brchPredicateEqual = criteriaBuilder.equal(brchCode, brchCode1);


        if ((edareCode1.equals("-1") && brchCode1.equals("-1"))) {
            criteriaQuery.where(datePredicate, edareCdePredictLike, brchPredicateLike).groupBy(root.get("edareCode"), root.get("edareName"),root.get("brchCode"),
                    root.get("brchName"),root.get("payKind"), root.get("payKindDSC"));
        } else if (edareCode1.equals("-1") || brchCode1.equals("-1")) {
            if (edareCode1.equals("-1")) {
                criteriaQuery.where(datePredicate, edareCdePredictLike, brchPredicateEqual).groupBy(root.get("edareCode"), root.get("edareName"),root.get("brchCode"),
                        root.get("brchName"),root.get("payKind"), root.get("payKindDSC"));
            } else {
                criteriaQuery.where(datePredicate, edareCdePredictEqual, brchPredicateLike).groupBy(root.get("edareCode"), root.get("edareName"),root.get("brchCode"),
                        root.get("brchName"),root.get("payKind"), root.get("payKindDSC"));
            }
        } else {
            criteriaQuery.where(datePredicate, edareCdePredictEqual, brchPredicateEqual).groupBy(root.get("edareCode"), root.get("edareName"),root.get("brchCode"),
                    root.get("brchName"),root.get("payKind"), root.get("payKindDSC"));
        }
//        criteriaQuery.groupBy(root.get("edareCode"), root.get("edareName"),root.get("brchCode"),
//                root.get("brchName"),root.get("payKind"), root.get("payKindDSC"));
        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);


        List<Object[]> results = query.getResultList();
        List<ReportingOfficeBasedPaymentBranchView> list = new ArrayList<>();


        for (Object[] result : results) {
            String edareCode2 = (String) result[0];
            String edareName = (String) result[1];
            String branchCode = (String) result[2];
            String branchName = (String) result[3];
            String peymanKind = (String) result[4];
            String peymanKindDSC = (String) result[5];
            String startDate = (String) result[6];
            String endDate = (String) result[7];
            BigDecimal sumAmount = ((BigDecimal) result[8]);
            BigDecimal sumCountOrderNumber = ((BigDecimal) result[9]);

            ReportingOfficeBasedPaymentBranchView branchView = new ReportingOfficeBasedPaymentBranchView();

            branchView.setBrchCode(branchCode);
            branchView.setBrchName(branchName);
            branchView.setEdareCode(edareCode2);
            branchView.setEdareName(edareName);
            branchView.setOrdDate1(startDate);
            branchView.setOrdDate2(endDate);
            branchView.setPayKind(peymanKind);
            branchView.setPayKindDSC(peymanKindDSC);

            branchView.setSumAmount(sumAmount);
            branchView.setCountOrderNumber(sumCountOrderNumber);
            list.add(branchView);
        }

        BigDecimal sumSumValue = BigDecimal.valueOf(0);
        BigDecimal sumCountValue = BigDecimal.valueOf(0);

        for (ReportingOfficeBasedPaymentBranchView branchView : list) {
            BigDecimal sumAmount2 = branchView.getSumAmount();
            BigDecimal sumCountOrder2 = branchView.getCountOrderNumber();

            sumSumValue = sumSumValue.add(sumAmount2);
            sumCountValue = sumCountValue.add(sumCountOrder2);
        }

        BigDecimal total = BigDecimal.valueOf(list.size());
        ReportingOfficeBasedPaymentBranchSummary summery = new ReportingOfficeBasedPaymentBranchSummary(sumSumValue, sumCountValue);
//        map.put("page", currentPage);
//        map.put("pageSize", 10);
//        map.put("totalPages", Math.ceil((double) totalCount / pageSize));
        map.put("total", total);
        map.put("data", list);
        map.put("sum", summery);

        return map;
    }




    /******************************/

    public ReportingOfficeBasedPaymentBranchSummary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<ReportingOfficeBasedPaymentBranch> workshopDebitReportFrom = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, workshopDebitReportFrom, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(ReportingOfficeBasedPaymentBranchSummary.class,
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("sumAmount")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("countOrderNumber"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        ReportingOfficeBasedPaymentBranchSummary summery = (ReportingOfficeBasedPaymentBranchSummary) createQuery.getSingleResult();
        return summery;
    }

    @Override
    public Class<ReportingOfficeBasedPaymentBranch> getClazz() {
        return ReportingOfficeBasedPaymentBranch.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
