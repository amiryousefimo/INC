package ir.tamin.incomeBank.dao.similarBankSheets;


import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCards3070.SheetsWithoutBankCards3070;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCards3070.SheetsWithoutBankCardsSummary;
import ir.tamin.incomeBank.model.similarBankSheets.SimilarBankSheets;
import ir.tamin.incomeBank.model.similarBankSheets.SimilarBankSheetsSummary;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

/**
 * @author m_goharriz
 * @date 2025.05.18
 */
public class SimilarBankSheetsDAOService extends BaseDAOService<SimilarBankSheets> {

    @Inject
    private EntityManager entityManager;


    /**************** summary ****************/
   public SimilarBankSheetsSummary getSummaryPrice(FilterWrapper filter) {

       CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
       CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
       Root<SimilarBankSheets> root = criteriaQuery.from(getClazz());
       criteriaQuery = getFilteredQuery(criteriaQuery,root,filter,null);
       criteriaQuery.select(criteriaBuilder.construct(SimilarBankSheetsSummary.class,
               criteriaBuilder.sum(root.<BigDecimal>get("calculatorGovermentSold20")),
               criteriaBuilder.sum(root.<BigDecimal>get("calculatorGoverment")),
               criteriaBuilder.sum(root.<BigDecimal>get("govermentAmount")),
               criteriaBuilder.sum(root.<BigDecimal>get("orderAmount")),
               criteriaBuilder.sum(root.<BigDecimal>get("diffGoverment"))));
       TypedQuery createQuery = entityManager.createQuery(criteriaQuery);
       SimilarBankSheetsSummary summary = (SimilarBankSheetsSummary) createQuery.getSingleResult();
       return summary;
   }


    @Override
    public Class<SimilarBankSheets> getClazz() {
        return SimilarBankSheets.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
