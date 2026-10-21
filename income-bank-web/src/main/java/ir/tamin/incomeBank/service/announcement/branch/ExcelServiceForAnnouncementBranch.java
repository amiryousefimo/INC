package ir.tamin.incomeBank.service.announcement.branch;

import ir.tamin.incomeBank.dao.announcement.branch.AnnouncementBranchDAOService;
import ir.tamin.incomeBank.model.announcement.branch.AnnouncementBranchView;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import org.apache.poi.ss.usermodel.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class ExcelServiceForAnnouncementBranch {
    @Inject
    AnnouncementBranchDAOService service;

    public Workbook loadExcel(String startDate,String endDate,String edareCode,String brchCode) {

        Map<String, Object> map = null;
        List<AnnouncementBranchView> list = getDataList(startDate, endDate, edareCode==null?"-1":edareCode, brchCode==null?"-1":brchCode);
        try {
            map = service.getBranchByDateRange(startDate, endDate, edareCode==null?"-1":edareCode, brchCode==null?"-1":brchCode);
            if (map != null) {
                list = getDataList(startDate, endDate, edareCode==null?"-1":edareCode, brchCode==null?"-1":brchCode);
            }
        } catch (Exception eRole) {
          throw new IllegalArgumentException("data is wrong");
        }
        List<List<String>> rowsForExcel = prepareDataForExcel(list);
        ExcelReportHeader reportHeader = createReportHeaderDetails();
        return excelDataConverter.provideExcelFrom(reportHeader, rowsForExcel);
    }
    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("دفتر اعلامیه های ذیحسابی - شعبه-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }
    @Inject
    private ExcelDataProvider excelDataConverter;
    private List<List<String>> prepareDataForExcel(List<AnnouncementBranchView> announcementBranchList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "کد شعبه",
                "نام شعبه",
                "تاریخ اعلامیه از",
                "تاریخ اعلامیه تا",
                "مبلغ حق بیمه کارکنان دولت",
                "تعداد اعلامیه کارکنان دولت",
                "مبلغ حق بیمه پیمانکاران ذی حسابی",
                "تعداد اعلامیه پیمانکاران ذی حسابی",
                "جمع مبالغ",
                "تعداد اعلامیه"
        ));
        int rowCounter = 1;
        for (AnnouncementBranchView announcementBranch : announcementBranchList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == announcementBranchList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(announcementBranch.getEdareCode() != null ? announcementBranch.getEdareCode() : "-");
            row.add(announcementBranch.getEdareName() != null ? announcementBranch.getEdareName() : "-");
            row.add(announcementBranch.getBrchCode() != null ? announcementBranch.getBrchCode() : "-");
            row.add(announcementBranch.getBrchName() != null ? announcementBranch.getBrchName() : "-");
            row.add(announcementBranch.getLetterDate1() != null ? announcementBranch.getLetterDate1() : "-");
            row.add(announcementBranch.getLetterDate2() != null ? announcementBranch.getLetterDate2() : "-");
            row.add(announcementBranch.getEmployees() != null ? announcementBranch.getEmployees().toString() : "-");
            row.add(announcementBranch.getEmployeesCount() != null ? announcementBranch.getEmployeesCount().toString() : "-");
            row.add(announcementBranch.getPeyman() != null ? announcementBranch.getPeyman().toString() : "-");
            row.add(announcementBranch.getPeymanCount() != null ? announcementBranch.getPeymanCount().toString() : "-");
            row.add(announcementBranch.getSumAmount() != null ? announcementBranch.getSumAmount().toString() : "-");
            row.add(announcementBranch.getSumCount() != null ? announcementBranch.getSumCount().toString() : "-");

            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

//
//
//    @GET
//    @Path("/excel-export/{startDate}/{endDate}/{edareCode}/{brchCode}")
//    @Produces("application/vnd.ms-excel")
//    public Response exportData(  @PathParam("startDate") String startDate,
//                                 @PathParam("endDate") String endDate,
//                                 @PathParam("edareCode") String edareCode,
//                                 @PathParam("brchCode") String brchCode,
//                                 @Context HttpServletRequest request,
//                                 @Context SecurityContext sc) {
//        List<AnnouncementBranchView> dataList=getDataList( startDate,  endDate,  edareCode,  brchCode);
////        List<Record> dataList = getDataList(); // Get the data list from your data source
//
//        Workbook workbook = createWorkbook(dataList);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//        try {
//            workbook.write(baos);
//            byte[] bytes = baos.toByteArray();
//            return Response.ok(bytes).header("Content-Disposition", "attachment;filename=data.xlsx").build();
//        } catch (IOException e) {
//            // Handle exception
//            return Response.serverError().build();
//        } finally {
//            try {
//                baos.close();
//                workbook.close();
//            } catch (IOException e) {
//                // Handle exception
//            }
//        }
//    }
//
//    private Workbook createWorkbook(List<AnnouncementBranchView> dataList) {
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Data");
//
//        // Create header row
//        Row headerRow = sheet.createRow(0);
//        String[] headers = {"Branch Code", "Branch Name", "Edare Code", "Edare Name", "Letter Date 1",
//                "Letter Date 2", "Employees", "Employees Count", "Peyman", "Peyman Count",
//                "Sum Amount", "Sum Count"};
//        for (int i = 0; i < headers.length; i++) {
//            Cell cell = headerRow.createCell(i);
//            cell.setCellValue(headers[i]);
//        }
//
//        // Create data rows
//        for (int rowIndex = 0; rowIndex < dataList.size(); rowIndex++) {
//            AnnouncementBranchView record = dataList.get(rowIndex);
//            Row row = sheet.createRow(rowIndex + 1);
//
//            row.createCell(0).setCellValue(record.getBrchCode());
//            row.createCell(1).setCellValue(record.getBrchName());
//            row.createCell(2).setCellValue(record.getEdareCode());
//            row.createCell(3).setCellValue(record.getEdareName());
//            row.createCell(4).setCellValue(record.getLetterDate1());
//            row.createCell(5).setCellValue(record.getLetterDate2());
//            row.createCell(6).setCellValue(record.getEmployees().toString());
//            row.createCell(7).setCellValue(record.getEmployeesCount().toString());
//            row.createCell(8).setCellValue(record.getPeyman().toString());
//            row.createCell(9).setCellValue(record.getPeymanCount().toString());
//            row.createCell(10).setCellValue(record.getSumAmount().toString());
//            row.createCell(11).setCellValue(record.getSumCount().toString());
//        }
//
//        return workbook;
//    }

    private List<AnnouncementBranchView> getDataList(String startDate, String endDate, String edareCode, String brchCode) {
      return   (List<AnnouncementBranchView> )service.getBranchByDateRange(startDate,endDate,edareCode,brchCode).get("data");

    }
}
