package ir.tamin.incomeBank.service.centralPayment;

import ir.tamin.framework.ws.rest.json.Filter;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.Sort;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.model.centralPayment.CalcSubsystemReturnModel;
import ir.tamin.incomeBank.model.centralPayment.GlSubsystemType;
import ir.tamin.incomeBank.util.ServiceUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author s_maknooni
 * {@code @developer A_Jamali}
 */
@Stateless
public class SubSystemService {

    @Inject
    private EntityManager em;
    @Inject
    ServiceUtils<GlSubsystemType> util;


    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sortWrapper) {

        Map<String, Object> map = new HashMap<>();
        map.put("list", getList(filterWrapper, start, limit, sortWrapper));
        map.put("total", getCount(filterWrapper));
        return map;
    }

    public List<GlSubsystemType> getList(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) {
        TypedQuery<GlSubsystemType> createQuery = em.createQuery(getQuery(filterWrapper, sort));

        return util.getListWithConditions(start, limit, createQuery);


    }

    public Integer getCount(FilterWrapper filter) {

        TypedQuery<GlSubsystemType> createQuery = em.createQuery(getQuery(filter, null));
        return createQuery.getResultList().size();

    }

    private CriteriaQuery<GlSubsystemType> getQuery(FilterWrapper filterWrapper, SortWrapper sortWrapper) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<GlSubsystemType> criteriaQuery = criteriaBuilder.createQuery(GlSubsystemType.class);
            Root<GlSubsystemType> subSystemFrom = criteriaQuery.from(GlSubsystemType.class);
            Metamodel m = em.getMetamodel();
            EntityType<GlSubsystemType> subSystemEntityType = m.entity(GlSubsystemType.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (filterWrapper != null && filterWrapper.getFilters() != null) {
                for (Filter filter : filterWrapper.getFilters()) {

                    Object value = filter.getValue();
                    String field = filter.getProperty();
                    Filter.Operator operator = filter.getOperator();
                    String[] f = field.split("\\.");
                    Predicate predicate = null;
                    Path<String> path;
                    switch (operator) {
                        case LIKE:
                            if ("codeTitle".equals(field)) {

                                path = subSystemFrom.get("code");

                                predicates.add(
                                        criteriaBuilder.or(
                                                criteriaBuilder.like(path, value.toString()),
                                                criteriaBuilder.like(subSystemFrom.get(subSystemEntityType.getDeclaredSingularAttribute("title", String.class)), value.toString())
                                        )
                                );
                            } else {
                                predicates.add(criteriaBuilder.like(subSystemFrom.get(subSystemEntityType.getDeclaredSingularAttribute(field, String.class)), "%" + value + "%"));
                            }
                            break;
                        case _EQUAL:
                        case EQUAL:
                            path = subSystemFrom.get(f[0]);
                            for (int j = 1; j < f.length; j++) {
                                path = path.get(f[j]);
                            }
                            predicate = criteriaBuilder.equal(path, value);
                            predicates.add(predicate);
                            break;
                        case NOT_EQUAL:
                            path = subSystemFrom.get(f[0]);
                            for (int j = 1; j < f.length; j++) {
                                path = path.get(f[j]);
                            }
                            predicate = criteriaBuilder.notEqual(path, value);
                            predicates.add(predicate);
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

                    Path<String> path = subSystemFrom.get(sortProperties[0]);
                    for (int j = 1; j < sortProperties.length; j++) {
                        path = path.get(sortProperties[j]);
                    }
                    if (sortProperties.length > 1) {

                    } else {
                        if (sortSet.getDirection().getName().equals(Sort.Direction.DESC.getName())) {
                            order = criteriaBuilder.desc(subSystemFrom.get(sortProperties[0]));
                        } else {
                            order = criteriaBuilder.asc(subSystemFrom.get(sortProperties[0]));
                        }
                    }
                    orders.add(order);
                }

                if (!orders.isEmpty()) {
                    criteriaQuery.orderBy(orders);
                }
            }

            criteriaQuery.select(subSystemFrom);
            return criteriaQuery;

        } catch (Exception e) {
            return null;
        }
    }

    public List<CalcSubsystemReturnModel> getAllCalcSubSystems(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sortWrapper) {

        List<CalcSubsystemReturnModel> result = new ArrayList<>();
        CalcSubsystemReturnModel model;
        List<GlSubsystemType> subsystemTypeList = getList(filterWrapper, start, limit, sortWrapper);

        for (GlSubsystemType sub : subsystemTypeList) {
            model = new CalcSubsystemReturnModel(sub, 0L, BigDecimal.ZERO);
            result.add(model);
        }

        return result;
    }
//todo decide for this

//    public GlSubsystemType getByCode(String code) {
//        FilterWrapper filterWrapper = new FilterWrapper();
//        Filter filter = new Filter();
//        filter.setProperty("code");
//        filter.setValue(code);
//        filter.setOperator(Filter.Operator.EQUAL);
//        Set<Filter> filters = new HashSet<>();
//        filters.add(filter);
//        filterWrapper.setFilters(filters);
//        List<GlSubsystemType> list = getList(filterWrapper, 0, 100, null);
//
//        if (list != null && !list.isEmpty()) {
//            return list.get(0);
//        } else {
//            return null;
//        }
//
//    }

    public GlSubsystemType getByCode(String code, String systemId) {
        FilterWrapper filterWrapper = new FilterWrapper();
        Filter codeFilter = new Filter();
        codeFilter.setProperty("code");
        codeFilter.setValue(code);
        codeFilter.setOperator(Filter.Operator.EQUAL);

        Filter headIdFilter = new Filter();
        headIdFilter.setProperty("system.systemId");
        headIdFilter.setValue(systemId);
        headIdFilter.setOperator(Filter.Operator.EQUAL);

        Set<Filter> filters = new HashSet<>();

        filters.add(codeFilter);
        filters.add(headIdFilter);
        filterWrapper.setFilters(filters);

        List<GlSubsystemType> list = getList(filterWrapper, 0, 100, null);

        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }

    }
}
