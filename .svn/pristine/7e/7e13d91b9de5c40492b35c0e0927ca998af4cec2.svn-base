package ir.tamin.incomeBank.dao.basicTables.debtTypes;

import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.basicTables.debtTypes.DebtTypesTable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class DebtTypesDAOService  extends BaseDAOService<DebtTypesTable> {

    @Inject
    private EntityManager entityManager;


    @Override
    public Class<DebtTypesTable> getClazz() {
        return DebtTypesTable.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
