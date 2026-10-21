package ir.tamin.incomeBank.service.contradictionReport;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.contradictionReport.ContradictionReport1DAOService;
import ir.tamin.incomeBank.dao.contradictionReport.ContradictionReport4DAOService;
import ir.tamin.incomeBank.model.contradictionReport.ContradictionReport1;
import ir.tamin.incomeBank.model.contradictionReport.ContradictionReport4;
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

public class ContradictionReport4Service {

    @Inject
    private ContradictionReport4DAOService daoService;

    @Inject
    private ExcelDataProvider excelDataConverter;

    public Map<String, Object> getAll (FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) {

        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", daoService.search(filterWrapper, start, limit, sort));
            map.put("total", daoService.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(ContradictionReport4Service.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }


    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {

        List<ContradictionReport4> contradictionReport4List = null;

        try {
            contradictionReport4List = (List<ContradictionReport4>) this.getAll(filterWrapper, 0, Integer.MAX_VALUE, null).get("list");
        } catch (Exception ex) {
            throw ex;
        }

        List<List<String>> rowsForExcel = prepareDataForExcel(contradictionReport4List);
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

    private List<List<String>> prepareDataForExcel(List<ContradictionReport4> contradictionReport4List) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "کد شعبه",
                "نام شعبه",
                "شماره برگه پرداخت",
                "تاریخ",
                "مبلغ",
                "کد کارگاه",
                "نام کارگاه",
                "کد بانک",
                "وضعیت استرداد"
        ));
        int rowCounter = 1;
        for (ContradictionReport4 contradictionReport4 : contradictionReport4List) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(rowCounter));
            row.add(contradictionReport4.getEdareCode() != null ? contradictionReport4.getEdareCode() : "-");
            row.add(contradictionReport4.getEdareName() != null ? contradictionReport4.getEdareName() : "-");
            row.add(contradictionReport4.getBranchCode() != null ? contradictionReport4.getBranchCode() : "-");
            row.add(contradictionReport4.getBranchName() != null ? contradictionReport4.getBranchName() : "-");
            row.add(contradictionReport4.getOrderNo() != null ? contradictionReport4.getOrderNo() : "-");
            row.add(contradictionReport4.getBillDate() != null ? DateUtils.stringDateToStringSlashedDate(contradictionReport4.getBillDate()) : "-");
            row.add(contradictionReport4.getBillAmount() != null ? contradictionReport4.getBillAmount().toString() : "-");
            row.add(contradictionReport4.getWorkshopId() != null ? contradictionReport4.getWorkshopId() : "-");
            row.add(contradictionReport4.getWorkshopName() != null ? contradictionReport4.getWorkshopName() : "-");
            row.add(contradictionReport4.getBillBank() != null ? contradictionReport4.getBillBank() : "-");
            row.add(contradictionReport4.getStatDesc() != null ? contradictionReport4.getStatDesc() : "-");
            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش واریزی های تکراری و استرداد به کارفرما ");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }
}
