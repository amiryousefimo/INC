package ir.tamin.incomeBank.dao.workshopDebitReport.country;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.workshopDebitReport.country.WorkshopDebitReportCountry;
import ir.tamin.incomeBank.model.workshopDebitReport.country.WorkshopDebitReportSummeryCountry;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;


/**
 * @author A_Jamali
 */

public class WorkshopDebitReportDAOServiceCountry extends BaseDAOService<WorkshopDebitReportCountry> {

    @Inject
    private EntityManager em;

    public WorkshopDebitReportSummeryCountry getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<WorkshopDebitReportCountry> workshopDebitReportBranchesFrom = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, workshopDebitReportBranchesFrom, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(WorkshopDebitReportSummeryCountry.class,
                criteriaBuilder.sum(workshopDebitReportBranchesFrom.<BigDecimal>get("amountBed")),
                criteriaBuilder.sum(workshopDebitReportBranchesFrom.<BigDecimal>get("amountBes")),
                criteriaBuilder.sum(workshopDebitReportBranchesFrom.<BigDecimal>get("remainAmount"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        WorkshopDebitReportSummeryCountry summery = (WorkshopDebitReportSummeryCountry) createQuery.getSingleResult();
        return summery;
    }

    @Override
    public Class<WorkshopDebitReportCountry> getClazz() {
        return WorkshopDebitReportCountry.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
