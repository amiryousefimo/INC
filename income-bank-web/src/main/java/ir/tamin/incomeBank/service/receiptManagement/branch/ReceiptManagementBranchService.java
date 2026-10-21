package ir.tamin.incomeBank.service.receiptManagement.branch;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.receiptManagement.branch.ReceiptManagementBranchDAO;
import ir.tamin.incomeBank.dto.receiptManagement.branch.ReceiptManagementBranchDto;
import ir.tamin.incomeBank.dto.receiptManagement.branch.ReceiptManagementBranchSummary;
import ir.tamin.incomeBank.mapper.receiptManagement.branch.ReceiptManagementBranchMapper;
import ir.tamin.incomeBank.model.receiptManagement.branch.ReceiptManagementBranch;
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
public class ReceiptManagementBranchService {

    @Inject
    private ReceiptManagementBranchDAO daoService;

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
        List<ReceiptManagementBranch> entityList = daoService.search(filterWrapper, start, limit, sort);
        List<ReceiptManagementBranchDto> dtoList = ReceiptManagementBranchMapper.mapToDtoList(entityList);
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
     *
     * @param filterWrapper includes object values of entity and database operator (LIKE, EQUAL, AFTER, BEFORE, etc.)
     * @return an object that includes entity variables, all of which are of type {@link java.math.BigDecimal}
     * @throws Exception if an {@link Exception} occurs
     */
    public ReceiptManagementBranchSummary getSummery(FilterWrapper filterWrapper) throws Exception {
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

        List<ReceiptManagementBranch> entityList =null;
        List<ReceiptManagementBranchDto> dtoList =null;

        try {
            entityList = (List<ReceiptManagementBranch>) this.getAll(filterWrapper, 0, Integer.MAX_VALUE, null).get("list");
            dtoList = ReceiptManagementBranchMapper.mapToDtoList(entityList);
        } catch (Exception ex) {
            throw ex;
        }
        ReceiptManagementBranchSummary summaryPrice = daoService.getSummaryPrice(filterWrapper);
        ReceiptManagementBranch summery = new ReceiptManagementBranch();
        summery.setIntljPrc(summaryPrice.getIntljPrc());
        summery.setOraghPrc(summaryPrice.getOraghPrc());
        summery.setNaghdiPrc(summaryPrice.getNaghdiPrc());
        summery.setPrc3070(summaryPrice.getPrc3070());
        summery.setKarkonanPrc(summaryPrice.getKarkonanPrc());
        summery.setSabaPrc(summaryPrice.getSabaPrc());
        summery.setSumPrc(summaryPrice.getSumPrc());

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
    private List<List<String>> prepareDataForExcel(List<ReceiptManagementBranchDto> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "کد شعبه",
                "نام شعبه",
                "تاریخ ",
                "کارت بانک سیستم سبا",
                "دفتر لیست هوشمند",
                "دفتر اعلامیه واریز نقدی",
                "دفتر اعلامیه کارکنان دولت",
                "دفتر اعلامیه کارکنان دولت",
                "دفتر 3070",
                "جمع مبالغ وصول"
        ));
        int rowCounter = 1;
        for (ReceiptManagementBranchDto dto : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dto.getEdareCode() != null ? dto.getEdareCode() : "-");
            row.add(dto.getEdareName() != null ? dto.getEdareName() : "-");
            row.add(dto.getBrchCode() != null ? dto.getBrchCode() : "-");
            row.add(dto.getBrchName() != null ? dto.getBrchName() : "-");
            row.add(dto.getSoratDate() != null ? dto.getSoratDate() : "-");
            row.add(dto.getSabaPrc() != null ? dto.getSabaPrc().toString() : "-");
            row.add(dto.getIntljPrc() != null ? dto.getIntljPrc().toString() : "-");
            row.add(dto.getKarkonanPrc() != null ? dto.getKarkonanPrc().toString() : "-");
            row.add(dto.getNaghdiPrc() != null ? dto.getNaghdiPrc().toString() : "-");
            row.add(dto.getOraghPrc() != null ? dto.getOraghPrc().toString() : "-");
            row.add(dto.getPrc3070() != null ? dto.getPrc3070().toString() : "-");
            row.add(dto.getSumPrc() != null ? dto.getSumPrc().toString() : "-");

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
