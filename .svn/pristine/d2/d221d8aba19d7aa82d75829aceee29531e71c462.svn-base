package ir.tamin.incomeBank.dao.specialInsuredsReport;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.dto.smartlist.branch.SmartListBranchSummary;
import ir.tamin.incomeBank.dto.specialInsuredsReport.SpecialInsuredsReportDto;
import ir.tamin.incomeBank.dto.specialInsuredsReport.SpecialInsuredsReportSummary;
import ir.tamin.incomeBank.model.payment3070.account503branch.Account503Branch;
import ir.tamin.incomeBank.model.receiptReport.receiptDate.ReceiptReportReceiptDateDTO;
import ir.tamin.incomeBank.model.smartlist.branch.SmartListBranch;
import ir.tamin.incomeBank.model.specialInsuredsReport.SpecialInsuredsReport;
import ir.tamin.incomeBank.model.specialInsuredsReport.SpecialInsuredsReportView;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecialInsuredsReportDAO extends BaseDAOService<SpecialInsuredsReport> {

    @Inject
    private EntityManager entityManager;

    public Map<String ,Object> getSystemNameByDateRange(String startDate1 , String endDate1 ,String systemType1){
        Map<String, Object> map = new HashMap<>();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<SpecialInsuredsReport> root = criteriaQuery.from(SpecialInsuredsReport.class);

        Expression<String> soratDate = root.get("soratDate");
        Expression<String> soratOrdPayment = root.get("soratOrdPayment");
        Expression<BigDecimal> soratPrice1 = root.get("soratPrice1");
        Expression<String> soratPaymentSeq = root.get("soratPaymentSeq");
        Expression<String> systemDesc = root.get("systemDesc");
        Expression<String> systemType = root.get("systemType");

        Predicate datePredicate = criteriaBuilder.between(soratDate , startDate1, endDate1);
        Predicate systemTypeLike = criteriaBuilder.equal(systemType,systemType1);


        criteriaQuery.multiselect(
                soratDate,
                soratPaymentSeq,
                soratOrdPayment,
                soratPrice1,
                systemDesc,
                systemType
                //criteriaBuilder.sum(soratPrice1)

        );


        criteriaQuery.where(datePredicate,systemTypeLike);

        TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery);

        List<Object[]> results = query.getResultList();

        List<SpecialInsuredsReportDto> list = new ArrayList<>();



        for(Object[] result : results){
            String startDate = (String) result[0];
            String soratPaymentSeq2 = (String) result[1];
            String soratOrdPayment2 = (String) result[2];
            BigDecimal soratPrice = (BigDecimal) result[3];
            String systemDesc2 = (String) result[4];

            SpecialInsuredsReportDto specialInsuredsReportDto = new SpecialInsuredsReportDto();

            specialInsuredsReportDto.setSoratDate1(startDate);
            specialInsuredsReportDto.setSoratPaymentSeq(soratPaymentSeq2);
            specialInsuredsReportDto.setSoratOrdPayment(soratOrdPayment2);
            specialInsuredsReportDto.setSoratPrice1(soratPrice);
            specialInsuredsReportDto.setSystemDesc(systemDesc2);

            list.add(specialInsuredsReportDto);
        }

        BigDecimal sumSoratPrice = BigDecimal.valueOf(0);

        for(SpecialInsuredsReportDto dto : list){
            BigDecimal sumSoratPrice1 = dto.getSoratPrice1() != null ? dto.getSoratPrice1() : BigDecimal.ZERO;

            sumSoratPrice = sumSoratPrice.add(sumSoratPrice1);
        }

        BigDecimal total = BigDecimal.valueOf(list.size());
        SpecialInsuredsReportSummary summary = new SpecialInsuredsReportSummary(sumSoratPrice);
        map.put("total", total);
        map.put("data", list);
        map.put("sum", summary);

        return map;
    }
    public SpecialInsuredsReportSummary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<SpecialInsuredsReport> root = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(SpecialInsuredsReportSummary.class,
                criteriaBuilder.sum(root.<BigDecimal>get("soratPrice1"))));
        TypedQuery createQuery = entityManager.createQuery(criteriaQuery);
        SpecialInsuredsReportSummary summery = (SpecialInsuredsReportSummary) createQuery.getSingleResult();
        return summery;
    }

    @Override
    public Class<SpecialInsuredsReport> getClazz() {
        return SpecialInsuredsReport.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
