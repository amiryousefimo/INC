package ir.tamin.incomeBank.service.smartlist.branch;

import ir.tamin.incomeBank.dao.smartlist.branch.SmartListBranchDao;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.FilterAndUserRoleValidator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Map;

/**
 * @author A_Jamali
 */

@Stateless
public class SmartListBranchParamService {

    @Inject
    private SmartListBranchDao daoService;

    @Inject
    private FilterAndUserRoleValidator userRoleValidator;


    @Inject
    private ExcelDataProvider excelDataConverter;

    /**
     * @param startDate given the date from user for AFTER operator
     * @param endDate   given the date from user for BEFORE operator
     * @param edareCode give the Branch Code From User
     * @param brchCode  give the Branch Code From User
     * @return a map of list of entity , object of summary and size of list as total number of data
     */
    public Map<String, Object> getBranchByDateRange(String startDate, String endDate, String edareCode, String brchCode) {
        return daoService.getBranchByDateRange(startDate, endDate, edareCode, brchCode);
    }


}
