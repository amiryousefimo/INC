package ir.tamin.incomeBank.service.payment3070.account503branch;


import ir.tamin.incomeBank.dao.payment3070.account503branch.Account503BranchDAO;
import ir.tamin.incomeBank.dto.pey3070.account503branch.Account503BranchDto;
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
public class Account503BranchParamExcelService {
    @Inject
    private Account503BranchDAO service;

    @Inject
    private ExcelDataProvider excelDataConverter;


    /**
     * @param startDate given the date from user for AFTER operator
     * @param endDate   given the date from user for BEFORE operator
     * @param edareCode give the Branch Code From User
     * @param brchCode  give the Branch Code From User
     * @return a new excel report of a dto list
     */
    public Workbook loadExcel(String startDate, String endDate, String edareCode, String brchCode) {

        Map<String, Object> map = null;
        List<Account503BranchDto> list = getDataList(startDate, endDate, edareCode, brchCode);
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
     * @return A header for excel report
     */
    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("دفتر روزانه پروژه 3070 – شعب-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }


    /**
     * @param dtoList for filling fields and cells for excel report
     * @return provide cells with exact name for object variables
     */
    private List<List<String>> prepareDataForExcel(List<Account503BranchDto> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "کد شعبه",
                "نام شعبه",
                "تاریخ وصول از",
                "تاریخ وصول تا",
                "جمع مبالغ وصول",
                "مبلغ حق بیمه مشاغل آزاد",
                "مبلغ حق بیمه اختیاری",
                "مبلغ سربازی",
                " مبلغ کارگران ساختمانی",
                "مبلغ کسر از ماه",
                "تعداد برگه وصول",
                " مبلغ کمک دولت"
        ));
        int rowCounter = 1;
        for (Account503BranchDto dto : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dto.getEdareCode() != null ? dto.getEdareCode() : "-");
            row.add(dto.getEdareName() != null ? dto.getEdareName() : "-");
            row.add(dto.getBrchCode() != null ? dto.getBrchCode() : "-");
            row.add(dto.getBrchName() != null ? dto.getBrchName() : "-");
            row.add(dto.getOrdDate() != null ? dto.getOrdDate() : "-");
            row.add(dto.getOrdDate1() != null ? dto.getOrdDate1() : "-");
            row.add(dto.getSumAmount() != null ? dto.getSumAmount().toString() : "-");
            row.add(dto.getMashAmount() != null ? dto.getMashAmount().toString() : "-");
            row.add(dto.getEkhtAmount() != null ? dto.getEkhtAmount().toString() : "-");
            row.add(dto.getSarbaziAmt() != null ? dto.getSarbaziAmt().toString() : "-");
            row.add(dto.getSakhtemanAmt() != null ? dto.getSakhtemanAmt().toString() : "-");
            row.add(dto.getKasrMahAmt() != null ? dto.getKasrMahAmt().toString() : "-");
            row.add(dto.getCountAmt() != null ? dto.getCountAmt().toString() : "-");
            row.add(dto.getDolatAmt() != null ? dto.getDolatAmt().toString() : "-");

            rows.add(row);
            rowCounter++;
        }
        return rows;
    }


    /**
     * @param startDate given the date from user for AFTER operator
     * @param endDate   given the date from user for BEFORE operator
     * @param edareCode give the Branch Code From User
     * @param brchCode  give the Branch Code From User
     * @return handle casting list
     */

    private List<Account503BranchDto> getDataList(String startDate, String endDate, String edareCode, String brchCode) {
        return (List<Account503BranchDto>) service.getBranchByDateRange(startDate, endDate, edareCode, brchCode).get("data");

    }
}
