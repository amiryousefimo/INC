package ir.tamin.incomeBank.service.reportingOfficeBasedPayment.department;


import ir.tamin.incomeBank.dao.reportingOfficeBasedPayment.department.ReportingOfficeBasedPaymentDepartmentDAOService;
import ir.tamin.incomeBank.model.reportingOfficeBasedPayment.department.ReportingOfficeBasedPaymentDepartmentView;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import org.apache.poi.ss.usermodel.Workbook;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class ReportingOfficeBasedPaymentDepartmentWithParamServiceExcelProviderWithParam {
    @Inject
    ReportingOfficeBasedPaymentDepartmentDAOService service;

    public Workbook loadExcel(String startDate, String endDate, String edareCode) {

        Map<String, Object> map = null;
        List<ReportingOfficeBasedPaymentDepartmentView> list = getDataList(startDate, endDate, edareCode == null ? "-1" : edareCode);
        try {
            map = service.getBranchByDateRange(startDate, endDate, edareCode == null ? "-1" : edareCode);
            if (map != null) {
                list = getDataList(startDate, endDate, edareCode == null ? "-1" : edareCode);
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

    private List<List<String>> prepareDataForExcel(List<ReportingOfficeBasedPaymentDepartmentView> announcementBranchList) {
        //  cell8.setCellValue("شرح موضوع پرداخت  ");
        //            Cell cell9 = firstRow.createCell(5);
        //            cell9.setCellStyle(style);
        //            cell9.setCellValue("مبلغ وصول  ");
        //            Cell cell10 = firstRow.createCell(6);
        //            cell10.setCellStyle(style);
        //            cell10.setCellValue("تعداد وصول  ");
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
        for (ReportingOfficeBasedPaymentDepartmentView branchView : announcementBranchList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == announcementBranchList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
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


    private List<ReportingOfficeBasedPaymentDepartmentView> getDataList(String startDate, String endDate, String edareCode) {
        return (List<ReportingOfficeBasedPaymentDepartmentView>) service.getBranchByDateRange(startDate, endDate, edareCode).get("data");

    }
}
