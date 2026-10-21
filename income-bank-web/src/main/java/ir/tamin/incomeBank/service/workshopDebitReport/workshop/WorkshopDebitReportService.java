package ir.tamin.incomeBank.service.workshopDebitReport.workshop;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.workshopDebitReport.workshop.WorkshopDebitReportDAOService;
import ir.tamin.incomeBank.model.workshopDebitReport.workshop.WorkshopDebitReport;
import ir.tamin.incomeBank.model.workshopDebitReport.workshop.WorkshopDebitReportSummery;
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
public class WorkshopDebitReportService {

    @Inject
    private WorkshopDebitReportDAOService workshopDAOService;

    @Inject
    private ExcelDataProvider excelDataConverter;

    @Inject
    FilterAndUserRoleValidator filterValidator;


    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        filterValidator.filterIsValid(filterWrapper);
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", workshopDAOService.search(filterWrapper, start, limit, sort));
            map.put("total", workshopDAOService.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(FinancialDocService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }

    public WorkshopDebitReportSummery getSummery(FilterWrapper filterWrapper) throws Exception {
        filterValidator.filterIsValid(filterWrapper);
        try {
            return workshopDAOService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(FinancialDocService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {
        filterValidator.filterIsValid(filterWrapper);
        List<WorkshopDebitReport> workshopDebitReportList = null;
        WorkshopDebitReportSummery workshopDebitReportSummery = null;

        try {
            workshopDebitReportList = (List<WorkshopDebitReport>) this.getAll(filterWrapper, 0, Integer.MAX_VALUE, null).get("list");
        } catch (Exception ex) {
            throw ex;
        }

        workshopDebitReportSummery = workshopDAOService.getSummaryPrice(filterWrapper);
        WorkshopDebitReport summery = new WorkshopDebitReport();
        summery.setBankAmount(workshopDebitReportSummery.getBankAmount());
        summery.setOrderAmount(workshopDebitReportSummery.getOrderAmount());

        workshopDebitReportList.add(summery);

        List<List<String>> rowsForExcel = prepareDataForExcel(workshopDebitReportList);
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

    private List<List<String>> prepareDataForExcel(List<WorkshopDebitReport> workshopDebitReportList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "شماره برگه پرداخت",
                "مبلغ برگه پرداخت",
                "تاریخ صدور برگه پرداخت",
                "مبلغ وصول برگه پرداخت",
                "تاریخ وصول برگه پرداخت"
        ));
        int rowCounter = 1;
        for (WorkshopDebitReport workshopDebitReport : workshopDebitReportList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == workshopDebitReportList.size() ? "مجموع" : String.valueOf(rowCounter));
            row.add(workshopDebitReport.getOrderNumber() != null ? workshopDebitReport.getOrderNumber() : "-");
            row.add(workshopDebitReport.getOrderAmount() != null ? workshopDebitReport.getOrderAmount().toString() : "-");
            row.add(workshopDebitReport.getOrderDate() != null ? workshopDebitReport.getOrderDate() : "-");
            row.add(workshopDebitReport.getBankAmount() != null ? workshopDebitReport.getBankAmount().toString() : "-");
            row.add(workshopDebitReport.getBankDate() != null ? workshopDebitReport.getBankDate() : "-");
            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش بدهی کارگاه");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }
}
