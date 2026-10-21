package ir.tamin.incomeBank.service.financialDoc.newFinancialDocSpec;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.financialDoc.DifferenceMoreThan72HDAOService;
import ir.tamin.incomeBank.dao.financialDoc.newFinancialDocSpec.DifferenceMoreThan72HDocSpeDAOService;
import ir.tamin.incomeBank.model.financialDoc.DifferenceMoreThan72SumAmount;
import ir.tamin.incomeBank.model.financialDoc.newFinancialDocSpec.DifferenceMoreThan72DocSpecSumAmount;
import ir.tamin.incomeBank.util.FilterAndUserRoleValidator;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DifferenceMoreThan72HDocSpecService {

    @Inject
    private DifferenceMoreThan72HDocSpeDAOService daoService;

    @Inject
    FilterAndUserRoleValidator userRoleValidator;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", daoService.search(filterWrapper, start, limit, sort));
            map.put("total", daoService.getCount(filterWrapper));
//            map.put("sum",getSummary(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(DifferenceMoreThan72HDocSpecService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }

    public DifferenceMoreThan72DocSpecSumAmount getSummary(FilterWrapper filterWrapper)throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(DifferenceMoreThan72HDocSpecService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

}
