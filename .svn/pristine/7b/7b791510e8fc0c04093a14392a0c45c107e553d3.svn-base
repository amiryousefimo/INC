package ir.tamin.incomeBank.service.saba.dateBranch;


import ir.tamin.incomeBank.dao.saba.dateBranch.SabaDateBranchesDAOService;
import ir.tamin.incomeBank.model.saba.dateBranch.SabaDateBranchesDTO;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import org.apache.poi.ss.usermodel.Workbook;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class SabaDateBranchesExcelService {
    @Inject
    private SabaDateBranchesDAOService daoService;

    public Workbook loadExcel(String startDate, String endDate, String edareCode,String brchCode) {

        Map<String, Object> map = null;
        List<SabaDateBranchesDTO> list = getDataList(startDate, endDate, edareCode,brchCode);

        try {
            map = daoService.getByDateRange(startDate, endDate, edareCode,brchCode);
            if (map != null) {
                list = getDataList(startDate, endDate, edareCode,brchCode);
            }
        } catch (Exception eRole) {
            throw new IllegalArgumentException("data is wrong");
        }
        if (list.size() == 0) {
            throw new IllegalArgumentException("مقدار انتخابی خالی است");
        }
        List<List<String>> rowsForExcel = prepareDataForExcel(list);
        ExcelReportHeader reportHeader = createReportHeaderDetails();
        return excelDataConverter.provideExcelFrom(reportHeader, rowsForExcel);
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("کارت بانک سبا – ادارات کل  -");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }

    @Inject
    private ExcelDataProvider excelDataConverter;

    private List<List<String>> prepareDataForExcel(List<SabaDateBranchesDTO> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "کد شعبه",
                "نام شعبه",
                "تاریخ وصول از",
                "تاریخ وصول تا",
                "جمع مبالغ",
                "تعداد اعلامیه"
        ));
        int rowCounter = 1;
        for (SabaDateBranchesDTO dto : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dto.getEdareCode() != null ? dto.getEdareCode() : "-");
            row.add(dto.getEdareName() != null ? dto.getEdareName() : "-");
            row.add(dto.getBrchCode() != null ? dto.getBrchCode() : "-");
            row.add(dto.getBrchName() != null ? dto.getBrchName() : "-");
            row.add(dto.getSoratDate1() != null ? dto.getSoratDate1() : "-");
            row.add(dto.getSoratDate2() != null ? dto.getSoratDate2() : "-");
            row.add(dto.getSoratPrice() != null ? dto.getSoratPrice().toString() : "-");
            row.add(dto.getCountPrice() != null ? dto.getCountPrice().toString() : "-");

            rows.add(row);
            rowCounter++;
        }
        return rows;
    }


    private List<SabaDateBranchesDTO> getDataList(String startDate, String endDate, String edareCode,String brchCode) {
        return (List<SabaDateBranchesDTO>) daoService.getByDateRange(startDate, endDate, edareCode,brchCode).get("data");
    }
}
