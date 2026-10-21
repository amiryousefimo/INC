package ir.tamin.incomeBank.service.announcement.edare;

import ir.tamin.incomeBank.dao.announcement.setad.AnnouncementEdareDAOService;
import ir.tamin.incomeBank.model.announcement.setad.AnnouncementEdareKolView;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import org.apache.poi.ss.usermodel.Workbook;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class ExcelServiceForAnnouncementEdare {
    @Inject
    private AnnouncementEdareDAOService daoService;

    public Workbook loadExcel(String startDate, String endDate, String edareCode) {

        Map<String, Object> map = null;
        List<AnnouncementEdareKolView> list = getDataList(startDate, endDate, edareCode == null ? "-1" : edareCode);

        try {
            map = daoService.getSetdaByDateRange(startDate, endDate, edareCode == null ? "-1" : edareCode);
            if (map != null ) {
                list = getDataList(startDate, endDate, edareCode == null ? "-1" : edareCode);
            }
        } catch (Exception eRole) {
            throw new IllegalArgumentException("data is wrong");
        }
        if (list.size()==0){
            throw new IllegalArgumentException("مقدار انتخابی خالی است");
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

    private List<List<String>> prepareDataForExcel(List<AnnouncementEdareKolView> announcementBranchList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
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
        for (AnnouncementEdareKolView announcementBranch : announcementBranchList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == announcementBranchList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(announcementBranch.getEdareCode() != null ? announcementBranch.getEdareCode() : "-");
            row.add(announcementBranch.getEdareName() != null ? announcementBranch.getEdareName() : "-");
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


    private List<AnnouncementEdareKolView> getDataList(String startDate, String endDate, String edareCode) {
        return (List<AnnouncementEdareKolView>) daoService.getSetdaByDateRange(startDate, endDate, edareCode).get("data");
    }
}
