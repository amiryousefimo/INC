package ir.tamin.incomeBank.dao.payment3070.total;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.model.payment3070.total.Total3070;
import ir.tamin.incomeBank.model.payment3070.total.Total3070Summary;
import ir.tamin.incomeBank.util.BaseDAOService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
/**
 * @author m_goharriz
 * Date : 1404.02.10
 * */

public class Total3070DAOService extends BaseDAOService<Total3070> {
    @Inject
    private EntityManager em;


    public Total3070Summary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<Total3070> root = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(Total3070Summary.class,

                criteriaBuilder.sum(root.<BigDecimal>get("soratPrice1")),
                criteriaBuilder.sum(root.<BigDecimal>get("soratPrice2"))));

        TypedQuery createQuery = em.createQuery(criteriaQuery);
        Total3070Summary summery = (Total3070Summary) createQuery.getSingleResult();
        return summery;
    }

    @Override
    public Class<Total3070> getClazz() {
        return Total3070.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
