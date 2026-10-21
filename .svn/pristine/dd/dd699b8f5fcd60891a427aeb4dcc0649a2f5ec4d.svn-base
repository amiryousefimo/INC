package ir.tamin.incomeBank.dao.contradictionReport;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.contradictionReport.ContradictionReport2;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class ContradictionReport2DAOService extends BaseDAOService<ContradictionReport2> {

    @Inject
    private EntityManager em;


    public BigDecimal getSummaryPrice(FilterWrapper filter) {
        Object result;
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<ContradictionReport2> contradictionReportFrom = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, contradictionReportFrom, filter, null);
        criteriaQuery.select(criteriaBuilder.sum(contradictionReportFrom.<BigDecimal> get("billAmount")));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        result = createQuery.getSingleResult();
        return new BigDecimal(result != null ? result.toString() : "0");
    }

    @Override
    public Class<ContradictionReport2> getClazz() {
        return ContradictionReport2.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
