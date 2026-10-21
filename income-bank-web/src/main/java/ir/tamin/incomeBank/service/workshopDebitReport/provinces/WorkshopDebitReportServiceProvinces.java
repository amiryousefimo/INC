package ir.tamin.incomeBank.service.workshopDebitReport.provinces;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.workshopDebitReport.provinces.WorkshopDebitReportDAOServiceProvinces;
import ir.tamin.incomeBank.model.workshopDebitReport.provinces.WorkshopDebitReportProvinces;
import ir.tamin.incomeBank.model.workshopDebitReport.provinces.WorkshopDebitReportSummeryProvinces;
import ir.tamin.incomeBank.service.financialDoc.FinancialDocService;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import ir.tamin.incomeBank.util.FilterAndUserRoleValidator;
import org.apache.poi.ss.usermodel.Workbook;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author A_Jamali
 */

public class WorkshopDebitReportServiceProvinces {

    @Inject
    private WorkshopDebitReportDAOServiceProvinces DAOService;

    @Inject
    private ExcelDataProvider excelDataConverter;

    @Inject
    FilterAndUserRoleValidator filterValidator;


    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        filterValidator.filterIsValid(filterWrapper);
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", DAOService.search(filterWrapper, start, limit, sort));
            map.put("total", DAOService.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(FinancialDocService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }

    public WorkshopDebitReportSummeryProvinces getSummery(FilterWrapper filterWrapper) throws Exception {
        filterValidator.filterIsValid(filterWrapper);
        try {
            return DAOService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(FinancialDocService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {
        filterValidator.filterIsValid(filterWrapper);
        List<WorkshopDebitReportProvinces> workshopDebitReportListProvinces = null;
        WorkshopDebitReportSummeryProvinces workshopDebitReportSummeryProvinces = null;

        try {
            workshopDebitReportListProvinces = (List<WorkshopDebitReportProvinces>) this.getAll(filterWrapper, 0, Integer.MAX_VALUE, null).get("list");
        } catch (Exception ex) {
            throw ex;
        }

        workshopDebitReportSummeryProvinces = DAOService.getSummaryPrice(filterWrapper);
        WorkshopDebitReportProvinces summery = new WorkshopDebitReportProvinces();
        summery.setAmountBed(workshopDebitReportSummeryProvinces.getSumAmountBed());
        summery.setAmountBes(workshopDebitReportSummeryProvinces.getSumAmountBes());
        summery.setRemainAmount(workshopDebitReportSummeryProvinces.getSumRemainAmount());

        workshopDebitReportListProvinces.add(summery);

        List<List<String>> rowsForExcel = prepareDataForExcel(workshopDebitReportListProvinces);
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

    private List<List<String>> prepareDataForExcel(List<WorkshopDebitReportProvinces> workshopDebitReportListProvinces) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "اداره کل",
                "شعبه",
                "تعداد کارگاه",
                "مبلغ بدهی ایجادی",
                "کل وصولی",
                "مبلغ مانده بدهي"
        ));
        int rowCounter = 1;
        for (WorkshopDebitReportProvinces workshopDebitReportProvinces : workshopDebitReportListProvinces) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == workshopDebitReportListProvinces.size() ? "مجموع" : String.valueOf(rowCounter));
            row.add(workshopDebitReportProvinces.getEdareCode() != null ? workshopDebitReportProvinces.getEdareName()+"-"+ workshopDebitReportProvinces.getEdareCode() : "-");
            row.add(workshopDebitReportProvinces.getBrchCode() != null ? workshopDebitReportProvinces.getBrchName() : "-");
//            row.add(workshopDebitReportProvinces.getAmountBed() != null ? workshopDebitReportProvinces.getAmountBed().toString().replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") : "-");
            row.add(workshopDebitReportProvinces.getCountWSH() != null ? workshopDebitReportProvinces.getCountWSH().toString() : "-");
            row.add(workshopDebitReportProvinces.getAmountBed() != null ? workshopDebitReportProvinces.getAmountBed().toString() : "-");
            row.add(workshopDebitReportProvinces.getAmountBes() != null ? workshopDebitReportProvinces.getAmountBes().toString() : "-");
            row.add(workshopDebitReportProvinces.getRemainAmount() != null ? workshopDebitReportProvinces.getRemainAmount().toString(): "-");
            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش در سطح استان-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }
}
