package ir.tamin.incomeBank.service.announcement.branch;

import ir.tamin.incomeBank.dao.announcement.branch.AnnouncementBranchDAOService;
import ir.tamin.incomeBank.model.announcement.branch.AnnouncementBranchSummery;
import ir.tamin.incomeBank.model.announcement.branch.AnnouncementBranchView;
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

/**
 * @author A_Jamali
 */

@Stateless
public class AnnouncementBranchWithParamService {

    @Inject
    private AnnouncementBranchDAOService daoService;

    @Inject
    private FilterAndUserRoleValidator userRoleValidator;


    @Inject
    private ExcelDataProvider excelDataConverter;

    /**************Get List Of Announcement-Branch's DTO***************/
    public Map<String, Object> getBranchByDateRange(String startDate, String endDate, String edareCode, String brchCode) {
        return daoService.getBranchByDateRange(startDate, endDate, edareCode, brchCode );
    }

    /**************Prepare Data For Excel WITH PARAMS***************/
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

    /************** Create Header ***************/
    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("دفتر اعلامیه های ذیحسابی - شعبه-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }

    /************** Load Excel Method To Invoke By REST ***************/
    public StreamingOutput loadExcel(String startDate, String endDate, String edareCode, String brchCode) throws Exception {
        List<AnnouncementBranchView> announcementBranchViewList = null;
        AnnouncementBranchSummery announcementBranchSummery = null;

        try {
            announcementBranchViewList = (List<AnnouncementBranchView>) this.getBranchByDateRange(startDate, endDate, edareCode == null ? "-1" : edareCode, brchCode == null ? "-1" : brchCode).get("data");
        } catch (Exception ex) {
            throw ex;
        }

        announcementBranchSummery = (AnnouncementBranchSummery) daoService.getBranchByDateRange(startDate, endDate, edareCode == null ? "-1" : edareCode, brchCode == null ? "-1" : brchCode).get("sum");
        AnnouncementBranchView summery = new AnnouncementBranchView();
        summery.setSumAmount(announcementBranchSummery.getSumSumAmount());
        summery.setSumCount(announcementBranchSummery.getSumCount());
        summery.setPeyman(announcementBranchSummery.getSumPeyman());
        summery.setPeymanCount(announcementBranchSummery.getSumPeymanCount());
        summery.setEmployees(announcementBranchSummery.getSumEmployees());
        summery.setEmployeesCount(announcementBranchSummery.getSumEmployeesCount());

        announcementBranchViewList.add(summery);

        List<List<String>> rowsForExcel = prepareDataForExcel(announcementBranchViewList);
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
