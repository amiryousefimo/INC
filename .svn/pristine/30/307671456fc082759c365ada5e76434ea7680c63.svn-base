package ir.tamin.incomeBank.dao.receiptManagement.edare;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.dto.receiptManagement.edare.ReceiptManagementEdareDto;
import ir.tamin.incomeBank.dto.receiptManagement.edare.ReceiptManagementEdareSummary;
import ir.tamin.incomeBank.model.receiptManagement.edare.ReceiptManagementEdare;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author a_jamali
 */
public class ReceiptManagementEdareDAO extends BaseDAOService<ReceiptManagementEdare> {


    @Inject
    private EntityManager em;


    public Map<String, Object> getEdareByDate(String startDate1, String endDate1, String edareCode1) {
        Map<String, Object> map = new HashMap<>();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ReceiptManagementEdare> root = criteriaQuery.from(ReceiptManagementEdare.class);

        Expression<String> edareCode = root.get("edareCode");
        Expression<String> soratDate = root.get("soratDate");
        Expression<BigDecimal> sumSabaPrc = root.get("sabaPrc");
        Expression<BigDecimal> sumIntljPrc = root.get("intljPrc");
        Expression<BigDecimal> sumKarkonaPrc = root.get("karkonanPrc");
        Expression<BigDecimal> sumNaghdiPrc = root.get("naghdiPrc");
        Expression<BigDecimal> sumOraghPrc = root.get("oraghPrc");
        Expression<BigDecimal> sumPrc3070 = root.get("prc3070");
        Expression<BigDecimal> sumSumPrc = root.get("sumPrc");

        Predicate datePredicate = criteriaBuilder.between(soratDate, startDate1, endDate1);
        Predicate edareCdePredictLike = criteriaBuilder.like(edareCode, "%%");
        Predicate edareCdePredictEqual = criteriaBuilder.equal(edareCode, edareCode1);

        criteriaQuery.multiselect(
                root.get("edareCode"),
                root.get("edareName"),
                criteriaBuilder.literal(startDate1),
                criteriaBuilder.literal(endDate1),
                criteriaBuilder.sum(sumSabaPrc),
                criteriaBuilder.sum(sumIntljPrc),
                criteriaBuilder.sum(sumKarkonaPrc),
                criteriaBuilder.sum(sumNaghdiPrc),
                criteriaBuilder.sum(sumOraghPrc),
                criteriaBuilder.sum(sumPrc3070),
                criteriaBuilder.sum(sumSumPrc)
        );


        if (edareCode1.equals("-1")) {
            criteriaQuery.where(datePredicate, edareCdePredictLike).groupBy(root.get("edareCode"), root.get("edareName"));
        } else {
            criteriaQuery.where(datePredicate, edareCdePredictEqual).groupBy(root.get("edareCode"), root.get("edareName"));
        }


        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
        List<Object[]> results = query.getResultList();
        List<ReceiptManagementEdareDto> list = new ArrayList<>();

        BigDecimal sSabaPrc = BigDecimal.ZERO;
        BigDecimal sIntljPrc = BigDecimal.ZERO;
        BigDecimal sKarkonanPrc = BigDecimal.ZERO;
        BigDecimal sNaghdiPrc = BigDecimal.ZERO;
        BigDecimal sOraghPrc = BigDecimal.ZERO;
        BigDecimal sPrc3070 = BigDecimal.ZERO;
        BigDecimal sSumPrc = BigDecimal.ZERO;

        for (Object[] result : results) {
            String edareCode2 = (String) result[0];
            String edareName = (String) result[1];
            String startDate = startDate1;
            String endDate = endDate1;
            BigDecimal sabaPrc = (BigDecimal) result[4];
            BigDecimal intljPrc = (BigDecimal) result[5];
            BigDecimal karkonanPrc = (BigDecimal) result[6];
            BigDecimal naghdiPrc = (BigDecimal) result[7];
            BigDecimal oraghPrc = (BigDecimal) result[8];
            BigDecimal prc3070 = (BigDecimal) result[9];
            BigDecimal sumPrc = (BigDecimal) result[10];

            ReceiptManagementEdareDto dto = new ReceiptManagementEdareDto();
            dto.setEdareCode(edareCode2);
            dto.setEdareName(edareName);
            dto.setSoratDate(startDate);
            dto.setSoratDate2(endDate);
            dto.setSabaPrc(sabaPrc);
            dto.setIntljPrc(intljPrc);
            dto.setKarkonanPrc(karkonanPrc);
            dto.setNaghdiPrc(naghdiPrc);
            dto.setOraghPrc(oraghPrc);
            dto.setPrc3070(prc3070);
            dto.setSumPrc(sumPrc);
            list.add(dto);

            sSabaPrc = sSabaPrc.add(sabaPrc == null ? BigDecimal.ZERO : sabaPrc);
            sIntljPrc = sIntljPrc.add(intljPrc == null ? BigDecimal.ZERO : intljPrc);
            sKarkonanPrc = sKarkonanPrc.add(karkonanPrc == null ? BigDecimal.ZERO : karkonanPrc);
            sNaghdiPrc = sNaghdiPrc.add(naghdiPrc == null ? BigDecimal.ZERO : naghdiPrc);
            sOraghPrc = sOraghPrc.add(oraghPrc == null ? BigDecimal.ZERO : oraghPrc);
            sPrc3070 = sPrc3070.add(prc3070 == null ? BigDecimal.ZERO : prc3070);
            sSumPrc = sSumPrc.add(sumPrc == null ? BigDecimal.ZERO : sumPrc);
        }

        BigDecimal total = BigDecimal.valueOf(list.size());
        ReceiptManagementEdareSummary summery = new ReceiptManagementEdareSummary(sSabaPrc, sIntljPrc, sKarkonanPrc, sNaghdiPrc, sOraghPrc, sPrc3070, sSumPrc);
        map.put("total", total);
        map.put("data", list);
        map.put("sum", summery);
        return map;
    }

    public ReceiptManagementEdareSummary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<ReceiptManagementEdare> workshopDebitReportFrom = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, workshopDebitReportFrom, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(ReceiptManagementEdareSummary.class,
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("sabaPrc")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("intljPrc")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("karkonanPrc")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("naghdiPrc")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("oraghPrc")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("prc3070")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("sumPrc"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        ReceiptManagementEdareSummary summery = (ReceiptManagementEdareSummary) createQuery.getSingleResult();
        return summery;
    }

    @Override
    public Class<ReceiptManagementEdare> getClazz() {
        return ReceiptManagementEdare.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
