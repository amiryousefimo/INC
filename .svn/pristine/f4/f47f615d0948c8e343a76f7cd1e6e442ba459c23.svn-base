package ir.tamin.incomeBank.service.payment3070.total;

import ir.tamin.framework.ws.rest.json.Filter;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.payment3070.total.Total3070DAOService;
import ir.tamin.incomeBank.model.payment3070.daily.Daily3070Summary;
import ir.tamin.incomeBank.model.payment3070.total.Total3070;
import ir.tamin.incomeBank.model.payment3070.total.Total3070Summary;
import ir.tamin.incomeBank.service.jdbc.StoredProcedure;
import ir.tamin.incomeBank.service.payment3070.effective.Effective3070Service;
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
import java.sql.Types;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author m_goharriz
 * Date : 1404.02.10
 */

public class Total3070Service {

    @Inject
    private Total3070DAOService daoService;

    @Inject
    private ExcelDataProvider excelDataConverter;

    @Inject
    private FilterAndUserRoleValidator filterValidator;

    @Inject
    private StoredProcedure procedure;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        filterValidator.filterIsValid(filterWrapper);
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", daoService.search(filterWrapper, start, limit, sort));
            map.put("total", daoService.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(Total3070DAOService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }

    public Total3070Summary getSummery(FilterWrapper filterWrapper) throws Exception {
        filterValidator.filterIsValid(filterWrapper);
        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(Total3070DAOService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {
        filterValidator.filterIsValid(filterWrapper);
        List<Total3070> list = null;
        Total3070Summary summary = null;

        try {
            list = (List<Total3070>) this.getAll(filterWrapper, 0, Integer.MAX_VALUE, null).get("list");
        } catch (Exception ex) {
            throw ex;
        }

        summary = daoService.getSummaryPrice(filterWrapper);
        Total3070 summery = new Total3070();

        summery.setSoratPrice1(summary.getSoratPrice1());
        summery.setSoratPrice2(summary.getSoratPrice2());



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

    private List<List<String>> prepareDataForExcel(List<Total3070> list) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "تاريخ",
                " موجودی ابتدای دوره",
                "مبلغ بدهکار(واریز)",
                "مبلغ بستانکار(برداشت)",
                "مبلغ مانده",
                "تعداد",
                "مبلغ موثر (بین راهی)"
        ));
        int rowCounter = 1;
        for (Total3070 entity : list) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == list.size() ? "مجموع" : String.valueOf(rowCounter));
            row.add(entity.getSoratDate() != null ? entity.getSoratDate() : "-");
            row.add(entity.getSoratAmount() != null ? entity.getSoratAmount().toString(): "-");
            row.add(entity.getSoratPrice1() != null ? entity.getSoratPrice1().toString() : "-");
            row.add(entity.getSoratPrice2() != null ? entity.getSoratPrice2().toString() : "-");
            row.add(entity.getPriceDate() != null ? entity.getPriceDate().toString() : "-");
            row.add(entity.getCountDate() != null ? entity.getCountDate().toString() : "-");
            row.add(entity.getPriceEfDate() != null ? entity.getPriceEfDate().toString() : "-");
            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("کارت بانک 3070 - کلیات ");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }
    public Map<String, Object> financialGetBankInfo(FilterWrapper filterWrapper) throws Exception {

        String startDate = null;
        String endDate = null;
        String bankRadif = "16";

        if (filterWrapper != null && filterWrapper.getFilters() != null) {
            for (Filter filter : filterWrapper.getFilters()) {

                String field = filter.getProperty();
                if (field.contains("soratDate") && filter.getOperator().equals(Filter.Operator.AFTER)) {
                    startDate = filter.getValue();
                }
                if (field.contains("soratDate") && filter.getOperator().equals(Filter.Operator.BEFORE)) {
                    endDate = filter.getValue();
                }
                if (field.contains("bankRadif")) {
                    bankRadif = filter.getValue();
                }
            }

        }
        procedure.query("{?=call pck_telproc_core.getbankinfo(?,?,?,?,?,?)}");
        procedure.setOutParameter(1, Types.FLOAT)
                .setInParameter(2, startDate)
                .setInParameter(3, endDate)
                .setInParameter(4, bankRadif)
                .setOutParameter(5, Types.FLOAT)
                .setOutParameter(6, Types.FLOAT)
                .setOutParameter(7, Types.FLOAT);
        procedure.execute();

        Map<String, Object> result = new HashMap<>();

        result.put("param1", procedure.getOutParameter(1).toString());

        Total3070Summary total3070Summary = getSummery(filterWrapper);
        result.put("soratPrice1", total3070Summary.getSoratPrice1());
        result.put("soratPrice2", total3070Summary.getSoratPrice2());

        return result;
    }

}

