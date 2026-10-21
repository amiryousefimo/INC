package ir.tamin.incomeBank.service.payment3070.account503edare;


import ir.tamin.incomeBank.dao.payment3070.account503edare.Account503EdareDAO;

import ir.tamin.incomeBank.dto.pey3070.account503edare.Account503EdareDto;
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
public class Account503EdareParamExcelService {
    @Inject
    private Account503EdareDAO daoService;

    @Inject
    private ExcelDataProvider excelDataConverter;


    /**
     * @param startDate given the date from user for AFTER operator
     * @param endDate   given the date from user for BEFORE operator
     * @param edareCode give the Branch Code From User
     * @return a new excel report of a dto list
     */
    public Workbook loadExcel(String startDate, String endDate, String edareCode) {

        Map<String, Object> map = null;
        List<Account503EdareDto> list = getDataList(startDate, endDate, edareCode);
        try {
            map = daoService.getListByDate(startDate, endDate, edareCode);
            if (map != null) {
                list = getDataList(startDate, endDate, edareCode);
            }
        } catch (Exception eRole) {
            throw new IllegalArgumentException("data is wrong");
        }
        List<List<String>> rowsForExcel = prepareDataForExcel(list);
        ExcelReportHeader reportHeader = createReportHeaderDetails();
        return excelDataConverter.provideExcelFrom(reportHeader, rowsForExcel);
    }

    /**
     * @return A header for excel report
     */
    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("دفتر روزانه پروژه 3070 – ادارات کل-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }


    /**
     * @param dtoList for filling fields and cells for excel report
     * @return provide cells with exact name for object variables
     */
    private List<List<String>> prepareDataForExcel(List<Account503EdareDto> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
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
        for (Account503EdareDto dto : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dto.getEdareCode() != null ? dto.getEdareCode() : "-");
            row.add(dto.getEdareName() != null ? dto.getEdareName() : "-");
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
     * @return handle casting list
     */

    private List<Account503EdareDto> getDataList(String startDate, String endDate, String edareCode) {
        return (List<Account503EdareDto>) daoService.getListByDate(startDate, endDate, edareCode).get("data");

    }
}
