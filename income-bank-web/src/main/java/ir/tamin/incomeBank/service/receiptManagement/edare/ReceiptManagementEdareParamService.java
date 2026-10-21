package ir.tamin.incomeBank.service.receiptManagement.edare;


import ir.tamin.incomeBank.dao.receiptManagement.edare.ReceiptManagementEdareDAO;
import ir.tamin.incomeBank.dto.receiptManagement.edare.ReceiptManagementEdareDto;
import ir.tamin.incomeBank.dto.receiptManagement.edare.ReceiptManagementEdareSummary;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import ir.tamin.incomeBank.util.FilterAndUserRoleValidator;
import org.apache.poi.ss.usermodel.Workbook;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @author A_Jamali
 */

@Stateless
public class ReceiptManagementEdareParamService {

    @Inject
    private ReceiptManagementEdareDAO daoService;

    @Inject
    private FilterAndUserRoleValidator userRoleValidator;


    @Inject
    private ExcelDataProvider excelDataConverter;

    /**
     *
     * @param startDate given the date from user for AFTER operator
     * @param endDate given the date from user for BEFORE operator
     * @param edareCode give the Branch Code From User
     * @return a map of list of entity , object of summary and size of list as total number of data
     */
    public Map<String, Object> getBranchByDateRange(String startDate, String endDate, String edareCode) {
        return daoService.getEdareByDate(startDate, endDate, edareCode);
    }

    /**
     *
     * @param dtoList for filling fields and cells for excel report
     * @return provide cells with exact name for object variables
     */
    private List<List<String>> prepareDataForExcel(List<ReceiptManagementEdareDto> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "تاریخ از",
                "تاریخ تا",
                "کارت بانک سیستم سبا",
                "دفتر لیست هوشمند",
                "پ دفتر اعلامیه کارکنان دولت",
                "دفتر اعلامیه واریز نقدی",
                "دفتر اعلامیه اوراق خزانه",
                "دفتر 3070",
                "جمع مبالغ وصول"
        ));
        int rowCounter = 1;
        for (ReceiptManagementEdareDto dto : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dto.getEdareCode() != null ? dto.getEdareCode() : "-");
            row.add(dto.getEdareName() != null ? dto.getEdareName() : "-");
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
     * @return A header for excel report
     */
    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش وصول – ادارات کل-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }

    /**
     *
     * @param startDate given the date from user for AFTER operator
     * @param endDate given the date from user for BEFORE operator
     * @param edareCode give the Branch Code From User
     * @return a new excel report of a dto list
     */
    public StreamingOutput loadExcel(String startDate, String endDate, String edareCode) throws Exception {
        List<ReceiptManagementEdareDto> list = null;
        ReceiptManagementEdareSummary summery = null;

        try {
            list = (List<ReceiptManagementEdareDto>) daoService.getEdareByDate(startDate, endDate, edareCode).get("data");
        } catch (Exception ex) {
            throw ex;
        }

        summery = (ReceiptManagementEdareSummary) daoService.getEdareByDate(startDate, endDate, edareCode).get("sum");
        ReceiptManagementEdareDto entity = new ReceiptManagementEdareDto();
        entity.setSabaPrc(summery.getSabaPrc());
        entity.setPrc3070(summery.getPrc3070());
        entity.setKarkonanPrc(summery.getKarkonanPrc());
        entity.setNaghdiPrc(summery.getNaghdiPrc());
        entity.setOraghPrc(summery.getOraghPrc());
        entity.setIntljPrc(summery.getIntljPrc());
        entity.setSumPrc(summery.getSumPrc());

        list.add(entity);

        List<List<String>> rowsForExcel = prepareDataForExcel(list);
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
