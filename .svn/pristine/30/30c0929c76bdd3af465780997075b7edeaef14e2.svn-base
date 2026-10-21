package ir.tamin.incomeBank.service.similarBankSheets;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.sheetsWithoutBankCards.bankCards3070.SheetsWithoutBankCards3070DAOService;
import ir.tamin.incomeBank.dao.similarBankSheets.SimilarBankSheetsDAOService;
import ir.tamin.incomeBank.dto.sheetsWithoutBankCards3070.bankCards3070.SheetsWithoutBankCards3070Dto;
import ir.tamin.incomeBank.mapper.sheetsWithoutBankCards3070.bankCards3070.SheetsWithoutBankCards3070Mapper;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCards3070.SheetsWithoutBankCards3070;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCards3070.SheetsWithoutBankCardsSummary;
import ir.tamin.incomeBank.model.similarBankSheets.SimilarBankSheets;
import ir.tamin.incomeBank.model.similarBankSheets.SimilarBankSheetsSummary;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import ir.tamin.incomeBank.util.FilterAndUserRoleValidator;
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
 * @date 2025.05.18
 */
@Stateless
public class SimilarBankSheetsService {

    @Inject
    private SimilarBankSheetsDAOService daoService;

/*    @Inject
    private FilterAndUserRoleValidator userRoleValidator;*/

    @Inject
    private ExcelDataProvider excelDataProvider;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {

        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", daoService.search(filterWrapper, start, limit, sort));
            map.put("total", daoService.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(SimilarBankSheetsService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return map;
    }

    public SimilarBankSheetsSummary getSummary(FilterWrapper filterWrapper) throws Exception {
        //userRoleValidator.filterIsValid(filterWrapper);
        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(SimilarBankSheetsService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {
       /* userRoleValidator.filterIsValid(filterWrapper);*/

        List<SimilarBankSheets> entityList = null;

        try {
            entityList = daoService.search(filterWrapper, 0, Integer.MAX_VALUE, null);
        } catch (Exception ex) {
            throw ex;
        }

        SimilarBankSheetsSummary summaryPrice = daoService.getSummaryPrice(filterWrapper);
        SimilarBankSheets entity = new SimilarBankSheets();

        entity.setCalculatorGovermentSold20(summaryPrice.getSumCalculatorGovermentSold20());
        entity.setCalculatorGoverment(summaryPrice.getSumCalculatorGoverment());
        entity.setGovermentAmount(summaryPrice.getSumGovermentAmount());
        entity.setOrderAmount(summaryPrice.getSumOrderAmount());
        entity.setDiffGoverment(summaryPrice.getSumDiffGoverment());
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

    private List<List<String>> prepareDataForExcel(List<SimilarBankSheets> list) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                " شماره برگه پرداخت",
                "تاريخ موثر",
                "تاريخ وصول",
                "نوع درصد کمک دولت",
                "مبلغ محاسبه 20 درصد دولت",
                "مبلغ محاسبه شده کمک دولت",
                "مبلغ کمک دولت دفتر",
                "مبلغ اختلاف کمک دولت",
                "موضوع پرداخت",
                "مبلغ حق بیمه",
                "کدملی بیمه شده",
                "نام و نام خانوادگی بیمه شده",
                "کد اداره کل",
                "نام اداره کل"
        ));
        int rowCounter = 1;
        for (SimilarBankSheets entity : list) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == list.size() ? "مجموع" : String.valueOf(rowCounter));
            row.add(entity.getOrderNumber() != null ? entity.getOrderNumber() : "-");
            row.add(entity.getOrderDate() != null ? entity.getOrderDate() : "-");
            row.add(entity.getSoratDate() != null ? entity.getSoratDate() : "-");
            row.add(entity.getCalculatorGovermentDesc() != null ? entity.getCalculatorGovermentDesc() : "-");
            row.add(entity.getCalculatorGovermentSold20()!= null ? String.valueOf(entity.getCalculatorGovermentSold20()) : "-");
            row.add(entity.getCalculatorGoverment()!= null ? String.valueOf(entity.getCalculatorGoverment()) : "-");
            row.add(entity.getGovermentAmount()!= null ? String.valueOf(entity.getGovermentAmount()) : "-");
            row.add(entity.getDiffGoverment()!= null ? String.valueOf(entity.getDiffGoverment()) : "-");
            row.add(entity.getDebitReasonDesc() != null ? entity.getDebitReasonDesc() : "-");
            row.add(entity.getOrderAmount()!= null ? String.valueOf(entity.getOrderAmount()) : "-");
            row.add(entity.getCustomerId() != null ? entity.getCustomerId() : "-");
            row.add(entity.getCustomerName() != null ? entity.getCustomerName() : "-");
            row.add(entity.getEdareCode() != null ? entity.getEdareCode() : "-");
            row.add(entity.getEdareName() != null ? entity.getEdareName() : "-");

            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش مغایرت محاسبات درصد دولت با دفتر");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }







}
