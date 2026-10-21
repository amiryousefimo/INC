package ir.tamin.incomeBank.service.workshopDebitReport.country;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.workshopDebitReport.country.WorkshopDebitReportDAOServiceCountry;
import ir.tamin.incomeBank.model.workshopDebitReport.country.WorkshopDebitReportCountry;
import ir.tamin.incomeBank.model.workshopDebitReport.country.WorkshopDebitReportSummeryCountry;
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
 * @author  A_Jamali
 */

public class WorkshopDebitReportServiceCountry {

    @Inject
    private WorkshopDebitReportDAOServiceCountry DAOService;

    @Inject
    private ExcelDataProvider excelDataConverter;
    @Inject
    private FilterAndUserRoleValidator filterValidator;



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

    public WorkshopDebitReportSummeryCountry getSummery(FilterWrapper filterWrapper) throws Exception {
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
        List<WorkshopDebitReportCountry> workshopDebitReportListCountry = null;
        WorkshopDebitReportSummeryCountry workshopDebitReportSummeryCountry = null;

        try {
            workshopDebitReportListCountry = (List<WorkshopDebitReportCountry>) this.getAll(filterWrapper, 0, Integer.MAX_VALUE, null).get("list");
        } catch (Exception ex) {
            throw ex;
        }

    workshopDebitReportSummeryCountry = DAOService.getSummaryPrice(filterWrapper);
    WorkshopDebitReportCountry summery = new WorkshopDebitReportCountry();
    summery.setAmountBed(workshopDebitReportSummeryCountry.getSumAmountBed());
    summery.setAmountBes(workshopDebitReportSummeryCountry.getSumAmountBes());
    summery.setRemainAmount(workshopDebitReportSummeryCountry.getSumRemainAmount());

    workshopDebitReportListCountry.add(summery);

    List<List<String>> rowsForExcel = prepareDataForExcel(workshopDebitReportListCountry);
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

    private List<List<String>> prepareDataForExcel(List<WorkshopDebitReportCountry> workshopDebitReportListCountry) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "اداره کل",
                "تعداد کارگاه",
                "مبلغ بدهی ایجادی",
                "کل وصولی",
                "مبلغ مانده بدهي"
        ));
        int rowCounter = 1;
        for (WorkshopDebitReportCountry workshopDebitReportCountry : workshopDebitReportListCountry) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == workshopDebitReportListCountry.size() ? "مجموع" : String.valueOf(rowCounter));
            row.add(workshopDebitReportCountry.getEdareCode() != null ? workshopDebitReportCountry.getEdareName() : "-");
//            row.add(workshopDebitReportCountry.getAmountBed() != null ? workshopDebitReportCountry.getAmountBed().toString().replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") : "-");
            row.add(workshopDebitReportCountry.getCountWSH() != null ? workshopDebitReportCountry.getCountWSH().toString() : "-");
            row.add(workshopDebitReportCountry.getAmountBed() != null ? workshopDebitReportCountry.getAmountBed().toString() : "-");
            row.add(workshopDebitReportCountry.getAmountBes() != null ? workshopDebitReportCountry.getAmountBes().toString() : "-");
            row.add(workshopDebitReportCountry.getRemainAmount() != null ? workshopDebitReportCountry.getRemainAmount().toString(): "-");
            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش در سطح کشور-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }
}
