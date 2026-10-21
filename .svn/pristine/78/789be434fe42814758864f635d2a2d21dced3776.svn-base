package ir.tamin.incomeBank.dao.sheetsWithoutBankCards.bankCardsSmartList;


import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCards3070.SheetsWithoutBankCards3070;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCards3070.SheetsWithoutBankCardsSummary;
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
public class SheetsWithoutBankCardsSmartListDAOService extends BaseDAOService<SheetsWithoutBankCardsSmartList> {

    @Inject
    private EntityManager entityManager;

    public SheetsWithoutBankCardsSmartListSummary getSummaryPrice(FilterWrapper filter) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<SheetsWithoutBankCardsSmartList> root = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(SheetsWithoutBankCardsSmartListSummary.class,
                criteriaBuilder.sum(root.get("glAmount"))));
        TypedQuery createQuery = entityManager.createQuery(criteriaQuery);
        SheetsWithoutBankCardsSmartListSummary summary = (SheetsWithoutBankCardsSmartListSummary) createQuery.getSingleResult();
        return summary;
    }


    @Override
    public Class<SheetsWithoutBankCardsSmartList> getClazz() {
        return SheetsWithoutBankCardsSmartList.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
