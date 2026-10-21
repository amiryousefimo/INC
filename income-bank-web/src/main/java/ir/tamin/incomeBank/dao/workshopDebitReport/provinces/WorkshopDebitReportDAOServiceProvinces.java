package ir.tamin.incomeBank.dao.workshopDebitReport.provinces;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.workshopDebitReport.provinces.WorkshopDebitReportProvinces;
import ir.tamin.incomeBank.model.workshopDebitReport.provinces.WorkshopDebitReportSummeryProvinces;

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

public class WorkshopDebitReportDAOServiceProvinces extends BaseDAOService<WorkshopDebitReportProvinces> {

    @Inject
    private EntityManager em;



    public WorkshopDebitReportSummeryProvinces getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<WorkshopDebitReportProvinces> workshopDebitReportBranchesFrom = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, workshopDebitReportBranchesFrom, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(WorkshopDebitReportSummeryProvinces.class,
                criteriaBuilder.sum(workshopDebitReportBranchesFrom.<BigDecimal>get("amountBed")),
                criteriaBuilder.sum(workshopDebitReportBranchesFrom.<BigDecimal>get("amountBes")),
                criteriaBuilder.sum(workshopDebitReportBranchesFrom.<BigDecimal>get("remainAmount"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        WorkshopDebitReportSummeryProvinces summery = (WorkshopDebitReportSummeryProvinces) createQuery.getSingleResult();
        return summery;
    }

    public Class<WorkshopDebitReportProvinces> getClazz() {
        return WorkshopDebitReportProvinces.class;
    }

    public EntityManager getEntityManager() {
        return em;
    }


}
