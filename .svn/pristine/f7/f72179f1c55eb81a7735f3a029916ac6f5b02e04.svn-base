package ir.tamin.incomeBank.dao.financialDoc;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.financialDoc.RepeatedDeposits;
import ir.tamin.incomeBank.model.financialDoc.RepeatedDepositsSum;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class RepeatedDepositsDAOService extends BaseDAOService<RepeatedDeposits> {

    @Inject
    private EntityManager em;


    @Override
    public Class<RepeatedDeposits> getClazz() {
        return RepeatedDeposits.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public RepeatedDepositsSum getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<RepeatedDeposits> root = criteriaQuery.from(getClazz());

        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(RepeatedDepositsSum.class,
                criteriaBuilder.sum(root.<BigDecimal>get("soratPrice1"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        RepeatedDepositsSum summery = (RepeatedDepositsSum) createQuery.getSingleResult();
        return summery;
    }
}
