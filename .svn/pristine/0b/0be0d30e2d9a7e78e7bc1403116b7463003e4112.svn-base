package ir.tamin.incomeBank.dao.paymentWorkers.branch;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.dto.paymentWorkers.branch.PaymentWorkersBranchDto;
import ir.tamin.incomeBank.dto.paymentWorkers.branch.PaymentWorkersBranchSummary;
import ir.tamin.incomeBank.model.paymentWorkers.branch.PaymentWorkersBranch;
import ir.tamin.incomeBank.model.paymentWorkers.branch.PaymentWorkersBranchView;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentWorkersBranchDAOService extends BaseDAOService<PaymentWorkersBranch> {

    @Inject
    private EntityManager em;

    public Map<String, Object> getBranchByDateRange(String startDate1, String endDate1, String edareCode1, String brchCode1) {
        Map<String, Object> map = new HashMap<>();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<PaymentWorkersBranch> root = criteriaQuery.from(PaymentWorkersBranch.class);

        Expression<String> edareCode = root.get("edareCode");
        Expression<String> brchCode = root.get("brchCode");
        Expression<String> ordDate = root.get("ordDate");
        Expression<BigDecimal> sumAmount = root.get("sumAmount");
        Expression<BigDecimal> countOrderNumber = root.get("countOrderNumber");
        Expression<BigDecimal> bimeh7sakht = root.get("bimeh7sakht");
        Expression<BigDecimal> bimeh20sakht = root.get("bimeh20sakht");
        Expression<BigDecimal> jarimehSakht = root.get("jarimehSakht");
        Expression<BigDecimal> sumAmtGov = root.get("sumAmtGov");


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
                criteriaBuilder.sum(sumAmount),
                criteriaBuilder.sum(countOrderNumber),
                criteriaBuilder.sum(bimeh7sakht),
                criteriaBuilder.sum(bimeh20sakht),
                criteriaBuilder.sum(jarimehSakht),
                criteriaBuilder.sum(sumAmtGov)
        );


        if ((edareCode1.equals("-1") && brchCode1.equals("-1"))) {
            criteriaQuery.where(datePredicate, edareCdePredictLike, brchPredicateLike).groupBy(root.get("edareCode"), root.get("edareName"), root.get("brchCode"), root.get("brchName"));
        } else if ((edareCode1.equals("-1") || brchCode1.equals("-1"))) {
            if (edareCode1.equals("-1")) {
                criteriaQuery.where(datePredicate, edareCdePredictLike, brchPredicateEqual).groupBy(root.get("edareCode"), root.get("edareName"), root.get("brchCode"), root.get("brchName"));
            } else {
                criteriaQuery.where(datePredicate, edareCdePredictEqual, brchPredicateLike).groupBy(root.get("edareCode"), root.get("edareName"), root.get("brchCode"), root.get("brchName"));
            }
        } else {
            criteriaQuery.where(datePredicate, edareCdePredictEqual, brchPredicateEqual).groupBy(root.get("edareCode"), root.get("edareName"), root.get("brchCode"), root.get("brchName"));
        }
        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
        List<Object[]> results = query.getResultList();
        List<PaymentWorkersBranchView> list = new ArrayList<>();


        for (Object[] result : results) {
            String edareCode2 = (String) result[0];
            String edareName = (String) result[1];
            String branchCode = (String) result[2];
            String branchName = (String) result[3];
            String startDate = (String) result[4];
            String endDate = (String) result[5];
            //BigDecimal sumAmt = ((BigDecimal) result[6]) != null ? ((BigDecimal) result[4]) : BigDecimal.ZERO;
            BigDecimal sumAmt = ((BigDecimal) result[6]);
            BigDecimal sumCountOrderNumber = ((BigDecimal) result[7]);
            BigDecimal sumBimeh7sakht = ((BigDecimal) result[8]);
            BigDecimal sumBimeh20sakht = ((BigDecimal) result[9]);
            BigDecimal sumJarimehSakht = ((BigDecimal) result[10]);
            BigDecimal sumAmountGov = ((BigDecimal) result[11]);

            PaymentWorkersBranchView dto = new PaymentWorkersBranchView();
            dto.setEdareCode(edareCode2);
            dto.setEdareName(edareName);
            dto.setBrchName(branchName);
            dto.setBrchCode(branchCode);
            dto.setOrdDate1(startDate);
            dto.setOrdDate2(endDate);

            dto.setSumAmount(sumAmt);
            dto.setCountOrderNumber(sumCountOrderNumber);
            dto.setBimeh7sakht(sumBimeh7sakht);
            dto.setBimeh20sakht(sumBimeh20sakht);
            dto.setJarimehSakht(sumJarimehSakht);
            dto.setSumAmtGov(sumAmountGov);
            list.add(dto);
        }
        BigDecimal sumSumAmount = BigDecimal.valueOf(0);
        BigDecimal sumCountOrdNumber = BigDecimal.valueOf(0);
        BigDecimal sumBimeh7sakht = BigDecimal.valueOf(0);
        BigDecimal sumBimeh20sakht = BigDecimal.valueOf(0);
        BigDecimal sumJarimehSakht = BigDecimal.valueOf(0);
        BigDecimal sumSumAmtGov = BigDecimal.valueOf(0);

        for (PaymentWorkersBranchView dto : list) {
            BigDecimal sumAmount2 = dto.getSumAmount() != null ? dto.getSumAmount() : BigDecimal.ZERO;
            BigDecimal countOrdNumber2 = dto.getCountOrderNumber() != null ? dto.getCountOrderNumber() : BigDecimal.ZERO;
            BigDecimal bimeh7sakht2 = dto.getBimeh7sakht() != null ? dto.getBimeh7sakht() : BigDecimal.ZERO;
            BigDecimal bimeh20sakht2 = dto.getBimeh20sakht() != null ? dto.getBimeh20sakht() : BigDecimal.ZERO;
            BigDecimal jarimehSakht2 = dto.getJarimehSakht() != null ? dto.getJarimehSakht() : BigDecimal.ZERO;
            BigDecimal sumAmtGov2 = dto.getSumAmtGov() != null ? dto.getSumAmtGov() : BigDecimal.ZERO;

            sumSumAmount = sumSumAmount.add(sumAmount2);
            sumCountOrdNumber = sumCountOrdNumber.add(countOrdNumber2);
            sumBimeh7sakht = sumBimeh7sakht.add(bimeh7sakht2);
            sumBimeh20sakht = sumBimeh20sakht.add(bimeh20sakht2);
            sumJarimehSakht = sumJarimehSakht.add(jarimehSakht2);
            sumSumAmtGov = sumSumAmtGov.add(sumAmtGov2);
        }

        BigDecimal total = BigDecimal.valueOf(list.size());
        PaymentWorkersBranchSummary summary = new PaymentWorkersBranchSummary(sumSumAmount, sumCountOrdNumber, sumBimeh7sakht, sumBimeh20sakht, sumJarimehSakht, sumSumAmtGov);
        map.put("total", total);
        map.put("data", list);
        map.put("sum", summary);

        return map;
    }

    /**************** summary ****************/
    public PaymentWorkersBranchSummary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<PaymentWorkersBranch> root = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(PaymentWorkersBranchSummary.class,
                criteriaBuilder.sum(root.get("sumAmount")),
                criteriaBuilder.sum(root.get("countOrderNumber")),
                criteriaBuilder.sum(root.get("bimeh7sakht")),
                criteriaBuilder.sum(root.get("bimeh20sakht")),
                criteriaBuilder.sum(root.get("jarimehSakht")),
                criteriaBuilder.sum(root.get("sumAmtGov"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        PaymentWorkersBranchSummary summary = (PaymentWorkersBranchSummary) createQuery.getSingleResult();
        return summary;
    }

    @Override
    public Class<PaymentWorkersBranch> getClazz() {
        return PaymentWorkersBranch.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
