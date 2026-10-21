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
import ir.tamin.incomeBank.model.daramadBank.BajOrdpused;
import ir.tamin.incomeBank.service.identityManager.UserBean;
import ir.tamin.incomeBank.util.ServiceUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author m_salami
 * {@code @developer A_Jamali}
 */
@Stateless
public class BajOrdpusedService {

    @Inject
    private EntityManager em;
    @Inject
    UserBean userBean;


    @Inject
    ServiceUtils<BajOrdpused> util;

    public Map<String, Object> getAll(FilterWrapper filter, Integer start, Integer limit, SortWrapper sort) {

        Map<String, Object> map = new HashMap<>();
        map.put("list", getList(filter, start, limit, sort));
        map.put("total", getCount(filter));
        return map;
    }

    public List<BajOrdpused> getList(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) {

        TypedQuery<BajOrdpused> createQuery = em.createQuery(getQuery(filterWrapper, sort));

        return util.getListWithConditions(start, limit, createQuery);
    }

    public Integer getCount(FilterWrapper filter) {

        TypedQuery<BajOrdpused> createQuery = em.createQuery(getQuery(filter, null));
        return createQuery.getResultList().size();

    }

    private CriteriaQuery<BajOrdpused> getQuery(FilterWrapper filterWrapper, SortWrapper sortWrapper) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<BajOrdpused> criteriaQuery = criteriaBuilder.createQuery(BajOrdpused.class);
            Root<BajOrdpused> bajOrdpusedFrom = criteriaQuery.from(BajOrdpused.class);
            List<Predicate> predicates = new ArrayList<>();
            Metamodel metamodel = em.getMetamodel();
            EntityType<BajOrdpused> bajOrdpusedEntityType = metamodel.entity(BajOrdpused.class);
            if (filterWrapper != null && filterWrapper.getFilters() != null) {
                for (Filter filter : filterWrapper.getFilters()) {

                    Object value = filter.getValue();
                    String field = filter.getProperty();
                    Filter.Operator operator = filter.getOperator();
                    String[] f = field.split("\\.");

                    Predicate predicate = null;
                    switch (operator) {

                        case _EQUAL:
                        case EQUAL:
                            Path<String> path = bajOrdpusedFrom.get(f[0]);
                            for (int j = 1; j < f.length; j++) {
                                path = path.get(f[j]);
                            }
                            predicate = criteriaBuilder.equal(path, value);
                            predicates.add(predicate);
                            break;
                        case LIKE:

                            predicates.add(criteriaBuilder.like(bajOrdpusedFrom.get(bajOrdpusedEntityType.getDeclaredSingularAttribute(field, String.class)), "%" + value + "%"));

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

                    Path<String> path = bajOrdpusedFrom.get(sortProperties[0]);
                    for (int j = 1; j < sortProperties.length; j++) {
                        path = path.get(sortProperties[j]);
                    }
                    if (sortProperties.length > 0) {
                        if (sortSet.getDirection().getName().equals(Sort.Direction.DESC.getName())) {
                            order = criteriaBuilder.desc(bajOrdpusedFrom.get(sortProperties[0]));
                        } else {
                            order = criteriaBuilder.asc(bajOrdpusedFrom.get(sortProperties[0]));
                        }
                    }
                    orders.add(order);
                }

                if (!orders.isEmpty()) {
                    criteriaQuery.orderBy(orders);
                }
            }

            criteriaQuery.select(bajOrdpusedFrom);
            return criteriaQuery;

        } catch (Exception e) {
            return null;
        }
    }

    public boolean isCalculated(String brchCode, String ordOrdno, String orpOrdrow) {
        FilterWrapper fw = ServiceUtils.createOrAddToFilterWrapper(null, "bajOrdpusedPK.brchCode", brchCode, Filter.Operator.EQUAL);
        fw = ServiceUtils.createOrAddToFilterWrapper(fw, "bajOrdpusedPK.orpOrdrow", orpOrdrow, Filter.Operator.EQUAL);
        fw = ServiceUtils.createOrAddToFilterWrapper(fw, "bajOrdpusedPK.ordOrdno", ordOrdno, Filter.Operator.EQUAL);
        SortWrapper sw = ServiceUtils.createOrAddToSortWrapper(null, null, null);
        List<BajOrdpused> list = getList(fw, 0, 1, sw);
        return !list.isEmpty();
    }
}
