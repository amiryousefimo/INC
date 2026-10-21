package ir.tamin.incomeBank.service.sheetsWithoutBankCards.bankCards3070;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.sheetsWithoutBankCards.bankCards3070.SheetsWithoutBankCards3070DAOService;
import ir.tamin.incomeBank.dto.paymentWorkers.branch.PaymentWorkersBranchDto;
import ir.tamin.incomeBank.dto.sheetsWithoutBankCards3070.bankCards3070.SheetsWithoutBankCards3070Dto;
import ir.tamin.incomeBank.mapper.paymentWorkers.branch.PaymentWorkersBranchMapper;
import ir.tamin.incomeBank.mapper.paymentWorkers.edare.PaymentWorkersEdareMapper;
import ir.tamin.incomeBank.mapper.sheetsWithoutBankCards3070.bankCards3070.SheetsWithoutBankCards3070Mapper;
import ir.tamin.incomeBank.model.payment3070.daily.Daily3070;
import ir.tamin.incomeBank.model.payment3070.daily.Daily3070Summary;
import ir.tamin.incomeBank.model.paymentWorkers.branch.PaymentWorkersBranch;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCards3070.SheetsWithoutBankCards3070;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCards3070.SheetsWithoutBankCardsSummary;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCards3070.SheetsWithoutBankCardsView;
import ir.tamin.incomeBank.service.payment3070.effective.Effective3070Service;
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
 * @date 2024.12.15
 */
@Stateless
public class SheetsWithoutBankCards3070Service {

    @Inject
    private SheetsWithoutBankCards3070DAOService daoService;

    @Inject
    private FilterAndUserRoleValidator userRoleValidator;

    @Inject
    private ExcelDataProvider excelDataProvider;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        Map<String, Object> map = new HashMap<>();
        List<SheetsWithoutBankCards3070> entiryList = daoService.search(filterWrapper, start, limit, sort);
        List<SheetsWithoutBankCards3070Dto> dtoList = SheetsWithoutBankCards3070Mapper.mapToDtoList(entiryList);

        try {
            map.put("list", dtoList);
            map.put("total", daoService.getCount(filterWrapper));
            map.put("sum", getSummary(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(SheetsWithoutBankCards3070Service.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return map;
    }

    public SheetsWithoutBankCardsSummary getSummary(FilterWrapper filterWrapper) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(SheetsWithoutBankCards3070Service.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);

        List<SheetsWithoutBankCards3070> entityList = null;

        try {
            entityList = daoService.search(filterWrapper, 0, Integer.MAX_VALUE, null);
        } catch (Exception ex) {
            throw ex;
        }

        SheetsWithoutBankCardsSummary summaryPrice = daoService.getSummaryPrice(filterWrapper);
        SheetsWithoutBankCards3070 entity = new SheetsWithoutBankCards3070();

        entity.setGlAmount(summaryPrice.getGlAmount());
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

    private List<List<String>> prepareDataForExcel(List<SheetsWithoutBankCards3070> list) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "کد شعبه",
                "نام شعبه",
                "تاريخ موثر",
                " شماره برگه پرداخت",
                "مبلغ وصول",
                "کدملی بیمه شده",
                "نام و نام خانوادگی بیمه شده",
                "نام سیستم"
        ));
        int rowCounter = 1;
        for (SheetsWithoutBankCards3070 entity : list) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == list.size() ? "مجموع" : String.valueOf(rowCounter));
            row.add(entity.getEdareCode() != null ? entity.getEdareCode() : "-");
            row.add(entity.getEdareName() != null ? entity.getEdareName() : "-");
            row.add(entity.getBrchCode() != null ? entity.getBrchCode() : "-");
            row.add(entity.getBrchName() != null ? entity.getBrchName() : "-");
            row.add(entity.getGlDate()!= null ? entity.getGlDate() : "-");
            row.add(entity.getOrderNumber() != null ? entity.getOrderNumber() : "-");
            row.add(entity.getGlAmount() != null ? entity.getGlAmount().toString() : "-");
            row.add(entity.getCustomerId() != null ? entity.getCustomerId() : "-");
            row.add(entity.getCustomerName() != null ? entity.getCustomerName() : "-");
            row.add(entity.getSystemDesc() != null ? entity.getSystemDesc() : "-");

            //row.add(entity.getGlDate2() != null ? entity.getGlDate2().toString() : "-");//TODO----------- from / to Date
            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("برگه های فاقد کارت بانک 3070 - ");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }







}
