package ir.tamin.incomeBank.service.paymentWorkers.branch;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.paymentWorkers.branch.PaymentWorkersBranchDAOService;
import ir.tamin.incomeBank.dto.paymentWorkers.branch.PaymentWorkersBranchDto;
import ir.tamin.incomeBank.dto.paymentWorkers.branch.PaymentWorkersBranchSummary;
import ir.tamin.incomeBank.mapper.paymentWorkers.branch.PaymentWorkersBranchMapper;
import ir.tamin.incomeBank.model.paymentWorkers.branch.PaymentWorkersBranch;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import ir.tamin.incomeBank.util.FilterAndUserRoleValidator;
import ir.tamin.incomeBank.ws.rest.receipt.ReceiptPaperRestService;
/*import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;*/
import org.apache.poi.ss.usermodel.*;


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
public class PaymentWorkersBranchService {

    @Inject
    private PaymentWorkersBranchDAOService daoService;
    @Inject
    private FilterAndUserRoleValidator userRoleValidator;

    @Inject
    private ExcelDataProvider excelDataProvider;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        Map<String, Object> map = new HashMap<>();
        List<PaymentWorkersBranch> entityList = daoService.search(filterWrapper, start, limit, sort);
        List<PaymentWorkersBranchDto> dtoList = PaymentWorkersBranchMapper.mapToDtoList(entityList);


        try {
            map.put("list", dtoList);
            //map.put("list", daoService.search(filterWrapper, start, limit, sort));
            map.put("total", daoService.getCount(filterWrapper));
            map.put("sum", getSummery(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(ReceiptPaperRestService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return map;
    }

    public PaymentWorkersBranchSummary getSummery(FilterWrapper filterWrapper) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(PaymentWorkersBranchService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {

        List<PaymentWorkersBranch> entityList = null;
        List<PaymentWorkersBranchDto> dtoList = null;

        try {
            entityList = daoService.search(filterWrapper, 0, Integer.MAX_VALUE, null);
            dtoList = PaymentWorkersBranchMapper.mapToDtoList(entityList);
        } catch (Exception ex) {
            throw ex;
        }
        PaymentWorkersBranchSummary summaryPrice = daoService.getSummaryPrice(filterWrapper);
        PaymentWorkersBranch entity = new PaymentWorkersBranch();
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

    private List<List<String>> prepareDataForExcel(List<PaymentWorkersBranchDto> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(

                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "کد شعبه",
                "نام شعبه",
                "تاریخ وصول",
                "مبلغ وصول",
                "حق بیمه کارگران",
                "20 درصد سهم کارفرما",
                "جریمه",
                "مبلغ کمک دولت",
                "تعداد وصول"

        ));
        int rowCounter = 1;
        for (PaymentWorkersBranchDto dto : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dto.getEdareCode() != null ? dto.getEdareCode() : "-");
            row.add(dto.getEdareName() != null ? dto.getEdareName() : "-");
            row.add(dto.getBrchCode() != null ? dto.getBrchCode() : "-");
            row.add(dto.getBrchName() != null ? dto.getBrchName() : "-");
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
        reportHeader.setTitle("گزارش موضوع پرداخت کارگران ساختمانی تفکیک روزانه – شعبه-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }

}
