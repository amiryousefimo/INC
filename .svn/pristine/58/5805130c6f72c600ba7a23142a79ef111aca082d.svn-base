package ir.tamin.incomeBank.dao.smartlist.branch;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.dto.smartlist.branch.SmartListBranchDto;
import ir.tamin.incomeBank.dto.smartlist.branch.SmartListBranchSummary;
import ir.tamin.incomeBank.model.smartlist.branch.SmartListBranch;

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
 * @author a_jamali
 */
public class SmartListBranchDao extends BaseDAOService<SmartListBranch> {


    @Inject
    private EntityManager em;


    public Map<String, Object> getBranchByDateRange(String startDate1, String endDate1, String edareCode1, String brchCode1) {
        Map<String, Object> map = new HashMap<>();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<SmartListBranch> root = criteriaQuery.from(SmartListBranch.class);

        Expression<String> edareCode = root.get("edareCode");
        Expression<String> brchCode = root.get("brchCode");
        Expression<String> orderDate = root.get("orderDate");
        Expression<BigDecimal> sumAmount = root.get("sumAmount");
        Expression<BigDecimal> sumISu = root.get("sumISu");
        Expression<BigDecimal> sumAmountGov = root.get("sumAmountGov");
        Expression<BigDecimal> sumIsuGov = root.get("sumIsuGov");
        Expression<BigDecimal> countOrdNumber = root.get("countOrdNumber");
        Expression<BigDecimal> hagheBimehGov = root.get("hagheBimehGov");
        Expression<BigDecimal> hagheBimeh = root.get("hagheBimeh");
        Expression<BigDecimal> bikari = root.get("bikari");
        Expression<BigDecimal> jarimeh = root.get("jarimeh");

        Predicate datePredicate = criteriaBuilder.between(orderDate, startDate1, endDate1);
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
                criteriaBuilder.sum(sumISu),
                criteriaBuilder.sum(sumAmountGov),
                criteriaBuilder.sum(sumIsuGov),
                criteriaBuilder.sum(countOrdNumber),
                criteriaBuilder.sum(hagheBimehGov),
                criteriaBuilder.sum(hagheBimeh),
                criteriaBuilder.sum(bikari),
                criteriaBuilder.sum(jarimeh)
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

        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
        List<Object[]> results = query.getResultList();
        List<SmartListBranchDto> list = new ArrayList<>();

        for (Object[] result : results) {
            String edareeCode = (String) result[0];
            String edareName = (String) result[1];
            String branchCode = (String) result[2];
            String branchName = (String) result[3];
            String startDate = (String) result[4];
            String endDate = (String) result[5];
            BigDecimal sumAmt = result[6] != null ? ((BigDecimal) result[6]) : BigDecimal.ZERO;
            BigDecimal sumIS = result[7] != null ? ((BigDecimal) result[7]) : BigDecimal.ZERO;
            BigDecimal sumAG = result[8] != null ? ((BigDecimal) result[8]) : BigDecimal.ZERO;
            BigDecimal sumISG = result[9] != null ? ((BigDecimal) result[9]) : BigDecimal.ZERO;
            BigDecimal countON = result[10] != null ? ((BigDecimal) result[10]) : BigDecimal.ZERO;
            BigDecimal haghBG = result[11] != null ? ((BigDecimal) result[11]) : BigDecimal.ZERO;
            BigDecimal haghB = result[12] != null ? ((BigDecimal) result[12]) : BigDecimal.ZERO;
            BigDecimal bik = result[13] != null ? ((BigDecimal) result[13]) : BigDecimal.ZERO;
            BigDecimal jar = result[14] != null ? ((BigDecimal) result[14]) : BigDecimal.ZERO;


            SmartListBranchDto dto = new SmartListBranchDto();
            dto.setEdareCode(edareeCode);
            dto.setEdareName(edareName);
            dto.setBrchCode(branchCode);
            dto.setBrchName(branchName);
            dto.setOrderDate(startDate);
            dto.setOrderDate2(endDate);
            dto.setSumAmount(sumAmt);
            dto.setSumISu(sumIS);
            dto.setSumAmountGov(sumAG);
            dto.setSumIsuGov(sumISG);
            dto.setCountOrdNumber(countON);
            dto.setHagheBimehGov(haghBG);
            dto.setHagheBimeh(haghB);
            dto.setBikari(bik);
            dto.setJarimeh(jar);
            list.add(dto);
        }
        BigDecimal sumSumAmount = BigDecimal.valueOf(0);
        BigDecimal sumSumISu = BigDecimal.valueOf(0);
        BigDecimal sumSumAmountGov = BigDecimal.valueOf(0);
        BigDecimal sumSumIsuGov = BigDecimal.valueOf(0);
        BigDecimal sumCountOrdNumber = BigDecimal.valueOf(0);
        BigDecimal sumHagheBimehGov = BigDecimal.valueOf(0);
        BigDecimal sumHagheBimeh = BigDecimal.valueOf(0);
        BigDecimal sumBikari = BigDecimal.valueOf(0);
        BigDecimal sumJarimeh = BigDecimal.valueOf(0);

        for (SmartListBranchDto dto : list) {
            BigDecimal sumAmount2 = dto.getSumAmount() != null ? dto.getSumAmount() : BigDecimal.ZERO;
            BigDecimal sumISu2 = dto.getSumISu() != null ? dto.getSumISu() : BigDecimal.ZERO;
            BigDecimal sumAmountGov2 = dto.getSumAmountGov() != null ? dto.getSumAmountGov() : BigDecimal.ZERO;
            BigDecimal sumIsuGov2 = dto.getSumIsuGov() != null ? dto.getSumIsuGov() : BigDecimal.ZERO;
            BigDecimal countOrdNumber2 = dto.getCountOrdNumber() != null ? dto.getCountOrdNumber() : BigDecimal.ZERO;
            BigDecimal hagheBimehGov2 = dto.getHagheBimehGov() != null ? dto.getHagheBimehGov() : BigDecimal.ZERO;
            BigDecimal hagheBimeh2 = dto.getHagheBimeh() != null ? dto.getHagheBimeh() : BigDecimal.ZERO;
            BigDecimal bikari2 = dto.getBikari() != null ? dto.getBikari() : BigDecimal.ZERO;
            BigDecimal jarimeh2 = dto.getJarimeh() != null ? dto.getJarimeh() : BigDecimal.ZERO;

            sumSumAmount = sumSumAmount.add(sumAmount2);
            sumSumISu = sumSumISu.add(sumISu2);
            sumSumAmountGov = sumSumAmountGov.add(sumAmountGov2);
            sumSumIsuGov = sumSumIsuGov.add(sumIsuGov2);
            sumCountOrdNumber = sumCountOrdNumber.add(countOrdNumber2);
            sumHagheBimehGov = sumHagheBimehGov.add(hagheBimehGov2);
            sumHagheBimeh = sumHagheBimeh.add(hagheBimeh2);
            sumBikari = sumBikari.add(bikari2);
            sumJarimeh = sumJarimeh.add(jarimeh2);
        }
        BigDecimal total = BigDecimal.valueOf(list.size());
        SmartListBranchSummary summery = new SmartListBranchSummary(sumSumAmount, sumSumISu, sumSumAmountGov, sumSumIsuGov, sumCountOrdNumber, sumHagheBimehGov, sumHagheBimeh, sumBikari, sumJarimeh);
        map.put("total", total);
        map.put("data", list);
        map.put("sum", summery);
        return map;

    }


    public SmartListBranchSummary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<SmartListBranch> root = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(SmartListBranchSummary.class,
                criteriaBuilder.sum(root.<BigDecimal>get("sumAmount")),
                criteriaBuilder.sum(root.<BigDecimal>get("sumISu")),
                criteriaBuilder.sum(root.<BigDecimal>get("sumAmountGov")),
                criteriaBuilder.sum(root.<BigDecimal>get("sumIsuGov")),
                criteriaBuilder.sum(root.<BigDecimal>get("countOrdNumber")),
                criteriaBuilder.sum(root.<BigDecimal>get("hagheBimehGov")),
                criteriaBuilder.sum(root.<BigDecimal>get("hagheBimeh")),
                criteriaBuilder.sum(root.<BigDecimal>get("bikari")),
                criteriaBuilder.sum(root.<BigDecimal>get("jarimeh"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        SmartListBranchSummary summery = (SmartListBranchSummary) createQuery.getSingleResult();
        return summery;
    }

    @Override
    public Class<SmartListBranch> getClazz() {
        return SmartListBranch.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
