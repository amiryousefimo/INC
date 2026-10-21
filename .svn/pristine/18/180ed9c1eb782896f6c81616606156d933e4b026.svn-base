package ir.tamin.incomeBank.service.financialDoc.newFinancialDocSpec;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.financialDoc.Collection3070DAOService;
import ir.tamin.incomeBank.dao.financialDoc.newFinancialDocSpec.CollectionDocSpecDAOService;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CollectionDocSpecService {

    @Inject
    private CollectionDocSpecDAOService daoService;



    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", daoService.search(filterWrapper, start, limit, sort));
            map.put("total", daoService.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(CollectionDocSpecService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }

}
