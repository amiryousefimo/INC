/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.tamin.incomeBank.service.baseinfo;

import ir.tamin.framework.ws.rest.json.Filter;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.model.baseinfo.Bank;
import ir.tamin.incomeBank.model.baseinfo.OperationalBankEnum;
import ir.tamin.incomeBank.model.centralPayment.BankOperationPension;
import ir.tamin.incomeBank.model.centralPayment.CalcBankReturnModel;
import ir.tamin.incomeBank.model.pension.enums.PensionPayModelEnum;
import ir.tamin.incomeBank.util.ServiceUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author s_maknooni
 * {@code @developer A_Jamali}
 */
public class BankService {

    @Inject
    private EntityManager em;

    @Inject
    ServiceUtils<Bank> util;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sortWrapper) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", getList(filterWrapper, start, limit, sortWrapper));
        map.put("total", getCount(filterWrapper));
        return map;
    }


    public List<Bank> getList(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) {
        TypedQuery<Bank> createQuery = em.createQuery(getQuery(filterWrapper, sort));
        return util.getListWithConditions(start, limit, createQuery);
    }

    public Integer getCount(FilterWrapper filter) {

        TypedQuery<Bank> createQuery = em.createQuery(getQuery(filter, null));
        return createQuery.getResultList().size();

    }

    private CriteriaQuery<Bank> getQuery(FilterWrapper filterWrapper, SortWrapper sortWrapper) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Bank> criteriaQuery = criteriaBuilder.createQuery(Bank.class);
            Root<Bank> bankFrom = criteriaQuery.from(Bank.class);
            Metamodel m = em.getMetamodel();
            EntityType<Bank> bankEntityType = m.entity(Bank.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (filterWrapper != null && filterWrapper.getFilters() != null) {
                for (Filter filter : filterWrapper.getFilters()) {

                    Object value = filter.getValue();
                    String field = filter.getProperty();
                    Filter.Operator operator = filter.getOperator();
                    String[] f = field.split("\\.");
                    Predicate predicate = null;
                    switch (operator) {
                        case LIKE:
                            predicates.add(criteriaBuilder.like(bankFrom.get(bankEntityType.getDeclaredSingularAttribute(field, String.class)), "%" + value + "%"));
                            break;
                        case _EQUAL:
                        case EQUAL:
                            Path<String> path = bankFrom.get(f[0]);
                            for (int j = 1; j < f.length; j++) {
                                path = path.get(f[j]);
                            }
                            predicate = criteriaBuilder.equal(path, value);
                            predicates.add(predicate);
                            break;
                        default:
                            break;
                    }
                }
                criteriaQuery.where(predicates.toArray(new Predicate[]{}));
            }

            criteriaQuery.select(bankFrom);
            return criteriaQuery;

        } catch (Exception e) {
            return null;
        }
    }

    public Bank get(String code) {
        return em.find(Bank.class, code);
    }

    public Map<String, Object> getAllCalcOPBankWithOther() {

        List<CalcBankReturnModel> result = new ArrayList<>();
        CalcBankReturnModel model;
        Bank bank;
        for (OperationalBankEnum opb : OperationalBankEnum.values()) {
            model = new CalcBankReturnModel();
            bank = get(opb.getCode());
            model.setBank(bank);
            model.setBankAmmount(BigDecimal.ZERO);
            model.setBankQty(0L);
            model.setIsSelected(Boolean.FALSE);
            result.add(model);
        }

        model = new CalcBankReturnModel();
        bank = new Bank();
        bank.setBankCode("00");
        bank.setBankName("سایر بانکها");
        model.setBank(bank);
        model.setBankCode("00");
        model.setBankName("سایر بانکها");
        model.setBankAmmount(BigDecimal.ZERO);
        model.setBankQty(0L);
        model.setIsSelected(Boolean.FALSE);
        result.add(model);

        Map<String, Object> map = new HashMap<>();
        map.put("list", result);
        map.put("total", OperationalBankEnum.values().length);

        return map;
    }

//    public Map<String, Object> getAllPensionCalcOPBank() {
//
//        List<CalcBankReturnModel> result = new ArrayList<>();
//        CalcBankReturnModel model;
//        Bank bank;
//        for (OperationalBankEnum opb : OperationalBankEnum.values()) {
//            model = new CalcBankReturnModel();
//            bank = get(opb.getCode());
//            model.setBank(bank);
//            model.setBankAmmount(BigDecimal.ZERO);
//            model.setBankQty(0L);
//            model.setIsSelected(Boolean.FALSE);
//            result.add(model);
//        }
//
//        model = new CalcBankReturnModel();
//        bank = new Bank();
//        bank.setBankCode("00");
//        bank.setBankName(PensionPayModelEnum.BONYAD_SHAHID.getName());
//        model.setBank(bank);
//        model.setBankCode("00");
//        model.setBankName(PensionPayModelEnum.BONYAD_SHAHID.getName());
//        model.setBankAmmount(BigDecimal.ZERO);
//        model.setBankQty(0L);
//        model.setIsSelected(Boolean.FALSE);
//        result.add(model);
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("list", result);
//        map.put("total", OperationalBankEnum.values().length);
//
//        return map;
//    }

    public Map<String, Object> getAllCalcOPBank() {

        List<CalcBankReturnModel> result = new ArrayList<>();
        CalcBankReturnModel model;
        Bank bank;
        for (OperationalBankEnum opb : OperationalBankEnum.values()) {
            model = new CalcBankReturnModel();
            bank = get(opb.getCode());
            model.setBank(bank);
            model.setBankAmmount(BigDecimal.ZERO);
            model.setBankQty(0L);
            model.setIsSelected(Boolean.FALSE);
            result.add(model);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("list", result);
        map.put("total", OperationalBankEnum.values().length);

        return map;
    }

    public Map<String, Object> getAllPensionOPBank() {

        List<BankOperationPension> result = new ArrayList<>();

        BankOperationPension model;

        for (OperationalBankEnum opb : OperationalBankEnum.values()) {
            model = new BankOperationPension();
            model.setCode(opb.getCode());
            model.setName(opb.getName());
            result.add(model);
        }

        model = new BankOperationPension();
        model.setCode("00");
        model.setName(PensionPayModelEnum.BONYAD_SHAHID.getName());
        result.add(model);

        Map<String, Object> map = new HashMap<>();
        map.put("list", result);
        map.put("total", OperationalBankEnum.values().length + 1);

        return map;
    }

    public Map<String, Object> getAllTcrCalcOPBank() {

        List<CalcBankReturnModel> result = new ArrayList<>();
        CalcBankReturnModel model;
        Bank bank;
        for (OperationalBankEnum opb : OperationalBankEnum.values()) {
            model = new CalcBankReturnModel();
            bank = get(opb.getCode());
            model.setBank(bank);
            model.setBankAmmount(BigDecimal.ZERO);
            model.setBankQty(0L);
            model.setIsSelected(Boolean.FALSE);
            result.add(model);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("list", result);
        map.put("total", OperationalBankEnum.values().length);

        return map;
    }

}
