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
import ir.tamin.incomeBank.model.daramadBank.RegInsuranceSpec;
import ir.tamin.incomeBank.model.daramadBank.RegWorkshopSpec;
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
 * @author f_fotuhi
 * {@code @developer A_Jamali}
 */
@Stateless
public class CustomerCodeService {

    @Inject
    private EntityManager em;
    @Inject
    UserBean userBean;
    @Inject
    private ServiceUtils<RegWorkshopSpec> utilForWorkshopList;
    @Inject
    private ServiceUtils<RegInsuranceSpec> utilInsurance;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) {

        Map<String, Object> map = new HashMap<>();

        String customerType = null;
        if (filterWrapper != null && filterWrapper.getFilters() != null) {
            Object[] filterArray = filterWrapper.getFilters().toArray();

            for (Object o : filterArray) {
                Filter filter = (Filter) o;
                String field = filter.getProperty();
                String value = filter.getValue();
                if (field.equals("mastCustType")) {
                    if (value.equals("04") || value.equals("05") || value.equals("06")) {
                        customerType = "workShop";
                        filter.setOperator(Filter.Operator.NOT_EQUAL);

                    } else if (value.equals("01") || value.equals("02") || value.equals("03")) {
                        customerType = "insurance";
                    }
                }
            }
        }

        if (customerType != null && customerType.equals("workShop")) {
            map.put("list", getWorkShopList(filterWrapper, start, limit, sort));
            map.put("total", getWorkShopCount(filterWrapper));
        } else if (customerType != null && customerType.equals("insurance")) {
            map.put("list", getInsuranceList(filterWrapper, start, limit, sort));
            map.put("total", getInsuranceCount(filterWrapper));
        }
        return map;
    }

    public List<RegWorkshopSpec> getWorkShopList(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) {

        TypedQuery<RegWorkshopSpec> createQuery = em.createQuery(getWorkShopQuery(filterWrapper, sort));
        return utilForWorkshopList.getListWithConditions(start, limit, createQuery);
    }

    public Integer getWorkShopCount(FilterWrapper filter) {

        TypedQuery<RegWorkshopSpec> createQuery = em.createQuery(getWorkShopQuery(filter, null));
        return createQuery.getResultList().size();

    }

    public List<RegInsuranceSpec> getInsuranceList(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) {

        TypedQuery<RegInsuranceSpec> createQuery = em.createQuery(getInsuranceQuery(filterWrapper, sort));
        return utilInsurance.getListWithConditions(start, limit, createQuery);
    }

    public Integer getInsuranceCount(FilterWrapper filter) {

        TypedQuery<RegInsuranceSpec> createQuery = em.createQuery(getInsuranceQuery(filter, null));
        return createQuery.getResultList().size();

    }

    private CriteriaQuery<RegWorkshopSpec> getWorkShopQuery(FilterWrapper filterWrapper, SortWrapper sortWrapper) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<RegWorkshopSpec> criteriaQuery = criteriaBuilder.createQuery(RegWorkshopSpec.class);
            Root<RegWorkshopSpec> workshopFrom = criteriaQuery.from(RegWorkshopSpec.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            Metamodel metamodel = em.getMetamodel();
            EntityType<RegWorkshopSpec> workshopEntityType = metamodel.entity(RegWorkshopSpec.class);
            if (filterWrapper != null && filterWrapper.getFilters() != null) {
                for (Filter filter : filterWrapper.getFilters()) {

                    Object value = filter.getValue();
                    String field = filter.getProperty();
                    Filter.Operator operator = filter.getOperator();

                    if ("codeName".equals(field)) {
                        field = "regWorkshopSpecPK.workShopId";
                    }

                    String[] f = field.split("\\.");
                    Predicate predicate = null;
                    switch (operator) {

                        case _EQUAL:
                        case EQUAL:
                            Path<String> path = workshopFrom.get(f[0]);
                            for (int j = 1; j < f.length; j++) {
                                path = path.get(f[j]);
                            }
                            predicate = criteriaBuilder.equal(path, value);
                            predicates.add(predicate);
                            break;
                        case LIKE:

                            path = workshopFrom.get(f[0]);
                            for (int j = 1; j < f.length; j++) {
                                path = path.get(f[j]);
                            }
                            if ("regWorkshopSpecPK.workShopId".equals(field)) {

                                // path = workshopFrom.get("regWorkshopSpecPK.workShopId");

                                predicates.add(
                                        criteriaBuilder.or(
                                                criteriaBuilder.like(path, value.toString()),
                                                criteriaBuilder.like(workshopFrom.get(workshopEntityType.getDeclaredSingularAttribute("workShopName", String.class)), value.toString())
                                        )
                                );
                            } else {
                                predicates.add(criteriaBuilder.like(workshopFrom.get(workshopEntityType.getDeclaredSingularAttribute(field, String.class)), value.toString()));
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

                    Path<String> path = workshopFrom.get(sortProperties[0]);
                    for (int j = 1; j < sortProperties.length; j++) {
                        path = path.get(sortProperties[j]);
                    }
                    if (sortProperties.length == 1) {
                        if (sortSet.getDirection().getName().equals(Sort.Direction.DESC.getName())) {
                            order = criteriaBuilder.desc(workshopFrom.get(sortProperties[0]));
                        } else {
                            order = criteriaBuilder.asc(workshopFrom.get(sortProperties[0]));
                        }
                    }
                    orders.add(order);
                }

                if (!orders.isEmpty()) {
                    criteriaQuery.orderBy(orders);
                }
            }

            criteriaQuery.select(workshopFrom);
            return criteriaQuery;

        } catch (Exception e) {
            return null;
        }
    }

    private CriteriaQuery<RegInsuranceSpec> getInsuranceQuery(FilterWrapper filterWrapper, SortWrapper sortWrapper) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<RegInsuranceSpec> criteriaQuery = criteriaBuilder.createQuery(RegInsuranceSpec.class);
            Root<RegInsuranceSpec> insuranceFrom = criteriaQuery.from(RegInsuranceSpec.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            Metamodel metamodel = em.getMetamodel();
            EntityType<RegInsuranceSpec> insuranceEntityType = metamodel.entity(RegInsuranceSpec.class);
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
                            Path<String> path = insuranceFrom.get(f[0]);
                            for (int j = 1; j < f.length; j++) {
                                path = path.get(f[j]);
                            }
                            predicate = criteriaBuilder.equal(path, value);
                            predicates.add(predicate);
                            break;
                        case LIKE:

                            if ("codeName".equals(field)) {

                                path = insuranceFrom.get("code");

                                predicates.add(
                                        criteriaBuilder.or(
                                                criteriaBuilder.like(path, value.toString()),
                                                criteriaBuilder.like(insuranceFrom.get(insuranceEntityType.getDeclaredSingularAttribute("risuFName", String.class)), value.toString()),
                                                criteriaBuilder.like(insuranceFrom.get(insuranceEntityType.getDeclaredSingularAttribute("risuLName", String.class)), value.toString())
                                        )
                                );
                            } else {
                                predicates.add(criteriaBuilder.like(insuranceFrom.get(insuranceEntityType.getDeclaredSingularAttribute(field, String.class)), value.toString()));
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

                    Path<String> path = insuranceFrom.get(sortProperties[0]);
                    for (int j = 1; j < sortProperties.length; j++) {
                        path = path.get(sortProperties[j]);
                    }
                    if (sortProperties.length == 1) {
                        if (sortSet.getDirection().getName().equals(Sort.Direction.DESC.getName())) {
                            order = criteriaBuilder.desc(insuranceFrom.get(sortProperties[0]));
                        } else {
                            order = criteriaBuilder.asc(insuranceFrom.get(sortProperties[0]));
                        }
                    }
                    orders.add(order);
                }

                if (!orders.isEmpty()) {
                    criteriaQuery.orderBy(orders);
                }
            }

            criteriaQuery.select(insuranceFrom);
            return criteriaQuery;

        } catch (Exception e) {
            return null;
        }
    }

}
