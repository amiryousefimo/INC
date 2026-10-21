/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.tamin.incomeBank.service.daramadBank;

import ir.tamin.framework.ws.rest.json.Filter;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.Sort;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.model.daramadBank.TmpCompDet;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ServiceUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.*;

/**
 * @author f_fotuhi
 * {@code @developer A_Jamali}
 */
public class TmpCompDetService {

    @Inject
    private EntityManager em;
    /*    @Inject
       UserBean userBean;
        @Inject
        @MessageBundle
     Bundle messageBundle;*/

    public Map<String, Object> getAll(FilterWrapper filter, Integer start, Integer limit, SortWrapper sort) {

        Map<String, Object> map = new HashMap<>();
        map.put("list", getList(filter, start, limit, sort));
        map.put("total", getCount(filter));
        return map;
    }

    @Inject
    ServiceUtils<TmpCompDet> util;

    public List<TmpCompDet> getList(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) {

        TypedQuery<TmpCompDet> createQuery = em.createQuery(getQuery(filterWrapper, sort));
        List<TmpCompDet> tmpCompDetList = util.getListWithConditions(start, limit, createQuery);
        List<TmpCompDet> noLimitList;
        TypedQuery<TmpCompDet> createQueryNoLimit = em.createQuery(getQuery(filterWrapper, sort));
        noLimitList = createQueryNoLimit.getResultList();
        Long sumPrice = 0L;
        if (noLimitList.size() > 0) {
            for (TmpCompDet item : noLimitList) {
                sumPrice += item.getTmpcompPrice();
            }
            tmpCompDetList.get(0).setSum(sumPrice);
        }

        return tmpCompDetList;
    }

    public Integer getCount(FilterWrapper filter) {
        TypedQuery<TmpCompDet> createQuery = em.createQuery(getQuery(filter, null));
        return createQuery.getResultList().size();
    }

    private CriteriaQuery<TmpCompDet> getQuery(FilterWrapper filterWrapper, SortWrapper sortWrapper) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<TmpCompDet> criteriaQuery = criteriaBuilder.createQuery(TmpCompDet.class);
            Root<TmpCompDet> tmpCompDetFrom = criteriaQuery.from(TmpCompDet.class);
            List<Predicate> predicates = new ArrayList<>();
            Metamodel metamodel = em.getMetamodel();
            EntityType<TmpCompDet> tmpCompDetEntityType = metamodel.entity(TmpCompDet.class);
            if (filterWrapper != null && filterWrapper.getFilters() != null) {
                for (Filter filter : filterWrapper.getFilters()) {

                    Object value = filter.getValue();
                    String field = filter.getProperty();
                    Filter.Operator operator = filter.getOperator();

                    if (field.equals("drmdComparePK.compMdate")) {
                        field = "tmpcompMDate";
                    }
                    if (field.equals("drmdComparePK.brchCode")) {
                        field = "tmpCompDetPK.brchCode";
                    }

                    if (!(field.equals("tmpcompMDate")) && field.toLowerCase().contains("date")) {

                        Date reqDate = DateUtils.convertDateStringToDate(value.toString().replace("/", ""));
                        value = DateUtils.format(reqDate, "yyyyMMdd");
                    }

                    String[] f = field.split("\\.");

                    Predicate predicate = null;
                    switch (operator) {

                        case _EQUAL:
                        case EQUAL:
                            Path<String> path = tmpCompDetFrom.get(f[0]);
                            for (int j = 1; j < f.length; j++) {
                                path = path.get(f[j]);
                            }
                            predicate = criteriaBuilder.equal(path, value);
                            predicates.add(predicate);
                            break;
                        case LIKE:

                            predicates.add(criteriaBuilder.like(tmpCompDetFrom.get(tmpCompDetEntityType.getDeclaredSingularAttribute(field, String.class)), "%" + value + "%"));

                            break;
                        default:
                            break;
                    }
                }
                criteriaQuery.where(predicates.toArray(new Predicate[]{}));
            }

            if (sortWrapper != null) {
                List<Order> orders = new ArrayList<>();
                for (Sort sortSet : sortWrapper.getSortSet()) {
                    Order order = null;
                    String[] sortProperties = sortSet.getProperty().split("\\.");

                    javax.persistence.criteria.Path path = tmpCompDetFrom.get(sortProperties[0]);
                    for (int j = 1; j < sortProperties.length; j++) {
                        path = path.get(sortProperties[j]);
                    }
                    if (sortProperties.length > 0) {
                        if (sortSet.getDirection().getName().equals(Sort.Direction.DESC.getName())) {
                            order = criteriaBuilder.desc(tmpCompDetFrom.get(sortProperties[0]));
                        } else {
                            order = criteriaBuilder.asc(tmpCompDetFrom.get(sortProperties[0]));
                        }
                    }
                    orders.add(order);
                }

                if (!orders.isEmpty()) {
                    criteriaQuery.orderBy(orders);
                }
            }

            criteriaQuery.select(tmpCompDetFrom);
            return criteriaQuery;

        } catch (Exception e) {
            return null;
        }
    }
}
