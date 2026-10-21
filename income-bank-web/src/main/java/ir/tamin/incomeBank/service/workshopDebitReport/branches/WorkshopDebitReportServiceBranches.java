package ir.tamin.incomeBank.service.workshopDebitReport.branches;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.workshopDebitReport.branches.WorkshopDebitReportDAOServiceBranches;
import ir.tamin.incomeBank.model.workshopDebitReport.branches.WorkshopDebitReportBranches;
import ir.tamin.incomeBank.model.workshopDebitReport.branches.WorkshopDebitReportSummeryBranches;
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
public class WorkshopDebitReportServiceBranches {

    @Inject
    private WorkshopDebitReportDAOServiceBranches workshopDAOServiceBranches;

    @Inject
    private ExcelDataProvider excelDataConverter;
    @Inject
    private FilterAndUserRoleValidator filterValidator;


    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        filterValidator.filterIsValid(filterWrapper);
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", workshopDAOServiceBranches.search(filterWrapper, start, limit, sort));
            map.put("total", workshopDAOServiceBranches.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(FinancialDocService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }

    public WorkshopDebitReportSummeryBranches getSummery(FilterWrapper filterWrapper) throws Exception {
        filterValidator.filterIsValid(filterWrapper);
        try {
            return workshopDAOServiceBranches.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(FinancialDocService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {
        filterValidator.filterIsValid(filterWrapper);
        List<WorkshopDebitReportBranches> workshopDebitReportListBranches = null;
        WorkshopDebitReportSummeryBranches workshopDebitReportSummeryBranches = null;

        try {
            workshopDebitReportListBranches = (List<WorkshopDebitReportBranches>) this.getAll(filterWrapper, 0, Integer.MAX_VALUE, null).get("list");
        } catch (Exception ex) {
            throw ex;
        }

        workshopDebitReportSummeryBranches = workshopDAOServiceBranches.getSummaryPrice(filterWrapper);
        WorkshopDebitReportBranches summery = new WorkshopDebitReportBranches();
        summery.setAmountBed(workshopDebitReportSummeryBranches.getSumAmountBed());
        summery.setAmountBes(workshopDebitReportSummeryBranches.getSumAmountBes());

        workshopDebitReportListBranches.add(summery);

        List<List<String>> rowsForExcel = prepareDataForExcel(workshopDebitReportListBranches);
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

    private List<List<String>> prepareDataForExcel(List<WorkshopDebitReportBranches> workshopDebitReportListBranches) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "اداره کل",
                "شعبه",
                "نام کارگاه",
                "شماره برگه پرداخت",
                "تاریخ ایجاد بدهی",
                "مبلغ بدهی ایجادی",
                "تاریخ وصول بدهی",
                "کل وصولی"
        ));
        int rowCounter = 1;
        for (WorkshopDebitReportBranches workshopDebitReportBranches : workshopDebitReportListBranches) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == workshopDebitReportListBranches.size() ? "مجموع" : String.valueOf(rowCounter));
            row.add(workshopDebitReportBranches.getEdareCode() != null ? workshopDebitReportBranches.getEdareName()+"-"+workshopDebitReportBranches.getEdareCode() : "-");
            row.add(workshopDebitReportBranches.getBrchCode() != null ? workshopDebitReportBranches.getBrchName() : "-");
            row.add(workshopDebitReportBranches.getWshName() != null ? workshopDebitReportBranches.getWshName() : "-");
            row.add(workshopDebitReportBranches.getOrdOrdNum() != null ? workshopDebitReportBranches.getOrdOrdNum() : "-");
            row.add(workshopDebitReportBranches.getOrderDate1() != null ? workshopDebitReportBranches.getOrderDate1() : "-");
//            row.add(workshopDebitReportBranches.getAmountBed() != null ? workshopDebitReportBranches.getAmountBed().toString().replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") : "-");
            row.add(workshopDebitReportBranches.getAmountBed() != null ? workshopDebitReportBranches.getAmountBed().toString() : "-");
            row.add(workshopDebitReportBranches.getOrderDate2() != null ? workshopDebitReportBranches.getOrderDate2() : "-");
            row.add(workshopDebitReportBranches.getAmountBes() != null ? workshopDebitReportBranches.getAmountBes().toString(): "-");
            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش در سطح شعبه-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }
}
