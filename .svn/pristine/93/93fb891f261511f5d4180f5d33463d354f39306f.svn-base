package ir.tamin.incomeBank.dao.financialDoc.newFinancialDocSpec;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.financialDoc.DifferenceMoreThan72;
import ir.tamin.incomeBank.model.financialDoc.DifferenceMoreThan72SumAmount;
import ir.tamin.incomeBank.model.financialDoc.newFinancialDocSpec.DifferenceMoreThan72DocSpec;
import ir.tamin.incomeBank.model.financialDoc.newFinancialDocSpec.DifferenceMoreThan72DocSpecSumAmount;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class DifferenceMoreThan72HDocSpeDAOService extends BaseDAOService<DifferenceMoreThan72DocSpec> {

    @Inject
    private EntityManager em;


    @Override
    public Class<DifferenceMoreThan72DocSpec> getClazz() {
        return DifferenceMoreThan72DocSpec.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public DifferenceMoreThan72DocSpecSumAmount getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<DifferenceMoreThan72DocSpec> root = criteriaQuery.from(getClazz());

        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(DifferenceMoreThan72DocSpecSumAmount.class,
                criteriaBuilder.sum(root.<BigDecimal>get("soratPrice1"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        DifferenceMoreThan72DocSpecSumAmount summery = (DifferenceMoreThan72DocSpecSumAmount) createQuery.getSingleResult();
        return summery;
    }
}
