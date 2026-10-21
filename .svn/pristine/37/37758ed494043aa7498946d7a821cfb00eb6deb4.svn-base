package ir.tamin.incomeBank.service.daramadBank;

import ir.tamin.framework.ws.rest.json.Filter;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.Sort;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.model.daramadBank.DrmdBank;
import ir.tamin.incomeBank.service.identityManager.UserBean;
import ir.tamin.incomeBank.util.ServiceUtils;
import ir.tamin.portal.model.org.Organization;
import ir.tamin.portal.model.org.User;

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
public class DrmdBankService {

    @Inject
    private EntityManager em;
    @Inject
    UserBean userBean;

    @Inject
    private ServiceUtils<DrmdBank> util;

    public Map<String, Object> getAll(FilterWrapper filter, Integer start, Integer limit, SortWrapper sort) {

        Map<String, Object> map = new HashMap<>();
        map.put("list", getList(filter, start, limit, sort));
        map.put("total", getCount(filter));
        return map;
    }

    public List<DrmdBank> getList(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) {

        TypedQuery<DrmdBank> createQuery = em.createQuery(getQuery(filterWrapper, sort));
        return util.getListWithConditions(start, limit, createQuery);

    }

    public Integer getCount(FilterWrapper filter) {
        TypedQuery<DrmdBank> createQuery = em.createQuery(getQuery(filter, null));
        return createQuery.getResultList().size();
    }

    private CriteriaQuery<DrmdBank> getQuery(FilterWrapper filterWrapper, SortWrapper sortWrapper) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<DrmdBank> criteriaQuery = criteriaBuilder.createQuery(DrmdBank.class);
            Root<DrmdBank> drmdBankFrom = criteriaQuery.from(DrmdBank.class);
            List<Predicate> predicates = new ArrayList<>();
            Metamodel metamodel = em.getMetamodel();
            EntityType<DrmdBank> drmdBankEntityType = metamodel.entity(DrmdBank.class);
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
                            Path<String> path = drmdBankFrom.get(f[0]);
                            for (int j = 1; j < f.length; j++) {
                                path = path.get(f[j]);
                            }
                            predicate = criteriaBuilder.equal(path, value);
                            predicates.add(predicate);
                            break;
                        case LIKE:

                            predicates.add(criteriaBuilder.like(drmdBankFrom.get(drmdBankEntityType.getDeclaredSingularAttribute(field, String.class)), "%" + value + "%"));

                            break;
                        default:
                            break;
                    }
                }
                criteriaQuery.where(predicates.toArray(new Predicate[]{}));
            }

            Set<Sort> sortSet0 = new HashSet<>();
            Sort e = new Sort();
            e.setProperty("drmdBankPK.bankRadif");
            e.setDirection(Sort.Direction.ASC);
            sortSet0.add(e);
            if (sortWrapper == null) {
                sortWrapper = new SortWrapper();
            }
            sortWrapper.setSortSet(sortSet0);

            if (sortWrapper != null) {
                List<Order> orders = new ArrayList<>();
                for (Sort sortSet : sortWrapper.getSortSet()) {
                    Order order = null;
                    String[] sortProperties = sortSet.getProperty().split("\\.");

                    Path<String> path = drmdBankFrom.get(sortProperties[0]);
                    for (int j = 1; j < sortProperties.length; j++) {
                        path = path.get(sortProperties[j]);
                    }
                    if (sortProperties.length > 0) {
                        if (sortSet.getDirection().getName().equals(Sort.Direction.DESC.getName())) {
                            order = criteriaBuilder.desc(drmdBankFrom.get(sortProperties[0]));
                        } else {
                            order = criteriaBuilder.asc(drmdBankFrom.get(sortProperties[0]));
                        }
                    }
                    orders.add(order);
                }

                if (!orders.isEmpty()) {
                    criteriaQuery.orderBy(orders);
                }
            }

            criteriaQuery.select(drmdBankFrom);
            return criteriaQuery;

        } catch (Exception e) {
            return null;
        }
    }

    public Map<String, Object> getItemPage(String bankRadif, FilterWrapper filter, Integer start, Integer limit, SortWrapper sortWrapper) {
        Map<String, Object> map = new HashMap<>();

        if (bankRadif == null) {
            map.put("filter", filter);
            map.put("start", start);
            map.put("limit", limit);
            map.put("sort", sortWrapper);
            return map;
        }
        if (limit == null) {
            limit = 10;
        }

        Integer countResult = getPageNumber(filter, bankRadif);
        Integer pageNumber = countResult / limit;
        start = pageNumber * limit;
        map.put("filter", filter);
        map.put("start", start);
        map.put("limit", limit);
        map.put("sort", sortWrapper);
        return map;
    }

    public Integer getPageNumber(FilterWrapper filters, String brchCode) {
//        FilterWrapper searchFilterWrapper = new FilterWrapper();
//        searchFilterWrapper.setFilters(new HashSet<Filter>());
        Filter filter = new Filter();
        filter.setOperator(Filter.Operator.BEFORE);
        filter.setValue(brchCode);
        filter.setProperty("drmdBankPK.bankRadif");
        filters.getFilters().add(filter);

//        SortWrapper sortwr = new SortWrapper();
//        sortwr.setSortSet(new HashSet<Sort>());
//        Sort sort = new Sort();
//        sort.setProperty("\"drmdBankPK.bankRadif");
//        sort.setDirection(ASC);
//        sortwr.getSortSet().add(sort);

        TypedQuery<DrmdBank> query = em.createQuery(getQuery(filters, null));
        return query.getResultList().size() - 1;


    }


}
