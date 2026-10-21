package ir.tamin.incomeBank.dao.announcement.monthlyComparisonReportBankAndOfficeStatement;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.announcement.monthlyComparisonReportBankAndOfficeStatement.MonthlyComparisonReportBankAndOfficeStatement;
import ir.tamin.incomeBank.model.announcement.monthlyComparisonReportBankAndOfficeStatement.MonthlyComparisonReportBankAndOfficeStatementSummary;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

/**
 * @author a_jamali
 */
public class MonthlyComparisonReportBankAndOfficeStatementDAO extends BaseDAOService<MonthlyComparisonReportBankAndOfficeStatement> {
    @Inject
    private EntityManager em;

    public MonthlyComparisonReportBankAndOfficeStatementSummary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<MonthlyComparisonReportBankAndOfficeStatement> workshopDebitReportBranchesFrom = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, workshopDebitReportBranchesFrom, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(MonthlyComparisonReportBankAndOfficeStatementSummary.class,
                criteriaBuilder.sum(workshopDebitReportBranchesFrom.<BigDecimal>get("sumCard")),
                criteriaBuilder.sum(workshopDebitReportBranchesFrom.<BigDecimal>get("sumLetter"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        MonthlyComparisonReportBankAndOfficeStatementSummary summery = (MonthlyComparisonReportBankAndOfficeStatementSummary) createQuery.getSingleResult();
        return summery;
    }


    @Override
    public Class<MonthlyComparisonReportBankAndOfficeStatement> getClazz() {
        return MonthlyComparisonReportBankAndOfficeStatement.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
