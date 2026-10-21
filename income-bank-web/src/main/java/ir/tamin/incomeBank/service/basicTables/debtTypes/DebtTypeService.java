package ir.tamin.incomeBank.service.basicTables.debtTypes;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.basicTables.debtTypes.DebtTypesDAOService;
import ir.tamin.incomeBank.dto.basicTables.debtTypes.DebtTypesTableDto;
import ir.tamin.incomeBank.mapper.basicTables.debtTypes.DebtTypesTableMapper;
import ir.tamin.incomeBank.model.basicTables.debtTypes.DebtTypesTable;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import ir.tamin.incomeBank.util.FilterAndUserRoleValidator;
import org.apache.poi.ss.usermodel.Workbook;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author m_goharriz
 */
@Stateless
public class DebtTypeService {

    @Inject
    private DebtTypesDAOService daoService;

    @Inject
    private FilterAndUserRoleValidator userRoleValidator;

    @Inject
    private ExcelDataProvider excelDataProvider;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        //userRoleValidator.filterIsValid(filterWrapper);
        Map<String, Object> map = new HashMap<>();
        List<DebtTypesTable> entityList = daoService.search(filterWrapper, start, limit, sort);

        try {

            map.put("list", daoService.search(filterWrapper, start, limit, sort));
            map.put("total", daoService.getCount(filterWrapper));

        } catch (Exception ex) {
            Logger.getLogger(DebtTypeService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return map;
    }

    public StreamingOutput loadExcel(FilterWrapper filterWrapper)throws Exception{
        List<DebtTypesTable> entityList = null;
        List<DebtTypesTableDto> dtoList = null;

        try{
            entityList = daoService.search(filterWrapper,0,Integer.MAX_VALUE,null);
            dtoList= DebtTypesTableMapper.mapToDtoList(entityList);
        }catch (Exception ex){
            throw ex;
        }

        List<List<String>> rowsForExcel = prepareDataForExcel(dtoList);
        ExcelReportHeader reportHeader = createReportHeaderDetails();

        StreamingOutput streamingOutput = new StreamingOutput() {
            final Workbook workbook = excelDataProvider.provideExcelFrom(reportHeader, rowsForExcel);
            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException {
                workbook.write(output);
            }
        };
        return streamingOutput;
    }
    private List<List<String>> prepareDataForExcel(List<DebtTypesTableDto> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(

                "ردیف",
                "کد بدهی",
                "شرح بدهی",
                "کد کل",
                "کد معین",
                "کد تفضیلی"

        ));
        int rowCounter = 1;
        for (DebtTypesTableDto dto : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dto.getDebitCode() != null ? dto.getDebitCode() : "-");
            row.add(dto.getDebitDescription() != null ? dto.getDebitDescription() : "-");
            row.add(dto.getCodeKol() != null ? dto.getCodeKol() : "-");
            row.add(dto.getCodeMoin() != null ? dto.getCodeMoin() : "-");
            row.add(dto.getCodeTafsili() != null ? dto.getCodeTafsili() : "-");

            rows.add(row);
            rowCounter++;
        }

        return rows;

    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش جدول انواع بدهی و کدینگ مالی مرتبط");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }



}
