package ir.tamin.incomeBank.service.announcement.edare;

import ir.tamin.incomeBank.dao.announcement.setad.AnnouncementEdareDAOService;
import ir.tamin.incomeBank.model.announcement.setad.AnnouncementEdareKolSummery;
import ir.tamin.incomeBank.model.announcement.setad.AnnouncementEdareKolView;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
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
public class AnnouncementEdareKolWithParamService {

    @Inject
    private AnnouncementEdareDAOService daoService;


    @Inject
    private ExcelDataProvider excelDataConverter;


    /**************Get List Of Announcement-Edare's DTO***************/

    public Map<String, Object> getSetadByDateRange(String startDate, String endDate, String edareCode) {
        return daoService.getSetdaByDateRange(startDate, endDate, edareCode );
    }

    /**************Prepare Data For Excel WITH PARAMS***************/
    private List<List<String>> prepareDataForExcel(List<AnnouncementEdareKolView> announcementSetadList) {
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
        for (AnnouncementEdareKolView announcementSetad : announcementSetadList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == announcementSetadList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(announcementSetad.getEdareCode() != null ? announcementSetad.getEdareCode() : "-");
            row.add(announcementSetad.getEdareName() != null ? announcementSetad.getEdareName() : "-");
            row.add(announcementSetad.getLetterDate1() != null ? announcementSetad.getLetterDate1() : "-");
            row.add(announcementSetad.getLetterDate2() != null ? announcementSetad.getLetterDate2() : "-");
            row.add(announcementSetad.getEmployees() != null ? announcementSetad.getEmployees().toString() : "-");
            row.add(announcementSetad.getEmployeesCount() != null ? announcementSetad.getEmployeesCount().toString() : "-");
            row.add(announcementSetad.getPeyman() != null ? announcementSetad.getPeyman().toString() : "-");
            row.add(announcementSetad.getPeymanCount() != null ? announcementSetad.getPeymanCount().toString() : "-");
            row.add(announcementSetad.getSumAmount() != null ? announcementSetad.getSumAmount().toString() : "-");
            row.add(announcementSetad.getSumCount() != null ? announcementSetad.getSumCount().toString() : "-");

            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    /************** Create Header ***************/
    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("دفتر اعلامیه های ذیحسابی - ادارات کل-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }

    /************** Load Excel Method To Invoke By REST ***************/
    public StreamingOutput loadExcel(String startDate, String endDate, String edareCode) throws Exception {
        List<AnnouncementEdareKolView> announcementEdareKolViewList = null;
        AnnouncementEdareKolSummery announcementSetadSummery = null;

        try {
            announcementEdareKolViewList = (List<AnnouncementEdareKolView>) this.getSetadByDateRange(startDate, endDate, edareCode == null ? "-1" : edareCode).get("data");
        } catch (Exception ex) {
            throw ex;
        }

        announcementSetadSummery = (AnnouncementEdareKolSummery) daoService.getSetdaByDateRange(startDate, endDate, edareCode == null ? "-1" : edareCode).get("sum");
        AnnouncementEdareKolView summery = new AnnouncementEdareKolView();
        summery.setSumAmount(announcementSetadSummery.getSumSumAmount());
        summery.setSumCount(announcementSetadSummery.getSumCount());
        summery.setPeyman(announcementSetadSummery.getSumPeyman());
        summery.setPeymanCount(announcementSetadSummery.getSumPeymanCount());
        summery.setEmployees(announcementSetadSummery.getSumEmployees());
        summery.setEmployeesCount(announcementSetadSummery.getSumEmployeesCount());

        announcementEdareKolViewList.add(summery);

        List<List<String>> rowsForExcel = prepareDataForExcel(announcementEdareKolViewList);
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
