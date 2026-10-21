package ir.tamin.incomeBank.dao.financialDoc.newFinancialDoc3070;

import ir.tamin.framework.cdi.util.WebProperties;
import ir.tamin.framework.core.util.Bundle;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.financialDoc.FinancialDocSummery;
import ir.tamin.incomeBank.model.financialDoc.newFinancialDoc3070.FinancialDocSorat3070;
import ir.tamin.incomeBank.model.financialDoc.newFinancialDoc3070.FinancialDocStatus;
import ir.tamin.incomeBank.service.jdbc.StoredProcedure;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class FinancialDocStatus3070DAOService extends BaseDAOService<FinancialDocStatus> {

    @Inject
    private EntityManager em;

    @Inject
    private StoredProcedure procedure;


    @Override
    public Class<FinancialDocStatus> getClazz() {
        return FinancialDocStatus.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
