package ir.tamin.incomeBank.dao.saba.details;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.dto.saba.details.SabaDetailsSummary;
import ir.tamin.incomeBank.model.saba.details.SabaDetails;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
public class SabaDetailsDAO extends BaseDAOService<SabaDetails> {


    @Inject
    private EntityManager em;

    public List<Map<String, String>> getBankDetails() {


        String queryString = "select bankcode , bankname" +
                " from baseinfo.tb_bank " +
                " where bankcode <='07'";

        Query query = em.createNativeQuery(queryString);

        List<Object[]> resultList = query.getResultList();
        List<Map<String, String>> result = new ArrayList<>();

        for (Object[] row : resultList) {
            Map<String, String> map = new HashMap<>();
            map.put("bankCode", (String) row[0]);
            map.put("bankName", (String) row[1]);
            result.add(map);
        }

        return result;
    }

    public SabaDetailsSummary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<SabaDetails> workshopDebitReportFrom = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, workshopDebitReportFrom, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(SabaDetailsSummary.class,
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("soratPrice1")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("soratPrice2"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        SabaDetailsSummary summery = (SabaDetailsSummary) createQuery.getSingleResult();
        return summery;
    }

    @Override
    public Class<SabaDetails> getClazz() {
        return SabaDetails.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
