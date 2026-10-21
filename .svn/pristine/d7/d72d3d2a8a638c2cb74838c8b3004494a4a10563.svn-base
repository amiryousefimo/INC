package ir.tamin.incomeBank.dao.sheetsWithoutBankCards.bankCards3070;


import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCards3070.SheetsWithoutBankCards3070;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCards3070.SheetsWithoutBankCardsSummary;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCards3070.SheetsWithoutBankCardsView;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author m_goharriz
 * @date 2024.12.15
 */
public class SheetsWithoutBankCards3070DAOService extends BaseDAOService<SheetsWithoutBankCards3070> {

    @Inject
    private EntityManager entityManager;


    /**************** summary ****************/
   public SheetsWithoutBankCardsSummary getSummaryPrice(FilterWrapper filter) {

       CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
       CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
       Root<SheetsWithoutBankCards3070> root = criteriaQuery.from(getClazz());
       criteriaQuery = getFilteredQuery(criteriaQuery,root,filter,null);
       criteriaQuery.select(criteriaBuilder.construct(SheetsWithoutBankCardsSummary.class,
               criteriaBuilder.sum(root.get("glAmount"))));
       TypedQuery createQuery = entityManager.createQuery(criteriaQuery);
       SheetsWithoutBankCardsSummary summary = (SheetsWithoutBankCardsSummary) createQuery.getSingleResult();
       return summary;
   }


    @Override
    public Class<SheetsWithoutBankCards3070> getClazz() {
        return SheetsWithoutBankCards3070.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
