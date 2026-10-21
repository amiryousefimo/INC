package ir.tamin.incomeBank.service.officeCollectionSystemCenter;

import ir.tamin.framework.domain.proxy.ProxyProcessingException;
import ir.tamin.framework.ws.rest.security.TokenContext;
import ir.tamin.incomeBank.model.officeCollectionSystemCenter.OfficeCollectionSystemCenterModel;
import ir.tamin.incomeBank.service.identityManager.UserBean;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import ir.tamin.incomeBank.util.FarsiExtractor;
import ir.tamin.portal.model.org.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.*;

/**
 * @author f_mousavi
 */

public class OfficeCollectionSystemCenterService {
    @Inject
    EntityManager entityManager;
    @Inject
    private ExcelDataProvider excelDataConverter;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OfficeCollectionSystemCenterService.class);

    @Inject
    private TokenContext tokenContext;

    @Inject
    UserBean userBean;

    @Inject
    FarsiExtractor farsiExtractor;

    public Object getAll(String startDate, String endDate, String edareCode, String brchCode, String bankCode, String brhFlag, String daysFlag, String bnkFlag, String orderNumber) throws Exception {
        try {
            User user = userBean.getUserByUserName(tokenContext.getCurrentUser().getName());
            if (user == null) {
                throw new Exception("User not found.");
            }

            switch (user.getOrganization().getOrganizationCustomerType()) {
                case "Branch":
                    brchCode = user.getOrganization().getCode();
                    edareCode = user.getOrganization().getParent().getCode();
                    break;
                case "Department":
                    edareCode = user.getOrganization().getCode();
                    break;
                case "Company":
                    break;
                default:
                    throw new ProxyProcessingException("403 forbidden", new String[]{});
            }

            List<Object[]> list = entityManager.createNativeQuery("select * from the(select cast (pck_telproc_core.get_saba_pays(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10) as  PAY_DOCS_TBL) from dual)")
                    .setParameter(1, startDate)
                    .setParameter(2, endDate)
                    .setParameter(3, edareCode)
                    .setParameter(4, brchCode)
                    .setParameter(5, daysFlag)
                    .setParameter(6, brhFlag)
                    .setParameter(7, bnkFlag)
                    .setParameter(8, bankCode)
                    .setParameter(9, orderNumber)
                    .setParameter(10, user.getOrganization().getCode())
                    .getResultList();

            List<OfficeCollectionSystemCenterModel> finalList = new ArrayList<>();
            long sumAmountBed = 0L;
            long sumAmountBes = 0L;

            for (Object[] obja : list) {
                sumAmountBed += Long.decode(obja[2].toString());
                sumAmountBes += Long.decode(obja[3].toString());

                OfficeCollectionSystemCenterModel officeCollectionSystemCenterModel = new OfficeCollectionSystemCenterModel();
                officeCollectionSystemCenterModel.setEdKolName(obja[0] != null ? obja[0].toString() : "");
                officeCollectionSystemCenterModel.setBrchName(obja[1] != null ? obja[1].toString() : "");
                officeCollectionSystemCenterModel.setAmountBed(obja[2] != null ? obja[2].toString() : "");
                officeCollectionSystemCenterModel.setAmountBes(obja[3] != null ? obja[3].toString() : "");
                officeCollectionSystemCenterModel.setOrdDate1(obja[4] != null ? obja[4].toString() : "");
                officeCollectionSystemCenterModel.setOrdDate2(obja[5] != null ? obja[5].toString() : "");
                officeCollectionSystemCenterModel.setOrdOrdNo(obja[6] != null ? obja[6].toString() : "");
                officeCollectionSystemCenterModel.setBankName(obja[7] != null ? obja[7].toString() : "");
                finalList.add(officeCollectionSystemCenterModel);
            }

            if (!finalList.isEmpty()) {
                finalList.get(0).setSumAmountBed(sumAmountBed);
                finalList.get(0).setSumAmountBes(sumAmountBes);
            }
            Map<String, Object> dataPage = new HashMap<>();
            dataPage.put("list", finalList);
            dataPage.put("total", finalList.size());
            return dataPage;

        } catch (Exception e) {
            if (e.getCause() != null && e.getCause().getCause() instanceof SQLException) {
                throw new Exception(farsiExtractor.extractFarsiMessage(e.getMessage()));
            }
            throw new Exception("An unexpected error occurred.", e);
        }
    }

    public Object loadExcel(String startDate, String endDate, String orderNumber, String edareCode, String brchCode, String bankCode, String brhFlag, String daysFlag, String bnkFlag) throws Exception {

        User user = userBean.getUserByUserName(tokenContext.getCurrentUser().getName());
        if (user == null) {
            throw new Exception("User not found.");
        }

        switch (user.getOrganization().getOrganizationCustomerType()) {
            case "Branch":
                brchCode = user.getOrganization().getCode();
                edareCode = user.getOrganization().getParent().getCode();
                break;
            case "Department":
                edareCode = user.getOrganization().getCode();
                break;
            case "Company":
                break;
            default:
                throw new ProxyProcessingException("403 forbidden", new String[]{});
        }

        List<Object[]> list;
        List<OfficeCollectionSystemCenterModel> finalList = new ArrayList<>();
//        User user = userBean.getUserByUserName(tokenContext.getCurrentUser().getName());
        switch (user.getOrganization().getOrganizationCustomerType()) {
            case "Branch":
                brchCode = user.getOrganization().getCode();
                break;
            case "Department":
                edareCode = user.getOrganization().getCode();
                break;
            case "Company":
                break;
            default:
                throw new ProxyProcessingException("403 forbidden", new String[]{});
        }
        Long SumAmountBed = 0L;
        Long SumAmountBes = 0L;
        list = entityManager.createNativeQuery("select * from the(select cast (pck_telproc_core.get_saba_pays(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10) as  PAY_DOCS_TBL) from dual)")
                .setParameter(1, startDate)
                .setParameter(2, endDate)
                .setParameter(3, edareCode)
                .setParameter(4, brchCode)
                .setParameter(5, daysFlag)
                .setParameter(6, brhFlag)
                .setParameter(7, bnkFlag)
                .setParameter(8, bankCode)
                .setParameter(9, orderNumber)
                .setParameter(10, user.getOrganization().getCode())
                .getResultList();
        for (Object[] obja : list) {
            SumAmountBed = (SumAmountBed + Long.decode(obja[2].toString()));
            SumAmountBes = (SumAmountBes + Long.decode(obja[3].toString()));
            OfficeCollectionSystemCenterModel officeCollectionSystemCenterModels = new OfficeCollectionSystemCenterModel();
            officeCollectionSystemCenterModels.setEdKolName(obja[0] != null ? obja[0].toString() : "");
            officeCollectionSystemCenterModels.setBrchName(obja[1] != null ? obja[1].toString() : "");
            officeCollectionSystemCenterModels.setAmountBed(obja[2] != null ? obja[2].toString() : "");
            officeCollectionSystemCenterModels.setAmountBes(obja[3] != null ? obja[3].toString() : "");
            officeCollectionSystemCenterModels.setOrdDate1(obja[4] != null ? obja[4].toString() : "");
            officeCollectionSystemCenterModels.setOrdDate2(obja[5] != null ? obja[5].toString() : "");
            officeCollectionSystemCenterModels.setOrdOrdNo(obja[6] != null ? obja[6].toString() : "");
            officeCollectionSystemCenterModels.setBankName(obja[7] != null ? obja[7].toString() : "");
            finalList.add(officeCollectionSystemCenterModels);
        }
        OfficeCollectionSystemCenterModel summery = new OfficeCollectionSystemCenterModel();
        summery.setAmountBed(String.valueOf(SumAmountBed));
        summery.setAmountBes(String.valueOf(SumAmountBes));
        finalList.add(summery);
        List<List<String>> rowsForExcel = prepareDataForExcel(finalList);
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

    private List<List<String>> prepareDataForExcel(List<OfficeCollectionSystemCenterModel> finalList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "اداره کل",
                "شعبه",
                "مبلغ بدهکار",
                "مبلغ بستانکار",
                "تاریخ وصول از",
                "تاریخ وصول تا",
                "برگه پرداخت",
                "بانک عامل"

        ));
        int rowCounter = 1;
        for (OfficeCollectionSystemCenterModel receipt : finalList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == finalList.size() ? "مجموع" : String.valueOf(rowCounter));
            row.add(receipt.getEdKolName() != null ? receipt.getEdKolName() : "-");
            row.add(receipt.getBrchName() != null ? receipt.getBrchName() : "-");
            row.add(receipt.getAmountBed() != null ? receipt.getAmountBed() : "-");
            row.add(receipt.getAmountBes() != null ? receipt.getAmountBes() : "-");
            row.add(receipt.getOrdDate1() != null ? receipt.getOrdDate1().toString() : "-");
            row.add(receipt.getOrdDate2() != null ? receipt.getOrdDate2().toString() : "-");
            row.add(receipt.getOrdOrdNo() != null ? receipt.getOrdOrdNo() : "-");
            row.add(receipt.getBankName() != null ? receipt.getBankName() : "-");
            rows.add(row);
            rowCounter++;
        }

        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش دفتر وصولی های سیستم سبا در مرکز");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }


}
