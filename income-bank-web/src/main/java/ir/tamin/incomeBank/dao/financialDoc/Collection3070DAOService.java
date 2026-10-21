package ir.tamin.incomeBank.dao.financialDoc;

import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.financialDoc.Collection3070;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class Collection3070DAOService  extends BaseDAOService<Collection3070> {

    @Inject
    private EntityManager em;

    @Override
    public Class<Collection3070> getClazz() {
        return Collection3070.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
