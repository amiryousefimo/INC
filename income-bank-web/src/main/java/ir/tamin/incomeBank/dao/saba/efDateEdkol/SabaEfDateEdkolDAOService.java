package ir.tamin.incomeBank.dao.saba.efDateEdkol;

import ir.tamin.framework.ws.rest.json.FilterWrapper;

import ir.tamin.incomeBank.model.saba.efDateEdkol.SabaEfDateEdkol;
import ir.tamin.incomeBank.model.saba.efDateEdkol.SabaEfDateEdkolDTO;
import ir.tamin.incomeBank.model.saba.efDateEdkol.SabaEfDateEdkolSummary;
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

public class SabaEfDateEdkolDAOService extends BaseDAOService<SabaEfDateEdkol> {

    @Inject
    private EntityManager em;


    public Class<SabaEfDateEdkol> getClazz() {
        return SabaEfDateEdkol.class;
    }


    public EntityManager getEntityManager() {
        return em;
    }

    public SabaEfDateEdkolSummary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SabaEfDateEdkolSummary> criteriaQuery = criteriaBuilder.createQuery(SabaEfDateEdkolSummary.class);

        Root<SabaEfDateEdkol> root = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);

        criteriaQuery.select(criteriaBuilder.construct(
                SabaEfDateEdkolSummary.class,
                criteriaBuilder.sum(root.get("soratPrice")),
                criteriaBuilder.sum(root.get("countPrice"))));

        TypedQuery typedQuery = em.createQuery(criteriaQuery);
        SabaEfDateEdkolSummary summary = (SabaEfDateEdkolSummary) typedQuery.getSingleResult();
        return summary;
    }

    //////////////////////////////////////////////////////////////////////
    public Map<String, Object> getByDateRange(String startDate1, String endDate1,String edareCode1) {
        Map<String, Object> map = new HashMap<>();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<SabaEfDateEdkol> root = criteriaQuery.from(SabaEfDateEdkol.class);

//        Expression<String> soratDesc = root.get("soratDesc");
        Expression<String> soratDate = root.get("soratEfDate");
        Expression<String> edareCode = root.get("edareCode");
        Expression<BigDecimal> soratPrice = root.get("soratPrice");
        Expression<BigDecimal> countPrice = root.get("countPrice");

        Predicate datePredicate = criteriaBuilder.between(soratDate, startDate1, endDate1);

        criteriaQuery.multiselect(
                root.get("edareCode"),
                root.get("edareName"),
//                soratDesc,
                criteriaBuilder.literal(startDate1),
                criteriaBuilder.literal(endDate1),
                criteriaBuilder.sum(soratPrice),
                criteriaBuilder.sum(countPrice)
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
        List<SabaEfDateEdkolDTO> list = new ArrayList<>();

        for (Object[] result : results) {
            String edareCode2=(String)result[0];
            String edareName2=(String)result[1];
//            String soratDesc2 = (String) result[2];
            String startDate = (String) result[2];
            String endDate = (String) result[3];
            BigDecimal sumAmount = ((BigDecimal) result[4]);
            BigDecimal sumCountOrderNumber = ((BigDecimal) result[5]);

            SabaEfDateEdkolDTO dto = new SabaEfDateEdkolDTO();
            dto.setEdareCode(edareCode2);
            dto.setEdareName(edareName2);
//            dto.setSoratDesc(soratDesc2);
            dto.setSoratDate1(startDate);
            dto.setSoratDate2(endDate);
            dto.setSoratPrice(sumAmount);
            dto.setCountPrice(sumCountOrderNumber);
            list.add(dto);
        }

        BigDecimal sumSumValue = BigDecimal.valueOf(0);
        BigDecimal sumCountValue = BigDecimal.valueOf(0);

        for (SabaEfDateEdkolDTO dto : list) {
            BigDecimal sumAmount2 = dto.getSoratPrice();
            BigDecimal sumCountOrder2 = dto.getCountPrice();

            sumSumValue = sumSumValue.add(sumAmount2);
            sumCountValue = sumCountValue.add(sumCountOrder2);
        }

        BigDecimal total = BigDecimal.valueOf(list.size());
        SabaEfDateEdkolSummary summery = new SabaEfDateEdkolSummary(sumSumValue, sumCountValue);
        map.put("total", total);
        map.put("data", list);
        map.put("sum", summery);
        return map;
    }



}
