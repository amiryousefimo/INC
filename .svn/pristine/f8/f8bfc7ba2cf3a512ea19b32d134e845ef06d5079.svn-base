package ir.tamin.incomeBank.service.saba.details;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.saba.details.SabaDetailsDAO;
import ir.tamin.incomeBank.dto.saba.details.SabaDetailsDto;
import ir.tamin.incomeBank.dto.saba.details.SabaDetailsSummary;
import ir.tamin.incomeBank.mapper.saba.details.SabaDetailsMapper;
import ir.tamin.incomeBank.model.saba.details.SabaDetails;
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
public class SabaDetailsService {
    @Inject
    private ExcelDataProvider excelDataConverter;

    public Map<String, List<Map<String, String>>> getBank() {
        Map<String, List<Map<String, String>>> map = new HashMap<>();
        map.put("list", daoService.getBankDetails());
        return map;
    }
    @Inject
    private SabaDetailsDAO daoService;

    @Inject
    private FilterAndUserRoleValidator filterAndUserRoleValidator;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        filterAndUserRoleValidator.filterIsValid(filterWrapper);
        Map<String, Object> map = new HashMap<>();
        List<SabaDetails> announcementEdareKolList = daoService.search(filterWrapper, start, limit, sort);
        List<SabaDetailsDto> dtoList = SabaDetailsMapper.mapToDtoList(announcementEdareKolList);

