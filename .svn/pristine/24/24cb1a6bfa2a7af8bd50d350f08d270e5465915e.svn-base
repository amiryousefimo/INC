package ir.tamin.incomeBank.service.reportingOfficeBasedPayment.department;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;

import ir.tamin.incomeBank.dao.reportingOfficeBasedPayment.department.ReportingOfficeBasedPaymentDepartmentDAOService;
import ir.tamin.incomeBank.model.reportingOfficeBasedPayment.department.ReportingOfficeBasedPaymentDepartment;
import ir.tamin.incomeBank.model.reportingOfficeBasedPayment.department.ReportingOfficeBasedPaymentDepartmentSummary;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.FilterAndUserRoleValidator;
import ir.tamin.incomeBank.ws.rest.receipt.ReceiptPaperRestService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.StreamingOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author A_Jamali
 */

@Stateless
public class ReportingOfficeBasedPaymentDepartmentService {

    @Inject
    private ReportingOfficeBasedPaymentDepartmentDAOService daoService;

    @Inject
    private FilterAndUserRoleValidator userRoleValidator;


    @Inject
    private ExcelDataProvider excelDataConverter;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", daoService.search(filterWrapper, start, limit, sort));
            map.put("total", daoService.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(ReceiptPaperRestService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }

    public ReportingOfficeBasedPaymentDepartmentSummary getSummery(FilterWrapper filterWrapper) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(ReceiptPaperRestService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        List<ReportingOfficeBasedPaymentDepartment> list = null;
        ReportingOfficeBasedPaymentDepartment summery = new ReportingOfficeBasedPaymentDepartment();

        try {
            list = (List<ReportingOfficeBasedPaymentDepartment>) this.getAll(filterWrapper, 0, Integer.MAX_VALUE, null).get("list");
            summery.setSumAmount(daoService.getSummaryPrice(filterWrapper).getSumSumAmount());
            summery.setCountOrderNumber(daoService.getSummaryPrice(filterWrapper).getSumCount());
            list.add(summery);
        } catch (Exception ex) {
            throw ex;
        }

        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("sheet 1");
            HSSFCellStyle style = workbook.createCellStyle();
            HSSFCellStyle style2 = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();

            font.setFontName("Arial");
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font.setColor(HSSFColor.BLACK.index);

            style.setFont(font);
            style.setBorderBottom(CellStyle.BORDER_THIN);
            style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderLeft(CellStyle.BORDER_THIN);
            style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderRight(CellStyle.BORDER_THIN);
            style.setRightBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderTop(CellStyle.BORDER_THIN);
            style.setTopBorderColor(IndexedColors.BLACK.getIndex());

            HSSFColor lightGray = setColor(workbook, (byte) 230, (byte) 230, (byte) 230);

            style.setFillForegroundColor(lightGray.getIndex());
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            style.setAlignment(CellStyle.ALIGN_CENTER);
            HSSFFont font2 = workbook.createFont();
            font2.setFontName("Arial");
            font2.setColor(HSSFColor.BLACK.index);
            style2.setFont(font2);
            style2.setBorderBottom(CellStyle.BORDER_THIN);
            style2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            style2.setBorderLeft(CellStyle.BORDER_THIN);
            style2.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            style2.setBorderRight(CellStyle.BORDER_THIN);
            style2.setRightBorderColor(IndexedColors.BLACK.getIndex());
            style2.setBorderTop(CellStyle.BORDER_THIN);
            style2.setTopBorderColor(IndexedColors.BLACK.getIndex());
            style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
            style2.setAlignment(CellStyle.ALIGN_CENTER);
            HSSFColor white = setColor(workbook, (byte) 255, (byte) 255, (byte) 255);
            style2.setFillForegroundColor(white.getIndex());
            sheet.setRightToLeft(true);
            Row firstRow = sheet.createRow(0);
            Cell cell0 = firstRow.createCell(0);
            cell0.setCellStyle(style);
            cell0.setCellValue("#");
            Cell cell1 = firstRow.createCell(1);
            cell1.setCellStyle(style);
            cell1.setCellValue("کد اداره کل");
            Cell cell2 = firstRow.createCell(2);
            cell2.setCellStyle(style);
            cell2.setCellValue("نام اداره کل");
            Cell cell3 = firstRow.createCell(3);
            cell3.setCellStyle(style);
            cell3.setCellValue("تاریخ ");
            Cell cell4 = firstRow.createCell(4);
            cell4.setCellStyle(style);
            cell4.setCellValue(" موضوع پرداخت  ");
            Cell cell5 = firstRow.createCell(5);
            cell5.setCellStyle(style);
            cell5.setCellValue("شرح موضوع پرداخت  ");
            Cell cell6 = firstRow.createCell(6);
            cell6.setCellStyle(style);
            cell6.setCellValue("مبلغ وصول  ");
            Cell cell7 = firstRow.createCell(7);
            cell7.setCellStyle(style);
            cell7.setCellValue("تعداد وصول  ");



            for (int i = 0; i <= list.size() - 1; i++) {
                Row row = sheet.createRow(i + 1);
                for (int c = 0; c < firstRow.getPhysicalNumberOfCells(); c++) {
                    Cell cell = row.createCell(c);
                    cell.setCellStyle(style2);
                    if (c == 0) {
                        cell.setCellValue(list.get(i).getRowNum());
                    } else if (c == 1) {
                        cell.setCellValue(list.get(i).getEdareCode());
                    } else if (c == 2) {
                        cell.setCellValue(list.get(i).getEdareName());
                    }  else if (c == 3) {
                        cell.setCellValue(list.get(i).getOrdDate());
                    } else if (c == 4) {
                        cell.setCellValue(list.get(i).getPayKind());
                    } else if (c == 5) {
                        cell.setCellValue(list.get(i).getPayKindDSC());
                    }   else if (c == 6) {
                        cell.setCellValue(list.get(i).getSumAmount().toString());
                    } else if (c == 7) {
                        cell.setCellValue(list.get(i).getCountOrderNumber().toString());
                    }
                }

            }

            return output -> workbook.write(output);
        } catch (Exception ex) {
            Logger.getLogger(ReportingOfficeBasedPaymentDepartmentService.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public HSSFColor setColor(HSSFWorkbook workbook, byte r, byte g, byte b) {
        HSSFPalette palette = workbook.getCustomPalette();
        HSSFColor hssfColor = null;
        try {
            hssfColor = palette.findColor(r, g, b);
            if (hssfColor == null) {
                palette.setColorAtIndex(HSSFColor.LAVENDER.index, r, g, b);
                hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
            }
        } catch (Exception e) {
            //logger.error(e);
        }

        return hssfColor;
    }



}
