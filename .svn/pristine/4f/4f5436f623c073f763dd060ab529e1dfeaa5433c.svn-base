package ir.tamin.incomeBank.service.announcement.monthlyComparisonReportBankAndOfficeStatement;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.announcement.monthlyComparisonReportBankAndOfficeStatement.MonthlyComparisonReportBankAndOfficeStatementDAO;
import ir.tamin.incomeBank.dto.monthlyComparisonReportBankAndOfficeStatement.MonthlyComparisonReportBankAndOfficeStatementDto;
import ir.tamin.incomeBank.mapper.monthlyComparisonReportBankAndOfficeStatement.MonthlyComparisonReportBankAndOfficeStatementMapper;
import ir.tamin.incomeBank.model.announcement.monthlyComparisonReportBankAndOfficeStatement.MonthlyComparisonReportBankAndOfficeStatement;

import ir.tamin.incomeBank.model.announcement.monthlyComparisonReportBankAndOfficeStatement.MonthlyComparisonReportBankAndOfficeStatementSummary;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import ir.tamin.incomeBank.ws.rest.receipt.ReceiptPaperRestService;
import org.apache.poi.ss.usermodel.*;

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
public class MonthlyComparisonReportBankAndOfficeStatementService {

    @Inject
    private MonthlyComparisonReportBankAndOfficeStatementDAO daoService;

    @Inject
    private ExcelDataProvider excelDataConverter;
    /**
     * @param filterWrapper filter contains date
     * @param start         start page (1,2,3,....)
     * @param limit         limit of page (5 or 10 or 50 ....)
     * @param sort          for sorting of SortWrapper
     * @return a map of which contains a list of dto and size of list as total
     * @throws Exception exception
     */

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<MonthlyComparisonReportBankAndOfficeStatement> entityList = daoService.search(filterWrapper, start, limit, sort);
        List<MonthlyComparisonReportBankAndOfficeStatementDto> dtoList = MonthlyComparisonReportBankAndOfficeStatementMapper.mapToDtoList(entityList);

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
     * @param filterWrapper filter contains date
     * @return a object of dto summary class which contains sum of {@link java.math.BigDecimal} variables of dto class
     * @throws Exception exception
     */
    public MonthlyComparisonReportBankAndOfficeStatementSummary getSummery(FilterWrapper filterWrapper) throws Exception {
        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(MonthlyComparisonReportBankAndOfficeStatementService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }


    /**
     * @param filterWrapper filter contains date
     * @return a excel report of MonthlyComparisonReportBankAndOfficeStatementDto list
     * @throws Exception exception
     */
    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {
        List<MonthlyComparisonReportBankAndOfficeStatement> entityList;
        List<MonthlyComparisonReportBankAndOfficeStatementDto> dtoList ;



        entityList = daoService.search(filterWrapper, null, null, null);
        dtoList=MonthlyComparisonReportBankAndOfficeStatementMapper.mapToDtoList(entityList);

        MonthlyComparisonReportBankAndOfficeStatementSummary      summary = daoService.getSummaryPrice(filterWrapper);
        MonthlyComparisonReportBankAndOfficeStatement entity = new MonthlyComparisonReportBankAndOfficeStatement();
        entity.setSumCard(summary.getSumCard());
        entity.setSumLetter(summary.getSumLetter());

        dtoList.add(MonthlyComparisonReportBankAndOfficeStatementMapper.mapToDto(entity,new MonthlyComparisonReportBankAndOfficeStatementDto()));

        List<List<String>> rowsForExcel = prepareDataForExcel(dtoList);
        ExcelReportHeader reportHeader = createReportHeaderDetails();

        return new StreamingOutput() {
            final Workbook workbook = excelDataConverter.provideExcelFrom(reportHeader, rowsForExcel);

            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException {
                workbook.write(output);
            }
        };
    }

    private List<List<String>> prepareDataForExcel(List<MonthlyComparisonReportBankAndOfficeStatementDto> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "سال و ماه ",
                "مبلغ کارت بانک و اوراق ",
                "مبلغ اعلامیه های ارسال شده"
        ));
        int rowCounter = 1;
        for (MonthlyComparisonReportBankAndOfficeStatementDto dto : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() ? "مجموع" : String.valueOf(rowCounter));
            row.add(dto.getDate() != null ? dto.getDate() : "-");
            row.add(dto.getSumCard() != null ? dto.getSumCard().toString() : "-");
            row.add(dto.getSumLetter() != null ? dto.getSumLetter().toString(): "-");
            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش مقایسه ای ماهیانه بانک و دفتر اعلامیه -");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }

}
