package ir.tamin.incomeBank.service.financialDoc.newFinancialDocSpec;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.financialDoc.RepeatedDepositsDAOService;
import ir.tamin.incomeBank.dao.financialDoc.newFinancialDocSpec.RepeatedDepositsDocSpecDAOService;
import ir.tamin.incomeBank.model.financialDoc.RepeatedDepositsSum;
import ir.tamin.incomeBank.model.financialDoc.newFinancialDocSpec.RepeatedDepositsDocSpecSum;
import ir.tamin.incomeBank.util.FilterAndUserRoleValidator;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepeatedDepositsDocSpecService {
    @Inject
    private RepeatedDepositsDocSpecDAOService daoService;

    @Inject
    FilterAndUserRoleValidator userRoleValidator;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", daoService.search(filterWrapper, start, limit, sort));
            map.put("total", daoService.getCount(filterWrapper));

        } catch (Exception ex) {
            Logger.getLogger(RepeatedDepositsDocSpecService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return map;
    }
    public RepeatedDepositsDocSpecSum getSummary(FilterWrapper filterWrapper)throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(RepeatedDepositsDocSpecService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }


}
