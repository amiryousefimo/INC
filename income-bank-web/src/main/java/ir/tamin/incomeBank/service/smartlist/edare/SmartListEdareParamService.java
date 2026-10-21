package ir.tamin.incomeBank.service.smartlist.edare;

import ir.tamin.incomeBank.dao.smartlist.edare.SmartListEdareDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Map;

/**
 * @author A_Jamali
 */

@Stateless
public class SmartListEdareParamService {

    @Inject
    private SmartListEdareDao daoService;

    /**
     * @param startDate given the date from user for AFTER operator
     * @param endDate   given the date from user for BEFORE operator
     * @param edareCode give the Branch Code From User
     * @return a map of list of entity , object of summary and size of list as total number of data
     */
    public Map<String, Object> getBranchByDateRange(String startDate, String endDate, String edareCode) {
        return daoService.getBranchByDateRange(startDate, endDate, edareCode);
    }


}
