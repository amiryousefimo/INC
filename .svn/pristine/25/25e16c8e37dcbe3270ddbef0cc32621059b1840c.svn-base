package ir.tamin.incomeBank.service.announcement.newspaper;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;

import ir.tamin.incomeBank.dao.announcement.newspaper.AnnouncementNewspaperDao;
import ir.tamin.incomeBank.dto.announcement.newspaper.AnnouncementNewspaperDto;
import ir.tamin.incomeBank.mapper.announcement.newspaper.AnnouncementNewspaperMapper;
import ir.tamin.incomeBank.model.announcement.newspaper.AnnouncementNewspaper;
import ir.tamin.incomeBank.dto.announcement.newspaper.AnnouncementNewspaperSummary;
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
public class AnnouncementNewspaperService {

    @Inject
    private AnnouncementNewspaperDao daoService;

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
        List<AnnouncementNewspaper> entityList = daoService.search(filterWrapper, start, limit, sort);
        List<AnnouncementNewspaperDto> dtoList = AnnouncementNewspaperMapper.mapToDtoList(entityList);
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
    public AnnouncementNewspaperSummary getSummery(FilterWrapper filterWrapper) throws Exception {
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

        List<AnnouncementNewspaper> entityList =null;
        List<AnnouncementNewspaperDto> dtoList =null;

        try {
            entityList = daoService.search(filterWrapper, 0, Integer.MAX_VALUE, null);
            dtoList = AnnouncementNewspaperMapper.mapToDtoList(entityList);
        } catch (Exception ex) {
            throw ex;
        }
        AnnouncementNewspaperSummary summaryPrice = daoService.getSummaryPrice(filterWrapper);
        AnnouncementNewspaper summery = new AnnouncementNewspaper();
        summery.setLetterPrice1(summaryPrice.getLetterPrice1());


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
    private List<List<String>> prepareDataForExcel(List<AnnouncementNewspaperDto> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(


                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "کد شعبه",
                "نام شعبه",
                "شرح اعلامیه",
                "مبلغ",
                "کد کارگاه",
                "نام کارگاه",
                "تاریخ اعلامیه",
                "تاریخ موثر",
                "شماره واریزی",
                "شماره برگه پرداخت",
                "تاریخ عملکرد لیست"

        ));
        int rowCounter = 1;
        for (AnnouncementNewspaperDto dto : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dto.getEdareCode() != null ? dto.getEdareCode() : "-");
            row.add(dto.getEdareName() != null ? dto.getEdareName() : "-");
            row.add(dto.getBrchCode() != null ? dto.getBrchCode() : "-");
            row.add(dto.getBrchName() != null ? dto.getBrchName() : "-");
            row.add(dto.getElamDesc() != null ? dto.getElamDesc() : "-");
            row.add(dto.getLetterPrice1() != null ? dto.getLetterPrice1().toString() : "-");
            row.add(dto.getRwshId() != null ? dto.getRwshId() : "-");
            row.add(dto.getLetterName() != null ? dto.getLetterName() : "-");
            row.add(dto.getLetterDate() != null ? dto.getLetterDate() : "-");
            row.add(dto.getLetterLDate() != null ? dto.getLetterLDate() : "-");
            row.add(dto.getChqNo() != null ? dto.getChqNo() : "-");
            row.add(dto.getOrdNo() != null ? dto.getOrdNo() : "-");
            row.add(dto.getFunctionalDate() != null ? dto.getFunctionalDate() : "-");
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
        reportHeader.setTitle("دفتر روزنامه اعلامیه های ذی حسابی-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }


}
