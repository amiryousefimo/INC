package ir.tamin.incomeBank.service.smartlist.branch;



import ir.tamin.incomeBank.dao.smartlist.branch.SmartListBranchDao;
import ir.tamin.incomeBank.dto.smartlist.branch.SmartListBranchDto;
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
public class ExcelServiceForSmartListBranch {
    @Inject
    private SmartListBranchDao daoService;

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
        List<SmartListBranchDto> list = getDataList(startDate, endDate, edareCode, brchCode);
        try {
            map = daoService.getBranchByDateRange(startDate, endDate, edareCode, brchCode);
            if (map != null) {
                list = getDataList(startDate, endDate, edareCode, brchCode);
            }
        } catch (Exception eRole) {
            throw new IllegalArgumentException("data is wrong");
        }
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
        reportHeader.setTitle("گزارش وصولی روزانه لیست مشترک – شعبه-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }



    /**
     *
     * @param dtoList for filling fields and cells for excel report
     * @return provide cells with exact name for object variables
     */
    private List<List<String>> prepareDataForExcel(List<SmartListBranchDto> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "کد شعبه",
                "نام شعبه",
                "تاریخ وصول از",
                "تاریخ وصول تا",
                "مبلغ وصول",
                "تعداد بیمه پرداز",
                "مبلغ کمک دولت",
                "تعداد نفرات کمک دولت",
                "تعداد برگه وصول",
                "حققققق بیمه کارگاه های مشمول کمک دولت",
                "حق بیمه کارگاه های غیر مشمول",
                "بیمه بیکاری",
                "جرایم"
        ));
        int rowCounter = 1;
        for (SmartListBranchDto dto : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dto.getEdareCode() != null ? dto.getEdareCode() : "-");
            row.add(dto.getEdareName() != null ? dto.getEdareName() : "-");
            row.add(dto.getBrchCode() != null ? dto.getBrchCode() : "-");
            row.add(dto.getBrchName() != null ? dto.getBrchName() : "-");
            row.add(dto.getOrderDate() != null ? dto.getOrderDate() : "-");
            row.add(dto.getOrderDate2() != null ? dto.getOrderDate2() : "-");
            row.add(dto.getSumAmount() != null ? dto.getSumAmount().toString() : "-");
            row.add(dto.getSumISu() != null ? dto.getSumISu().toString() : "-");
            row.add(dto.getSumAmountGov() != null ? dto.getSumAmountGov().toString() : "-");
            row.add(dto.getSumIsuGov() != null ? dto.getSumIsuGov().toString() : "-");
            row.add(dto.getCountOrdNumber() != null ? dto.getCountOrdNumber().toString() : "-");
            row.add(dto.getHagheBimehGov() != null ? dto.getHagheBimehGov().toString() : "-");
            row.add(dto.getHagheBimeh() != null ? dto.getHagheBimeh().toString() : "-");
            row.add(dto.getBikari() != null ? dto.getBikari().toString() : "-");
            row.add(dto.getJarimeh() != null ? dto.getJarimeh().toString(): "-");

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

    private List<SmartListBranchDto> getDataList(String startDate, String endDate, String edareCode, String brchCode) {
        return (List<SmartListBranchDto>) daoService.getBranchByDateRange(startDate, endDate, edareCode, brchCode).get("data");

    }
}
