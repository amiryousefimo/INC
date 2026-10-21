package ir.tamin.incomeBank.service.payment3070.account503edare;

import ir.tamin.incomeBank.dao.payment3070.account503edare.Account503EdareDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Map;

/**
 * @author A_Jamali
 */

@Stateless
public class Account503EdareParamService {
    @Inject
    private Account503EdareDAO daoService;

    /**
     * @param startDate given the date from user for AFTER operator
     * @param endDate   given the date from user for BEFORE operator
     * @param edareCode give the Branch Code From User
     * @return a map of list of entity , object of summary and size of list as total number of data
     */
    public Map<String, Object> getBranchByDateRange(String startDate, String endDate, String edareCode) {
        return daoService.getListByDate(startDate, endDate, edareCode);
    }
}
