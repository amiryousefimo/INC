package ir.tamin.incomeBank.dao.financialDoc;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.financialDoc.DifferenceBetweenBankOffice;
import ir.tamin.incomeBank.model.financialDoc.DifferenceBetweenBankOfficeSumAmount;
import ir.tamin.incomeBank.model.financialDoc.DifferenceMoreThan72;
import ir.tamin.incomeBank.model.financialDoc.DifferenceMoreThan72SumAmount;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class DifferenceBetweenBankOfficeDAOService extends BaseDAOService<DifferenceBetweenBankOffice> {

    @Inject
    private EntityManager em;


    @Override
    public Class<DifferenceBetweenBankOffice> getClazz() {
        return DifferenceBetweenBankOffice.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public DifferenceBetweenBankOfficeSumAmount getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<DifferenceBetweenBankOffice> root = criteriaQuery.from(getClazz());

        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(DifferenceBetweenBankOfficeSumAmount.class,
                criteriaBuilder.sum(root.<BigDecimal>get("soratPrice1")),
                criteriaBuilder.sum(root.<BigDecimal>get("orderAmount")),
                criteriaBuilder.sum(root.<BigDecimal>get("diffrentAmount"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        DifferenceBetweenBankOfficeSumAmount summery = (DifferenceBetweenBankOfficeSumAmount) createQuery.getSingleResult();
        return summery;
    }
}
