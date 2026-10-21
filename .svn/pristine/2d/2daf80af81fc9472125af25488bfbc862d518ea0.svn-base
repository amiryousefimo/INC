package ir.tamin.incomeBank.service.financialDoc.newFinancialDoc3070;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.financialDoc.newFinancialDoc3070.FinancialDocSorat3070DAOService;
import ir.tamin.incomeBank.dao.financialDoc.newFinancialDoc3070.FinancialDocStatus3070DAOService;
import ir.tamin.incomeBank.model.basicTables.debtTypes.DebtTypesTable;
import ir.tamin.incomeBank.model.financialDoc.newFinancialDoc3070.FinancialDocStatus;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FinancialDocStatusService {

    @Inject
    private FinancialDocStatus3070DAOService daoService;


    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        Map<String, Object> map = new HashMap<>();

        try {
            map.put("list", daoService.search(filterWrapper, start, limit, sort));
            map.put("total", daoService.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(FinancialDocStatusService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }
}
