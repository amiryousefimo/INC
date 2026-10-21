package ir.tamin.incomeBank.service.soratEfDate;


import ir.tamin.incomeBank.dao.soratEfDate.SoratEfDateDailyDAOService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Map;

/**
 * @author A_Jamali
 */

@Stateless
public class SoratEfDateDailyService {

    @Inject
    private SoratEfDateDailyDAOService daoService;



    /**************Get List Of ReportingOfficeBasedPayment-Department's DTO***************/
    public Map<String, Object> getBranchByDateRange(String startDate, String endDate) {
        return daoService.getByDateRange(startDate, endDate);
    }


}
