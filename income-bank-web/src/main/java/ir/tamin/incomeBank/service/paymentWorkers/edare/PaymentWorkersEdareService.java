package ir.tamin.incomeBank.service.paymentWorkers.edare;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.paymentWorkers.edare.PaymentWorkersEdareDAOService;
import ir.tamin.incomeBank.dto.paymentWorkers.edare.PaymentWorkersEdareDto;
import ir.tamin.incomeBank.dto.paymentWorkers.edare.PaymentWorkersEdareSummary;
import ir.tamin.incomeBank.mapper.paymentWorkers.edare.PaymentWorkersEdareMapper;
import ir.tamin.incomeBank.model.paymentWorkers.edare.PaymentWorkersEdare;
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
public class PaymentWorkersEdareService {

    @Inject
    private PaymentWorkersEdareDAOService daoService;
    @Inject
    private FilterAndUserRoleValidator userRoleValidator;

    @Inject
    private ExcelDataProvider excelDataProvider;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        Map<String, Object> map = new HashMap<>();
        List<PaymentWorkersEdare> entityList = daoService.search(filterWrapper, start, limit, sort);
        List<PaymentWorkersEdareDto> dtoList = PaymentWorkersEdareMapper.mapToDtoList(entityList);


        try {
            map.put("list", dtoList);
            map.put("total", daoService.getCount(filterWrapper));
            map.put("sum", getSummery(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(ReceiptPaperRestService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return map;
    }

    public PaymentWorkersEdareSummary getSummery(FilterWrapper filterWrapper) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(PaymentWorkersEdareService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {

        List<PaymentWorkersEdare> entityList = null;
        List<PaymentWorkersEdareDto> dtoList = null;

        try {
            entityList = daoService.search(filterWrapper, 0, Integer.MAX_VALUE, null);
            dtoList = PaymentWorkersEdareMapper.mapToDtoList(entityList);
        } catch (Exception ex) {
            throw ex;
        }
        PaymentWorkersEdareSummary summaryPrice = daoService.getSummaryPrice(filterWrapper);
        PaymentWorkersEdare entity = new PaymentWorkersEdare();
        entity.setSumAmount(summaryPrice.getSumAmount());
        entity.setCountOrderNumber(summaryPrice.getCountOrdNumber());
        entity.setBimeh7sakht(summaryPrice.getBimeh7sakht());
        entity.setBimeh20sakht(summaryPrice.getBimeh20sakht());
        entity.setJarimehSakht(summaryPrice.getJarimehSakht());
        entity.setSumAmtGov(summaryPrice.getSumAmtGov());

        entityList.add(entity);

        List<List<String>> rowsForExcel = prepareDataForExcel(dtoList);
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

    private List<List<String>> prepareDataForExcel(List<PaymentWorkersEdareDto> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "تاریخ وصول",
                "مبلغ وصول",
                "حق بیمه کارگران",
                "20 درصد سهم کارفرما",
                "جریمه",
                "مبلغ کمک دولت",
                "تعداد وصول"

        ));
        int rowCounter = 1;
        for (PaymentWorkersEdareDto dto : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dto.getEdareCode() != null ? dto.getEdareCode() : "-");
            row.add(dto.getEdareName() != null ? dto.getEdareName() : "-");
            row.add(dto.getOrderDate() != null ? dto.getOrderDate() : "-");
            row.add(dto.getSumAmount() != null ? dto.getSumAmount().toString() : "-");
            row.add(dto.getBimeh7sakht() != null ? dto.getBimeh7sakht().toString() : "-");
            row.add(dto.getBimeh20sakht() != null ? dto.getBimeh20sakht().toString() : "-");
            row.add(dto.getJarimehSakht() != null ? dto.getJarimehSakht().toString() : "-");
            row.add(dto.getSumAmtGov() != null ? dto.getSumAmtGov().toString() : "-");
            row.add(dto.getCountOrdNumber() != null ? dto.getCountOrdNumber().toString() : "-");


            rows.add(row);
            rowCounter++;
        }

        return rows;

    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش موضوع پرداخت کارگران ساختمانی تفکیک روزانه – ادارات کل-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }

}
