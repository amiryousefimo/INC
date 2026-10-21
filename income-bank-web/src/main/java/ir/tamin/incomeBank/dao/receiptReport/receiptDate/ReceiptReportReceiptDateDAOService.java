package ir.tamin.incomeBank.dao.receiptReport.receiptDate;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.model.receiptReport.receiptDate.ReceiptReportReceiptDate;
import ir.tamin.incomeBank.model.receiptReport.receiptDate.ReceiptReportReceiptDateDTO;
import ir.tamin.incomeBank.model.receiptReport.receiptDate.ReceiptReportReceiptDateSummary;
import ir.tamin.incomeBank.model.reportingOfficeBasedPayment.department.ReportingOfficeBasedPaymentDepartmentSummary;
import ir.tamin.incomeBank.model.reportingOfficeBasedPayment.department.ReportingOfficeBasedPaymentDepartmentView;
import ir.tamin.incomeBank.util.BaseDAOService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiptReportReceiptDateDAOService extends BaseDAOService<ReceiptReportReceiptDate> {

    @Inject
    private EntityManager em;


    public Class<ReceiptReportReceiptDate> getClazz() {
        return ReceiptReportReceiptDate.class;
    }


    public EntityManager getEntityManager() {
        return em;
    }

    public ReceiptReportReceiptDateSummary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ReceiptReportReceiptDateSummary> criteriaQuery = criteriaBuilder.createQuery(ReceiptReportReceiptDateSummary.class);

        Root<ReceiptReportReceiptDate> root = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);

        criteriaQuery.select(criteriaBuilder.construct(
                ReceiptReportReceiptDateSummary.class,
                criteriaBuilder.sum(root.get("soratPrice")),
                criteriaBuilder.sum(root.get("countPrice"))));

        TypedQuery typedQuery = em.createQuery(criteriaQuery);
        ReceiptReportReceiptDateSummary summary = (ReceiptReportReceiptDateSummary) typedQuery.getSingleResult();
        return summary;
    }

    //////////////////////////////////////////////////////////////////////
    public Map<String, Object> getByDateRange(String startDate1, String endDate1) {
        Map<String, Object> map = new HashMap<>();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ReceiptReportReceiptDate> root = criteriaQuery.from(ReceiptReportReceiptDate.class);

        Expression<String> soratDesc = root.get("soratDesc");
        Expression<String> soratDate = root.get("soratDate");
        Expression<BigDecimal> soratPrice = root.get("soratPrice");
        Expression<BigDecimal> countPrice = root.get("countPrice");

        Predicate datePredicate = criteriaBuilder.between(soratDate, startDate1, endDate1);

        criteriaQuery.multiselect(
                soratDesc,
                criteriaBuilder.literal(startDate1),
                criteriaBuilder.literal(endDate1),
                criteriaBuilder.sum(soratPrice),
                criteriaBuilder.sum(countPrice)
        );


        criteriaQuery.where(datePredicate);


        criteriaQuery.groupBy(soratDesc);

        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
        List<Object[]> results = query.getResultList();
        List<ReceiptReportReceiptDateDTO> list = new ArrayList<>();

        for (Object[] result : results) {
            String soratDesc2 = (String) result[0];
            String startDate = (String) result[1];
            String endDate = (String) result[2];
            BigDecimal sumAmount = ((BigDecimal) result[3]);
            BigDecimal sumCountOrderNumber = ((BigDecimal) result[4]);

            ReceiptReportReceiptDateDTO reportReceiptDateDTO = new ReceiptReportReceiptDateDTO();
            reportReceiptDateDTO.setSoratDesc(soratDesc2);
            reportReceiptDateDTO.setSoratDate1(startDate);
            reportReceiptDateDTO.setSoratDate2(endDate);
            reportReceiptDateDTO.setSoratPrice(sumAmount);
            reportReceiptDateDTO.setCountPrice(sumCountOrderNumber);
            list.add(reportReceiptDateDTO);
        }

        BigDecimal sumSumValue = BigDecimal.valueOf(0);
        BigDecimal sumCountValue = BigDecimal.valueOf(0);

        for (ReceiptReportReceiptDateDTO dto : list) {
            BigDecimal sumAmount2 = dto.getSoratPrice();
            BigDecimal sumCountOrder2 = dto.getCountPrice();

            sumSumValue = sumSumValue.add(sumAmount2);
            sumCountValue = sumCountValue.add(sumCountOrder2);
        }

        BigDecimal total = BigDecimal.valueOf(list.size());
        ReportingOfficeBasedPaymentDepartmentSummary summery = new ReportingOfficeBasedPaymentDepartmentSummary(sumSumValue, sumCountValue);
        map.put("total", total);
        map.put("data", list);
        map.put("sum", summery);
        return map;
    }



}
