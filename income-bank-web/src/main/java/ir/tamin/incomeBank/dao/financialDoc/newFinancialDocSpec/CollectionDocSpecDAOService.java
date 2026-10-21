package ir.tamin.incomeBank.dao.financialDoc.newFinancialDocSpec;

import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.financialDoc.Collection3070;
import ir.tamin.incomeBank.model.financialDoc.newFinancialDocSpec.CollectionDocSpec;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class CollectionDocSpecDAOService extends BaseDAOService<CollectionDocSpec> {

    @Inject
    private EntityManager em;

    @Override
    public Class<CollectionDocSpec> getClazz() {
        return CollectionDocSpec.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
