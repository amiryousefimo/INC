package ir.tamin.incomeBank.service.paymentWorkers.edare;

import ir.tamin.incomeBank.dao.paymentWorkers.branch.PaymentWorkersBranchDAOService;
import ir.tamin.incomeBank.dao.paymentWorkers.edare.PaymentWorkersEdareDAOService;
import ir.tamin.incomeBank.dto.paymentWorkers.edare.PaymentWorkersEdareDto;
import ir.tamin.incomeBank.model.paymentWorkers.branch.PaymentWorkersBranchView;
import ir.tamin.incomeBank.model.paymentWorkers.edare.PaymentWorkersEdareView;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import org.apache.poi.ss.usermodel.Workbook;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class PaymentWorkersEdareWithParamServiceExcel {

    @Inject
    private PaymentWorkersEdareDAOService daoService;
    @Inject
    private ExcelDataProvider excelDataConverter;

    public Workbook loadExcel(String startDate, String endDate, String edareCode) {
        Map<String, Object> map = null;
        List<PaymentWorkersEdareDto> list = getDataList(startDate, endDate, edareCode == null ? "-1" : edareCode);
        List<List<String>> rowsForExcel = prepareDataForExcel(list);
        ExcelReportHeader reportHeader = createReportHeaderDetails();
        return excelDataConverter.provideExcelFrom(reportHeader, rowsForExcel);
    }

    private List<List<String>> prepareDataForExcel(List<PaymentWorkersEdareDto> announcementEdareList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد اداره کل",
                "نام اداره کل",
                "تاریخ وصول از",
                "تاریخ وصول تا",
                "جمع وصول",
                "حق بیمه کارگران",
                "20 درصد سهم کارفرما",
                "جریمه",
                "مبلغ کمک دولت",
                "تعداد وصول"
        ));
        int rowCounter = 1;
        for (PaymentWorkersEdareDto dtoList : announcementEdareList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == announcementEdareList.size() + 1 ? "مجموع" : String.valueOf(rowCounter));
            row.add(dtoList.getEdareCode() != null ? dtoList.getEdareCode() : "-");
            row.add(dtoList.getEdareName() != null ? dtoList.getEdareName() : "-");
            row.add(dtoList.getOrderDate() != null ? dtoList.getOrderDate() : "-");
            row.add(dtoList.getOrderDate2() != null ? dtoList.getOrderDate2() : "-");
            row.add(dtoList.getSumAmount() != null ? dtoList.getSumAmount().toString() : "-");
            row.add((dtoList.getBimeh7sakht() != null ? dtoList.getBimeh7sakht().toString() : "-"));
            row.add((dtoList.getBimeh20sakht() != null ? dtoList.getBimeh20sakht().toString() : "-"));
            row.add((dtoList.getJarimehSakht() != null ? dtoList.getJarimehSakht().toString() : "-"));
            row.add((dtoList.getSumAmtGov() != null ? dtoList.getSumAmtGov().toString() : "-"));
            row.add(dtoList.getCountOrdNumber() != null ? dtoList.getCountOrdNumber().toString() : "-");


            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("دفاتر وصولی سرجمع روزانه- ادارات کل-");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }

    private List<PaymentWorkersEdareDto> getDataList(String startDate, String endDate, String edareCode ) {
        return (List<PaymentWorkersEdareDto>) daoService.getEdareByDateRange(startDate, endDate, edareCode).get("data");

    }
}
