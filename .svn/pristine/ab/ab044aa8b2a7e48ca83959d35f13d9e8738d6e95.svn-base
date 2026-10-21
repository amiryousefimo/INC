package ir.tamin.incomeBank.service.saba.efDateEdkol;


import ir.tamin.incomeBank.dao.saba.efDateEdkol.SabaEfDateEdkolDAOService;
import ir.tamin.incomeBank.model.saba.efDateEdkol.SabaEfDateEdkolDTO;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import org.apache.poi.ss.usermodel.Workbook;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class SabaEfDateEdkolExcelService {
    @Inject
    private SabaEfDateEdkolDAOService daoService;

    public Workbook loadExcel(String startDate, String endDate, String edareCode) {

        Map<String, Object> map = null;
        List<SabaEfDateEdkolDTO> list = getDataList(startDate, endDate, edareCode );

        try {
            map = daoService.getByDateRange(startDate, endDate, edareCode);
            if (map != null ) {
                list = getDataList(startDate, endDate, edareCode );
            }
        } catch (Exception eRole) {
            throw new IllegalArgumentException("data is wrong");
        }
        if (list.size()==0){
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

    private List<List<String>> prepareDataForExcel(List<SabaEfDateEdkolDTO> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "تاریخ موثر از",
                "تاریخ موثر تا",
                "جمع مبالغ",
                "تعداد اعلامیه"
        ));
        int rowCounter = 1;
        for (SabaEfDateEdkolDTO dto : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dto.getEdareCode() != null ? dto.getEdareCode() : "-");
            row.add(dto.getEdareName() != null ? dto.getEdareName() : "-");
            row.add(dto.getSoratDate1() != null ? dto.getSoratDate1() : "-");
            row.add(dto.getSoratDate2() != null ? dto.getSoratDate2() : "-");
            row.add(dto.getSoratPrice() != null ? dto.getSoratPrice().toString() : "-");
            row.add(dto.getCountPrice() != null ? dto.getCountPrice().toString() : "-");

            rows.add(row);
            rowCounter++;
        }
        return rows;
    }


    private List<SabaEfDateEdkolDTO> getDataList(String startDate, String endDate, String edareCode) {
        return (List<SabaEfDateEdkolDTO>) daoService.getByDateRange(startDate, endDate, edareCode).get("data");
    }
}
