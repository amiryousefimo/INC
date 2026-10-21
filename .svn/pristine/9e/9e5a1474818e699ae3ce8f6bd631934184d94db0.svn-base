package ir.tamin.incomeBank.service.payment3070.account503branch;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;

import ir.tamin.incomeBank.dao.payment3070.account503branch.Account503BranchDAO;
import ir.tamin.incomeBank.dto.pey3070.account503branch.Account503BranchDto;
import ir.tamin.incomeBank.dto.pey3070.account503branch.Account503BranchSummary;
import ir.tamin.incomeBank.mapper.pey3070.account503branch.Account503BranchMapper;
import ir.tamin.incomeBank.model.payment3070.account503branch.Account503Branch;
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
 * @author A_Jamali
 */

@Stateless
public class Account503BranchFilterService {

    @Inject
    private Account503BranchDAO daoService;

    /**
     * validated filter that first of all can not be null then check users permissions
     */
    @Inject
    private FilterAndUserRoleValidator userRoleValidator;

    @Inject
    private ExcelDataProvider excelDataConverter;


    /**
     *
     * @param filterWrapper includes object values of entity and database operator (LIKE, EQUAL, AFTER, BEFORE, etc.)
     * @param start start page for pagination
     * @param limit page size of data that call from database
     * @param sort sort as order by in database
     * @return new map include dto list and total number of objects
     * @throws Exception a {@link Exception}
     */

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        Map<String, Object> map = new HashMap<>();
        List<Account503Branch> entityList = daoService.search(filterWrapper, start, limit, sort);
        List<Account503BranchDto> dtoList = Account503BranchMapper.mapToDtoList(entityList);
        try {
            map.put("list", dtoList);
            map.put("sum", daoService.getSummaryPrice(filterWrapper));//add by m_goharriz
            map.put("total", daoService.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(ReceiptPaperRestService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }

    /**
     *
     * @param filterWrapper includes object values of entity and database operator (LIKE, EQUAL, AFTER, BEFORE, etc.)
     * @return an object that includes entity variables, all of which are of type {@link java.math.BigDecimal}
     * @throws Exception if an {@link Exception} occurs
     */
    public Account503BranchSummary getSummery(FilterWrapper filterWrapper) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(ReceiptPaperRestService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    /**
     *
     * @param filterWrapper includes object values of entity and database operator (LIKE, EQUAL, AFTER, BEFORE, etc.)
     * @return excel report of a dto list of objects for filling cells
     * @throws Exception a {@link Exception}
     */
    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {

        List<Account503Branch> entityList =null;
        List<Account503BranchDto> dtoList =null;

        try {
            entityList = daoService.search(filterWrapper, 0, Integer.MAX_VALUE, null);
            dtoList = Account503BranchMapper.mapToDtoList(entityList);
        } catch (Exception ex) {
            throw ex;
        }

        //                "مبلغ بیمه شدگان خاص",
        //                "مبلغ سربازی",
        //                " مبلغ کارگران ساختمانی",
        //                "مبلغ کسر از ماه",
        //                "تعداد برگه وصول",
        //                " مبلغ کمک دولت"
        Account503BranchSummary summaryPrice = daoService.getSummaryPrice(filterWrapper);
        Account503Branch summery = new Account503Branch();
        summery.setSumAmount(summaryPrice.getSumAmount());
        /*summery.setKhasAmount(summaryPrice.getKhasAmount());*/
        summery.setSarbaziAmt(summaryPrice.getSarbaziAmt());
        summery.setSakhtemanAmt(summaryPrice.getSakhtemanAmt());
        summery.setKasrMahAmt(summaryPrice.getKasrMahAmt());
        summery.setCountAmt(summaryPrice.getCountAmt());
        summery.setDolatAmt(summaryPrice.getDolatAmt());

        entityList.add(summery);

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
     *
     * @param dtoList for prepare data for cells of excel report
     * @return data for excel report
     */
    private List<List<String>> prepareDataForExcel(List<Account503BranchDto> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "کد شعبه",
                "نام شعبه",
                "تاریخ وصول",
                "جمع مبالغ وصول",
                "مبلغ بیمه شدگان خاص",
                "مبلغ سربازی",
                " مبلغ کارگران ساختمانی",
                "مبلغ کسر از ماه",
                "تعداد برگه وصول",
                " مبلغ کمک دولت"
        ));
        int rowCounter = 1;
        for (Account503BranchDto dto : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dto.getEdareCode() != null ? dto.getEdareCode() : "-");
            row.add(dto.getEdareName() != null ? dto.getEdareName() : "-");
            row.add(dto.getBrchCode() != null ? dto.getBrchCode() : "-");
            row.add(dto.getBrchName() != null ? dto.getBrchName() : "-");
            row.add(dto.getOrdDate() != null ? dto.getOrdDate() : "-");
            row.add(dto.getSumAmount() != null ? dto.getSumAmount().toString() : "-");
           /* row.add(dto.getKhasAmount() != null ? dto.getKhasAmount().toString() : "-");*/
            row.add(dto.getSarbaziAmt() != null ? dto.getSarbaziAmt().toString() : "-");
            row.add(dto.getSakhtemanAmt() != null ? dto.getSakhtemanAmt().toString() : "-");
            row.add(dto.getKasrMahAmt() != null ? dto.getKasrMahAmt().toString() : "-");
            row.add(dto.getCountAmt() != null ? dto.getCountAmt().toString() : "-");
            row.add(dto.getDolatAmt() != null ? dto.getDolatAmt().toString() : "-");

            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    /**
     *
     * @return a header name for excel
     */
    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش وصول – شعب-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }


}
