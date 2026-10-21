package ir.tamin.incomeBank.dao.payment3070.account503;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.dto.pey3070.account503.Account503Summary;
import ir.tamin.incomeBank.model.payment3070.account503.Account503;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author a_jamali
 */
public class Account503Dao extends BaseDAOService<Account503> {
    @Inject
    private EntityManager em;
    public List<Map<String, String>> getDebitReasonDescAndCode() {


        String queryString = "select T.DEBITREASONDESC, T.DEBITCRTREASONCODE " +
                "from vwdrmd_gl3070_det t " +
                "group by T.DEBITREASONDESC, T.DEBITCRTREASONCODE";

        Query query = em.createNativeQuery(queryString);

        List<Object[]> resultList = query.getResultList();
        List<Map<String, String>> result = new ArrayList<>();

        for (Object[] row : resultList) {
            Map<String, String> map = new HashMap<>();
            map.put("debitReasonDesc", (String) row[0]);
            map.put("debitReasonCode", (String) row[1]);
            result.add(map);
        }

        return result;
    }


    public Account503Summary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<Account503> root = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(Account503Summary.class,
                criteriaBuilder.sum(root.<BigDecimal>get("orderAmount")),
                criteriaBuilder.sum(root.<BigDecimal>get("governmentAmount"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        Account503Summary summery = (Account503Summary) createQuery.getSingleResult();
        return summery;
    }

    @Override
    public Class<Account503> getClazz() {
        return Account503.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
