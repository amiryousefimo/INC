package ir.tamin.incomeBank.service.receiptManagement.branch;

import ir.tamin.incomeBank.dao.receiptManagement.branch.ReceiptManagementBranchDAO;
import ir.tamin.incomeBank.dto.receiptManagement.branch.ReceiptManagementBranchDto;
import ir.tamin.incomeBank.dto.receiptManagement.branch.ReceiptManagementBranchSummary;
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
public class ReceiptManagementBranchParamService {

    @Inject
    private ReceiptManagementBranchDAO daoService;

    @Inject
    private FilterAndUserRoleValidator userRoleValidator;


    @Inject
    private ExcelDataProvider excelDataConverter;

    /**
     *
     * @param startDate given the date from user for AFTER operator
     * @param endDate given the date from user for BEFORE operator
     * @param edareCode give the Branch Code From User
     * @param brchCode give the Branch Code From User
     * @return a map of list of entity , object of summary and size of list as total number of data
     */
    public Map<String, Object> getBranchByDateRange(String startDate, String endDate, String edareCode, String brchCode) {
        return daoService.getBranchByDateRange(startDate, endDate, edareCode, brchCode);
    }

    /**
     *
     * @param dtoList for filling fields and cells for excel report
     * @return provide cells with exact name for object variables
     */
    private List<List<String>> prepareDataForExcel(List<ReceiptManagementBranchDto> dtoList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "کد شعبه",
                "نام شعبه",
                "تاریخ از",
                "تاریخ تا",
                "کارت بانک سیستم سبا",
                "دفتر لیست هوشمند",
                "دفتر اعلامیه کارکنان دولت",
                "دفتر اعلامیه واریز نقدی",
                "دفتر اعلامیه اوراق خزانه",
                "دفتر 3070",
                "جمع مبالغ وصول"
        ));
        int rowCounter = 1;
        for (ReceiptManagementBranchDto announcementBranch : dtoList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == dtoList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(announcementBranch.getEdareCode() != null ? announcementBranch.getEdareCode() : "-");
            row.add(announcementBranch.getEdareName() != null ? announcementBranch.getEdareName() : "-");
            row.add(announcementBranch.getBrchCode() != null ? announcementBranch.getBrchCode() : "-");
            row.add(announcementBranch.getBrchName() != null ? announcementBranch.getBrchName() : "-");
            row.add(announcementBranch.getSoratDate() != null ? announcementBranch.getSoratDate() : "-");
            row.add(announcementBranch.getSoratDate2() != null ? announcementBranch.getSoratDate2() : "-");
            row.add(announcementBranch.getSabaPrc() != null ? announcementBranch.getSabaPrc().toString() : "-");
            row.add(announcementBranch.getIntljPrc() != null ? announcementBranch.getIntljPrc().toString() : "-");
            row.add(announcementBranch.getKarkonanPrc() != null ? announcementBranch.getKarkonanPrc().toString() : "-");
            row.add(announcementBranch.getNaghdiPrc() != null ? announcementBranch.getNaghdiPrc().toString() : "-");
            row.add(announcementBranch.getOraghPrc() != null ? announcementBranch.getOraghPrc().toString() : "-");
            row.add(announcementBranch.getPrc3070() != null ? announcementBranch.getPrc3070().toString() : "-");
            row.add(announcementBranch.getSumPrc() != null ? announcementBranch.getSumPrc().toString() : "-");

            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    /**
     *
     * @return A header for excel report
     */
    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش وصول – شعب-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }

    /**
     *
     * @param startDate given the date from user for AFTER operator
     * @param endDate given the date from user for BEFORE operator
     * @param edareCode give the Branch Code From User
     * @param brchCode give the Branch Code From User
     * @return a new excel report of a dto list
     */
    public StreamingOutput loadExcel(String startDate, String endDate, String edareCode, String brchCode) throws Exception {
        List<ReceiptManagementBranchDto> list = null;
        ReceiptManagementBranchSummary summery = null;

        try {
            list = (List<ReceiptManagementBranchDto>) daoService.getBranchByDateRange(startDate, endDate, edareCode, brchCode).get("data");
        } catch (Exception ex) {
            throw ex;
        }

        summery = (ReceiptManagementBranchSummary) daoService.getBranchByDateRange(startDate, endDate, edareCode , brchCode ).get("sum");
        ReceiptManagementBranchDto entity = new ReceiptManagementBranchDto();
        entity.setSabaPrc(summery.getSabaPrc());
        entity.setPrc3070(summery.getPrc3070());
        entity.setOraghPrc(summery.getOraghPrc());
        entity.setNaghdiPrc(summery.getNaghdiPrc());
        entity.setKarkonanPrc(summery.getKarkonanPrc());
        entity.setIntljPrc(summery.getIntljPrc());
        entity.setSumPrc(summery.getSumPrc());

        list.add(entity);

        List<List<String>> rowsForExcel = prepareDataForExcel(list);
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
