package ir.tamin.incomeBank.dao.payment3070.effective;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.model.payment3070.effective.Effective3070;
import ir.tamin.incomeBank.model.payment3070.effective.Effective3070Summary;
import ir.tamin.incomeBank.util.BaseDAOService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class Effective3070DAOService extends BaseDAOService<Effective3070> {


    @Inject
    private EntityManager em;

//    @Inject
//    private FilterAndUserRoleValidator userRoleValidator;

    public Effective3070Summary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<Effective3070> root = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(Effective3070Summary.class,
                criteriaBuilder.sum(root.<BigDecimal>get("priceDate")),
                criteriaBuilder.sum(root.<BigDecimal>get("countDate")),
                criteriaBuilder.sum(root.<BigDecimal>get("priceEfDate")),
                criteriaBuilder.sum(root.<BigDecimal>get("countEfDate")),
                criteriaBuilder.sum(root.<BigDecimal>get("soratPrice1")),
                criteriaBuilder.sum(root.<BigDecimal>get("soratPrice2"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        Effective3070Summary summery = (Effective3070Summary) createQuery.getSingleResult();
        return summery;
    }


    public Class<Effective3070> getClazz() {
        return Effective3070.class;
    }


    public EntityManager getEntityManager() {
        return em;
    }


}
