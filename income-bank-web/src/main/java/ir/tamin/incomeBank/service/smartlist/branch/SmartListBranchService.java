package ir.tamin.incomeBank.service.smartlist.branch;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;

import ir.tamin.incomeBank.dao.smartlist.branch.SmartListBranchDao;
import ir.tamin.incomeBank.dto.smartlist.branch.SmartListBranchDto;
import ir.tamin.incomeBank.dto.smartlist.branch.SmartListBranchSummary;
import ir.tamin.incomeBank.mapper.smartlist.branch.SmartListBranchMapper;
import ir.tamin.incomeBank.model.smartlist.branch.SmartListBranch;
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
public class SmartListBranchService {

    @Inject
    private SmartListBranchDao daoService;

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
        List<SmartListBranch> entityList = daoService.search(filterWrapper, start, limit, sort);
        List<SmartListBranchDto> dtoList = SmartListBranchMapper.mapToDtoList(entityList);
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
    public SmartListBranchSummary getSummery(FilterWrapper filterWrapper) throws Exception {
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

        List<SmartListBranch> entityList = null;
        List<SmartListBranchDto> dtoList = null;

        try {
            entityList = daoService.search(filterWrapper, 0, Integer.MAX_VALUE, null);
            dtoList = SmartListBranchMapper.mapToDtoList(entityList);
        } catch (Exception ex) {
            throw ex;
        }
        SmartListBranchSummary summaryPrice = daoService.getSummaryPrice(filterWrapper);
        SmartListBranch entity = new SmartListBranch();
        entity.setSumAmount(summaryPrice.getSumAmount());
        entity.setSumAmountGov(summaryPrice.getSumAmountGov());
        entity.setSumISu(summaryPrice.getSumISu());
        entity.setSumIsuGov(summaryPrice.getSumIsuGov());
        entity.setCountOrdNumber(summaryPrice.getCountOrdNumber());
        entity.setHagheBimehGov(summaryPrice.getHagheBimehGov());
        entity.setHagheBimeh(summaryPrice.getHagheBimeh());
        entity.setBikari(summaryPrice.getBikari());
        entity.setJarimeh(summaryPrice.getJarimeh());

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
    private List<List<String>> prepareDataForExcel(List<SmartListBranchDto> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                //کد اداره کل- نام اداره کل – تاریخ وصول از – تاریخ وصول تا – مبلغ وصول –  تعداد بیمه پرداز – مبلغ کمک دولت -  تعداد نفرات کمک دولت - تعداد برگه وصول
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "کد شعبه",
                "نام شعبه",
                "تاریخ وصول",
                "مبلغ وصول",
                "تعداد بیمه پرداز",
                "مبلغ کمک دولت",
                "تعداد نفرات کمک دولت",
                "تعداد برگه وصول",
                "حق بیمه کارگاههای غیر مشمول",
                "حق بیمه کارگاههای مشمول کمک دولت",
                "بیمه بیکاری",
                "جرایم"
        ));
        int rowCounter = 1;
        for (SmartListBranchDto dto : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dto.getEdareCode() != null ? dto.getEdareCode() : "-");
            row.add(dto.getEdareName() != null ? dto.getEdareName() : "-");
            row.add(dto.getBrchCode() != null ? dto.getBrchCode() : "-");
            row.add(dto.getBrchName() != null ? dto.getBrchName() : "-");
            row.add(dto.getOrderDate() != null ? dto.getOrderDate() : "-");
//            row.add(dto.getOrdDate2() != null ? dto.getOrdDate2().toString() : "-");
            row.add(dto.getSumAmount() != null ? dto.getSumAmount().toString() : "-");
            row.add(dto.getSumISu() != null ? dto.getSumISu().toString() : "-");
            row.add(dto.getSumAmountGov() != null ? dto.getSumAmountGov().toString() : "-");
            row.add(dto.getSumIsuGov() != null ? dto.getSumIsuGov().toString() : "-");
            row.add(dto.getCountOrdNumber() != null ? dto.getCountOrdNumber().toString() : "-");
            row.add(dto.getHagheBimeh() != null ? dto.getHagheBimeh().toString() : "-");
            row.add(dto.getHagheBimehGov() != null ? dto.getHagheBimehGov().toString() : "-");
            row.add(dto.getBikari() != null ? dto.getBikari().toString() : "-");
            row.add(dto.getJarimeh() != null ? dto.getJarimeh().toString() : "-");

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
        reportHeader.setTitle("گزارش وصولی روزانه لیست مشترک – شعبه-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }


}
