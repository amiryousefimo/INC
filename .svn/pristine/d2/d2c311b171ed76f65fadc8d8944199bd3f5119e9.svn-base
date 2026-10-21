package ir.tamin.incomeBank.service.contradictionReport;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.contradictionReport.ContradictionReport2DAOService;
import ir.tamin.incomeBank.model.contradictionReport.ContradictionReport2;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import org.apache.poi.ss.usermodel.Workbook;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContradictionReport2Service {

    @Inject
    private ContradictionReport2DAOService daoService;

    @Inject
    private ExcelDataProvider excelDataConverter;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) {

        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", daoService.search(filterWrapper, start, limit, sort));
            map.put("total", daoService.getCount(filterWrapper));
            map.put("sum",daoService.getSummaryPrice(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(ContradictionReport2Service.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }

    public BigDecimal getSummery(FilterWrapper filterWrapper) {

        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(ContradictionReport2Service.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {

        List<ContradictionReport2> list = null;

        try {
            list = (List<ContradictionReport2>) this.getAll(filterWrapper, 0, Integer.MAX_VALUE, null).get("list");
        } catch (Exception ex) {
            throw ex;
        }

        ContradictionReport2 summery = new ContradictionReport2();
        summery.setBillAmount(daoService.getSummaryPrice(filterWrapper));

        list.add(summery);

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

    private List<List<String>> prepareDataForExcel(List<ContradictionReport2> contradictionReport1List) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "سال",
                "ماه",
                "شماره برگه",
                "مبلغ وصول",
                "تاریخ وصول",
                "ردیف بانک",
                "نام بانک"
        ));
        int rowCounter = 1;
        for (ContradictionReport2 contradictionReport2 : contradictionReport1List) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == contradictionReport1List.size() ? "مجموع" : String.valueOf(rowCounter));
            row.add(contradictionReport2.getYear() != null ? contradictionReport2.getYear() : "-");
            row.add(contradictionReport2.getMonth() != null ? contradictionReport2.getMonth() : "-");
            row.add(contradictionReport2.getOrderNo() != null ? contradictionReport2.getOrderNo() : "-");
            row.add(contradictionReport2.getBillAmount() != null ? contradictionReport2.getBillAmount().toString() : "-");
            row.add(contradictionReport2.getBillDate() != null ? DateUtils.stringDateToStringSlashedDate(contradictionReport2.getBillDate()) : "-");
            row.add(contradictionReport2.getBillBank() != null ? contradictionReport2.getBillBank() : "-");
            row.add(contradictionReport2.getBankName() != null ? contradictionReport2.getBankName() : "-");
            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش مغایرت بانک و دفتر");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }
}
