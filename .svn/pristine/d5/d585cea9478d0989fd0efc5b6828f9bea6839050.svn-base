package ir.tamin.incomeBank.service.reportingOfficeBasedPayment.branch;


import ir.tamin.incomeBank.dao.reportingOfficeBasedPayment.branch.ReportingOfficeBasedPaymentBranchDAOService;
import ir.tamin.incomeBank.model.reportingOfficeBasedPayment.branch.ReportingOfficeBasedPaymentBranchView;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import org.apache.poi.ss.usermodel.Workbook;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class ReportingOfficeBasedPaymentBranchWithParamServiceExcelProviderWithParam {
    @Inject
    ReportingOfficeBasedPaymentBranchDAOService service;

    public Workbook loadExcel(String startDate, String endDate, String edareCode, String brchCode) {

        Map<String, Object> map = null;
        List<ReportingOfficeBasedPaymentBranchView> list = getDataList(startDate, endDate, edareCode == null ? "-1" : edareCode, brchCode == null ? "-1" : brchCode);
        try {
            map = service.getBranchByDateRange(startDate, endDate, edareCode == null ? "-1" : edareCode, brchCode == null ? "-1" : brchCode);
            if (map != null) {
                list = getDataList(startDate, endDate, edareCode == null ? "-1" : edareCode, brchCode == null ? "-1" : brchCode);
            }
        } catch (Exception eRole) {
            throw new IllegalArgumentException("data is wrong");
        }
        List<List<String>> rowsForExcel = prepareDataForExcel(list);
        ExcelReportHeader reportHeader = createReportHeaderDetails();
        return excelDataConverter.provideExcelFrom(reportHeader, rowsForExcel);
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("دفتر اعلامیه های ذیحسابی - شعبه-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }

    @Inject
    private ExcelDataProvider excelDataConverter;

    private List<List<String>> prepareDataForExcel(List<ReportingOfficeBasedPaymentBranchView> announcementBranchList) {
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
        for (ReportingOfficeBasedPaymentBranchView branchView : announcementBranchList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == announcementBranchList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(branchView.getEdareCode() != null ? branchView.getEdareCode() : "-");
            row.add(branchView.getEdareName() != null ? branchView.getEdareName() : "-");
            row.add(branchView.getBrchCode() != null ? branchView.getBrchCode() : "-");
            row.add(branchView.getBrchName() != null ? branchView.getBrchName() : "-");
            row.add(branchView.getOrdDate1() != null ? branchView.getOrdDate1() : "-");
            row.add(branchView.getOrdDate2() != null ? branchView.getOrdDate2() : "-");
//            row.add(branchView.getPayKind() != null ? branchView.getPayKind() : "-");
//            row.add(branchView.getPayKindDSC() != null ? branchView.getPayKindDSC() : "-");
            row.add(branchView.getSumAmount() != null ? branchView.getSumAmount().toString() : "-");
            row.add(branchView.getCountOrderNumber() != null ? branchView.getCountOrderNumber().toString() : "-");

            rows.add(row);
            rowCounter++;
        }
        return rows;
    }


    private List<ReportingOfficeBasedPaymentBranchView> getDataList(String startDate, String endDate, String edareCode, String brchCode) {
        return (List<ReportingOfficeBasedPaymentBranchView>) service.getBranchByDateRange(startDate, endDate, edareCode, brchCode).get("data");

    }
}
