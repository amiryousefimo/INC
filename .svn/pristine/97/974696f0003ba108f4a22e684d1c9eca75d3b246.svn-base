package ir.tamin.incomeBank.service.financialDoc.newFinancialDocSpec;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.financialDoc.newFinancialDocSpec.SheetsWithoutBankCardsDocSpecDAOService;
import ir.tamin.incomeBank.dao.sheetsWithoutBankCards.bankCardsSmartList.SheetsWithoutBankCardsSmartListDAOService;
import ir.tamin.incomeBank.dto.sheetsWithoutBankCards3070.bankCardsSmartList.SheetsWithoutBankCardsSmartListDto;
import ir.tamin.incomeBank.mapper.sheetsWithoutBankCards3070.bankCardsSmartList.SheetsWithoutBankCardsSmartListMapper;
import ir.tamin.incomeBank.model.financialDoc.newFinancialDocSpec.SheetsWithoutBankCardsDocSpec;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCardsSmartList.SheetsWithoutBankCardsSmartList;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCardsSmartList.SheetsWithoutBankCardsSmartListSummary;
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
 * @date 2025.07.01
 */
@Stateless
public class SheetsWithoutBankCardsDocSpecService {

    @Inject
    private SheetsWithoutBankCardsDocSpecDAOService daoService;

    @Inject
    private FilterAndUserRoleValidator userRoleValidator;

    @Inject
    private ExcelDataProvider excelDataProvider;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", daoService.search(filterWrapper, start, limit, sort));
            map.put("total", daoService.getCount(filterWrapper));

        } catch (Exception ex) {
            Logger.getLogger(SheetsWithoutBankCardsDocSpecService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return map;
    }




    /*public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        Map<String, Object> map = new HashMap<>();
        List<SheetsWithoutBankCardsDocSpec> entiryList = daoService.search(filterWrapper, start, limit, sort);
        List<SheetsWithoutBankCardsSmartListDto> dtoList = SheetsWithoutBankCardsSmartListMapper.mapToDtoList(entiryList);

        try {
            map.put("list", dtoList);
            map.put("total", daoService.getCount(filterWrapper));
            map.put("sum", getSummary(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(SheetsWithoutBankCardsDocSpecService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return map;
    }*/

   /* public SheetsWithoutBankCardsSmartListSummary getSummary(FilterWrapper filterWrapper) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(SheetsWithoutBankCardsDocSpecService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);

        List<SheetsWithoutBankCardsSmartList> entityList = null;

        try {
            entityList = daoService.search(filterWrapper, 0, Integer.MAX_VALUE, null);
        } catch (Exception ex) {
            throw ex;
        }

        SheetsWithoutBankCardsSmartListSummary summaryPrice = daoService.getSummaryPrice(filterWrapper);
        SheetsWithoutBankCardsSmartList entity = new SheetsWithoutBankCardsSmartList();

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

    private List<List<String>> prepareDataForExcel(List<SheetsWithoutBankCardsSmartList> list) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "کد شعبه",
                "نام شعبه",
                " شماره برگه پرداخت",
                "مبلغ وصول",
                "تاريخ موثر",
                "بانک",
                "کد کارگاه",
                "نام کارگاه"

        ));
        int rowCounter = 1;
        for (SheetsWithoutBankCardsSmartList entity : list) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == list.size() ? "مجموع" : String.valueOf(rowCounter));
            row.add(entity.getEdareCode() != null ? entity.getEdareCode() : "-");
            row.add(entity.getEdareName() != null ? entity.getEdareName() : "-");
            row.add(entity.getBrchCode() != null ? entity.getBrchCode() : "-");
            row.add(entity.getBrchName() != null ? entity.getBrchName() : "-");
            row.add(entity.getOrderNumber() != null ? entity.getOrderNumber() : "-");
            row.add(entity.getGlAmount() != null ? entity.getGlAmount().toString() : "-");
            row.add(entity.getGlDate() != null ? entity.getGlDate() : "-");
            row.add(entity.getGlBank() !=null ? entity.getGlBank() : "-");
            row.add(entity.getCustomerId() != null ? entity.getCustomerId() : "-");
            row.add(entity.getCustomerName() != null ? entity.getCustomerName() : "-");


            //row.add(entity.getGlDate2() != null ? entity.getGlDate2().toString() : "-");//TODO----------- from / to Date
            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("برگه های فاقد اعلام وصول بانک - لیست هوشمند -  ");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }
*/

}