        try {
            map.put("list", dtoList);
            map.put("total", daoService.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(ReceiptPaperRestService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }

    public SabaDetailsSummary getSummery(FilterWrapper filterWrapper) throws Exception {
        filterAndUserRoleValidator.filterIsValid(filterWrapper);
        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(ReceiptPaperRestService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

//    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {
//        filterAndUserRoleValidator.filterIsValid(filterWrapper);
//        List<SabaDetails> list = null;
//        SabaDetails summery = new SabaDetails();
//
//        try {
//            list = (List<SabaDetails>) this.getAll(filterWrapper, 0, Integer.MAX_VALUE, null).get("list");
//            summery.setSoratPrice1(daoService.getSummaryPrice(filterWrapper).getSoratPrice1());
//            summery.setSoratPrice2(daoService.getSummaryPrice(filterWrapper).getSoratPrice2());
//            list.add(summery);
//        } catch (Exception ex) {
//            throw ex;
//        }
//
//        try {
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            HSSFSheet sheet = workbook.createSheet("sheet 1");
//            HSSFCellStyle style = workbook.createCellStyle();
//            HSSFCellStyle style2 = workbook.createCellStyle();
//            HSSFFont font = workbook.createFont();
//
//            font.setFontName("Arial");
//            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
//            font.setColor(HSSFColor.BLACK.index);
//
//            style.setFont(font);
//            style.setBorderBottom(CellStyle.BORDER_THIN);
//            style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
//            style.setBorderLeft(CellStyle.BORDER_THIN);
//            style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
//            style.setBorderRight(CellStyle.BORDER_THIN);
//            style.setRightBorderColor(IndexedColors.BLACK.getIndex());
//            style.setBorderTop(CellStyle.BORDER_THIN);
//            style.setTopBorderColor(IndexedColors.BLACK.getIndex());
//
//            HSSFColor lightGray = setColor(workbook, (byte) 230, (byte) 230, (byte) 230);
//
//            style.setFillForegroundColor(lightGray.getIndex());
//            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//            style.setAlignment(CellStyle.ALIGN_CENTER);
//            HSSFFont font2 = workbook.createFont();
//            font2.setFontName("Arial");
//            font2.setColor(HSSFColor.BLACK.index);
//            style2.setFont(font2);
//            style2.setBorderBottom(CellStyle.BORDER_THIN);
//            style2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
//            style2.setBorderLeft(CellStyle.BORDER_THIN);
//            style2.setLeftBorderColor(IndexedColors.BLACK.getIndex());
//            style2.setBorderRight(CellStyle.BORDER_THIN);
//            style2.setRightBorderColor(IndexedColors.BLACK.getIndex());
//            style2.setBorderTop(CellStyle.BORDER_THIN);
//            style2.setTopBorderColor(IndexedColors.BLACK.getIndex());
//            style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
//            style2.setAlignment(CellStyle.ALIGN_CENTER);
//            HSSFColor white = setColor(workbook, (byte) 255, (byte) 255, (byte) 255);
//            style2.setFillForegroundColor(white.getIndex());
//            sheet.setRightToLeft(true);
//            Row firstRow = sheet.createRow(0);
//            Cell cell0 = firstRow.createCell(0);
//            cell0.setCellStyle(style);
//            cell0.setCellValue("#");
//
//            Cell cell1 = firstRow.createCell(1);
//            cell1.setCellStyle(style);
//            cell1.setCellValue("کد اداره کل");
//
//            Cell cell2 = firstRow.createCell(2);
//            cell2.setCellStyle(style);
//            cell2.setCellValue("نام اداره کل");
//
//            Cell cell3 = firstRow.createCell(3);
//            cell3.setCellStyle(style);
//            cell3.setCellValue("کد شعبه ");
//
//            Cell cell4 = firstRow.createCell(4);
//            cell4.setCellStyle(style);
//            cell4.setCellValue("نام شعبه ");
//
//            Cell cell5 = firstRow.createCell(5);
//            cell5.setCellStyle(style);
//            cell5.setCellValue(" شماره برگه پرداخت");
//
//            Cell cell6 = firstRow.createCell(6);
//            cell6.setCellStyle(style);
//            cell6.setCellValue("تاریخ وصول  ");
//
//            Cell cell7 = firstRow.createCell(7);
//            cell7.setCellStyle(style);
//            cell7.setCellValue("مبلغ بدهکار");
//
//            Cell cell8 = firstRow.createCell(8);
//            cell8.setCellStyle(style);
//            cell8.setCellValue("مبلغ بستانکار  ");
//
//            Cell cell9 = firstRow.createCell(8);
//            cell9.setCellStyle(style);
//            cell9.setCellValue("تاریخ موثر وصول  ");
//
//            Cell cell10 = firstRow.createCell(9);
//            cell10.setCellStyle(style);
//            cell10.setCellValue("نام بانک  ");
//
//            Cell cell11 = firstRow.createCell(9);
//            cell11.setCellStyle(style);
//            cell11.setCellValue("کد بانک  ");
//
//            List<SabaDetailsDto> dtoList = SabaDetailsMapper.mapToDtoList(list);
//
//            for (int i = 0; i <= dtoList.size() - 1; i++) {
//                Row row = sheet.createRow(i + 1);
//                for (int c = 0; c < firstRow.getPhysicalNumberOfCells(); c++) {
//                    Cell cell = row.createCell(c);
//                    cell.setCellStyle(style2);
//                    if (c == 0) {
//                        cell.setCellValue(dtoList.get(i).getRowNum());
//                    } else if (c == 1) {
//                        cell.setCellValue(dtoList.get(i).getEdareCode());
//                    } else if (c == 2) {
//                        cell.setCellValue(dtoList.get(i).getEdareName());
//                    } else if (c == 3) {
//                        cell.setCellValue(dtoList.get(i).getBrchCode());
//                    } else if (c == 4) {
//                        cell.setCellValue(dtoList.get(i).getBrchName());
//                    } else if (c == 5) {
//                        cell.setCellValue(dtoList.get(i).getSoratOrdPay());
//                    } else if (c == 6) {
//                        cell.setCellValue(dtoList.get(i).getSoratDate());
//                    } else if (c == 7) {
//                        cell.setCellValue(dtoList.get(i).getSoratPrice1().toString());
//                    } else if (c == 8) {
//                        cell.setCellValue(dtoList.get(i).getSoratPrice2().toString());
//                    } else if (c == 9) {
//                        cell.setCellValue(dtoList.get(i).getSoratEfDate());
//                    } else if (c == 10) {
//                        cell.setCellValue(dtoList.get(i).getBankName());
//                    }else if (c == 11) {
//                        cell.setCellValue(dtoList.get(i).getBankCode());
//                    }
//                }
//
//            }
//
//            return output -> workbook.write(output);
//        } catch (Exception ex) {
//            Logger.getLogger(SabaDetailsService.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
//            return null;
//        }
//    }

    //    public HSSFColor setColor(HSSFWorkbook workbook, byte r, byte g, byte b) {
//        HSSFPalette palette = workbook.getCustomPalette();
//        HSSFColor hssfColor = null;
//        try {
//            hssfColor = palette.findColor(r, g, b);
//            if (hssfColor == null) {
//                palette.setColorAtIndex(HSSFColor.LAVENDER.index, r, g, b);
//                hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
//            }
//        } catch (Exception e) {
//            //logger.error(e);
//        }
//
//        return hssfColor;
//    }
//
//    /***********                 ***************************************************** */
//    @Inject
//    private ExcelDataProvider excelDataConverter;
//    public Map<String, List<Map<String, String>>> getDebitReasonDescAndCode() {
//        Map<String, List<Map<String, String>>> map = new HashMap<>();
//        map.put("list", daoService.getBankDetails());
//        return map;
//    }
//    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {
//
//        List<SabaDetails> entityList =null;
//        List<SabaDetailsDto> dtoList =null;
//
//        try {
//            entityList =daoService.search(filterWrapper, 0, Integer.MAX_VALUE, null);
//            dtoList = SabaDetailsMapper.mapToDtoList(entityList);
//        } catch (Exception ex) {
//            throw ex;
//        }
//        SabaDetailsSummary summaryPrice = daoService.getSummaryPrice(filterWrapper);
//        SabaDetails entity = new SabaDetails();
//        entity.setSoratPrice1(summaryPrice.getSoratPrice1());
//        entity.setSoratPrice2(summaryPrice.getSoratPrice2());
//
//        entityList.add(entity);
//
//        List<List<String>> rowsForExcel = prepareDataForExcel(dtoList);
//        ExcelReportHeader reportHeader = createReportHeaderDetails();
//
//        StreamingOutput streamingOutPut = new StreamingOutput() {
//            final Workbook workbook = excelDataConverter.provideExcelFrom(reportHeader, rowsForExcel);
//
//            @Override
//            public void write(OutputStream output) throws IOException, WebApplicationException {
//                workbook.write(output);
//            }
//        };
//
//        return streamingOutPut;
//    }

    /**
     * @param dtoList for prepare data for cells of excel report
     * @return data for excel report
     */
    private List<List<String>> prepareDataForExcel(List<SabaDetailsDto> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                //- کد اداره کل- نام اداره کل –کد شعبه – نام شعبه -  شماره برگه پرداخت  - تاریخ وصول  – مبلغ بدهکار- مبلغ بستانکار - – تاریخ موثر وصول  - نام بانک
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "کد شعبه",
                "نام شعبه",
                "شماره برگه پرداخت",
                "تاریخ وصول",
                "مبلغ بدهکار",
                "مبلغ بستانکار",
                "تاریخ موثر وصول",
                "نام بانک"
        ));
        int rowCounter = 1;
        for (SabaDetailsDto dto : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dto.getEdareCode() != null ? dto.getEdareCode() : "-");
            row.add(dto.getEdareName() != null ? dto.getEdareName() : "-");
            row.add(dto.getBrchCode() != null ? dto.getBrchCode() : "-");
            row.add(dto.getBrchName() != null ? dto.getBrchName() : "-");
            row.add(dto.getSoratOrdPay() != null ? dto.getSoratOrdPay() : "-");
            row.add(dto.getSoratDate() != null ? dto.getSoratDate() : "-");
            row.add(dto.getSoratPrice1() != null ? dto.getSoratPrice1().toString() : "-");
            row.add(dto.getSoratPrice2() != null ? dto.getSoratPrice2().toString() : "-");
            row.add(dto.getSoratEfDate() != null ? dto.getSoratEfDate() : "-");
            row.add(dto.getBankName() != null ? dto.getBankName() : "-");

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

    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {

        List<SabaDetails> entityList = null;
        List<SabaDetailsDto> dtoList = null;

        try {
            entityList = daoService.search(filterWrapper, 0, Integer.MAX_VALUE, null);
            dtoList = SabaDetailsMapper.mapToDtoList(entityList);
        } catch (Exception ex) {
            throw ex;
        }
        SabaDetailsSummary summaryPrice = daoService.getSummaryPrice(filterWrapper);
        SabaDetails entity = new SabaDetails();
        entity.setSoratPrice1(summaryPrice.getSoratPrice1());
        entity.setSoratPrice2(summaryPrice.getSoratPrice2());

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
}
