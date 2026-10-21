package ir.tamin.incomeBank.service.receiptReport.receiptDate;


import ir.tamin.incomeBank.dao.receiptReport.receiptDate.ReceiptReportReceiptDateDAOService;
import ir.tamin.incomeBank.util.ExcelDataProvider;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Map;

/**
 * @author A_Jamali
 */

@Stateless
public class ReceiptReportReceiptDateWithService {

    @Inject
    private ReceiptReportReceiptDateDAOService daoService;



    /**************Get List Of ReportingOfficeBasedPayment-Department's DTO***************/
    public Map<String, Object> getBranchByDateRange(String startDate, String endDate) {
        return daoService.getByDateRange(startDate, endDate);
    }


}
