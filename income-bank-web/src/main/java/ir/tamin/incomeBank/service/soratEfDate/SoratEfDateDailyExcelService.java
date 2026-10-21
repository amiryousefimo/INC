package ir.tamin.incomeBank.service.soratEfDate;

import ir.tamin.incomeBank.dao.soratEfDate.SoratEfDateDailyDAOService;
import ir.tamin.incomeBank.model.soratEfDate.SoratEfDateDailyDTO;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import org.apache.poi.ss.usermodel.Workbook;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class SoratEfDateDailyExcelService {
    @Inject
    SoratEfDateDailyDAOService service;

    public Workbook loadExcel(String startDate, String endDate) {

        Map<String, Object> map = null;
        List<SoratEfDateDailyDTO> list = getDataList(startDate, endDate);
        try {
            map = service.getByDateRange(startDate, endDate);
            if (map != null) {
                list = getDataList(startDate, endDate);
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
        reportHeader.setTitle("گزارش وصول – تاریخ موثر -");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }

    @Inject
    private ExcelDataProvider excelDataConverter;

    private List<List<String>> prepareDataForExcel(List<SoratEfDateDailyDTO> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "شرح وصول",
                "تاریخ وصول از",
                "تاریخ وصول تا",
                "مبلغ وصول",
                "تعداد وصول"
        ));
        int rowCounter = 1;
        for (SoratEfDateDailyDTO dto : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dto.getSoratDesc() != null ? dto.getSoratDesc() : "-");
            row.add(dto.getSoratDate1() != null ? dto.getSoratDate1() : "-");
            row.add(dto.getSoratDate2() != null ? dto.getSoratDate2() : "-");
            row.add(dto.getSoratPrice() != null ? dto.getSoratPrice().toString() : "-");
            row.add(dto.getCountPrice() != null ? dto.getCountPrice().toString() : "-");

            rows.add(row);
            rowCounter++;
        }
        return rows;
    }


    private List<SoratEfDateDailyDTO> getDataList(String startDate, String endDate) {
        return (List<SoratEfDateDailyDTO>) service.getByDateRange(startDate, endDate).get("data");

    }
}
