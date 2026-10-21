package ir.tamin.incomeBank.dao.financialDoc.newFinancialDocSpec;


import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.financialDoc.newFinancialDocSpec.SheetsWithoutBankCardsDocSpec;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCardsSmartList.SheetsWithoutBankCardsSmartList;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCardsSmartList.SheetsWithoutBankCardsSmartListSummary;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author m_goharriz
 * @date 2024.12.15
 */
public class SheetsWithoutBankCardsDocSpecDAOService extends BaseDAOService<SheetsWithoutBankCardsDocSpec> {

    @Inject
    private EntityManager entityManager;
//todo summary-------------------------
    public SheetsWithoutBankCardsSmartListSummary getSummaryPrice(FilterWrapper filter) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<SheetsWithoutBankCardsDocSpec> root = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(SheetsWithoutBankCardsSmartListSummary.class,
                criteriaBuilder.sum(root.get("glAmount"))));
        TypedQuery createQuery = entityManager.createQuery(criteriaQuery);
        SheetsWithoutBankCardsSmartListSummary summary = (SheetsWithoutBankCardsSmartListSummary) createQuery.getSingleResult();
        return summary;
    }


    @Override
    public Class<SheetsWithoutBankCardsDocSpec> getClazz() {
        return SheetsWithoutBankCardsDocSpec.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
