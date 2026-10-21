package ir.tamin.incomeBank.service.commonList;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.commonList.CommonListDaoService;
import ir.tamin.incomeBank.model.commonList.CommonLIst;
import ir.tamin.incomeBank.model.commonList.CommonListSumAmount;
import ir.tamin.incomeBank.service.financialDoc.FinancialDocService;
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
 * @author A_Jamali
 */
public class CommonListSERVICE {

    @Inject
    private CommonListDaoService daoService;

    @Inject
    private ExcelDataProvider excelDataConverter;


    @Inject
    FilterAndUserRoleValidator userRoleValidator;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", daoService.search(filterWrapper, start, limit, sort));
            map.put("total", daoService.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(FinancialDocService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }


    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        List<CommonLIst> commonLIstList ;
        try {
            commonLIstList = (List<CommonLIst>) this.getAll(filterWrapper, 0, Integer.MAX_VALUE, null).get("list");
        } catch (Exception ex) {
            throw ex;
        }



        List<List<String>> rowsForExcel = prepareDataForExcel(commonLIstList);
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

    private List<List<String>> prepareDataForExcel(List<CommonLIst> commonLIstList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "تاریخ وصول",
                "شماره برگه پرداخت",
                "نام بانک",
                "مبلغ بدهکار",
                "مبلغ بستانکار",
                "تاریخ موثر وصول",
                "ردیف بانک"
        ));
        int rowCounter = 1;
        for (CommonLIst commonLIst : commonLIstList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == commonLIstList.size()+1 ? "-" : String.valueOf(rowCounter));
            row.add(commonLIst.getSoratDate() != null ? commonLIst.getSoratDate() : "-");
            row.add(commonLIst.getSoratOrdPay() != null ? commonLIst.getSoratOrdPay() : "-");
            row.add(commonLIst.getBankNme() != null ? commonLIst.getBankNme() : "-");
            row.add(commonLIst.getSoratPrice1() != null ? commonLIst.getSoratPrice1().toString() : "-");
            row.add(commonLIst.getSoratPrice2() != null ? commonLIst.getSoratPrice2().toString() : "-");
            row.add(commonLIst.getSoratEfDate() != null ? commonLIst.getSoratEfDate() : "-");
            row.add(commonLIst.getSoratRadif() != null ? commonLIst.getSoratRadif() : "-");
            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش کارت بانک لیست مشترک");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }


    /********************for another form on ui***********************/


    public Map<String, Object> getAllSetadi(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", daoService.search(filterWrapper, start, limit, sort));
            map.put("total", daoService.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(FinancialDocService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }



    public CommonListSumAmount getSummerySetadi(FilterWrapper filterWrapper) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(FinancialDocService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public StreamingOutput loadExcelSetadi(FilterWrapper filterWrapper) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        List<CommonLIst> commonLIstList = null;
        CommonListSumAmount commonListSumAmount = null;

        try {
            commonLIstList = (List<CommonLIst>) this.getAll(filterWrapper, 0, Integer.MAX_VALUE, null).get("list");
        } catch (Exception ex) {
            throw ex;
        }

        commonListSumAmount = daoService.getSummaryPrice(filterWrapper);
        CommonLIst summery = new CommonLIst();
        summery.setSoratPrice1(commonListSumAmount.getSumSoratPrice1());
        summery.setSoratPrice2(commonListSumAmount.getSumSoratPrice2());
//        summery.setSoratOrdPay(commonListSumAmount.getCountOfSoratOrdPay());

        commonLIstList.add(summery);

        List<List<String>> rowsForExcel = prepareDataForExcelSetadi(commonLIstList);
        ExcelReportHeader reportHeader = createReportHeaderDetailsSetadi();

        StreamingOutput streamingOutPut = new StreamingOutput() {
            final Workbook workbook = excelDataConverter.provideExcelFrom(reportHeader, rowsForExcel);

            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException {
                workbook.write(output);
            }
        };

        return streamingOutPut;
    }

    private List<List<String>> prepareDataForExcelSetadi(List<CommonLIst> commonLIstList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "تاریخ وصول",
                "شماره برگه پرداخت",
                "نام بانک",
                "مبلغ بدهکار",
                "مبلغ بستانکار",
                "تاریخ موثر وصولی",
                "ردیف بانک"
        ));
        int rowCounter = 1;
        for (CommonLIst commonLIst : commonLIstList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == commonLIstList.size() ? "مجموع" : String.valueOf(rowCounter));
            row.add(commonLIst.getSoratDate() != null ? commonLIst.getSoratDate() : "-");
            row.add(commonLIst.getSoratOrdPay() != null ? commonLIst.getSoratOrdPay().toString() : "-");
            row.add(commonLIst.getBankNme() != null ? commonLIst.getBankNme() : "-");
            row.add(commonLIst.getSoratPrice1() != null ? commonLIst.getSoratPrice1().toString() : "-");
            row.add(commonLIst.getSoratPrice2() != null ? commonLIst.getSoratPrice2().toString() : "-");
            row.add(commonLIst.getSoratEfDate() != null ? commonLIst.getSoratEfDate() : "-");
            row.add(commonLIst.getSoratRadif() != null ? commonLIst.getSoratRadif() : "-");
            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetailsSetadi() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش در سطح شعبه-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }


}
