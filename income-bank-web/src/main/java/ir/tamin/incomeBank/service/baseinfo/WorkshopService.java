package ir.tamin.incomeBank.service.baseinfo;

import ir.tamin.framework.ws.rest.json.Filter;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.model.baseinfo.Workshop;
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
 * @author e_shoghi
 * {@code @developer A_Jamali}
 */
@Stateless
public class WorkshopService {

    @Inject
    private EntityManager em;

    @Inject
    private ServiceUtils<Workshop> util;

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sortWrapper) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", getList(filterWrapper, start, limit, sortWrapper));
        map.put("total", getCount(filterWrapper));
        return map;
    }


    public List<Workshop> getList(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) {
        TypedQuery<Workshop> createQuery = em.createQuery(getQuery(filterWrapper, sort));


        return util.getListWithConditions(start, limit, createQuery);

    }

    public Integer getCount(FilterWrapper filter) {

        TypedQuery<Workshop> createQuery = em.createQuery(getQuery(filter, null));
        return createQuery.getResultList().size();

    }

    private CriteriaQuery<Workshop> getQuery(FilterWrapper filterWrapper, SortWrapper sortWrapper) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Workshop> criteriaQuery = criteriaBuilder.createQuery(Workshop.class);
            Root<Workshop> workshoFrom = criteriaQuery.from(Workshop.class);
            Metamodel m = em.getMetamodel();
            EntityType<Workshop> workshopEntityType = m.entity(Workshop.class);
            List<Predicate> predicates = new ArrayList<>();
            if (filterWrapper != null && filterWrapper.getFilters() != null) {
                for (Filter filter : filterWrapper.getFilters()) {

                    Object value = filter.getValue();
                    String field = filter.getProperty();
                    Filter.Operator operator = filter.getOperator();
                    String[] f = field.split("\\.");
                    Predicate predicate = null;
                    switch (operator) {
                        case LIKE:
                            predicates.add(criteriaBuilder.like(workshoFrom.get(workshopEntityType.getDeclaredSingularAttribute(field, String.class)), "%" + value + "%"));
                            break;
                        case _EQUAL:
                        case EQUAL:
                            Path<String> path = workshoFrom.get(f[0]);
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

            criteriaQuery.select(workshoFrom);
            return criteriaQuery;

        } catch (Exception e) {
            return null;
        }
    }

    public Workshop get(String code) {
        return em.find(Workshop.class, code);
    }
}
