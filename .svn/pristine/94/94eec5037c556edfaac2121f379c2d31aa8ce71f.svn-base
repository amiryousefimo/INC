package ir.tamin.incomeBank.dao.payment3070.daily;

import ir.tamin.framework.ws.rest.json.FilterWrapper;

import ir.tamin.incomeBank.model.payment3070.daily.Daily3070;
import ir.tamin.incomeBank.model.payment3070.daily.Daily3070Summary;
import ir.tamin.incomeBank.util.BaseDAOService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class Daily3070DAOService extends BaseDAOService<Daily3070> {
    @Inject
    private EntityManager em;


    public Daily3070Summary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<Daily3070> root = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(Daily3070Summary.class,
                criteriaBuilder.sum(root.<BigDecimal>get("soratPrice1")),
                criteriaBuilder.sum(root.<BigDecimal>get("soratPrice2"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        Daily3070Summary summery = (Daily3070Summary) createQuery.getSingleResult();
        return summery;
    }

    @Override
    public Class<Daily3070> getClazz() {
        return Daily3070.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
