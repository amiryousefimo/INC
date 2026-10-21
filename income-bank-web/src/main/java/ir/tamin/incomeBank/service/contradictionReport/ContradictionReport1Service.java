package ir.tamin.incomeBank.service.contradictionReport;

import ir.tamin.framework.cdi.util.MessageBundle;
import ir.tamin.framework.core.util.Bundle;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.contradictionReport.ContradictionReport1DAOService;
import ir.tamin.incomeBank.model.contradictionReport.ContradictionReport1;
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

public class ContradictionReport1Service {

    @Inject
    private ContradictionReport1DAOService daoService;

    @Inject
    private ExcelDataProvider excelDataConverter;


    public Map<String, Object> getAll (FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) {

        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", daoService.search(filterWrapper, start, limit, sort));
            map.put("total", daoService.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(ContradictionReport1Service.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }

    public BigDecimal getSummery (FilterWrapper filterWrapper) {

        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(ContradictionReport1Service.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {

        List<ContradictionReport1> contradictionReport1List = null;

        try {
            contradictionReport1List = (List<ContradictionReport1>) this.getAll(filterWrapper, 0, Integer.MAX_VALUE, null).get("list");
        } catch (Exception ex) {
            throw ex;
        }

        ContradictionReport1 summery = new ContradictionReport1();
        summery.setDaftarAmount(daoService.getSummaryPrice(filterWrapper));

        contradictionReport1List.add(summery);

        List<List<String>> rowsForExcel = prepareDataForExcel(contradictionReport1List);
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

    private List<List<String>> prepareDataForExcel(List<ContradictionReport1> contradictionReport1List) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "سال",
                "ماه",
                "شماره برگه",
                "مبلغ دفتر",
//                "مبلغ صورتحساب",
//                "تفاوت مبلغ",
                "تاریخ دفتر",
                "بانک",
//                "تاریخ صورتحساب",
//                "بانک صورت حساب",
                "کد کارگاه",
                "نام کارگاه",
                "نوع مغایرت"
        ));
        int rowCounter = 1;
        for (ContradictionReport1 contradictionReport1 : contradictionReport1List) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == contradictionReport1List.size() ? "مجموع" : String.valueOf(rowCounter));
            row.add(contradictionReport1.getYear() != null ? contradictionReport1.getYear() : "-");
            row.add(contradictionReport1.getMonth() != null ? contradictionReport1.getMonth() : "-");
            row.add(contradictionReport1.getOrderNo() != null ? contradictionReport1.getOrderNo() : "-");
            row.add(contradictionReport1.getDaftarAmount() != null ? contradictionReport1.getDaftarAmount().toString() : "-");
//            row.add(contradictionReport1.getBillAmount() != null ? contradictionReport1.getBillAmount().toString() : "-");
//            row.add(contradictionReport1.getDiffAmount() != null ? contradictionReport1.getDiffAmount().toString() : "-");
            row.add(contradictionReport1.getDaftarDate() != null ? DateUtils.stringDateToStringSlashedDate(contradictionReport1.getDaftarDate()) : "-");
            row.add(contradictionReport1.getDaftarBank() != null ? contradictionReport1.getDaftarBank() : "-");
//            row.add(contradictionReport1.getBillDate() != null ? DateUtils.stringDateToStringSlashedDate(contradictionReport1.getBillDate()) : "-");
//            row.add(contradictionReport1.getBillBank() != null ? contradictionReport1.getBillBank() : "-");
            row.add(contradictionReport1.getWorkshopId() != null ? contradictionReport1.getWorkshopId() : "-");
            row.add(contradictionReport1.getWorkshopName() != null ? contradictionReport1.getWorkshopName() : "-");
            row.add(translateType(contradictionReport1.getType()));
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

    private String translateType(String type) {
        if (type == null) {
            return "-";
        }
        switch (type) {
            case "1":
                return "دفتر وجود ندارد";
            case "2":
                return "صورتحساب وجود ندارد";
            case "3":
                return "سایر مغایرت ها";
            case "4":
                return "همه";
            default:
                return "-";
        }
    }
}
