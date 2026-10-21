package ir.tamin.incomeBank.dao.financialDoc.newFinancialDocSpec;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.contradictionReport.ContradictionReport2;
import ir.tamin.incomeBank.model.financialDoc.newFinancialDocSpec.SpecialInsuredsReportDocSpec;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class SpecialInsuredsReportDocSpecDAOService extends BaseDAOService<SpecialInsuredsReportDocSpec> {

    @Inject
    private EntityManager em;


    public BigDecimal getSummaryPrice(FilterWrapper filter) {
        Object result;
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<SpecialInsuredsReportDocSpec> reportFrom = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, reportFrom, filter, null);
        criteriaQuery.select(criteriaBuilder.sum(reportFrom.<BigDecimal> get("billAmount")));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        result = createQuery.getSingleResult();
        return new BigDecimal(result != null ? result.toString() : "0");
    }

    @Override
    public Class<SpecialInsuredsReportDocSpec> getClazz() {
        return SpecialInsuredsReportDocSpec.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
