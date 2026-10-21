package ir.tamin.incomeBank.service.specialInsuredsReport;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.specialInsuredsReport.SpecialInsuredsReportDAO;
import ir.tamin.incomeBank.dto.specialInsuredsReport.SpecialInsuredsReportDto;
import ir.tamin.incomeBank.dto.specialInsuredsReport.SpecialInsuredsReportSummary;
import ir.tamin.incomeBank.mapper.specialInsuredsReport.SpecialInsuredsReportMapper;
import ir.tamin.incomeBank.model.specialInsuredsReport.SpecialInsuredsReport;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import ir.tamin.incomeBank.util.FilterAndUserRoleValidator;
import ir.tamin.incomeBank.ws.rest.receipt.ReceiptPaperRestService;
import org.apache.poi.ss.usermodel.Workbook;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author m_goharriz
 */
@Stateless
public class SpecialInsuredsReportService {

    @Inject
    private SpecialInsuredsReportDAO daoService;

    /*
        @Inject
        private EntityManager em;
    */
    @Inject
    private ExcelDataProvider excelDataProvider;

    @Inject
    private FilterAndUserRoleValidator userRoleValidator;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        Map<String, Object> map = new HashMap<>();
        List<SpecialInsuredsReport> entityList = daoService.search(filterWrapper, start, limit, sort);
        List<SpecialInsuredsReportDto> dtoList = SpecialInsuredsReportMapper.mapToDtoList(entityList);
        try {
            map.put("list", dtoList);
            map.put("total", daoService.getCount(filterWrapper));
            map.put("sum", getSummary(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(ReceiptPaperRestService.class.getName()).log(Level.SEVERE, null, ex);//TODO----------
            throw ex;
        }
        return map;
    }


    public SpecialInsuredsReportSummary getSummary(FilterWrapper filterWrapper) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(ReceiptPaperRestService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }


    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);

        List<SpecialInsuredsReport> entityList = null;

        try {
            entityList = daoService.search(filterWrapper, 0, Integer.MAX_VALUE, null);
        } catch (Exception ex) {
            throw ex;
        }

        SpecialInsuredsReportSummary summaryPrice = daoService.getSummaryPrice(filterWrapper);
        SpecialInsuredsReport entity = new SpecialInsuredsReport();

        entity.setSoratPrice1(summaryPrice.getSoratPrice());
        entityList.add(entity);

        List<List<String>> rowsForExcel = prepareDataForExcel(entityList);
        ExcelReportHeader reportHeader = createReportHeaderDetails();

        StreamingOutput streamingOutPut = new StreamingOutput() {
            final Workbook workbook = excelDataProvider.provideExcelFrom(reportHeader, rowsForExcel);

            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException {
                workbook.write(output);
            }
        };

        return streamingOutPut;
    }

    private List<List<String>> prepareDataForExcel(List<SpecialInsuredsReport> list) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "تاریخ وصول",
                "شماره برگه پرداخت",
                "شناسه پرداخت",
                "مبلغ وصول",
                "نام سیستم"
        ));
        int rowCounter = 1;
        for (SpecialInsuredsReport entity : list) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == list.size()  ? "مجموع" : String.valueOf(rowCounter));
            row.add(entity.getSoratDate() != null ? entity.getSoratDate() : "-");
            row.add(entity.getSoratPaymentSeq() != null ? entity.getSoratPaymentSeq().toString() : "-");
            row.add(entity.getSoratOrdPayment() != null ? entity.getSoratOrdPayment() : "-");
            row.add(entity.getSoratPrice1() != null ? entity.getSoratPrice1().toString() : "-");
            row.add(entity.getSystemDesc() != null ? entity.getSystemDesc().toString() : "-");

            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش واریزی های ناشناس");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }
}
