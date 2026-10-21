package ir.tamin.incomeBank.dao.financialDoc;

import ir.tamin.framework.cdi.util.WebProperties;
import ir.tamin.framework.core.util.Bundle;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.financialDoc.FinancialDocTotal3070;
import ir.tamin.incomeBank.model.financialDoc.FinancialDocSummery;
import ir.tamin.incomeBank.model.financialDoc.FinancialDocTotal3070;
import ir.tamin.incomeBank.model.financialDoc.FinancialDocTotalSummery;
import ir.tamin.incomeBank.service.jdbc.StoredProcedure;

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

@Stateless
public class FinancialDocTotal3070DAOService extends BaseDAOService<FinancialDocTotal3070> {

    @Inject
    private EntityManager em;

    @Inject
    private StoredProcedure procedure;

    @Inject
    @WebProperties
    Bundle webBundle;


    public FinancialDocTotalSummery getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<FinancialDocTotal3070> financialFrom = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, financialFrom, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(FinancialDocTotalSummery.class,
                criteriaBuilder.sum(financialFrom.<BigDecimal>get("debtAmount")),
                criteriaBuilder.sum(financialFrom.<BigDecimal>get("creditAmount"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        FinancialDocTotalSummery summery = (FinancialDocTotalSummery) createQuery.getSingleResult();
        return summery;
    }

    public Map<Object, String> financialIssuance(String year, String month) throws SQLException {

        procedure.init();
        procedure.query("{?=call Pck_Telproc_Core.Create_AccDoc3070(?,?,?)}");
        procedure.setOutParameter(1, Types.NVARCHAR)
                .setInParameter(2, year)
                .setInParameter(3, month)
                .setOutParameter(4, Types.INTEGER);
        procedure.execute();
        Map<Object, String> result = new HashMap<>();
        result.put("param1", procedure.getOutParameter(1).toString());
        //result.put("param4", procedure.getOutParameter(4).toString());
        return result;
    }

    public Map<Object, String> financial3070Remove(String year, String month) throws SQLException {

        procedure.init();
        procedure.query("{?=call Pck_Telproc_Core.Delete_AccDoc3070(?,?,?)}");
        procedure.setOutParameter(1, Types.NVARCHAR)
                .setInParameter(2, year)
                .setInParameter(3, month)
                .setOutParameter(4, Types.INTEGER);
        procedure.execute();
        Map<Object, String> result = new HashMap<>();
        result.put("param1", procedure.getOutParameter(1).toString());
        result.put("param4", procedure.getOutParameter(4).toString());
        return result;
    }

//سند ارسال به مالی 3070
    public String storeTempSequence(String year, String month, String vahedCode, String sequence) throws SQLException {

        procedure.init();
        procedure.query("{?=call Pck_Telproc_Core.Send_AccDoc3070(?,?,?,?,?)}");
        procedure.setOutParameter(1, Types.NVARCHAR)
                .setInParameter(2, vahedCode)
                .setInParameter(3, year)
                .setInParameter(4, month)
                .setInParameter(5, sequence)
                .setOutParameter(6, Types.INTEGER);
        procedure.execute();
        return procedure.getOutParameter(1).toString();// خروجی هرچی باشه نمایش میده

        /********* old **************/
        /*if (procedure.getOutParameter(6).toString().equals("1")) {
            return procedure.getOutParameter(1).toString();
        } else {
            throw new WebApplicationException(procedure.getOutParameter(1).toString());
        }*/
    }

    @Override
    public Class<FinancialDocTotal3070> getClazz() {
        return FinancialDocTotal3070.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
