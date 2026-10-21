package ir.tamin.incomeBank.service.payment3070.effective;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.payment3070.effective.Effective3070DAOService;
import ir.tamin.incomeBank.model.payment3070.effective.Effective3070;
import ir.tamin.incomeBank.model.payment3070.effective.Effective3070Summary;
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
 * @author a_jamali
 */
public class Effective3070Service {

    @Inject
    private Effective3070DAOService daoService;

    @Inject
    private ExcelDataProvider excelDataConverter;

    @Inject
    private FilterAndUserRoleValidator filterValidator;


    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        filterValidator.filterIsValid(filterWrapper);
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", daoService.search(filterWrapper, start, limit, sort));
            map.put("total", daoService.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(Effective3070Service.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }

    public Effective3070Summary getSummery(FilterWrapper filterWrapper) throws Exception {
        filterValidator.filterIsValid(filterWrapper);
        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(Effective3070Service.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {
        filterValidator.filterIsValid(filterWrapper);
        List<Effective3070> list = null;
        Effective3070Summary summary = null;

        try {
            list = (List<Effective3070>) this.getAll(filterWrapper, 0, Integer.MAX_VALUE, null).get("list");
        } catch (Exception ex) {
            throw ex;
        }

        summary = daoService.getSummaryPrice(filterWrapper);
        Effective3070 summery = new Effective3070();
        summery.setPriceDate(summary.getPriceDate());
        summery.setPriceEfDate(summary.getPriceEfDate());
        summery.setCountDate(summary.getCountDate());
        summery.setCountEfDate(summary.getCountEfDate());
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

    private List<List<String>> prepareDataForExcel(List<Effective3070> list) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "تاريخ",
                "مبلغ مانده",
                "تعداد (واریز و برداشت)",
                "مبلغ واریز (وصول)",
                "مبلغ برداشت",
                "مبلغ موثر (بین راهی)",
                "تعداد موثر (بین راهی)"
        ));
        int rowCounter = 1;
        for (Effective3070 entity : list) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == list.size() ? "مجموع" : String.valueOf(rowCounter));
            row.add(entity.getSoratDate() != null ? entity.getSoratDate() : "-");
            row.add(entity.getPriceDate() != null ? entity.getPriceDate().toString() : "-");
            row.add(entity.getCountDate() != null ? entity.getCountDate().toString() : "-");
            row.add(entity.getSoratPrice1() != null ? entity.getSoratPrice1().toString() : "-");
            row.add(entity.getSoratPrice2() != null ? entity.getSoratPrice2().toString() : "-");
            row.add(entity.getPriceEfDate() != null ? entity.getPriceEfDate().toString() : "-");
            row.add(entity.getCountEfDate() != null ? entity.getCountEfDate().toString() : "-");
            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("کارت بانک 3070 – تاریخ وصول و موثر ");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }
}
