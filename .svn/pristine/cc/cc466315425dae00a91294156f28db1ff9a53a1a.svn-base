package ir.tamin.incomeBank.dao;

import ir.tamin.framework.ws.rest.json.Filter;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.Sort;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.util.ServiceUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * {@code @developer A_Jamali}
 */

public abstract class BaseDAOService<T> {

    public abstract Class<T> getClazz();

    public abstract EntityManager getEntityManager();

    @Inject
    ServiceUtils<T> util;

    public List<T> search(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) {


        TypedQuery<T> createQuery = getEntityManager().createQuery(getListQuery(filterWrapper, sort));


        return util.getListWithConditions(start, limit, createQuery);

    }

    public Long getCount(FilterWrapper filter) {
        TypedQuery createQuery = getEntityManager().createQuery(getCountQuery(filter));
        return (Long) createQuery.getSingleResult();
    }

    private CriteriaQuery getListQuery(FilterWrapper filterWrapper, SortWrapper sortWrapper) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<T> entityFrom = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, entityFrom, filterWrapper, sortWrapper);
        criteriaQuery.select(entityFrom);
        return criteriaQuery;
    }

    private CriteriaQuery getCountQuery(FilterWrapper filterWrapper) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<T> entityFrom = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, entityFrom, filterWrapper, null);
        criteriaQuery.select(criteriaBuilder.count(entityFrom));
        return criteriaQuery;
    }

    public CriteriaQuery getFilteredQuery(CriteriaQuery criteriaQuery,
                                          Root<T> entityFrom,
                                          FilterWrapper filterWrapper,
                                          SortWrapper sortWrapper) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        try {
            criteriaQuery.where(getFilters(filterWrapper, entityFrom, criteriaBuilder).toArray(new Predicate[]{}));

            criteriaQuery.orderBy(getOrders(sortWrapper, entityFrom, criteriaBuilder));

            return criteriaQuery;

        } catch (Exception e) {
            return null;
        }
    }

    private List<Order> getOrders(SortWrapper sortWrapper, Root<T> entityFrom, CriteriaBuilder criteriaBuilder) {
        List<Order> orders = new ArrayList<>();
        if (sortWrapper != null) {
            for (Sort sortSet : sortWrapper.getSortSet()) {
                Order order = null;
                String[] sortProperties = sortSet.getProperty().split("\\.");

                javax.persistence.criteria.Path path = entityFrom.get(sortProperties[0]);
                for (int j = 1; j < sortProperties.length; j++) {
                    path = path.get(sortProperties[j]);
                }
                if (sortProperties.length > 0) {
                    if (sortSet.getDirection().getName().equals(Sort.Direction.DESC.getName())) {
                        order = criteriaBuilder.desc(entityFrom.get(sortProperties[0]));
                    } else {
                        order = criteriaBuilder.asc(entityFrom.get(sortProperties[0]));
                    }
                }
                orders.add(order);
            }
        }
        return orders;
    }

    protected List<Predicate> getFilters(FilterWrapper filterWrapper, Root<T> entityFrom, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Metamodel metaModel = getEntityManager().getMetamodel();
        EntityType<T> entityType = metaModel.entity(getClazz());
        List<Join> innerJoins = new ArrayList<>();
        if (filterWrapper != null && filterWrapper.getFilters() != null) {
            for (Filter filter : filterWrapper.getFilters()) {

                Object value = filter.getValue();
                String field = filter.getProperty();
                Filter.Operator operator = filter.getOperator();
                String[] f = field.split("\\.");
                Predicate predicate = null;

                Path path = entityFrom.get(f[0]);
                String name = f[0];
                for (int j = 1; j < f.length; j++) {
                    if (Collection.class.isAssignableFrom(path.getJavaType())) {
                        innerJoins.add(entityFrom.join(name));
                    }
                    path = path.get(f[j]);
                    name = f[j];
                }
                for (int j = 1; j < f.length; j++) {
                    path = path.get(f[j]);
                }
                switch (operator) {
                    case _EQUAL:
                    case EQUAL:
                        predicates.add(criteriaBuilder.equal(path, value));
                        break;
                    case LIKE:
                        predicates.add(criteriaBuilder.like(entityFrom.get(entityType.getDeclaredSingularAttribute(field, String.class)), "%" + value + "%"));
                        break;
                    case AFTER:
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(path, value.toString()));
                        break;
                    case BEFORE:
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(path, value.toString()));
                        break;
                    case GREATER_THAN:
                        predicates.add(criteriaBuilder.greaterThan(path, value.toString()));
                        break;
                    case LESS_THAN:
                        predicates.add(criteriaBuilder.lessThan(path, value.toString()));
                        break;
                    case IN:
                        String[] valuesArray = value.toString().split(",");
                        CriteriaBuilder.In<Object> inPredicate = criteriaBuilder.in(path);
                        for (String val : valuesArray) {
                            inPredicate.value(val);
                        }
                        predicates.add(inPredicate);
                        break;
                    case IS_NULL:
                        predicates.add(criteriaBuilder.isNull(path));
                        break;
                    case IS_NOT_NULL:
                        predicates.add(criteriaBuilder.isNotNull(path));
                        break;
                    default:
                        break;
                }
            }
        }
        return predicates;
    }
}
