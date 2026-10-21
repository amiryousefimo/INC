package ir.tamin.incomeBank.dao.workshopDebitReport.workshop;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.model.workshopDebitReport.workshop.WorkshopDebitReport;
import ir.tamin.incomeBank.model.workshopDebitReport.workshop.WorkshopDebitReportSummery;
import ir.tamin.incomeBank.dao.BaseDAOService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class WorkshopDebitReportDAOService extends BaseDAOService<WorkshopDebitReport> {

    @Inject
    private EntityManager em;

    public WorkshopDebitReportSummery getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<WorkshopDebitReport> workshopDebitReportFrom = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, workshopDebitReportFrom, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(WorkshopDebitReportSummery.class,
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("orderAmount")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("bankAmount"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        WorkshopDebitReportSummery summery = (WorkshopDebitReportSummery) createQuery.getSingleResult();
        return summery;
    }

    @Override
    public Class<WorkshopDebitReport> getClazz() {
        return WorkshopDebitReport.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
