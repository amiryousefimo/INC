package ir.tamin.incomeBank.service.financialDoc.newFinancialDoc3070;

import ir.tamin.framework.cdi.util.MessageBundle;
import ir.tamin.framework.cdi.util.WebProperties;
import ir.tamin.framework.core.util.Bundle;
import ir.tamin.framework.ws.rest.json.Filter;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.framework.ws.rest.security.TokenContext;
import ir.tamin.incomeBank.dao.financialDoc.newFinancialDoc3070.FinancialDocSorat3070DAOService;
import ir.tamin.incomeBank.model.financialDoc.*;
import ir.tamin.incomeBank.model.financialDoc.newFinancialDoc3070.FinancialDocSorat3070;
import ir.tamin.incomeBank.model.financialDoc.newFinancialDoc3070.FinancialDocStatus;
import ir.tamin.incomeBank.service.financialDoc.FinancialDocService;
import ir.tamin.incomeBank.service.identityManager.UserBean;
import ir.tamin.incomeBank.service.jdbc.StoredProcedure;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ExcelDataProvider;
import ir.tamin.incomeBank.util.ExcelReportHeader;
import ir.tamin.incomeBank.util.FilterAndUserRoleValidator;
import ir.tamin.incomeBank.ws.soap.*;
import ir.tamin.portal.model.org.User;
import org.apache.poi.ss.usermodel.Workbook;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Types;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author M_Goharriz
 */


public class FinancialDocSorat3070Service {

    @Inject
    private FinancialDocSorat3070DAOService daoService;
    @Inject
    private FilterAndUserRoleValidator userRoleValidator;
    @Inject
    private ExcelDataProvider excelDataConverter;

    @Inject
    @MessageBundle
    Bundle messageBundle;
    @Inject
    private StoredProcedure procedure;

    @Inject
    @WebProperties
    Bundle webBundle;

