package ir.tamin.incomeBank.dao.smartListBank.effectiveDate;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.model.smartListBank.effectiveDate.SmartListBankEffectiveDate;
import ir.tamin.incomeBank.model.smartListBank.effectiveDate.SmartListBankEffectiveDateSummary;
import ir.tamin.incomeBank.util.BaseDAOService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author A_Jamali
 */
public class SmartListBankEffectiveDateDAOService extends BaseDAOService<SmartListBankEffectiveDate> {

    @Inject
    private EntityManager em;

    public SmartListBankEffectiveDateSummary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<SmartListBankEffectiveDate> root = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(SmartListBankEffectiveDateSummary.class,
                criteriaBuilder.sum(root.get("priceDate")),
                criteriaBuilder.sum(root.get("cntDate")),
                criteriaBuilder.sum(root.get("priceEfDate")),
                criteriaBuilder.sum(root.get("cntEfDate")))
        );
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        SmartListBankEffectiveDateSummary summery = (SmartListBankEffectiveDateSummary) createQuery.getSingleResult();
        return summery;
    }

    @Override
    public Class<SmartListBankEffectiveDate> getClazz() {
        return SmartListBankEffectiveDate.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
