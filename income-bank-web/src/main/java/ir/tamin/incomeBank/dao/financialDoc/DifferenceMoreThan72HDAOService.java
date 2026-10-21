package ir.tamin.incomeBank.dao.financialDoc;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.financialDoc.DifferenceMoreThan72;
import ir.tamin.incomeBank.model.financialDoc.DifferenceMoreThan72SumAmount;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class DifferenceMoreThan72HDAOService extends BaseDAOService<DifferenceMoreThan72> {

    @Inject
    private EntityManager em;


    @Override
    public Class<DifferenceMoreThan72> getClazz() {
        return DifferenceMoreThan72.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public DifferenceMoreThan72SumAmount getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<DifferenceMoreThan72> root = criteriaQuery.from(getClazz());

        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(DifferenceMoreThan72SumAmount.class,
                criteriaBuilder.sum(root.<BigDecimal>get("soratPrice1"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        DifferenceMoreThan72SumAmount summery = (DifferenceMoreThan72SumAmount) createQuery.getSingleResult();
        return summery;
    }
}