    @Inject
    private TokenContext tokenContext;
    @Inject
    private UserBean userBean;
    @Inject
    private EntityManager entityManager;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("list", daoService.search(filterWrapper, start, limit, sort));
            map.put("total", daoService.getCount(filterWrapper));
        } catch (Exception ex) {
            Logger.getLogger(FinancialDocSorat3070Service.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return map;
    }

    public FinancialDocSummery getSummery(FilterWrapper filterWrapper) {

        try {
            return daoService.getSummaryPrice(filterWrapper);
        } catch (Exception ex) {
            Logger.getLogger(FinancialDocSorat3070Service.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public String sendDataToTemp(FilterWrapper filterWrapper) throws Exception {
        ServiceResult serviceResult;
        String result = null;
        ElmService elmService = new ElmService();
        ElmHeader elmHeader = new ElmHeader();
        List<ElmDetail> elmDetailList = new ArrayList<>();
        List<String> messageList = new ArrayList<>();
        Optional<Filter> yearFilter = null;
        Optional<Filter> monthFilter = null;

        if (filterWrapper == null) {
            throw new WebApplicationException(messageBundle.getProperty("financialDoc.filterIsEmpty"));
        }
        yearFilter = filterWrapper.getFilters().stream()
                .filter(item -> item.getProperty().equals("daramadYear"))
                .findFirst();
        monthFilter = filterWrapper.getFilters().stream()
                .filter(item -> item.getProperty().equals("daramadMonth"))
                .findFirst();
        if (!(yearFilter.isPresent() && monthFilter.isPresent())) {
            throw new WebApplicationException(messageBundle.getProperty("financialDoc.filterIsEmpty"));
        }
        List<FinancialDocSorat3070> financialDocList = daoService.search(filterWrapper, null, null, null);

        if (financialDocList.size() == 0) {
            throw new WebApplicationException(messageBundle.getProperty("financialDoc.docNotSent"));
        }
        elmHeader.setVahedCode(webBundle.getProperty("financialDoc.sendDataToTemp.vahedCode"));
        elmHeader.setFaDate(DateUtils.format(new Date(), "yyyyMMdd"));
        elmHeader.setDetailDesc(messageBundle.getProperty("financialDoc.headerDetailDesc")
                .replace("{month}", monthFilter.get().getValue())
                .replace("{year}", yearFilter.get().getValue()));
        elmHeader.setSysType("2");
        elmHeader.setYear(yearFilter.get().getValue());

        financialDocList.forEach(item -> {
            elmDetailList.add(new ElmDetail(webBundle.getProperty("financialDoc.sendDataToTemp.vahedCode"),
                    item.getAccountCode().substring(0, 2),
                    item.getAccountCode().substring(2, 4),
                    item.getAccountCode().substring(4, 10),
                    "", "",
                    item.getAccountDesc(),
                    item.getDebtAmount(),
                    item.getCreditAmount(),
                    "2", item.getValueShenaseh(), "",
                    item.getDaramadYear()));

        });

        AccVoucherEntity accVoucherEntity = new AccVoucherEntity();
        accVoucherEntity.setHeader(new HeadEntity(elmHeader));
        accVoucherEntity.setDetails(new ArrayOfDetailEntity(elmDetailList));

        serviceResult = elmService.getServiceSoap().sendDataToTemp(accVoucherEntity, false);
        if (serviceResult.getGetServiceErrorList().getString().size() != 0) {
            throw new WebApplicationException(messageBundle.getProperty("financialDoc.sendTempNoResponse"));
        }


        messageList = serviceResult.getGetServiceMessageList().getString();
        result = messageList.get(messageList.size() - 1).replaceAll("[^0-9]", "");

        return submitTempSequence(yearFilter.get().getValue(), monthFilter.get().getValue(), result);
    }

    private String submitTempSequence(String year, String month, String sequence) {

        String result = null;
        try {
            result = daoService.storeTempSequence(year, month,
                    webBundle.getProperty("financialDoc3070.submitTempSequence.vahedCode"), sequence);
        } catch (Exception ex) {
            Logger.getLogger(FinancialDocService.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(ex.getMessage());
        }
        return result;
    }


    //for test **********************************************************
    public String submint(FilterWrapper filterWrapper) throws Exception {
        String year = null;
        String month = null;
        String vahedCode = null;
        String sequence = "1";

        for (Filter filter : filterWrapper.getFilters()) {

            String field = filter.getProperty();
            if (field.equals("daramadYear")) {
                year = filter.getValue().toString();
            }
            if (field.contains("daramadMonth")) {
                month = filter.getValue().toString();
            }
            if (field.contains("brchcode")) {
                vahedCode = filter.getValue().toString();
            }
        }


        procedure.init();
        procedure.query("{?=call Pck_Telproc_Core.Send_AccDoc3070(?,?,?,?,?)}");
        procedure.setOutParameter(1, Types.NVARCHAR)
                .setInParameter(2, vahedCode)
                .setInParameter(3, year)
                .setInParameter(4, month)
                .setInParameter(5, sequence)
                .setOutParameter(6, Types.INTEGER);
        procedure.execute();
        return procedure.getOutParameter(1).toString();
        /*if (procedure.getOutParameter(6).toString().equals("1")) {
            return procedure.getOutParameter(1).toString();
        } else {
            throw new WebApplicationException(procedure.getOutParameter(1).toString());
        }*/
    }

    /**********************************************************************/
    //TODO----------------------- me : تهیه مغایرت
    public Map<String, Object> discrepancyPreparation(FilterWrapper filterWrapper) throws Exception {
        String year = null;
        String month = null;
        String brchcode = null;

        for (Filter filter : filterWrapper.getFilters()) {

            String field = filter.getProperty();
            if (field.contains("moghYear")) {
                year = filter.getValue();
            }
            if (field.contains("moghMonth")) {
                month = filter.getValue();
            }
            if (field.contains("moghSystem")) {
                brchcode = filter.getValue();
            }
        }
        procedure.init();
        procedure.query("{?=call Pck_Telproc_Core.getmogh3070info(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        procedure.setOutParameter(1, Types.NVARCHAR)
                .setInParameter(2, brchcode)
                .setInParameter(3, year)
                .setInParameter(4, month)
                .setOutParameter(5, Types.BIGINT)
                .setOutParameter(6, Types.BIGINT)
                .setOutParameter(7, Types.BIGINT)
                .setOutParameter(8, Types.BIGINT)
                .setOutParameter(9, Types.BIGINT)
                .setOutParameter(10, Types.BIGINT)
                .setOutParameter(11, Types.BIGINT)
                .setOutParameter(12, Types.BIGINT)
                .setOutParameter(13, Types.BIGINT)
                .setOutParameter(14, Types.BIGINT);

        procedure.execute();
        Map<String, Object> result = new HashMap<>();
        result.put("param1", procedure.getOutParameter(1).toString());

        //unused**************************************************************
       /* result.put("nashenas_cnt", procedure.getOutParameter(5).toString());
        result.put("nashenas_amt", procedure.getOutParameter(6).toString());
        result.put("tekrari_cnt", procedure.getOutParameter(7).toString());
        result.put("tekrari_amt", procedure.getOutParameter(8).toString());
        result.put("jaryan_cnt", procedure.getOutParameter(9).toString());
        result.put("jaryan_amt", procedure.getOutParameter(10).toString());
        result.put("g72_cnt", procedure.getOutParameter(11).toString());
        result.put("g72_amt", procedure.getOutParameter(12).toString());*/

        return result;
    }

    /**********************************************************************/
    public Object importFinancialDocs(String year, String month) {


        List<FinancialDocStatus> lst = (List<FinancialDocStatus>) entityManager.createQuery(
                        "select t from FinancialDocStatus t where t.daramadYear =:year and t.daramadMonth =:month")
                .setParameter("year", year)
                .setParameter("month", month)
                .getResultList();
        if (lst.isEmpty()) {
            try {
                daoService.financialIssuance(year, month);
            } catch (Exception ex) {
                Logger.getLogger(FinancialDocSorat3070Service.class.getName()).log(Level.SEVERE, null, ex);
                throw new WebApplicationException(ex.getMessage());
            }

        }
        return  lst;
    }



    private EntityManager getEntityManager() {
        return entityManager;
    }


    public Object removeFinancialDocs3070(String year, String month) {

        try {
            return daoService.financial3070Remove(year, month);
        } catch (Exception ex) {
            Logger.getLogger(FinancialDocSorat3070Service.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(ex.getMessage());
        }
    }

    public StreamingOutput loadExcel(FilterWrapper filterWrapper) throws Exception {
        List<FinancialDocSorat3070> financialDocSoratList = null;
        FinancialDocSummery financialDocSummery = null;

        try {
            financialDocSoratList = (List<FinancialDocSorat3070>) this.getAll(filterWrapper, 0, Integer.MAX_VALUE, null).get("list");
        } catch (Exception ex) {
            throw ex;
        }

        financialDocSummery = daoService.getSummaryPrice(filterWrapper);
        FinancialDocSorat3070 summery = new FinancialDocSorat3070();
        summery.setDebtAmount(financialDocSummery.getDebtAmount());
        summery.setCreditAmount(financialDocSummery.getCreditAmount());

        financialDocSoratList.add(summery);

        List<List<String>> rowsForExcel = prepareDataForExcel(financialDocSoratList);
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

    private List<List<String>> prepareDataForExcel(List<FinancialDocSorat3070> financialDocList) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList(
                "ردیف",
                "کد مالی",
                "شرح ردیف",
                "مبلغ بدهکار",
                "مبلغ بستانکار",
                "شناسه حساب"
        ));
        int rowCounter = 1;
        for (FinancialDocSorat3070 financialDoc : financialDocList) {
            List<String> row = new ArrayList<>();
            row.add(rowCounter == financialDocList.size() ? "مجموع" : String.valueOf(rowCounter));
            row.add(financialDoc.getAccountCode() != null ? financialDoc.getAccountCode() : "-");
            row.add(financialDoc.getAccountDesc() != null ? financialDoc.getAccountDesc() : "-");
            row.add(financialDoc.getDebtAmount() != null ? financialDoc.getDebtAmount().toString() : "-");
            row.add(financialDoc.getCreditAmount() != null ? financialDoc.getCreditAmount().toString() : "-");
            row.add(financialDoc.getValueShenaseh() != null ? financialDoc.getValueShenaseh().toString() : "-");
            rows.add(row);
            rowCounter++;
        }
        return rows;
    }

    private ExcelReportHeader createReportHeaderDetails() {
        ExcelReportHeader reportHeader = new ExcelReportHeader();
        reportHeader.setTitle("گزارش سند مالی");
        reportHeader.setReportDate(DateUtils.format(new Date(), "yyyy/MM/dd - hh:mm"));
        return reportHeader;
    }

    public Map<String, Object> confrimDisagreements(FilterWrapper filterWrapper) throws Exception {
        String year = null;
        String month = null;
        String brchcode = null;
        User user = this.userBean.getUserByUserName(this.tokenContext.getCurrentUser().getName());
        String userId = String.valueOf(user.getNationalCode());

        for (Filter filter : filterWrapper.getFilters()) {

            String field = filter.getProperty();
            if (field.contains("moghYear")) {
                year = filter.getValue();
            }
            if (field.contains("moghMonth")) {
                month = filter.getValue();
            }
            if (field.contains("moghSystem")) {
                brchcode = filter.getValue();
            }
        }
        procedure.init();
        procedure.query("{?=call Pck_Telproc_Core.ConfMogh3070(?,?,?,?)}");
        procedure.setOutParameter(1, Types.NVARCHAR)
                .setInParameter(2, brchcode)
                .setInParameter(3, year)
                .setInParameter(4, month)
                .setInParameter(5, userId);

        procedure.execute();
        Map<String, Object> result = new HashMap<>();
        result.put("param1", procedure.getOutParameter(1).toString());


        return result;
    }

    //updateInfo
    public Map<String, Object> financialUpdateInfo(FilterWrapper filterWrapper) throws Exception {
        String yearMonth = null;
        if (filterWrapper != null && filterWrapper.getFilters() != null) {
            for (Filter filter : filterWrapper.getFilters()) {

                String field = filter.getProperty();
                if (field.contains("yearMonth")) {
                    yearMonth = filter.getValue();
                }
            }
        }
        procedure.init();
        procedure.query("{?=call Pck_Telproc_Core.upd_3070_mon(?)}");
        procedure.setOutParameter(1, Types.NVARCHAR)
                .setInParameter(2, yearMonth);

        procedure.execute();
        Map<String, Object> result = new HashMap<>();
        result.put("param1", procedure.getOutParameter(1).toString());

        return result;
    }
}
