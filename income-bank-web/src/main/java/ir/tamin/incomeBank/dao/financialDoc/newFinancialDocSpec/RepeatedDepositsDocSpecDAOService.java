package ir.tamin.incomeBank.dao.financialDoc.newFinancialDocSpec;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.financialDoc.RepeatedDeposits;
import ir.tamin.incomeBank.model.financialDoc.RepeatedDepositsSum;
import ir.tamin.incomeBank.model.financialDoc.newFinancialDocSpec.RepeatedDepositsDocSpec;
import ir.tamin.incomeBank.model.financialDoc.newFinancialDocSpec.RepeatedDepositsDocSpecSum;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class RepeatedDepositsDocSpecDAOService extends BaseDAOService<RepeatedDepositsDocSpec> {

    @Inject
    private EntityManager em;


    @Override
    public Class<RepeatedDepositsDocSpec> getClazz() {
        return RepeatedDepositsDocSpec.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public RepeatedDepositsDocSpecSum getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<RepeatedDepositsDocSpec> root = criteriaQuery.from(getClazz());

        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(RepeatedDepositsDocSpecSum.class,
                criteriaBuilder.sum(root.<BigDecimal>get("soratPrice1"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        RepeatedDepositsDocSpecSum summery = (RepeatedDepositsDocSpecSum) createQuery.getSingleResult();
        return summery;
    }
}
