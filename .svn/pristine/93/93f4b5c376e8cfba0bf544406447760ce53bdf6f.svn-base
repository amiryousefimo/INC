package ir.tamin.incomeBank.dao.saba.dateBranch;

import ir.tamin.framework.ws.rest.json.FilterWrapper;

import ir.tamin.incomeBank.model.saba.dateBranch.SabaDateBranches;
import ir.tamin.incomeBank.model.saba.dateBranch.SabaDateBranchesDTO;
import ir.tamin.incomeBank.model.saba.dateBranch.SabaDateBranchesSummary;
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

public class SabaDateBranchesDAOService extends BaseDAOService<SabaDateBranches> {

    @Inject
    private EntityManager em;


    public Class<SabaDateBranches> getClazz() {
        return SabaDateBranches.class;
    }


    public EntityManager getEntityManager() {
        return em;
    }

    public SabaDateBranchesSummary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SabaDateBranchesSummary> criteriaQuery = criteriaBuilder.createQuery(SabaDateBranchesSummary.class);

        Root<SabaDateBranches> root = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);

        criteriaQuery.select(criteriaBuilder.construct(
                SabaDateBranchesSummary.class,
                criteriaBuilder.sum(root.get("soratPrice")),
                criteriaBuilder.sum(root.get("countPrice"))));

        TypedQuery typedQuery = em.createQuery(criteriaQuery);
        SabaDateBranchesSummary summary = (SabaDateBranchesSummary) typedQuery.getSingleResult();
        return summary;
    }

    //////////////////////////////////////////////////////////////////////
    public Map<String, Object> getByDateRange(String startDate1, String endDate1, String edareCode1, String brchCode1) {
        Map<String, Object> map = new HashMap<>();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<SabaDateBranches> root = criteriaQuery.from(SabaDateBranches.class);

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
        List<SabaDateBranchesDTO> list = new ArrayList<>();

        for (Object[] result : results) {
            String edareCode2 = (String) result[0];
            String edareName2 = (String) result[1];
            String brchCode2 = (String) result[2];
            String brchName2 = (String) result[3];
            String startDate = (String) result[4];
            String endDate = (String) result[5];
            BigDecimal sumAmount = ((BigDecimal) result[6]);
            BigDecimal sumCountOrderNumber = ((BigDecimal) result[7]);

            SabaDateBranchesDTO dto = new SabaDateBranchesDTO();
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

        for (SabaDateBranchesDTO dto : list) {
            BigDecimal sumAmount2 = dto.getSoratPrice();
            BigDecimal sumCountOrder2 = dto.getCountPrice();

            sumSumValue = sumSumValue.add(sumAmount2);
            sumCountValue = sumCountValue.add(sumCountOrder2);
        }

        BigDecimal total = BigDecimal.valueOf(list.size());
        SabaDateBranchesSummary summery = new SabaDateBranchesSummary(sumSumValue, sumCountValue);
        map.put("total", total);
        map.put("data", list);
        map.put("sum", summery);
        return map;
    }


}
