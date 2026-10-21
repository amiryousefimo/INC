package ir.tamin.incomeBank.dao.saba.efDateBranch;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.model.saba.efDateBranch.SabaEfDateBranches;
import ir.tamin.incomeBank.model.saba.efDateBranch.SabaEfDateBranchesDTO;
import ir.tamin.incomeBank.model.saba.efDateBranch.SabaEfDateBranchesSummary;
import ir.tamin.incomeBank.util.BaseDAOService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SabaEfDateBranchesDAOService extends BaseDAOService<SabaEfDateBranches> {

    @Inject
    private EntityManager em;


    public Class<SabaEfDateBranches> getClazz() {
        return SabaEfDateBranches.class;
    }


    public EntityManager getEntityManager() {
        return em;
    }

    public SabaEfDateBranchesSummary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SabaEfDateBranchesSummary> criteriaQuery = criteriaBuilder.createQuery(SabaEfDateBranchesSummary.class);

        Root<SabaEfDateBranches> root = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);

        criteriaQuery.select(criteriaBuilder.construct(
                SabaEfDateBranchesSummary.class,
                criteriaBuilder.sum(root.get("soratPrice")),
                criteriaBuilder.sum(root.get("countPrice"))));

        TypedQuery typedQuery = em.createQuery(criteriaQuery);
        SabaEfDateBranchesSummary summary = (SabaEfDateBranchesSummary) typedQuery.getSingleResult();
        return summary;
    }

    //////////////////////////////////////////////////////////////////////
    public Map<String, Object> getByDateRange(String startDate1, String endDate1, String edareCode1, String brchCode1) {
        Map<String, Object> map = new HashMap<>();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<SabaEfDateBranches> root = criteriaQuery.from(SabaEfDateBranches.class);

        Expression<String> soratDate = root.get("soratEfDate");
        Expression<String> edareCode = root.get("edareCode");
        Expression<String> brchCode = root.get("brchCode");
        Expression<BigDecimal> soratPrice = root.get("soratPrice");
        Expression<BigDecimal> countPrice = root.get("countPrice");

        Predicate datePredicate = criteriaBuilder.between(soratDate, startDate1, endDate1);
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
                criteriaBuilder.sum(soratPrice),
                criteriaBuilder.sum(countPrice)
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
        List<SabaEfDateBranchesDTO> list = new ArrayList<>();

        for (Object[] result : results) {
            String edareCode2 = (String) result[0];
            String edareName2 = (String) result[1];
            String brchCode2 = (String) result[2];
            String brchName2 = (String) result[3];
            String startDate = (String) result[4];
            String endDate = (String) result[5];
            BigDecimal sumAmount = ((BigDecimal) result[6]);
            BigDecimal sumCountOrderNumber = ((BigDecimal) result[7]);

            SabaEfDateBranchesDTO dto = new SabaEfDateBranchesDTO();
            dto.setEdareCode(edareCode2);
            dto.setEdareName(edareName2);
            dto.setBrchCode(brchCode2);
            dto.setBrchName(brchName2);

            dto.setSoratDate1(startDate);
            dto.setSoratDate2(endDate);
            dto.setSoratPrice(sumAmount);
            dto.setCountPrice(sumCountOrderNumber);
            list.add(dto);
        }

        BigDecimal sumSumValue = BigDecimal.valueOf(0);
        BigDecimal sumCountValue = BigDecimal.valueOf(0);

        for (SabaEfDateBranchesDTO dto : list) {
            BigDecimal sumAmount2 = dto.getSoratPrice();
            BigDecimal sumCountOrder2 = dto.getCountPrice();

            sumSumValue = sumSumValue.add(sumAmount2);
            sumCountValue = sumCountValue.add(sumCountOrder2);
        }

        BigDecimal total = BigDecimal.valueOf(list.size());
        SabaEfDateBranchesSummary summery = new SabaEfDateBranchesSummary(sumSumValue, sumCountValue);
        map.put("total", total);
        map.put("data", list);
        map.put("sum", summery);
        return map;
    }


}
