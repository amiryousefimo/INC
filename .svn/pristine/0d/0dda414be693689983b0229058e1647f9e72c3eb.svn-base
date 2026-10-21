package ir.tamin.incomeBank.service.receiptManagement.branch;


import ir.tamin.incomeBank.dao.receiptManagement.branch.ReceiptManagementBranchDAO;
import ir.tamin.incomeBank.dto.receiptManagement.branch.ReceiptManagementBranchDto;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import org.apache.poi.ss.usermodel.Workbook;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

/**
 * @author a_jamali
 */
@Stateless
public class ExcelServiceForReceiptManagementBranch {
    @Inject
    private ReceiptManagementBranchDAO service;

    @Inject
    private ExcelDataProvider excelDataConverter;


    /**
     *
     * @param startDate given the date from user for AFTER operator
     * @param endDate given the date from user for BEFORE operator
     * @param edareCode give the Branch Code From User
     * @param brchCode give the Branch Code From User
     * @return a new excel report of a dto list
     */
    public Workbook loadExcel(String startDate, String endDate, String edareCode, String brchCode) {

        Map<String, Object> map = null;
        List<ReceiptManagementBranchDto> list = getDataList(startDate, endDate, edareCode, brchCode);
//        try {
//            map = service.getBranchByDateRange(startDate, endDate, edareCode, brchCode);
//            if (map != null) {
//                list = getDataList(startDate, endDate, edareCode, brchCode);
//            }
//        } catch (Exception eRole) {
//            throw new IllegalArgumentException("data is wrong");
//        }
        List<List<String>> rowsForExcel = prepareDataForExcel(list);
        ExcelReportHeader reportHeader = createReportHeaderDetails();
        return excelDataConverter.provideExcelFrom(reportHeader, rowsForExcel);
    }

    /**
     *
     * @return A header for excel report
     */
    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش وصول – شعب-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }



    /**
     *
     * @param dtoList for filling fields and cells for excel report
     * @return provide cells with exact name for object variables
     */
    private List<List<String>> prepareDataForExcel(List<ReceiptManagementBranchDto> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "کد شعبه",
                "نام شعبه",
                "تاریخ از",
                "تاریخ تا",
                "کارت بانک سیستم سبا",
                "دفتر لیست هوشمند",
                "دفتر اعلامیه کارکنان دولت",
                "دفتر اعلامیه واریز نقدی",
                "اعلامیه اوراق خزانه",
                "دفتر 3070",
                "جمع مبالغ وصول"
        ));
        int rowCounter = 1;
        for (ReceiptManagementBranchDto dto : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dto.getEdareCode() != null ? dto.getEdareCode() : "-");
            row.add(dto.getEdareName() != null ? dto.getEdareName() : "-");
            row.add(dto.getBrchCode() != null ? dto.getBrchCode() : "-");
            row.add(dto.getBrchName() != null ? dto.getBrchName() : "-");
            row.add(dto.getSoratDate() != null ? dto.getSoratDate() : "-");
            row.add(dto.getSoratDate2() != null ? dto.getSoratDate2() : "-");
            row.add(dto.getSabaPrc() != null ? dto.getSabaPrc().toString() : "-");
            row.add(dto.getIntljPrc() != null ? dto.getIntljPrc().toString() : "-");
            row.add(dto.getKarkonanPrc() != null ? dto.getKarkonanPrc().toString() : "-");
            row.add(dto.getNaghdiPrc() != null ? dto.getNaghdiPrc().toString() : "-");
            row.add(dto.getOraghPrc() != null ? dto.getOraghPrc().toString() : "-");
            row.add(dto.getPrc3070() != null ? dto.getPrc3070().toString() : "-");
            row.add(dto.getSumPrc() != null ? dto.getSumPrc().toString() : "-");

            rows.add(row);
            rowCounter++;
        }
        return rows;
    }


    /**
     *
     * @param startDate given the date from user for AFTER operator
     * @param endDate given the date from user for BEFORE operator
     * @param edareCode give the Branch Code From User
     * @param brchCode give the Branch Code From User
     * @return handle casting list
     */

    private List<ReceiptManagementBranchDto> getDataList(String startDate, String endDate, String edareCode, String brchCode) {
        return (List<ReceiptManagementBranchDto>) service.getBranchByDateRange(startDate, endDate, edareCode, brchCode).get("data");

    }
}
