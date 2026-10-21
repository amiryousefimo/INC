package ir.tamin.incomeBank.service.payment3070.account503;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.payment3070.account503.Account503Dao;
import ir.tamin.incomeBank.dto.pey3070.account503.Account503Dto;
import ir.tamin.incomeBank.dto.pey3070.account503.Account503Summary;
import ir.tamin.incomeBank.mapper.pey3070.account503.Account503Mapper;
import ir.tamin.incomeBank.model.payment3070.account503.Account503;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import ir.tamin.incomeBank.util.FilterAndUserRoleValidator;
import ir.tamin.incomeBank.ws.rest.receipt.ReceiptPaperRestService;
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
public class Account503Service {

    @Inject
    private Account503Dao daoService;

    /**
     * validated filter that first of all can not be null then check users permissions
     */
    @Inject
    private FilterAndUserRoleValidator userRoleValidator;

    @Inject
    private ExcelDataProvider excelDataConverter;


    /**
     * @param filterWrapper includes object values of entity and database operator (LIKE, EQUAL, AFTER, BEFORE, etc.)
     * @param start         start page for pagination
     * @param limit         page size of data that call from database
     * @param sort          sort as order by in database
     * @return new map include dto list and total number of objects
     * @throws Exception a {@link Exception}
     */

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        Map<String, Object> map = new HashMap<>();
        List<Account503> entityList = daoService.search(filterWrapper, start, limit, sort);
        List<Account503Dto> dtoList = Account503Mapper.mapToDtoList(entityList);
        try {
            map.put("list", dtoList);
            map.put("total", daoService.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(ReceiptPaperRestService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }

    /**
     * @param filterWrapper includes object values of entity and database operator (LIKE, EQUAL, AFTER, BEFORE, etc.)
     * @return an object that includes entity variables, all of which are of type {@link java.math.BigDecimal}
     * @throws Exception if an {@link Exception} occurs
     */
    public Account503Summary getSummery(FilterWrapper filterWrapper) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(ReceiptPaperRestService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    /**
     * @param filterWrapper includes object values of entity and database operator (LIKE, EQUAL, AFTER, BEFORE, etc.)
     * @return excel report of a dto list of objects for filling cells
     * @throws Exception a {@link Exception}
     */
    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {

        List<Account503> entityList = null;
        List<Account503Dto> dtoList = null;

        try {
            entityList = daoService.search(filterWrapper, 0, Integer.MAX_VALUE, null);
            dtoList = Account503Mapper.mapToDtoList(entityList);
        } catch (Exception ex) {
            throw ex;
        }
        Account503Summary summaryPrice = daoService.getSummaryPrice(filterWrapper);
        Account503 entity = new Account503();
        entity.setOrderAmount(summaryPrice.getOrderAmount());
        entity.setGovernmentAmount(summaryPrice.getGovernmentAmount());

        entityList.add(entity);

        List<List<String>> rowsForExcel = prepareDataForExcel(dtoList);
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

    /**
     * @param dtoList for prepare data for cells of excel report
     * @return data for excel report
     */
    private List<List<String>> prepareDataForExcel(List<Account503Dto> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "کد شعبه",
                "نام شعبه",
                "تاریخ موثر وصول",
                " مبلغ وصول",
                "کد ملی",
                " نام بیمه شده",
                "موضوع پرداخت",
                "شناسه پرداخت",
                "شروع دوره",
                "پایان دوره",
                "مبلغ کمک دولت",
                "تاریخ دریافت در سیستم INCOMING",
                "وضعیت سند"

        ));
        int rowCounter = 1;
        for (Account503Dto dto : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dto.getEdareCode() != null ? dto.getEdareCode() : "-");
            row.add(dto.getEdareName() != null ? dto.getEdareName() : "-");
            row.add(dto.getBrchCode() != null ? dto.getBrchCode() : "-");
            row.add(dto.getBrchName() != null ? dto.getBrchName() : "-");
            row.add(dto.getOrdDate() != null ? dto.getOrdDate() : "-");
            row.add(dto.getOrderAmount() != null ? dto.getOrderAmount().toString() : "-");
            row.add(dto.getNationalCode() != null ? dto.getNationalCode() : "-");
            row.add(dto.getNameOfTheInsured() != null ? dto.getNameOfTheInsured() : "-");
            row.add(dto.getDebitReasonDesc() != null ? dto.getDebitReasonDesc(): "-");
            row.add(dto.getOrdOrderNumber() != null ? dto.getOrdOrderNumber() : "-");
            row.add(dto.getStartDateOfPeriod() != null ? dto.getStartDateOfPeriod() : "-");
            row.add(dto.getEndDateOfPeriod() != null ? dto.getEndDateOfPeriod() : "-");
            row.add(dto.getGovernmentAmount() != null ? dto.getGovernmentAmount().toString() : "-");
            row.add(dto.getSendDateOfInfoToIncoming() != null ? dto.getSendDateOfInfoToIncoming() : "-");
            //row.add(dto.getDocumentStatus() != null ? dto.getDocumentStatus() : "-");//TODO-------------------------
            row.add(dto.getDocumentStatus() != null ? dto.getDocumentStatus().toString().replace("0","صادر نشده") : "صادر شده");
            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    /**
     * @return a header name for excel
     */
    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("دفتر روزنامه پروژه 3070 – حساب 503 -");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }


    public Map<String, List<Map<String, String>>> getDebitReasonDescAndCode() {
        Map<String, List<Map<String, String>>> map = new HashMap<>();
        map.put("list", daoService.getDebitReasonDescAndCode());
        return map;
    }

}



