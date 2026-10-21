package ir.tamin.incomeBank.service.daramadBank;

import ir.tamin.framework.ws.rest.json.Filter;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.Sort;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.model.daramadBank.VwDskDrmdTel;
import ir.tamin.incomeBank.util.ServiceUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author f_fotuhi
 * {@code @developer A_Jamali}
 */
public class VwDskDrmdTelService {

    @Inject
    EntityManager em;


    @Inject
    private ServiceUtils<VwDskDrmdTel> util;
    public List<VwDskDrmdTel> getList(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) {

        TypedQuery<VwDskDrmdTel> createQuery = em.createQuery(getQuery(filterWrapper, sort));
        return util.getListWithConditions(start,limit,createQuery);
    }

    public Integer getCount(FilterWrapper filter) {

        TypedQuery<VwDskDrmdTel> createQuery = em.createQuery(getQuery(filter, null));
        return createQuery.getResultList().size();
    }

    private CriteriaQuery<VwDskDrmdTel> getQuery(FilterWrapper filterWrapper, SortWrapper sortWrapper) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<VwDskDrmdTel> criteriaQuery = criteriaBuilder.createQuery(VwDskDrmdTel.class);
            Root<VwDskDrmdTel> dskDrmdTelFrom = criteriaQuery.from(VwDskDrmdTel.class);
            List<Predicate> predicates = new ArrayList<>();
            Metamodel metamodel = em.getMetamodel();
            EntityType<VwDskDrmdTel> dskDrmdTelEntityType = metamodel.entity(VwDskDrmdTel.class);
            if (filterWrapper != null && filterWrapper.getFilters() != null) {
                for (Filter filter : filterWrapper.getFilters()) {

                    Object value = filter.getValue();
                    String field = filter.getProperty();
                    Filter.Operator operator = filter.getOperator();
                    String[] f = field.split("\\.");

                    Predicate predicate;
                    if (operator == Filter.Operator.EQUAL) {
                        Path<String> path = dskDrmdTelFrom.get(f[0]);
                        for (int j = 1; j < f.length; j++) {
                            path = path.get(f[j]);
                        }
                        predicate = criteriaBuilder.equal(path, value);
                        predicates.add(predicate);
                    }
                }
                criteriaQuery.where(predicates.toArray(new Predicate[]{}));
            }

            if (sortWrapper != null) {
                List<Order> orders = new ArrayList<>();
                for (Sort sortSet : sortWrapper.getSortSet()) {
                    Order order = null;
                    String[] sortProperties = sortSet.getProperty().split("\\.");

                    Path<String> path = dskDrmdTelFrom.get(sortProperties[0]);
                    for (int j = 1; j < sortProperties.length; j++) {
                        path = path.get(sortProperties[j]);
                    }
                    if (sortProperties.length > 0) {
                        if (sortSet.getDirection().getName().equals(Sort.Direction.DESC.getName())) {
                            order = criteriaBuilder.desc(dskDrmdTelFrom.get(sortProperties[0]));
                        } else {
                            order = criteriaBuilder.asc(dskDrmdTelFrom.get(sortProperties[0]));
                        }
                    }
                    orders.add(order);
                }

                if (!orders.isEmpty()) {
                    criteriaQuery.orderBy(orders);
                }
            }

            criteriaQuery.select(dskDrmdTelFrom);
            return criteriaQuery;

        } catch (Exception e) {
            return null;
        }
    }
}
