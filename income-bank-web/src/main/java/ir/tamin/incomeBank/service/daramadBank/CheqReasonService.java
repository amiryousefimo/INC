package ir.tamin.incomeBank.service.daramadBank;

import ir.tamin.framework.ws.rest.json.Filter;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.Sort;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.model.daramadBank.CheqReason;
import ir.tamin.incomeBank.util.ServiceUtils;

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
 * @author f_fotuhi
 * {@code @developer}  A_Jamali
 */
public class CheqReasonService {

    @Inject
    ServiceUtils<CheqReason> util;

    @Inject
    private EntityManager em;


    public Map<String, Object> getAll(FilterWrapper filter, Integer start, Integer limit, SortWrapper sort) {

        Map<String, Object> map = new HashMap<>();
        map.put("list", getList(filter, start, limit, sort));
        map.put("total", getCount(filter));
        return map;
    }


    public List<CheqReason> getList(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) {

        TypedQuery<CheqReason> createQuery = em.createQuery(getQuery(filterWrapper, sort));
        return util.getListWithConditions(start, limit, createQuery);
    }

    public Integer getCount(FilterWrapper filter) {
        TypedQuery<CheqReason> createQuery = em.createQuery(getQuery(filter, null));
        return createQuery.getResultList().size();
    }

    private CriteriaQuery<CheqReason> getQuery(FilterWrapper filterWrapper, SortWrapper sortWrapper) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<CheqReason> criteriaQuery = criteriaBuilder.createQuery(CheqReason.class);
            Root<CheqReason> cheqReasonFrom = criteriaQuery.from(CheqReason.class);
            List<Predicate> predicates = new ArrayList<>();
            Metamodel metamodel = em.getMetamodel();
            EntityType<CheqReason> cheqReasonEntityType = metamodel.entity(CheqReason.class);
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
                            Path<String> path = cheqReasonFrom.get(f[0]);
                            for (int j = 1; j < f.length; j++) {
                                path = path.get(f[j]);
                            }
                            predicate = criteriaBuilder.equal(path, value);
                            predicates.add(predicate);
                            break;
                        case LIKE:
                            if ("reasonCodeName".equals(field)) {

                                path = cheqReasonFrom.get("nrCheqReasonCode");

                                predicates.add(
                                        criteriaBuilder.or(
                                                criteriaBuilder.like(path, value.toString()),
                                                criteriaBuilder.like(cheqReasonFrom.get(cheqReasonEntityType.getDeclaredSingularAttribute("nrCheqReasonDesc", String.class)), value.toString())
                                        )
                                );
                            } else {
                                predicates.add(criteriaBuilder.like(cheqReasonFrom.get(cheqReasonEntityType.getDeclaredSingularAttribute(field, String.class)), "%" + value + "%"));
                            }
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

                    Path<String> path = cheqReasonFrom.get(sortProperties[0]);
                    for (int j = 1; j < sortProperties.length; j++) {
                        path = path.get(sortProperties[j]);
                    }
                    if (sortProperties.length > 0) {
                        if (sortSet.getDirection().getName().equals(Sort.Direction.DESC.getName())) {
                            order = criteriaBuilder.desc(cheqReasonFrom.get(sortProperties[0]));
                        } else {
                            order = criteriaBuilder.asc(cheqReasonFrom.get(sortProperties[0]));
                        }
                    }
                    orders.add(order);
                }

                if (!orders.isEmpty()) {
                    criteriaQuery.orderBy(orders);
                }
            }

            criteriaQuery.select(cheqReasonFrom);
            return criteriaQuery;

        } catch (Exception e) {
            return null;
        }
    }
}
