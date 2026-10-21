package ir.tamin.incomeBank.dao.workshopDebitReport.branches;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.workshopDebitReport.branches.WorkshopDebitReportBranches;
import ir.tamin.incomeBank.model.workshopDebitReport.branches.WorkshopDebitReportSummeryBranches;

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

public class WorkshopDebitReportDAOServiceBranches extends BaseDAOService<WorkshopDebitReportBranches> {

    @Inject
    private EntityManager em;

    public WorkshopDebitReportSummeryBranches getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<WorkshopDebitReportBranches> workshopDebitReportBranchesFrom = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, workshopDebitReportBranchesFrom, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(WorkshopDebitReportSummeryBranches.class,
                criteriaBuilder.sum(workshopDebitReportBranchesFrom.<BigDecimal>get("amountBed")),
                criteriaBuilder.sum(workshopDebitReportBranchesFrom.<BigDecimal>get("amountBes"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        WorkshopDebitReportSummeryBranches summery = (WorkshopDebitReportSummeryBranches) createQuery.getSingleResult();
        return summery;
    }

    @Override
    public Class<WorkshopDebitReportBranches> getClazz() {
        return WorkshopDebitReportBranches.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
