package ir.tamin.incomeBank.dao.financialDoc.newFinancialDocSpec;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.financialDoc.DifferenceBetweenBankOffice;
import ir.tamin.incomeBank.model.financialDoc.DifferenceBetweenBankOfficeSumAmount;
import ir.tamin.incomeBank.model.financialDoc.newFinancialDocSpec.DifferenceBetweenBankOfficeDocSpec;
import ir.tamin.incomeBank.model.financialDoc.newFinancialDocSpec.DifferenceBetweenBankOfficeDocSpecSumAmount;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class DifferenceBetweenBankOfficeDocSpecDAOService extends BaseDAOService<DifferenceBetweenBankOfficeDocSpec> {

    @Inject
    private EntityManager em;


    @Override
    public Class<DifferenceBetweenBankOfficeDocSpec> getClazz() {
        return DifferenceBetweenBankOfficeDocSpec.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public DifferenceBetweenBankOfficeDocSpecSumAmount getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<DifferenceBetweenBankOfficeDocSpec> root = criteriaQuery.from(getClazz());

        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(DifferenceBetweenBankOfficeDocSpecSumAmount.class,
                criteriaBuilder.sum(root.<BigDecimal>get("soratPrice1")),
                criteriaBuilder.sum(root.<BigDecimal>get("orderAmount")),
                criteriaBuilder.sum(root.<BigDecimal>get("diffrentAmount"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        DifferenceBetweenBankOfficeDocSpecSumAmount summery = (DifferenceBetweenBankOfficeDocSpecSumAmount) createQuery.getSingleResult();
        return summery;
    }
}
