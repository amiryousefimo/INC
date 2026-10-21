package ir.tamin.incomeBank.service.saba.efDateEdkol;

import ir.tamin.incomeBank.dao.saba.efDateEdkol.SabaEfDateEdkolDAOService;
import ir.tamin.incomeBank.model.saba.efDateEdkol.SabaEfDateEdkolDTO;
import ir.tamin.incomeBank.model.saba.efDateEdkol.SabaEfDateEdkolSummary;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import org.apache.poi.ss.usermodel.Workbook;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class SabaEfDateEdkolParamService {

    @Inject
    private SabaEfDateEdkolDAOService daoService;


    @Inject
    private ExcelDataProvider excelDataConverter;


    /**************Get List Of SabaEfDate-Edare's DTO***************/

    public Map<String, Object> getSetadByDateRange(String startDate, String endDate, String edareCode) {
        return daoService.getByDateRange(startDate, endDate, edareCode );
    }

    /**************Prepare Data For Excel WITH PARAMS***************/
    private List<List<String>> prepareDataForExcel(List<SabaEfDateEdkolDTO> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "تاریخ موثر از",
                "تاریخ موثر تا",
                "مبلغ وصول",
                "تعداد وصول"
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

    /************** Create Header ***************/
    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("کارت بانک سبا – ادارات کل  -");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }

    /************** Load Excel Method To Invoke By REST ***************/
    public StreamingOutput loadExcel(String startDate, String endDate, String edareCode) throws Exception {
        List<SabaEfDateEdkolDTO> dtoList = null;
        SabaEfDateEdkolSummary summary = null;

        try {
            dtoList = (List<SabaEfDateEdkolDTO>) this.getSetadByDateRange(startDate, endDate, edareCode ).get("data");
        } catch (Exception ex) {
            throw ex;
        }

        summary = (SabaEfDateEdkolSummary) daoService.getByDateRange(startDate, endDate, edareCode ).get("sum");
        SabaEfDateEdkolDTO summery = new SabaEfDateEdkolDTO();
        summery.setSoratPrice(summary.getSumSoratPrice());
        summery.setCountPrice(summary.getSumCountPrice());


        dtoList.add(summery);

        List<List<String>> rowsForExcel = prepareDataForExcel(dtoList);
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
