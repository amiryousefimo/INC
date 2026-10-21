package ir.tamin.incomeBank.service.smartListBank.effectiveDate;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.smartListBank.effectiveDate.SmartListBankEffectiveDateDAOService;
import ir.tamin.incomeBank.model.smartListBank.effectiveDate.SmartListBankEffectiveDate;
import ir.tamin.incomeBank.model.smartListBank.effectiveDate.SmartListBankEffectiveDateSummary;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
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
public class SmartListBankEffectiveDateService {

    @Inject
    private SmartListBankEffectiveDateDAOService daoService;

    @Inject
    private ExcelDataProvider excelDataConverter;


    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", daoService.search(filterWrapper, start, limit, sort));
            map.put("total", daoService.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(SmartListBankEffectiveDateService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }

    public SmartListBankEffectiveDateSummary getSummery(FilterWrapper filterWrapper) throws Exception {
        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(SmartListBankEffectiveDateService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {
        List<SmartListBankEffectiveDate> listBankEffectiveDates = null;
        SmartListBankEffectiveDateSummary smartListBankEffectiveDateSummary = null;

        try {
            listBankEffectiveDates = (List<SmartListBankEffectiveDate>) this.getAll(filterWrapper, 0, Integer.MAX_VALUE, null).get("list");
        } catch (Exception ex) {
            throw ex;
        }

        smartListBankEffectiveDateSummary = daoService.getSummaryPrice(filterWrapper);
        SmartListBankEffectiveDate summery = new SmartListBankEffectiveDate();
        summery.setPriceEfDate(smartListBankEffectiveDateSummary.getSumPriceEfDate());
        summery.setCntEfDate(smartListBankEffectiveDateSummary.getSumCntEfDate());
        summery.setPriceDate(smartListBankEffectiveDateSummary.getSumPriceDate());
        summery.setCntDate(smartListBankEffectiveDateSummary.getSumCntDate());

        listBankEffectiveDates.add(summery);

        List<List<String>> rowsForExcel = prepareDataForExcel(listBankEffectiveDates);
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

    private List<List<String>> prepareDataForExcel(List<SmartListBankEffectiveDate> listBankEffectiveDates) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "تاریخ ",
                "مبلغ در تاريخ وصول",
                "تعداد در تاريخ وصول",
                "مبلغ در تاریخ موثر ",
                "تعداد در تاریخ موثر"
        ));
        int rowCounter = 1;
        for (SmartListBankEffectiveDate smartListBankEffectiveDate : listBankEffectiveDates) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == listBankEffectiveDates.size() ? "مجموع" : String.valueOf(rowCounter));
            row.add(smartListBankEffectiveDate.getSoratDate() != null ? smartListBankEffectiveDate.getSoratDate() : "-");
            row.add(smartListBankEffectiveDate.getPriceDate() != null ? smartListBankEffectiveDate.getPriceDate().toString() : "-");
            row.add(smartListBankEffectiveDate.getCntDate() != null ? smartListBankEffectiveDate.getCntDate().toString() : "-");
            row.add(smartListBankEffectiveDate.getPriceEfDate() != null ? smartListBankEffectiveDate.getPriceEfDate().toString() : "-");
            row.add(smartListBankEffectiveDate.getCntEfDate() != null ? smartListBankEffectiveDate.getCntEfDate().toString() : "-");
            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("کارت بانک لیست هوشمند – تاریخ وصول و موثر -");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }
}
