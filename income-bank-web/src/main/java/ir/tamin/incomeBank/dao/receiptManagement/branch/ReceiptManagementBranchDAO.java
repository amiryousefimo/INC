package ir.tamin.incomeBank.dao.receiptManagement.branch;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.dto.receiptManagement.branch.ReceiptManagementBranchDto;
import ir.tamin.incomeBank.dto.receiptManagement.branch.ReceiptManagementBranchSummary;
import ir.tamin.incomeBank.model.receiptManagement.branch.ReceiptManagementBranch;

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
public class ReceiptManagementBranchDAO extends BaseDAOService<ReceiptManagementBranch> {


    @Inject
    private EntityManager em;


    public Map<String, Object> getBranchByDateRange(String startDate1, String endDate1, String edareCode1, String brchCode1) {
        Map<String, Object> map = new HashMap<>();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ReceiptManagementBranch> root = criteriaQuery.from(ReceiptManagementBranch.class);

        Expression<String> edareCode = root.get("edareCode");
        Expression<String> brchCode = root.get("brchCode");
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
        Predicate brchPredicateLike = criteriaBuilder.like(brchCode, "%%");
        Predicate brchPredicateEqual = criteriaBuilder.equal(brchCode, brchCode1);

        criteriaQuery.multiselect(
                root.get("edareCode"),
                root.get("edareName"),
                root.get("brchCode"),
                root.get("brchName"),
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

        if ((edareCode1.equals("-1") && brchCode1.equals("-1"))) {
            criteriaQuery.where(datePredicate, edareCdePredictLike, brchPredicateLike).groupBy(root.get("edareCode"), root.get("edareName"), root.get("brchCode"), root.get("brchName"));
        } else if (edareCode1.equals("-1") || brchCode1.equals("-1")) {
            if (edareCode1.equals("-1")) {
                criteriaQuery.where(datePredicate, edareCdePredictLike, brchPredicateEqual).groupBy(root.get("edareCode"), root.get("edareName"), root.get("brchCode"), root.get("brchName"));
            } else {
                criteriaQuery.where(datePredicate, edareCdePredictEqual, brchPredicateLike).groupBy(root.get("edareCode"), root.get("edareName"), root.get("brchCode"), root.get("brchName"));
            }
        } else {
            criteriaQuery.where(datePredicate, edareCdePredictEqual, brchPredicateEqual).groupBy(root.get("edareCode"), root.get("edareName"), root.get("brchCode"), root.get("brchName"));
        }

        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
        List<Object[]> results = query.getResultList();
        List<ReceiptManagementBranchDto> list = new ArrayList<>();

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
            String branchCode = (String) result[2];
            String branchName = (String) result[3];
            String startDate = startDate1;
            String endDate = endDate1;
            BigDecimal sabaPrc = (BigDecimal) result[6];
            BigDecimal intljPrc = (BigDecimal) result[7];
            BigDecimal karkonanPrc = (BigDecimal) result[8];
            BigDecimal naghdiPrc = (BigDecimal) result[9];
            BigDecimal oraghPrc = (BigDecimal) result[10];
            BigDecimal prc3070 = (BigDecimal) result[11];
            BigDecimal sumPrc = (BigDecimal) result[12];
            ReceiptManagementBranchDto branchView = new ReceiptManagementBranchDto();
            branchView.setBrchCode(branchCode);
            branchView.setBrchName(branchName);
            branchView.setEdareCode(edareCode2);
            branchView.setEdareName(edareName);
            branchView.setSoratDate(startDate);
            branchView.setSoratDate2(endDate);
            branchView.setSabaPrc(sabaPrc);
            branchView.setIntljPrc(intljPrc);
            branchView.setKarkonanPrc(karkonanPrc);
            branchView.setNaghdiPrc(naghdiPrc);
            branchView.setOraghPrc(oraghPrc);
            branchView.setPrc3070(prc3070);
            branchView.setSumPrc(sumPrc);
            list.add(branchView);

            sSabaPrc = sSabaPrc.add(sabaPrc == null ? BigDecimal.ZERO : sabaPrc);
            sIntljPrc = sIntljPrc.add(intljPrc == null ? BigDecimal.ZERO : intljPrc);
            sKarkonanPrc = sKarkonanPrc.add(karkonanPrc == null ? BigDecimal.ZERO : karkonanPrc);
            sNaghdiPrc = sNaghdiPrc.add(naghdiPrc == null ? BigDecimal.ZERO : naghdiPrc);
            sOraghPrc = sOraghPrc.add(oraghPrc == null ? BigDecimal.ZERO : oraghPrc);
            sPrc3070 = sPrc3070.add(prc3070 == null ? BigDecimal.ZERO : prc3070);
            sSumPrc = sSumPrc.add(sumPrc == null ? BigDecimal.ZERO : sumPrc);
        }

        BigDecimal total = BigDecimal.valueOf(list.size());
        ReceiptManagementBranchSummary summery = new ReceiptManagementBranchSummary(sSabaPrc, sIntljPrc, sKarkonanPrc, sNaghdiPrc, sOraghPrc, sPrc3070, sSumPrc);
        map.put("total", total);
        map.put("data", list);
        map.put("sum", summery);
        return map;
    }

    public ReceiptManagementBranchSummary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<ReceiptManagementBranch> workshopDebitReportFrom = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, workshopDebitReportFrom, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(ReceiptManagementBranchSummary.class,
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("sabaPrc")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("intljPrc")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("karkonanPrc")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("naghdiPrc")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("oraghPrc")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("prc3070")),
                criteriaBuilder.sum(workshopDebitReportFrom.<BigDecimal>get("sumPrc"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        ReceiptManagementBranchSummary summery = (ReceiptManagementBranchSummary) createQuery.getSingleResult();
        return summery;
    }

    @Override
    public Class<ReceiptManagementBranch> getClazz() {
        return ReceiptManagementBranch.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
