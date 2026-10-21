package ir.tamin.incomeBank.service.paymentWorkers.branch;

import ir.tamin.incomeBank.dao.paymentWorkers.branch.PaymentWorkersBranchDAOService;
import ir.tamin.incomeBank.dto.paymentWorkers.branch.PaymentWorkersBranchDto;
import ir.tamin.incomeBank.model.paymentWorkers.branch.PaymentWorkersBranchView;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import org.apache.poi.ss.usermodel.Workbook;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class PaymentWorkersBranchWithParamServiceExcel {

    @Inject
    private PaymentWorkersBranchDAOService daoService;
    @Inject
    private ExcelDataProvider excelDataConverter;

    public Workbook loadExcel(String startDate, String endDate, String edareCode, String brchCode) {
        Map<String, Object> map = null;
        List<PaymentWorkersBranchView> list = getDataList(startDate, endDate, edareCode == null ? "-1" : edareCode, brchCode == null ? "-1" : brchCode);
        try {
            map = daoService.getBranchByDateRange(startDate, endDate, edareCode == null ? "-1" : edareCode, brchCode == null ? "-1" : brchCode);
            if (map != null) {
                list = getDataList(startDate, endDate, edareCode == null ? "-1" : edareCode, brchCode == null ? "-1" : brchCode);
            }
        } catch (Exception eRole) {
            throw new IllegalArgumentException("data is wrong");
        }
        List<List<String>> rowsForExcel = prepareDataForExcel(list);
        ExcelReportHeader reportHeader = createReportHeaderDetails();
        return excelDataConverter.provideExcelFrom(reportHeader, rowsForExcel);

    }

    private List<List<String>> prepareDataForExcel(List<PaymentWorkersBranchView> announcementBranchList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "کد شعبه",
                "نام شعبه",
                "تاریخ وصول از",
                "تاریخ وصول تا",
                "مبلغ وصول",
                "حق بیمه کارگران",
                "20 درصد سهم کارفرما",
                "جریمه",
                "مبلغ کمک دولت",
                "تعداد وصول"
        ));
        int rowCounter = 1;
        for (PaymentWorkersBranchView dtoList : announcementBranchList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == announcementBranchList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dtoList.getEdareCode() != null ? dtoList.getEdareCode() : "-");
            row.add(dtoList.getEdareName() != null ? dtoList.getEdareName() : "-");
            row.add(dtoList.getBrchCode() != null ? dtoList.getBrchCode() : "-");
            row.add(dtoList.getBrchName() != null ? dtoList.getBrchName() : "-");
            row.add(dtoList.getOrdDate1() != null ? dtoList.getOrdDate1() : "-");
            row.add(dtoList.getOrdDate2() != null ? dtoList.getOrdDate2() : "-");
            row.add(dtoList.getSumAmount() != null ? dtoList.getSumAmount().toString() : "-");
            row.add((dtoList.getBimeh7sakht() != null ? dtoList.getBimeh7sakht().toString() : "-"));
            row.add((dtoList.getBimeh20sakht() != null ? dtoList.getBimeh20sakht().toString() : "-"));
            row.add((dtoList.getJarimehSakht() != null ? dtoList.getJarimehSakht().toString() : "-"));
            row.add((dtoList.getSumAmtGov() != null ? dtoList.getSumAmtGov().toString() : "-"));
            row.add(dtoList.getCountOrderNumber() != null ? dtoList.getCountOrderNumber().toString() : "-");


            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("دفاتر وصولی سرجمع روزانه- شعبه-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }

    private List<PaymentWorkersBranchView> getDataList(String startDate, String endDate, String edareCode , String brchCode) {
        return (List<PaymentWorkersBranchView>) daoService.getBranchByDateRange(startDate, endDate, edareCode,brchCode).get("data");

    }
}
