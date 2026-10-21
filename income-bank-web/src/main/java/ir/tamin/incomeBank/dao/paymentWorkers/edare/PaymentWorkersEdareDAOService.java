package ir.tamin.incomeBank.dao.paymentWorkers.edare;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.dto.paymentWorkers.branch.PaymentWorkersBranchSummary;
import ir.tamin.incomeBank.dto.paymentWorkers.edare.PaymentWorkersEdareDto;
import ir.tamin.incomeBank.dto.paymentWorkers.edare.PaymentWorkersEdareSummary;
import ir.tamin.incomeBank.dto.receiptManagement.edare.ReceiptManagementEdareDto;
import ir.tamin.incomeBank.dto.receiptManagement.edare.ReceiptManagementEdareSummary;
import ir.tamin.incomeBank.model.paymentWorkers.branch.PaymentWorkersBranch;
import ir.tamin.incomeBank.model.paymentWorkers.branch.PaymentWorkersBranchView;
import ir.tamin.incomeBank.model.paymentWorkers.edare.PaymentWorkersEdare;
import ir.tamin.incomeBank.model.paymentWorkers.edare.PaymentWorkersEdareView;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentWorkersEdareDAOService extends BaseDAOService<PaymentWorkersEdare> {

    @Inject
    private EntityManager em;

    /*public Map<String, Object> getBranchByDateRange(String startDate1, String endDate1, String edareCode1) {
        Map<String, Object> map = new HashMap<>();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<PaymentWorkersEdare> root = criteriaQuery.from(PaymentWorkersEdare.class);

        Expression<String> edareCode = root.get("edareCode");
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

        criteriaQuery.multiselect(
                root.get("edareCode"),
                root.get("edareName"),

                criteriaBuilder.literal(startDate1),
                criteriaBuilder.literal(endDate1),
                criteriaBuilder.sum(sumAmount),
                criteriaBuilder.sum(countOrderNumber),
                criteriaBuilder.sum(bimeh7sakht),
                criteriaBuilder.sum(bimeh20sakht),
                criteriaBuilder.sum(jarimehSakht),
                criteriaBuilder.sum(sumAmtGov)
        );


        if ((edareCode1.equals("-1"))) {
            criteriaQuery.where(datePredicate, edareCdePredictLike).groupBy(root.get("edareCode"), root.get("edareName"));
        } else {
            criteriaQuery.where(datePredicate, edareCdePredictEqual).groupBy(root.get("edareCode"), root.get("edareName"));
        }
        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
        List<Object[]> results = query.getResultList();
        List<PaymentWorkersEdareDto> list = new ArrayList<>();
        //List<PaymentWorkersEdareView> list = new ArrayList<>();

        BigDecimal sumSumAmount = BigDecimal.ZERO;
        BigDecimal sumCountOrdNumber = BigDecimal.ZERO;
        BigDecimal sumBimeh7sakht = BigDecimal.ZERO;
        BigDecimal sumBimeh20sakht = BigDecimal.ZERO;
        BigDecimal sumJarimehSakht = BigDecimal.ZERO;
        BigDecimal sumSumAmtGov = BigDecimal.ZERO;

        for (Object[] result : results) {
            String edareCode2 = (String) result[0];
            String edareName = (String) result[1];
            String startDate = (String) result[2];
            String endDate = (String) result[3];
            BigDecimal sumSumAmunt = ((BigDecimal) result[4]);
            BigDecimal sumCountOrderNumber = ((BigDecimal) result[5]);
            BigDecimal sumBime7sakht = ((BigDecimal) result[6]);
            BigDecimal sumBime20sakht = ((BigDecimal) result[7]);
            BigDecimal sumJarimeSakht = ((BigDecimal) result[8]);
            BigDecimal sumAmountGov = ((BigDecimal) result[9]);

            PaymentWorkersEdareDto dto = new PaymentWorkersEdareDto();

            dto.setEdareCode(edareCode2);
            dto.setEdareName(edareName);
            dto.setOrderDate(startDate);
            dto.setOrderDate2(endDate);

            dto.setSumAmount(sumSumAmunt);
            dto.setCountOrdNumber(sumCountOrderNumber);
            dto.setBimeh7sakht(sumBime7sakht);
            dto.setBimeh20sakht(sumBime20sakht);
            dto.setJarimehSakht(sumJarimeSakht);
            dto.setSumAmtGov(sumAmountGov);
            list.add(dto);
        }
        for (PaymentWorkersEdareDto dto : list) {
            BigDecimal sumAmount2 = dto.getSumAmount() != null ? dto.getSumAmount() : BigDecimal.ZERO;
            BigDecimal countOrdNumber2 = dto.getCountOrdNumber() != null ? dto.getCountOrdNumber() : BigDecimal.ZERO;
            BigDecimal bimeh7sakht2 = dto.getBimeh7sakht() != null ? dto.getBimeh7sakht() : BigDecimal.ZERO;
            BigDecimal bimeh20sakht2 = dto.getBimeh20sakht() != null ? dto.getBimeh20sakht() : BigDecimal.ZERO;
            BigDecimal jarimehSakht2 = dto.getJarimehSakht() != null ? dto.getJarimehSakht() : BigDecimal.ZERO;
            BigDecimal sumAmtGov2 = dto.getSumAmtGov() != null ? dto.getSumAmtGov() : BigDecimal.ZERO;

            BigDecimal sumSumAmount2 = sumSumAmount.add(sumAmount2);
            BigDecimal sumCountOrdNumber2 = sumCountOrdNumber.add(countOrdNumber2);
            BigDecimal sumBimeh7sakht2 = sumBimeh7sakht.add(bimeh7sakht2);
            BigDecimal sumBimeh20sakht2 = sumBimeh20sakht.add(bimeh20sakht2);
            BigDecimal sumJarimehSakht2 = sumJarimehSakht.add(jarimehSakht2);
            BigDecimal sumSumAmtGov2 = sumSumAmtGov.add(sumAmtGov2);
        }







        BigDecimal total = BigDecimal.valueOf(list.size());
        PaymentWorkersEdareSummary summary = new PaymentWorkersEdareSummary(sumSumAmount,sumCountOrdNumber,sumSumAmtGov,sumBimeh7sakht,sumBimeh20sakht,sumJarimehSakht);
        map.put("total", total);
        map.put("data", list);
        map.put("sum", summary);
        return map;






        *//*for (PaymentWorkersEdareView EdareView : list) {
            BigDecimal sumAmount2 = EdareView.getSumAmount() != null ? EdareView.getSumAmount() : BigDecimal.ZERO;
            BigDecimal countOrdNumber2 = EdareView.getCountOrderNumber() != null ? EdareView.getCountOrderNumber() : BigDecimal.ZERO;
            BigDecimal bimeh7sakht2 = EdareView.getBimeh7sakht() != null ? EdareView.getBimeh7sakht() : BigDecimal.ZERO;
            BigDecimal bimeh20sakht2 = EdareView.getBimeh20sakht() != null ? EdareView.getBimeh20sakht() : BigDecimal.ZERO;
            BigDecimal jarimehSakht2 = EdareView.getJarimehSakht() != null ? EdareView.getJarimehSakht() : BigDecimal.ZERO;
            BigDecimal sumAmtGov2 = EdareView.getSumAmtGov() != null ? EdareView.getSumAmtGov() : BigDecimal.ZERO;

            sumSumAmount = sumSumAmount.add(sumAmount2);
            sumCountOrdNumber = sumCountOrdNumber.add(countOrdNumber2);
            sumBimeh7sakht = sumBimeh7sakht.add(bimeh7sakht2);
            sumBimeh20sakht = sumBimeh20sakht.add(bimeh20sakht2);
            sumJarimehSakht = sumJarimehSakht.add(jarimehSakht2);
            sumSumAmtGov = sumSumAmtGov.add(sumAmtGov2);
        }
*//*
    }*/

    public Map<String, Object> getEdareByDateRange(String startDate1, String endDate1, String edareCode1) {
        Map<String, Object> map = new HashMap<>();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<PaymentWorkersEdare> root = criteriaQuery.from(PaymentWorkersEdare.class);

        Expression<String> edareCode = root.get("edareCode");
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



        criteriaQuery.multiselect(
                root.get("edareCode"),
                root.get("edareName"),

                criteriaBuilder.literal(startDate1),
                criteriaBuilder.literal(endDate1),
                criteriaBuilder.sum(sumAmount),
                criteriaBuilder.sum(countOrderNumber),
                criteriaBuilder.sum(bimeh7sakht),
                criteriaBuilder.sum(bimeh20sakht),
                criteriaBuilder.sum(jarimehSakht),
                criteriaBuilder.sum(sumAmtGov)
        );


        if ((edareCode1.equals("-1"))) {
            criteriaQuery.where(datePredicate, edareCdePredictLike).groupBy(root.get("edareCode"), root.get("edareName"));
        } else {
            criteriaQuery.where(datePredicate, edareCdePredictEqual).groupBy(root.get("edareCode"), root.get("edareName"));
        }


        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
        List<Object[]> results = query.getResultList();
        List<PaymentWorkersEdareDto> list = new ArrayList<>();


        for (Object[] result : results) {
            String edareCode2 = (String) result[0];
            String edareName = (String) result[1];
            String startDate = (String) result[2];
            String endDate = (String) result[3];
            BigDecimal sumAmt = ((BigDecimal) result[4]);
            BigDecimal sumCountOrderNumber = ((BigDecimal) result[5]);
            BigDecimal sumBimeh7sakht = ((BigDecimal) result[6]);
            BigDecimal sumBimeh20sakht = ((BigDecimal) result[7]);
            BigDecimal sumJarimehSakht = ((BigDecimal) result[8]);
            BigDecimal sumAmountGov = ((BigDecimal) result[9]);

            PaymentWorkersEdareDto dto = new PaymentWorkersEdareDto();
            dto.setEdareCode(edareCode2);
            dto.setEdareName(edareName);

            dto.setOrderDate(startDate);
            dto.setOrderDate2(endDate);

            dto.setSumAmount(sumAmt);
            dto.setCountOrdNumber(sumCountOrderNumber);
            dto.setBimeh7sakht(sumBimeh7sakht);
            dto.setBimeh20sakht(sumBimeh20sakht);
            dto.setJarimehSakht(sumJarimehSakht);
            dto.setSumAmtGov(sumAmountGov);
            list.add(dto);
        }
        BigDecimal sumSumAmount = BigDecimal.ZERO;
        BigDecimal sumCountOrdNumber = BigDecimal.ZERO;
        BigDecimal sumBimeh7sakht = BigDecimal.ZERO;
        BigDecimal sumBimeh20sakht = BigDecimal.ZERO;
        BigDecimal sumJarimehSakht = BigDecimal.ZERO;
        BigDecimal sumSumAmtGov = BigDecimal.ZERO;

        /*BigDecimal sumSumAmount = BigDecimal.valueOf(0);
        BigDecimal sumCountOrdNumber = BigDecimal.valueOf(0);
        BigDecimal sumBimeh7sakht = BigDecimal.valueOf(0);
        BigDecimal sumBimeh20sakht = BigDecimal.valueOf(0);
        BigDecimal sumJarimehSakht = BigDecimal.valueOf(0);
        BigDecimal sumSumAmtGov = BigDecimal.valueOf(0);*/

        for (PaymentWorkersEdareDto dto : list) {
            BigDecimal sumAmount2 = dto.getSumAmount() != null ? dto.getSumAmount() : BigDecimal.ZERO;
            BigDecimal countOrdNumber2 = dto.getCountOrdNumber() != null ? dto.getCountOrdNumber() : BigDecimal.ZERO;
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
    public PaymentWorkersEdareSummary getSummaryPrice(FilterWrapper filter) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<PaymentWorkersEdare> root = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(PaymentWorkersEdareSummary.class,
                criteriaBuilder.sum(root.<BigDecimal>get("sumAmount")),
                criteriaBuilder.sum(root.<BigDecimal>get("countOrderNumber")),
                criteriaBuilder.sum(root.<BigDecimal>get("bimeh7sakht")),
                criteriaBuilder.sum(root.<BigDecimal>get("bimeh20sakht")),
                criteriaBuilder.sum(root.<BigDecimal>get("jarimehSakht")),
                criteriaBuilder.sum(root.<BigDecimal>get("sumAmtGov"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        PaymentWorkersEdareSummary summary = (PaymentWorkersEdareSummary) createQuery.getSingleResult();
        return summary;
    }

    @Override
    public Class<PaymentWorkersEdare> getClazz() {
        return PaymentWorkersEdare.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
