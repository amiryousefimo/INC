/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.tamin.incomeBank.dao.receipt;

import ir.tamin.framework.ws.rest.json.Filter;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.model.receipt.ReceiptPaper;
import ir.tamin.incomeBank.model.receipt.ReceiptPaperSummery;
import ir.tamin.incomeBank.dao.BaseDAOService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

/**
 * @author a_khalighi
 */
public class ReceiptPaperDAOService extends BaseDAOService<ReceiptPaper> {

    @Inject
    private EntityManager em;

    public ReceiptPaperSummery getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<ReceiptPaper> receiptFrom = criteriaQuery.from(getClazz());
        criteriaQuery = super.getFilteredQuery(criteriaQuery, receiptFrom, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(ReceiptPaperSummery.class,
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("orderAmount")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmount")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("orderAmount1")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("orderAmount2")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("orderAmount3")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("orderAmount4")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee01")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured01")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee02")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured02")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee03")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured03")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee04")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured04")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee05")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured05")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee06")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured06")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee07")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured07")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee08")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured08")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee09")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured09")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee10")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured10")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee11")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured11")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee12")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured12")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee13")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured13")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee14")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured14")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee15")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured15")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee16")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured16")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee17")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured17")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee18")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured18")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee19")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured19")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountEmployee20")),
                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("governmentAmountInsured20")),

                criteriaBuilder.sum(receiptFrom.<BigDecimal>get("countIsu"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        ReceiptPaperSummery summery = (ReceiptPaperSummery) createQuery.getSingleResult();
        return summery;
    }


    @Override
    public Class<ReceiptPaper> getClazz() {
        return ReceiptPaper.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public Object getSummaryIsd(FilterWrapper filterWrapper) {
        Filter orderDateFilter = filterWrapper.getFilter("orderDate");
        String dateValue = orderDateFilter.getValue().toString();

        if (dateValue.length() > 6) {
            dateValue = dateValue.substring(0, 6);
        }

        return em.createNativeQuery("SELECT sum(round(h.thd_isvwag * 0.27)) isd27, " +
                        "round(sum(round(h.thd_isvwag * 0.27))/9) isd3 " +
                        "FROM baj_teched h " +
                        "WHERE EXISTS (" +
                        "    SELECT 1 " +
                        "    FROM clm_ordpay p " +
                        "    WHERE p.workshop_history_id = h.workshop_history_id " +
                        "      AND EXISTS (" +
                        "          SELECT 1 " +
                        "          FROM drmd_gl t " +
                        "          WHERE t.ord_ordno = p.ord_ordno " +
                        "            AND substr(t.ord_date, 1, 6) = ?1 " +
                        "            AND t.sorat_date IS NOT NULL " +
                        "      )" +
                        ")")
                .setParameter(1, dateValue)
                .getSingleResult();
    }





}
