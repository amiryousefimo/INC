package ir.tamin.incomeBank.service.reportingOfficeBasedPayment.department;


import ir.tamin.incomeBank.dao.reportingOfficeBasedPayment.department.ReportingOfficeBasedPaymentDepartmentDAOService;
import ir.tamin.incomeBank.model.reportingOfficeBasedPayment.department.ReportingOfficeBasedPaymentDepartmentView;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import org.apache.poi.ss.usermodel.Workbook;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @author A_Jamali
 */

@Stateless
public class ReportingOfficeBasedPaymentDepartmentWithParamService {

    @Inject
    private ReportingOfficeBasedPaymentDepartmentDAOService daoService;


    @Inject
    private ExcelDataProvider excelDataConverter;

    /**************Get List Of ReportingOfficeBasedPayment-Department's DTO***************/
    public Map<String, Object> getBranchByDateRange(String startDate, String endDate, String edareCode) {
        return daoService.getBranchByDateRange(startDate, endDate, edareCode);
    }

    /**************Prepare Data For Excel WITH PARAMS***************/
    private List<List<String>> prepareDataForExcel(List<ReportingOfficeBasedPaymentDepartmentView> branchViewList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "تاریخ اعلامیه از",
                "تاریخ اعلامیه تا",
                "مبلغ وصول",
                "تعداد وصول"
        ));
        int rowCounter = 1;
        for (ReportingOfficeBasedPaymentDepartmentView branchView : branchViewList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == branchViewList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(branchView.getEdareCode() != null ? branchView.getEdareCode() : "-");
            row.add(branchView.getEdareName() != null ? branchView.getEdareName() : "-");
            row.add(branchView.getOrdDate1() != null ? branchView.getOrdDate1() : "-");
            row.add(branchView.getOrdDate2() != null ? branchView.getOrdDate2() : "-");
            row.add(branchView.getSumAmount() != null ? branchView.getSumAmount().toString() : "-");
            row.add(branchView.getCountOrderNumber() != null ? branchView.getCountOrderNumber().toString() : "-");

            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    /************** Create Header ***************/
    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("دفتر اعلامیه های ذیحسابی - شعبه-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }

    /************** Load Excel Method To Invoke By REST ***************/
    public StreamingOutput loadExcel(String startDate, String endDate, String edareCode) throws Exception {
        List<ReportingOfficeBasedPaymentDepartmentView> departmentViews = null;
        ReportingOfficeBasedPaymentDepartmentView departmentView = null;

        try {
            departmentViews = (List<ReportingOfficeBasedPaymentDepartmentView>) this.getBranchByDateRange(startDate, endDate, edareCode  ).get("data");
        } catch (Exception ex) {
            throw ex;
        }

        departmentView = (ReportingOfficeBasedPaymentDepartmentView) daoService.getBranchByDateRange(startDate, endDate,  edareCode).get("sum");
        ReportingOfficeBasedPaymentDepartmentView summery = new ReportingOfficeBasedPaymentDepartmentView();
        summery.setSumAmount(departmentView.getSumAmount());
        summery.setCountOrderNumber(departmentView.getCountOrderNumber());

        departmentViews.add(summery);

        List<List<String>> rowsForExcel = prepareDataForExcel(departmentViews);
        ExcelReportHeader reportHeader = createReportHeaderDetails();

        StreamingOutput streamingOutPut = new StreamingOutput() {
            final Workbook workbook = excelDataConverter.provideExcelFrom(reportHeader, rowsForExcel);

            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException {
                workbook.write(output);
            }
        };

        return streamingOutPut;
    }


}
