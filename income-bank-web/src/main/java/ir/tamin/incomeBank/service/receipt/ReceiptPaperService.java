package ir.tamin.incomeBank.service.receipt;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.receipt.ReceiptPaperDAOService;
import ir.tamin.incomeBank.model.receipt.ReceiptPaper;
import ir.tamin.incomeBank.model.receipt.ReceiptPaperSummery;
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
public class ReceiptPaperService {

    @Inject
    private ReceiptPaperDAOService receiptDAOService;

    @Inject
    private ExcelDataProvider excelDataConverter;
    @Inject
    private FilterAndUserRoleValidator userRoleValidator;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", receiptDAOService.search(filterWrapper, start, limit, sort));
            map.put("total", receiptDAOService.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(ReceiptPaperRestService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }

    public ReceiptPaperSummery getSummery (FilterWrapper filterWrapper) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        try {
            return receiptDAOService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(ReceiptPaperRestService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {
        userRoleValidator.filterIsValid(filterWrapper);
        List<ReceiptPaper> receiptList = null;
        ReceiptPaperSummery receiptSummery = null;

        try {
            receiptList = (List<ReceiptPaper>) this.getAll(filterWrapper, 0, Integer.MAX_VALUE, null).get("list");
        } catch (Exception ex) {
            throw ex;
        }

        receiptSummery = receiptDAOService.getSummaryPrice(filterWrapper);
        ReceiptPaper summery = new ReceiptPaper();
        summery.setOrderAmount(receiptSummery.getOrderAmount());
        summery.setGovernmentAmount(receiptSummery.getGovernmentAmount());
        summery.setOrderAmount1(receiptSummery.getOrderAmount1());
        summery.setOrderAmount2(receiptSummery.getOrderAmount2());
        summery.setOrderAmount3(receiptSummery.getOrderAmount3());
        summery.setOrderAmount4(receiptSummery.getOrderAmount4());

        summery.setGovernmentAmountEmployee01(receiptSummery.getGovernmentAmountEmployee01());
        summery.setGovernmentAmountInsured01(receiptSummery.getGovernmentAmountInsured01());

        summery.setGovernmentAmountEmployee02(receiptSummery.getGovernmentAmountEmployee02());
        summery.setGovernmentAmountInsured02(receiptSummery.getGovernmentAmountInsured02());

        summery.setGovernmentAmountEmployee03(receiptSummery.getGovernmentAmountEmployee03());
        summery.setGovernmentAmountInsured03(receiptSummery.getGovernmentAmountInsured03());

        summery.setGovernmentAmountEmployee04(receiptSummery.getGovernmentAmountEmployee04());
        summery.setGovernmentAmountInsured04(receiptSummery.getGovernmentAmountInsured04());

        summery.setGovernmentAmountEmployee05(receiptSummery.getGovernmentAmountEmployee05());
        summery.setGovernmentAmountInsured05(receiptSummery.getGovernmentAmountInsured05());

        summery.setGovernmentAmountEmployee06(receiptSummery.getGovernmentAmountEmployee06());
        summery.setGovernmentAmountInsured06(receiptSummery.getGovernmentAmountInsured06());

        summery.setGovernmentAmountEmployee07(receiptSummery.getGovernmentAmountEmployee07());
        summery.setGovernmentAmountInsured07(receiptSummery.getGovernmentAmountInsured07());

        summery.setGovernmentAmountEmployee08(receiptSummery.getGovernmentAmountEmployee08());
        summery.setGovernmentAmountInsured08(receiptSummery.getGovernmentAmountInsured08());

        summery.setGovernmentAmountEmployee09(receiptSummery.getGovernmentAmountEmployee09());
        summery.setGovernmentAmountInsured09(receiptSummery.getGovernmentAmountInsured09());

        summery.setGovernmentAmountEmployee10(receiptSummery.getGovernmentAmountEmployee10());
        summery.setGovernmentAmountInsured10(receiptSummery.getGovernmentAmountInsured10());

        summery.setGovernmentAmountEmployee11(receiptSummery.getGovernmentAmountEmployee11());
        summery.setGovernmentAmountInsured11(receiptSummery.getGovernmentAmountInsured11());

        summery.setGovernmentAmountEmployee12(receiptSummery.getGovernmentAmountEmployee12());
        summery.setGovernmentAmountInsured12(receiptSummery.getGovernmentAmountInsured12());

        summery.setGovernmentAmountEmployee13(receiptSummery.getGovernmentAmountEmployee13());
        summery.setGovernmentAmountInsured13(receiptSummery.getGovernmentAmountInsured13());

        summery.setGovernmentAmountEmployee14(receiptSummery.getGovernmentAmountEmployee14());
        summery.setGovernmentAmountInsured14(receiptSummery.getGovernmentAmountInsured14());

        summery.setGovernmentAmountEmployee15(receiptSummery.getGovernmentAmountEmployee15());
        summery.setGovernmentAmountInsured15(receiptSummery.getGovernmentAmountInsured15());

        summery.setGovernmentAmountEmployee16(receiptSummery.getGovernmentAmountEmployee16());
        summery.setGovernmentAmountInsured16(receiptSummery.getGovernmentAmountInsured16());

        summery.setGovernmentAmountEmployee17(receiptSummery.getGovernmentAmountEmployee17());
        summery.setGovernmentAmountInsured17(receiptSummery.getGovernmentAmountInsured17());

        summery.setGovernmentAmountEmployee18(receiptSummery.getGovernmentAmountEmployee18());
        summery.setGovernmentAmountInsured18(receiptSummery.getGovernmentAmountInsured18());

        summery.setGovernmentAmountEmployee19(receiptSummery.getGovernmentAmountEmployee19());
        summery.setGovernmentAmountInsured19(receiptSummery.getGovernmentAmountInsured19());

        summery.setGovernmentAmountEmployee20(receiptSummery.getGovernmentAmountEmployee20());
        summery.setGovernmentAmountInsured20(receiptSummery.getGovernmentAmountInsured20());
        summery.setCountIsu(receiptSummery.getCountIsu());




        receiptList.add(summery);

        List<List<String>> rowsForExcel = prepareDataForExcel(receiptList);
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

    private List<List<String>> prepareDataForExcel(List<ReceiptPaper> receiptList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد شعبه",
                "نام شعبه",
                "نام کارگاه",
                "شماره کارگاه",
                "ردیف پیمان",
                "شماره برگه",
                "تاریخ موثر",
                "تاریخ وصول",
                "مبلغ 27 درصد حق بیمه",
                "مبلغ کل وصول",
                "مبلغ حق بیمه وصول",
                "3 درصد دولت",
                "مبلغ کمک دولت",
                "مبلغ پورسانتاژ",
                "3 درصد بیکاری",
                "جریمه تاخیر لیست",
                "جریمه تاخیر پرداخت",
                "تعداد بیمه شده",
                "تعداد کمک دولت",
                "واریز خزانه دولت",
                "وضعیت وصول",
                "کد بانک",
                "نام بانک",
                "تاریخ دریافت از سیستم لیست هوشمند",
                "کد اداره کل",
                "نام اداره کل",
                "ماه عملکرد لیست",
                "نوع پورسانتاژ",
                "وضعیت سند",
                "عنوان کمک دولت (01)",
                "مبلغ سهم کارفرما (01)",
                "تعداد بیمه شده مشمول (01)",
                "مبلغ سهم بیمه شده (01)",
                "عنوان کمک دولت (02)",
                "مبلغ سهم کارفرما (02)",
                "تعداد بیمه شده مشمول (02)",
                "مبلغ سهم بیمه شده (02)",
                "عنوان کمک دولت (03)",
                "مبلغ سهم کارفرما (03)",
                "تعداد بیمه شده مشمول (03)",
                "مبلغ سهم بیمه شده (03)",
                "عنوان کمک دولت (04)",
                "مبلغ سهم کارفرما (04)",
                "تعداد بیمه شده مشمول (04)",
                "مبلغ سهم بیمه شده (04)",
                "عنوان کمک دولت (05)",
                "مبلغ سهم کارفرما (05)",
                "تعداد بیمه شده مشمول (05)",
                "مبلغ سهم بیمه شده (05)",
                "عنوان کمک دولت (06)",
                "مبلغ سهم کارفرما (06)",
                "تعداد بیمه شده مشمول (06)",
                "مبلغ سهم بیمه شده (06)",
                "عنوان کمک دولت (07)",
                "مبلغ سهم کارفرما (07)",
                "تعداد بیمه شده مشمول (07)",
                "مبلغ سهم بیمه شده (07)",
                "عنوان کمک دولت (08)",
                "مبلغ سهم کارفرما (08)",
                "تعداد بیمه شده مشمول (08)",
                "مبلغ سهم بیمه شده (08)",
                "عنوان کمک دولت (09)",
                "مبلغ سهم کارفرما (09)",
                "تعداد بیمه شده مشمول (09)",
                "مبلغ سهم بیمه شده (09)",
                "عنوان کمک دولت (10)",
                "مبلغ سهم کارفرما (10)",
                "تعداد بیمه شده مشمول (10)",
                "مبلغ سهم بیمه شده (10)",
                "عنوان کمک دولت (11)",
                "مبلغ سهم کارفرما (11)",
                "تعداد بیمه شده مشمول (11)",
                "مبلغ سهم بیمه شده (11)",
                "عنوان کمک دولت (12)",
                "مبلغ سهم کارفرما (12)",
                "تعداد بیمه شده مشمول (12)",
                "مبلغ سهم بیمه شده (12)",
                "عنوان کمک دولت (13)",
                "مبلغ سهم کارفرما (13)",
                "تعداد بیمه شده مشمول (13)",
                "مبلغ سهم بیمه شده (13)",
                "عنوان کمک دولت (14)",
                "مبلغ سهم کارفرما (14)",
                "تعداد بیمه شده مشمول (14)",
                "مبلغ سهم بیمه شده (14)",
                "عنوان کمک دولت (15)",
                "مبلغ سهم کارفرما (15)",
                "تعداد بیمه شده مشمول (15)",
                "مبلغ سهم بیمه شده (15)",
                "عنوان کمک دولت (16)",
                "مبلغ سهم کارفرما (16)",
                "تعداد بیمه شده مشمول (16)",
                "مبلغ سهم بیمه شده (16)",
                "عنوان کمک دولت (17)",
                "مبلغ سهم کارفرما (17)",
                "تعداد بیمه شده مشمول (17)",
                "مبلغ سهم بیمه شده (18)",
                "عنوان کمک دولت (18)",
                "مبلغ سهم کارفرما (18)",
                "تعداد بیمه شده مشمول (18)",
                "مبلغ سهم بیمه شده (18)",
                "عنوان کمک دولت (19)",
                "مبلغ سهم کارفرما (19)",
                "تعداد بیمه شده مشمول (19)",
                "مبلغ سهم بیمه شده (19)",
                "عنوان کمک دولت (20)",
                "مبلغ سهم کارفرما (20)",
                "تعداد بیمه شده مشمول (20)",
                "مبلغ سهم بیمه شده (20)"

        ));
        int rowCounter = 1;
        for (ReceiptPaper receipt : receiptList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == receiptList.size() ? "مجموع" : String.valueOf(rowCounter));
            row.add(receipt.getBranchCode() != null ? receipt.getBranchCode() : "-");//کدشعبه
            row.add(receipt.getBranchName() != null ? receipt.getBranchName() : "-");// نام شعبه
            row.add(receipt.getCustomerName() != null ? receipt.getCustomerName() : "-");// نام کارگاه
            row.add(receipt.getCustomerId() != null ? receipt.getCustomerId() : "-");// شماره کارگاه
            row.add(receipt.getOrderPaymentSequence() != null ? receipt.getOrderPaymentSequence(): "-");// ردیف پیمان
            row.add(receipt.getOrderNo() != null ? receipt.getOrderNo() : "-");//شماره برگه پرداخت
            row.add(receipt.getOrderDate() != null ? DateUtils.stringDateToStringSlashedDate(receipt.getOrderDate()) : "-");//تاریخ موثر
            row.add(receipt.getSoratDate() != null ? receipt.getSoratDate(): "-");//تاریخ وصول
            row.add(receipt.getIsd27() != null ? String.valueOf(receipt.getIsd27()) : "-");//مبلغ 27 درصد حق بیمه
            row.add(receipt.getOrderAmount() != null ? String.valueOf(receipt.getOrderAmount()) : "-");//مبلغ وصول
            row.add(receipt.getOrderAmount1() != null ? String.valueOf(receipt.getOrderAmount1()) : "-");//مبلغ حق بیمه وصول
            row.add(receipt.getGovernment3() != null ? String.valueOf(receipt.getGovernment3()) : "-");//سه درصد دولت
            row.add(receipt.getGovernmentAmount() != null ? String.valueOf(receipt.getGovernmentAmount()) : "-");//مبلغ کمک دولت
            row.add(receipt.getPercentAmount() != null ? String.valueOf(receipt.getPercentAmount()) : "-");//مبلغ پورسانتاژ
            row.add(receipt.getOrderAmount2() != null ? String.valueOf(receipt.getOrderAmount2()) : "-");//مبلغ سه درصد بیمه بیکاری
//todo-----------------------------------------------------------------
            row.add(receipt.getOrderAmount3() != null ? String.valueOf(receipt.getOrderAmount3()) : "-");//جرایم تاخیر لیست
            row.add(receipt.getOrderAmount4() != null ? String.valueOf(receipt.getOrderAmount4()) : "-");//جرایم تاخیر پرداخت
            row.add(receipt.getCountIsu() != null ? String.valueOf(receipt.getCountIsu()) : "-");//تعداد بیمه شده
            row.add(receipt.getCountIsuGov() != null ? receipt.getCountIsuGov(): "-");//تعداد کمک دولت
            row.add(receipt.getKhazane() != null ? receipt.getKhazane().toString().replace("1","هست"): "-");//پرداخت خزانه دولت

            row.add(receipt.getSoratDate() != null ? "وصول شده": "-");//وضعیت وصول
            row.add(receipt.getBankCode() != null ? receipt.getBankCode() : "-");//کد بانک
            row.add(receipt.getBankName() != null ? receipt.getBankName() : "-");//نام بانک
            row.add(receipt.getAddDate() !=null ? receipt.getAddDate(): "-");//تاریخ دریافت از سیستم لیست هوشمند
            row.add(receipt.getEdareCode() != null ? receipt.getEdareCode(): "-");//کد اداره
            row.add(receipt.getEdareName() != null ? receipt.getEdareName(): "-");// نام اداره
            row.add(receipt.getListDate() != null ? receipt.getListDate(): "-");//ماه عملکرد لیست
            row.add(receipt.getPercentType() != null ? receipt.getPercentType(): "-");//نوع پورسانتاژ
            row.add(receipt.getDocumentStatus() != null ? receipt.getDocumentStatus().toString().replace("0" ,"صادر نشده") : "-");//وضعیت سند

            row.add(receipt.getGovernmentDesc01() != null ? receipt.getGovernmentDesc01(): "-");//دولت 01
            row.add(receipt.getGovernmentAmountEmployee01() != null ? String.valueOf(receipt.getGovernmentAmountEmployee01()) : "-");
            row.add(receipt.getGovernmentCount01() != null ? receipt.getGovernmentCount01(): "-");
            row.add(receipt.getGovernmentAmountInsured01() != null ? String.valueOf(receipt.getGovernmentAmountInsured01()) : "-");

            row.add(receipt.getGovernmentDesc02() != null ? receipt.getGovernmentDesc02(): "-");//دولت 02
            row.add(receipt.getGovernmentAmountEmployee02() != null ? String.valueOf(receipt.getGovernmentAmountEmployee02()) : "-");
            row.add(receipt.getGovernmentCount02() != null ? receipt.getGovernmentCount02(): "-");
            row.add(receipt.getGovernmentAmountInsured02() != null ? String.valueOf(receipt.getGovernmentAmountInsured02()) : "-");

            row.add(receipt.getGovernmentDesc03() != null ? receipt.getGovernmentDesc03(): "-");//دولت 03
            row.add(receipt.getGovernmentAmountEmployee03() != null ? String.valueOf(receipt.getGovernmentAmountEmployee03()) : "-");
            row.add(receipt.getGovernmentCount03() != null ? receipt.getGovernmentCount03(): "-");
            row.add(receipt.getGovernmentAmountInsured03() != null ? String.valueOf(receipt.getGovernmentAmountInsured03()) : "-");

            row.add(receipt.getGovernmentDesc04() != null ? receipt.getGovernmentDesc04(): "-");//دولت 04
            row.add(receipt.getGovernmentAmountEmployee04() != null ? String.valueOf(receipt.getGovernmentAmountEmployee04()) : "-");
            row.add(receipt.getGovernmentCount04() != null ? receipt.getGovernmentCount04(): "-");
            row.add(receipt.getGovernmentAmountInsured04() != null ? String.valueOf(receipt.getGovernmentAmountInsured04()) : "-");

            row.add(receipt.getGovernmentDesc05() != null ? receipt.getGovernmentDesc05(): "-");//دولت 05
            row.add(receipt.getGovernmentAmountEmployee05() != null ? String.valueOf(receipt.getGovernmentAmountEmployee05()) : "-");
            row.add(receipt.getGovernmentCount05() != null ? receipt.getGovernmentCount05(): "-");
            row.add(receipt.getGovernmentAmountInsured05() != null ? String.valueOf(receipt.getGovernmentAmountInsured05()) : "-");

            row.add(receipt.getGovernmentDesc06() != null ? receipt.getGovernmentDesc06(): "-");//دولت 06
            row.add(receipt.getGovernmentAmountEmployee06() != null ? String.valueOf(receipt.getGovernmentAmountEmployee06()) : "-");
            row.add(receipt.getGovernmentCount06() != null ? receipt.getGovernmentCount06(): "-");
            row.add(receipt.getGovernmentAmountInsured06() != null ? String.valueOf(receipt.getGovernmentAmountInsured06()) : "-");

            row.add(receipt.getGovernmentDesc07() != null ? receipt.getGovernmentDesc07(): "-");//دولت 07
            row.add(receipt.getGovernmentAmountEmployee07() != null ? String.valueOf(receipt.getGovernmentAmountEmployee07()) : "-");
            row.add(receipt.getGovernmentCount07() != null ? receipt.getGovernmentCount07(): "-");
            row.add(receipt.getGovernmentAmountInsured07() != null ? String.valueOf(receipt.getGovernmentAmountInsured07()) : "-");

            row.add(receipt.getGovernmentDesc08() != null ? receipt.getGovernmentDesc08(): "-");//دولت 08
            row.add(receipt.getGovernmentAmountEmployee08() != null ? String.valueOf(receipt.getGovernmentAmountEmployee08()) : "-");
            row.add(receipt.getGovernmentCount08() != null ? receipt.getGovernmentCount08(): "-");
            row.add(receipt.getGovernmentAmountInsured08() != null ? String.valueOf(receipt.getGovernmentAmountInsured08()) : "-");

            row.add(receipt.getGovernmentDesc09() != null ? receipt.getGovernmentDesc09(): "-");//دولت 09
            row.add(receipt.getGovernmentAmountEmployee09() != null ? String.valueOf(receipt.getGovernmentAmountEmployee09()) : "-");
            row.add(receipt.getGovernmentCount09() != null ? receipt.getGovernmentCount09(): "-");
            row.add(receipt.getGovernmentAmountInsured09() != null ? String.valueOf(receipt.getGovernmentAmountInsured09()) : "-");

            row.add(receipt.getGovernmentDesc10() != null ? receipt.getGovernmentDesc10(): "-");//دولت 10
            row.add(receipt.getGovernmentAmountEmployee10() != null ? String.valueOf(receipt.getGovernmentAmountEmployee10()) : "-");
            row.add(receipt.getGovernmentCount10() != null ? receipt.getGovernmentCount10(): "-");
            row.add(receipt.getGovernmentAmountInsured10() != null ? String.valueOf(receipt.getGovernmentAmountInsured10()) : "-");


            row.add(receipt.getGovernmentDesc11() != null ? receipt.getGovernmentDesc11(): "-");//دولت 11
            row.add(receipt.getGovernmentAmountEmployee11() != null ? String.valueOf(receipt.getGovernmentAmountEmployee11()) : "-");
            row.add(receipt.getGovernmentCount11() != null ? receipt.getGovernmentCount11(): "-");
            row.add(receipt.getGovernmentAmountInsured11() != null ? String.valueOf(receipt.getGovernmentAmountInsured11()) : "-");

            row.add(receipt.getGovernmentDesc12() != null ? receipt.getGovernmentDesc12(): "-");//دولت 12
            row.add(receipt.getGovernmentAmountEmployee12() != null ? String.valueOf(receipt.getGovernmentAmountEmployee12()) : "-");
            row.add(receipt.getGovernmentCount12() != null ? receipt.getGovernmentCount12(): "-");
            row.add(receipt.getGovernmentAmountInsured12() != null ? String.valueOf(receipt.getGovernmentAmountInsured12()) : "-");

            row.add(receipt.getGovernmentDesc13() != null ? receipt.getGovernmentDesc13(): "-");//دولت 13
            row.add(receipt.getGovernmentAmountEmployee13() != null ? String.valueOf(receipt.getGovernmentAmountEmployee13()) : "-");
            row.add(receipt.getGovernmentCount13() != null ? receipt.getGovernmentCount13(): "-");
            row.add(receipt.getGovernmentAmountInsured13() != null ? String.valueOf(receipt.getGovernmentAmountInsured13()) : "-");

            row.add(receipt.getGovernmentDesc14() != null ? receipt.getGovernmentDesc14(): "-");//دولت 14
            row.add(receipt.getGovernmentAmountEmployee14() != null ? String.valueOf(receipt.getGovernmentAmountEmployee14()) : "-");
            row.add(receipt.getGovernmentCount14() != null ? receipt.getGovernmentCount14(): "-");
            row.add(receipt.getGovernmentAmountInsured14() != null ? String.valueOf(receipt.getGovernmentAmountInsured14()) : "-");

            row.add(receipt.getGovernmentDesc15() != null ? receipt.getGovernmentDesc15(): "-");//دولت 15
            row.add(receipt.getGovernmentAmountEmployee15() != null ? String.valueOf(receipt.getGovernmentAmountEmployee15()) : "-");
            row.add(receipt.getGovernmentCount15() != null ? receipt.getGovernmentCount15(): "-");
            row.add(receipt.getGovernmentAmountInsured15() != null ? String.valueOf(receipt.getGovernmentAmountInsured15()) : "-");

            row.add(receipt.getGovernmentDesc16() != null ? receipt.getGovernmentDesc16(): "-");//دولت 16
            row.add(receipt.getGovernmentAmountEmployee16() != null ? String.valueOf(receipt.getGovernmentAmountEmployee16()) : "-");
            row.add(receipt.getGovernmentCount16() != null ? receipt.getGovernmentCount16(): "-");
            row.add(receipt.getGovernmentAmountInsured16() != null ? String.valueOf(receipt.getGovernmentAmountInsured16()) : "-");

            row.add(receipt.getGovernmentDesc17() != null ? receipt.getGovernmentDesc17(): "-");//دولت 17
            row.add(receipt.getGovernmentAmountEmployee17() != null ? String.valueOf(receipt.getGovernmentAmountEmployee17()) : "-");
            row.add(receipt.getGovernmentCount17() != null ? receipt.getGovernmentCount17(): "-");
            row.add(receipt.getGovernmentAmountInsured17() != null ? String.valueOf(receipt.getGovernmentAmountInsured17()) : "-");

            row.add(receipt.getGovernmentDesc18() != null ? receipt.getGovernmentDesc18(): "-");//دولت 18
            row.add(receipt.getGovernmentAmountEmployee18() != null ? String.valueOf(receipt.getGovernmentAmountEmployee18()) : "-");
            row.add(receipt.getGovernmentCount18() != null ? receipt.getGovernmentCount18(): "-");
            row.add(receipt.getGovernmentAmountInsured18() != null ? String.valueOf(receipt.getGovernmentAmountInsured18()) : "-");

            row.add(receipt.getGovernmentDesc19() != null ? receipt.getGovernmentDesc19(): "-");//دولت 19
            row.add(receipt.getGovernmentAmountEmployee19() != null ? String.valueOf(receipt.getGovernmentAmountEmployee19()) : "-");
            row.add(receipt.getGovernmentCount19() != null ? receipt.getGovernmentCount19(): "-");
            row.add(receipt.getGovernmentAmountInsured19() != null ? String.valueOf(receipt.getGovernmentAmountInsured19()) : "-");

            row.add(receipt.getGovernmentDesc20() != null ? receipt.getGovernmentDesc20(): "-");//دولت 05
            row.add(receipt.getGovernmentAmountEmployee20() != null ? String.valueOf(receipt.getGovernmentAmountEmployee20()) : "-");
            row.add(receipt.getGovernmentCount20() != null ? receipt.getGovernmentCount20(): "-");
            row.add(receipt.getGovernmentAmountInsured20() != null ? String.valueOf(receipt.getGovernmentAmountInsured20()) : "-");


            /*if(receipt.getDocumentStatus() != null && receipt.getDocumentStatus() == "0"){
           row.add(receipt.getDocumentStatus() != null ? receipt.getDocumentStatus().toString().replace("0" ,"صادر نشده") : "-");//وضعیت سند

            }else {
                row.add(receipt.getDocumentStatus() != null ? receipt.getDocumentStatus().toString().replace("1", "صادر شده") : "-");//وضعیت سند
            }*/

            //CreateDate :AddDate don't Needed
            //row.add(receipt.getAddDate() != null ? DateUtils.stringDateToStringSlashedDate(receipt.getAddDate().substring(0, 8)) : "-");

            //row.add(receipt.getSysType() != null ? receipt.getSysType(): "-");//TODO--------------



            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش برگه های وصولی");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }

    public Object getSummeryIsd(FilterWrapper filterWrapper) {
        return receiptDAOService.getSummaryIsd(filterWrapper);

    }
}
