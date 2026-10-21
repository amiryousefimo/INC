package ir.tamin.incomeBank.service.reportingOfficeBasedPayment.branch;


import ir.tamin.incomeBank.dao.reportingOfficeBasedPayment.branch.ReportingOfficeBasedPaymentBranchDAOService;
import ir.tamin.incomeBank.model.reportingOfficeBasedPayment.branch.ReportingOfficeBasedPaymentBranchView;
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
public class ReportingOfficeBasedPaymentBranchWithParamService {

    @Inject
    private ReportingOfficeBasedPaymentBranchDAOService daoService;


    @Inject
    private ExcelDataProvider excelDataConverter;

    /**************Get List Of ReportingOfficeBasedPayment-Branch's DTO***************/
    public Map<String, Object> getBranchByDateRange(String startDate, String endDate, String edareCode, String brchCode) {
        return daoService.getBranchByDateRange(startDate, endDate, edareCode,brchCode);
    }

    /**************Prepare Data For Excel WITH PARAMS***************/
    private List<List<String>> prepareDataForExcel(List<ReportingOfficeBasedPaymentBranchView> branchViewList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "کد شعبه",
                "نام شعبه",
                "تاریخ اعلامیه از",
                "تاریخ اعلامیه تا",
                "مبلغ وصول",
                "تعداد وصول"
        ));
        int rowCounter = 1;
        for (ReportingOfficeBasedPaymentBranchView branchView : branchViewList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == branchViewList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(branchView.getEdareCode() != null ? branchView.getEdareCode() : "-");
            row.add(branchView.getEdareName() != null ? branchView.getEdareName() : "-");
            row.add(branchView.getBrchCode() != null ? branchView.getBrchCode() : "-");
            row.add(branchView.getBrchName() != null ? branchView.getBrchName() : "-");
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
    public StreamingOutput loadExcel(String startDate, String endDate, String edareCode, String brchCode) throws Exception {
        List<ReportingOfficeBasedPaymentBranchView> branchViewList = null;
        ReportingOfficeBasedPaymentBranchView branchView = null;

        try {
            branchViewList = (List<ReportingOfficeBasedPaymentBranchView>) this.getBranchByDateRange(startDate, endDate, edareCode == null ? "-1" : edareCode, brchCode == null ? "-1" : brchCode).get("data");
        } catch (Exception ex) {
            throw ex;
        }

        branchView = (ReportingOfficeBasedPaymentBranchView) daoService.getBranchByDateRange(startDate, endDate, edareCode == null ? "-1" : edareCode, brchCode == null ? "-1" : brchCode).get("sum");
        ReportingOfficeBasedPaymentBranchView summery = new ReportingOfficeBasedPaymentBranchView();
        summery.setSumAmount(branchView.getSumAmount());
        summery.setCountOrderNumber(branchView.getCountOrderNumber());

        branchViewList.add(summery);

        List<List<String>> rowsForExcel = prepareDataForExcel(branchViewList);
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
