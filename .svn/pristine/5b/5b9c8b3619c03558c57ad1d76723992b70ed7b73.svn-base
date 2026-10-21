package ir.tamin.incomeBank.dao.payment3070.account503edare;


import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;

import ir.tamin.incomeBank.dto.pey3070.account503edare.Account503EdareDto;
import ir.tamin.incomeBank.dto.pey3070.account503edare.Account503EdareSummary;
import ir.tamin.incomeBank.model.payment3070.account503edare.Account503Edare;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account503EdareDAO extends BaseDAOService<Account503Edare> {


    @Inject
    private EntityManager em;


    public Map<String, Object> getListByDate(String startDate1, String endDate1, String edareCode1) {
        Map<String, Object> map = new HashMap<>();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Account503Edare> root = criteriaQuery.from(Account503Edare.class);

        Expression<String> edareCode = root.get("edareCode");
        Expression<String> soratDate = root.get("ordDate");


        Expression<BigDecimal> sumAmount = root.get("sumAmount");
        Expression<BigDecimal> mashAmount = root.get("mashAmount");
        Expression<BigDecimal> ekhtAmount = root.get("ekhtAmount");
        Expression<BigDecimal> sarbaziAmt = root.get("sarbaziAmt");
        Expression<BigDecimal> sakhtemanAmt = root.get("sakhtemanAmt");
        Expression<BigDecimal> kasrMahAmt = root.get("kasrMahAmt");
        Expression<BigDecimal> countAmt = root.get("countAmt");
        Expression<BigDecimal> dolatAmt = root.get("dolatAmt");

        Predicate datePredicate = criteriaBuilder.between(soratDate, startDate1, endDate1);
        Predicate edareCdePredictLike = criteriaBuilder.like(edareCode, "%%");
        Predicate edareCdePredictEqual = criteriaBuilder.equal(edareCode, edareCode1);


        criteriaQuery.multiselect(
                root.get("edareCode"),
                root.get("edareName"),
                criteriaBuilder.literal(startDate1),
                criteriaBuilder.literal(endDate1),
                criteriaBuilder.sum(sumAmount),
                criteriaBuilder.sum(mashAmount),
                criteriaBuilder.sum(ekhtAmount),
                criteriaBuilder.sum(sarbaziAmt),
                criteriaBuilder.sum(sakhtemanAmt),
                criteriaBuilder.sum(kasrMahAmt),
                criteriaBuilder.sum(countAmt),
                criteriaBuilder.sum(dolatAmt)
        );

        if (edareCode1.equals("-1")) {
            criteriaQuery.where(datePredicate, edareCdePredictLike).groupBy(root.get("edareCode"), root.get("edareName"));
        } else {
            criteriaQuery.where(datePredicate, edareCdePredictEqual).groupBy(root.get("edareCode"), root.get("edareName"));
        }

        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
        List<Object[]> results = query.getResultList();
        List<Account503EdareDto> list = new ArrayList<>();

        BigDecimal sumSumAmount = BigDecimal.ZERO;
        BigDecimal sumMashAmountAmt = BigDecimal.ZERO;
        BigDecimal sumEkhtAmountAmt = BigDecimal.ZERO;
        BigDecimal sumSarbaziAmt = BigDecimal.ZERO;
        BigDecimal sumSakhtemanAmt = BigDecimal.ZERO;
        BigDecimal sumkasrMahAmt = BigDecimal.ZERO;
        BigDecimal sumCountAmt = BigDecimal.ZERO;
        BigDecimal sumDolatAmt = BigDecimal.ZERO;

        for (Object[] result : results) {
            String edareCode2 = (String) result[0];
            String edareName2 = (String) result[1];
            String startDate2 = startDate1;
            String endDate2 = endDate1;

            BigDecimal sumAmount2 = (BigDecimal) result[4];
            BigDecimal mashAmt2 = (BigDecimal) result[5];
            BigDecimal ekhtAmt2 = (BigDecimal) result[6];
            BigDecimal sarbaziAmt2 = (BigDecimal) result[7];
            BigDecimal sakhtemanAmt2 = (BigDecimal) result[8];
            BigDecimal kasrMahAmt2 = (BigDecimal) result[9];
            BigDecimal countAmt2 = (BigDecimal) result[10];
            BigDecimal dolatAmt2 = (BigDecimal) result[11];

            Account503EdareDto dtos = new Account503EdareDto();
            dtos.setEdareCode(edareCode2);
            dtos.setEdareName(edareName2);
            dtos.setOrdDate(startDate2);
            dtos.setOrdDate1(endDate2);
            dtos.setSumAmount(sumAmount2);
            dtos.setMashAmount(mashAmt2);
            dtos.setEkhtAmount(ekhtAmt2);
            dtos.setSarbaziAmt(sarbaziAmt2);
            dtos.setSakhtemanAmt(sakhtemanAmt2);
            dtos.setKasrMahAmt(kasrMahAmt2);
            dtos.setCountAmt(countAmt2);
            dtos.setDolatAmt(dolatAmt2);
            list.add(dtos);

            sumSumAmount = sumSumAmount.add(sumAmount2 == null ? BigDecimal.ZERO : sumAmount2);
            sumMashAmountAmt = sumMashAmountAmt.add(mashAmt2 == null ? BigDecimal.ZERO : mashAmt2);
            sumEkhtAmountAmt = sumEkhtAmountAmt.add(ekhtAmt2 == null ? BigDecimal.ZERO : ekhtAmt2);
            sumSarbaziAmt = sumSarbaziAmt.add(sarbaziAmt2 == null ? BigDecimal.ZERO : sarbaziAmt2);
            sumSakhtemanAmt = sumSakhtemanAmt.add(sakhtemanAmt2 == null ? BigDecimal.ZERO : sakhtemanAmt2);
            sumkasrMahAmt = sumkasrMahAmt.add(kasrMahAmt2 == null ? BigDecimal.ZERO : kasrMahAmt2);
            sumCountAmt = sumCountAmt.add(countAmt2 == null ? BigDecimal.ZERO : countAmt2);
            sumDolatAmt = sumDolatAmt.add(dolatAmt2 == null ? BigDecimal.ZERO : dolatAmt2);
        }

        BigDecimal total = BigDecimal.valueOf(list.size());
        Account503EdareSummary summery = new Account503EdareSummary(sumSumAmount,sumMashAmountAmt, sumEkhtAmountAmt, sumSarbaziAmt, sumSakhtemanAmt, sumkasrMahAmt,  sumCountAmt, sumDolatAmt);
        map.put("total", total);
        map.put("data", list);
        map.put("sum", summery);
        return map;
    }

    public Account503EdareSummary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<Account503Edare> workshopDebitReportFrom = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, workshopDebitReportFrom, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(Account503EdareSummary.class,
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("sumAmount")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("mashAmount")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("ekhtAmount")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("sarbaziAmt")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("sakhtemanAmt")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("kasrMahAmt")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("countAmt")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("dolatAmt"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        Account503EdareSummary summery = (Account503EdareSummary) createQuery.getSingleResult();
        return summery;
    }

    @Override
    public Class<Account503Edare> getClazz() {
        return Account503Edare.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
