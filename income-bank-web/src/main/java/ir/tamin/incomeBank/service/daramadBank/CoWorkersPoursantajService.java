package ir.tamin.incomeBank.service.daramadBank;

import ir.tamin.framework.cdi.util.MessageBundle;
import ir.tamin.framework.core.util.Bundle;
import ir.tamin.framework.ws.rest.json.Filter;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.model.daramadBank.*;
import ir.tamin.incomeBank.service.jdbc.StoredProcedure;
import ir.tamin.incomeBank.util.DateUtilsFramework;
import ir.tamin.incomeBank.util.ServiceUtils;
import org.eclipse.persistence.internal.jpa.querydef.ListJoinImpl;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.sql.SQLException;
import java.util.*;

/**
 * @author e_shoghi
 * {@code @developer A_Jamali}
 */
@Stateless
public class CoWorkersPoursantajService {

    @Inject
    private EntityManager em;
    @Inject
    private StoredProcedure procedure;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CoWorkersPoursantajService.class);
    @Inject
    @MessageBundle
    Bundle messageBundle;
    @Inject
    private ServiceUtils<PoursantajView> util;


    public BajListHeader get(BajListHeaderPK bajListHeaderPK) throws WebApplicationException {
        return em.find(BajListHeader.class, bajListHeaderPK);
    }

    public Map<String, Object> getAllFromView(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sortWrapper) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", getListFromView(filterWrapper, start, limit, sortWrapper));
        map.put("total", getCountFromView(filterWrapper));
        return map;
    }


    public List<PoursantajView> getListFromView(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) {
        TypedQuery<PoursantajView> createQuery = em.createQuery(getQueryFromView(filterWrapper, sort));
        return util.getListWithConditions(start, limit, createQuery);
    }

    public Integer getCountFromView(FilterWrapper filter) {

        TypedQuery<PoursantajView> createQuery = em.createQuery(getQueryFromView(filter, null));
        return createQuery.getResultList().size();

    }

    private CriteriaQuery<PoursantajView> getQueryFromView(FilterWrapper filterWrapper, SortWrapper sortWrapper) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<PoursantajView> criteriaQuery = criteriaBuilder.createQuery(PoursantajView.class);
            Root<PoursantajView> coWorkersPousantajFrom = criteriaQuery.from(PoursantajView.class);
            Metamodel m = em.getMetamodel();
            EntityType<PoursantajView> coWorkersPoursantajEntityType = m.entity(PoursantajView.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (filterWrapper != null && filterWrapper.getFilters() != null) {
                for (Filter filter : filterWrapper.getFilters()) {

                    Object value = filter.getValue();
                    String field = filter.getProperty();
                    Filter.Operator operator = filter.getOperator();
                    String[] f = field.split("\\.");
                    Predicate predicate = null;
                    Path<String> path = coWorkersPousantajFrom.get(f[0]);
                    for (int j = 1; j < f.length; j++) {
                        path = path.get(f[j]);
                    }
                    switch (operator) {
                        case LIKE:
                            predicates.add(criteriaBuilder.like(coWorkersPousantajFrom.get(coWorkersPoursantajEntityType.getDeclaredSingularAttribute(field, String.class)), "%" + value + "%"));
                            break;
                        case _EQUAL:
                        case EQUAL:
                            if ("listDate".equals(field)) {
                                predicate = criteriaBuilder.equal(path, DateUtilsFramework.format(new Date(Long.parseLong(value + "")), "yyyyMM"));
                                //  Expression<Long> expression = criteriaBuilder.substring(path, 0, 6);
                                //  predicate = criteriaBuilder.equal(expression, DateUtilsFramework.format(new Date(Long.parseLong(value + "")), "yyyyMM"));
                            } else if ("vouchDate".equals(field)) {
                                predicate = criteriaBuilder.equal(path, DateUtilsFramework.format(new Date(Long.parseLong(value + "")), "yyyyMM"));
                            } else {
                                predicate = criteriaBuilder.equal(path, value);
                            }
                            predicates.add(predicate);
                            break;
                        case BEFORE:
                            predicates.add(criteriaBuilder.lessThanOrEqualTo(path, DateUtilsFramework.format(new Date(Long.parseLong(value + "")), "yyyyMM")));
                            break;
                        case AFTER:
                            predicates.add(criteriaBuilder.greaterThanOrEqualTo(path, DateUtilsFramework.format(new Date(Long.parseLong(value + "")), "yyyyMM")));
                            break;
                        case IN:
                            if ("vouchDate".equals(field) && "2".equals(value)) {
                                predicate = criteriaBuilder.isNull(path);
                            } else if ("vouchDate".equals(field) && "1".equals(value)) {
                                predicate = criteriaBuilder.isNotNull(path);
                            }
                            predicates.add(predicate);
                        default:
                            break;
                    }
                }
                criteriaQuery.where(predicates.toArray(new Predicate[]{}));
            }

            criteriaQuery.select(coWorkersPousantajFrom);
            return criteriaQuery;

        } catch (Exception e) {
            return null;
        }
    }

    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sortWrapper) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", getList(filterWrapper, start, limit, sortWrapper));
        map.put("total", getCount(filterWrapper));
        return map;
    }

    public List<BajListHeader> getList(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) {
        TypedQuery<BajListHeader> createQuery = em.createQuery(getQuery(filterWrapper, sort));
        List<BajListHeader> bajListHeaders = new ArrayList<>();
        if (start != null && limit != null) {
            createQuery.setFirstResult(start);
            createQuery.setMaxResults(limit);
            createQuery.getResultList();
            bajListHeaders = createQuery.setFirstResult(start).setMaxResults(limit).getResultList();
        }
        if (start == null && limit != null) {
            bajListHeaders = createQuery.setMaxResults(limit).getResultList();
        }
        if (start != null && limit == null) {
            bajListHeaders = createQuery.setFirstResult(start).getResultList();
        }
        if (start == null && limit == null) {
            bajListHeaders = createQuery.getResultList();
        }

        return bajListHeaders;
    }

    public Integer getCount(FilterWrapper filter) {

        TypedQuery<BajListHeader> createQuery = em.createQuery(getQuery(filter, null));
        return createQuery.getResultList().size();

    }

    private CriteriaQuery<BajListHeader> getQuery(FilterWrapper filterWrapper, SortWrapper sortWrapper) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<BajListHeader> criteriaQuery = criteriaBuilder.createQuery(BajListHeader.class);
            Root<BajListHeader> bajListHeaderFrom = criteriaQuery.from(BajListHeader.class);
            Metamodel m = em.getMetamodel();
            EntityType<BajListHeader> bajListHeaderEntityType = m.entity(BajListHeader.class);
            EntityType<TechnicalCalculationDetail> technicalCalculationDetailEntityType = m.entity(TechnicalCalculationDetail.class);
            EntityType<CoWorkersPoursantaj> coWorkersPoursantajEntityType = m.entity(CoWorkersPoursantaj.class);
            List<Predicate> predicates = new ArrayList<>();
            Fetch<BajListHeader, TechnicalCalculationDetail> detailsFetch = bajListHeaderFrom.fetch(bajListHeaderEntityType.getDeclaredSingularAttribute("technicalCalculationDetail", TechnicalCalculationDetail.class), JoinType.INNER);
//             JoinImpl<BajListHeader, TechnicalCalculationDetail> detailsJoinImpl = (JoinImpl<BajListHeader, TechnicalCalculationDetail>) detailsFetch;
            ListJoinImpl<BajListHeader, TechnicalCalculationDetail> detailsJoinImpl = (ListJoinImpl<BajListHeader, TechnicalCalculationDetail>) detailsFetch;
//            ListJoin <BajListHeader, TechnicalCalculationDetail> detailsJoinImpl = (ListJoin <BajListHeader, TechnicalCalculationDetail>)detailsFetch;
            Predicate poursantajPredicate = criteriaBuilder.equal(detailsJoinImpl.get(technicalCalculationDetailEntityType.getDeclaredSingularAttribute("tecSubjectType", String.class)), "2");
            Expression<String> coWorkersExpression = criteriaBuilder.substring(detailsJoinImpl.get(technicalCalculationDetailEntityType.getDeclaredSingularAttribute("workshopIdentity", String.class)), 4, 3);
            Predicate coWorkersPredicate = criteriaBuilder.equal(coWorkersExpression, "686");
            detailsJoinImpl.on(criteriaBuilder.or(poursantajPredicate, coWorkersPredicate));
//            predicates.add(criteriaBuilder.or(poursantajPredicate, coWorkersPredicate));
            if (filterWrapper != null && filterWrapper.getFilters() != null) {
                for (Filter filter : filterWrapper.getFilters()) {

                    Object value = filter.getValue();
                    String field = filter.getProperty();
                    Filter.Operator operator = filter.getOperator();
                    String[] f = field.split("\\.");
                    Predicate predicate = null;
                    Path<String> path = bajListHeaderFrom.get(f[0]);
                    for (int j = 1; j < f.length; j++) {
                        path = path.get(f[j]);
                    }
                    switch (operator) {
                        case LIKE:
                            predicates.add(criteriaBuilder.like(bajListHeaderFrom.get(bajListHeaderEntityType.getDeclaredSingularAttribute(field, String.class)), "%" + value + "%"));
                            break;
                        case _EQUAL:
                        case EQUAL:
                            if ("listDate".equals(field)) {
                                // Expression<Long> expression = criteriaBuilder.substring(path, 0, 6);
                                // predicate = criteriaBuilder.equal(expression, DateUtilsFramework.format(new Date(Long.parseLong(value + "")), "yyyyMM"));
                                predicate = criteriaBuilder.equal(path, DateUtilsFramework.format(new Date(Long.parseLong(value + "")), "yyyyMM"));
                                predicates.add(predicate);
                            } else if ("coWorkersPoursantaj.vouchDate".equals(field)) {
                                predicate = criteriaBuilder.equal(path, DateUtilsFramework.format(new Date(Long.parseLong(value + "")), "yyyyMM"));
                                predicates.add(predicate);
                            } else if ("technicalCalculationDetail.tecSubjectType".equals(field) && "3".equals(value)) {
//                                detailsJoinImpl.on(criteriaBuilder.notEqual(detailsJoinImpl.get(TechnicalCalculationDetail_.tecSubjectType), "2"));
//                                predicate = criteriaBuilder.notEqual(path, "2");
                                predicates.add(coWorkersPredicate);
                            } else if ("technicalCalculationDetail.tecSubjectType".equals(field) && "2".equals(value)) {
//                                detailsJoinImpl.on(criteriaBuilder.equal(detailsJoinImpl.get(TechnicalCalculationDetail_.tecSubjectType), "2"));
                                predicates.add(poursantajPredicate);
                            } else {
                                predicate = criteriaBuilder.equal(path, value);
                                predicates.add(predicate);
                            }

                            break;
                        case BEFORE:
                            predicates.add(criteriaBuilder.lessThanOrEqualTo(path, DateUtilsFramework.format(new Date(Long.parseLong(value + "")), "yyyyMM")));
                            break;
                        case AFTER:
                            predicates.add(criteriaBuilder.greaterThanOrEqualTo(path, DateUtilsFramework.format(new Date(Long.parseLong(value + "")), "yyyyMM")));
                            break;
                        case IN:
                            if ("coWorkersPoursantaj.vouchDate".equals(field) && "2".equals(value)) {
                                Fetch<BajListHeader, CoWorkersPoursantaj> bajListHeaderFetch = bajListHeaderFrom.fetch(bajListHeaderEntityType.getDeclaredSingularAttribute("coWorkersPoursantaj", CoWorkersPoursantaj.class), JoinType.LEFT);
                                Join<BajListHeader, CoWorkersPoursantaj> join = (Join<BajListHeader, CoWorkersPoursantaj>) bajListHeaderFetch;//bajListHeaderFrom.fetch("coWorkersPoursantaj");
                                predicate = criteriaBuilder.isNull(join.get(coWorkersPoursantajEntityType.getDeclaredSingularAttribute("vouchDate", CoWorkersPoursantaj.class)));
                            } else if ("coWorkersPoursantaj.vouchDate".equals(field) && "1".equals(value)) {
                                predicate = criteriaBuilder.isNotNull(path);
                            }
                            predicates.add(predicate);
                        default:
                            break;
                    }
                }
                //todo decide for this

                /////////////////////////////////////////////////////////////////////////////

//                criteriaQuery.select((Selection<BajListHeader>) bajListHeaderFrom);
//                ListJoin<BajListHeader, TechnicalCalculationDetail> detailList = bajListHeaderFrom.joinList(BajListHeader_.technicalCalculationDetail);
//                details.on(criteriaBuilder.equal(details.get(TechnicalCalculationDetail_.tecSubjectType), "2"));
//                Join<BajListHeader, TechnicalCalculationDetail> join = bajListHeaderFrom.join(BajListHeader_.technicalCalculationDetail);
//                Predicate typePredicate = criteriaBuilder.equal(technicalCalculationDetail.get("tecSubjectType"), "2");
//                bajListHeaderFrom.fetch("technicalCalculationDetail");
//                predicates.add(criteriaBuilder.equal(join.get(TechnicalCalculationDetail_.tecSubjectType), "2"));
                /////////////////////////////////////////////////////////////////////////////
//                bajListHeaderFrom.fetch(BajListHeader_.technicalCalculationDetail, JoinType.LEFT);
//                Predicate predicate1 = criteriaBuilder.equal(bajListHeaderFrom.get(BajListHeader_.technicalCalculationDetail).get(TechnicalCalculationDetail_.tecSubjectType), "2");
//                Expression<String> exp = criteriaBuilder.substring(bajListHeaderFrom.get(BajListHeader_.technicalCalculationDetail).get(TechnicalCalculationDetail_.workshopIdentity), 4, 3);
//                Predicate predicate2 = criteriaBuilder.equal(exp, "686");
//                predicates.add(criteriaBuilder.or(predicate1, predicate2));
                ////////////////////////////////////////////////////////////////////////////
//                Root<TechnicalCalculationDetail> detailRoot = criteriaQuery.from(TechnicalCalculationDetail.class);
//                Predicate predicate1 = criteriaBuilder.equal(detailRoot.get(TechnicalCalculationDetail_.tecSubjectType), "2");
//                Expression<String> exp = criteriaBuilder.substring(detailRoot.get(TechnicalCalculationDetail_.workshopIdentity), 4, 3);
//                Predicate predicate2 = criteriaBuilder.equal(exp, "686");
//                predicates.add(criteriaBuilder.or(predicate1,predicate2));
                /////////////////////////////poursantaj/////////////////////////////////////
//                String field1 = "technicalCalculationDetail.tecSubjectType";
//                String[] f1 = field1.split("\\.");
//                javax.persistence.criteria.Path path1 = bajListHeaderFrom.get(f1[0]);
//                for (int j = 1; j < f1.length; j++) {
//                    path1 = path1.get(f1[j]);
//                }
//                Predicate p1 = criteriaBuilder.equal(path1, "2");
//                predicates.add(p1);
                /////////////////////////////////employees//////////////////////////////
//                String field2 = "workshop.id";
//                String[] f2 = field2.split("\\.");
//                javax.persistence.criteria.Path path2 = bajListHeaderFrom.get(f2[0]);
//                for (int j = 1; j < f2.length; j++) {
//                    path2 = path2.get(f2[j]);
//                }
//                Expression<Long> expression = criteriaBuilder.substring(path2, 4, 3);
//                Predicate p2 = criteriaBuilder.equal(expression, "686");
//
//                String field22 = "technicalCalculationDetail.workshopIdentity";
//                String[] f22 = field22.split("\\.");
//                javax.persistence.criteria.Path path22 = bajListHeaderFrom.get(f22[0]);
//                for (int j = 1; j < f22.length; j++) {
//                    path22 = path22.get(f22[j]);
//                }
//                Expression<Long> expression2 = criteriaBuilder.substring(path22, 4, 3);
//                Predicate p22 = criteriaBuilder.equal(expression2, "686");
//                predicates.add(criteriaBuilder.or(p1, p22));
                //////////////////////////////////notCanceled//////////////////////////
                String field3 = "debitStatus.statusCode";
                String[] f3 = field3.split("\\.");
                Path<String> path3 = bajListHeaderFrom.get(f3[0]);
                for (int j = 1; j < f3.length; j++) {
                    path3 = path3.get(f3[j]);
                }
                Predicate p3 = criteriaBuilder.notEqual(path3, "33");
                predicates.add(p3);

//                Predicate detailsPredicate = criteriaBuilder.equal(detailsJoinImpl.get(TechnicalCalculationDetail_.tecSubjectType),"2");
//                predicates.add(detailsPredicate);
//                Join<BajListHeader, TechnicalCalculationDetail> join = bajListHeaderFrom.join("technicalCalculationDetail", JoinType.LEFT);
//                predicates.add(criteriaBuilder.equal(join.get("tecSubjectType"), "2"));
                criteriaQuery.where(predicates.toArray(new Predicate[]{}));
            }

//            Join<BajListHeader,TechnicalCalculationDetail> details = bajListHeaderFrom.join(BajListHeader_.technicalCalculationDetail);
//            criteriaQuery.multiselect(bajListHeaderFrom,criteriaBuilder.count(bajListHeaderFrom.get(BajListHeader_.technicalCalculationDetail)));
//            criteriaQuery.groupBy(bajListHeaderFrom);
//            criteriaQuery.select(join).groupBy(join.get("bajListHeaderPK")).having(criteriaBuilder.equal(join.get("tecSubjectType"), "2"));
            criteriaQuery.select(bajListHeaderFrom).distinct(true);
            return criteriaQuery;

        } catch (Exception e) {
            return null;
        }
    }

    public Map<String, Object> exportVouchers(List<PoursantajView> bajListHeaders, SecurityContext securityContext) {
        String uniqueID = UUID.randomUUID().toString();
        Map<String, Object> result = new HashMap<>();
        long price = 0L;
        for (PoursantajView bajListHeader : bajListHeaders) {
            price += bajListHeader.getPrice() != null ? Long.parseLong(bajListHeader.getPrice()) : 0L;
            procedure.query("{call pck_compare.prcextphvouch(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            procedure.setInParameter(1, bajListHeader.getYear() + bajListHeader.getMonth());
            procedure.setInParameter(2, bajListHeader.getDocTypeCode());
            procedure.setInParameter(3, bajListHeader.getBajListHeaderPK().getDebitNumber());
            procedure.setInParameter(4, bajListHeader.getBajListHeaderPK().getClaimSequence());
            procedure.setInParameter(5, bajListHeader.getWorkshopId());
            procedure.setInParameter(6, bajListHeader.getPrice());
            procedure.setInParameter(7, Long.parseLong(bajListHeader.getPercent1()));
            procedure.setInParameter(8, Long.parseLong(bajListHeader.getPercent7()));
            procedure.setInParameter(9, Long.parseLong(bajListHeader.getPercent08()));
            procedure.setInParameter(10, Long.parseLong(bajListHeader.getPercent5()));
            procedure.setInParameter(11, Long.parseLong(bajListHeader.getPercent06()));
            procedure.setInParameter(12, Long.parseLong(bajListHeader.getPercent4()));
            if (bajListHeader.equals(bajListHeaders.get(0))) {
                procedure.setInParameter(13, "1");
            } else {
                procedure.setInParameter(13, "2");
            }
            procedure.setInParameter(14, uniqueID);
            try {
                procedure.execute();
            } catch (Exception e) {
                logger.error("خطا در اجرای پکیج صدور سند برای پورسانتاژ و همکاران : " + e);
                result.put("status", Response.Status.INTERNAL_SERVER_ERROR);
                result.put("message", messageBundle.getProperty("incomeBank.EXPORT_POURSANTAJ_FAILED"));
                return result;
            }
        }
        try {
            procedure.query("{call pck_compare.prcextphvouch(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            procedure.setInParameter(1, bajListHeaders.get(0).getYear() + bajListHeaders.get(0).getMonth());
            procedure.setInParameter(2, bajListHeaders.get(0).getDocTypeCode());
            procedure.setInParameter(3, "");
            procedure.setInParameter(4, "");
            procedure.setInParameter(5, "");
            procedure.setInParameter(6, price);
            procedure.setInParameter(7, 0);
            procedure.setInParameter(8, 0);
            procedure.setInParameter(9, 0);
            procedure.setInParameter(10, 0);
            procedure.setInParameter(11, 0);
            procedure.setInParameter(12, 0);
            procedure.setInParameter(13, "3");
            procedure.setInParameter(14, uniqueID);
            procedure.execute();
            TypedQuery<Voucher> query = em.createNamedQuery("getCurrentVouchers", Voucher.class);
            query.setParameter("vouchersGroupId", uniqueID);
            List<Voucher> vouchers = query.getResultList();
            Long sumDebit = 0L;
            Long sumCredit = 0L;
            for (Voucher voucher : vouchers) {
                sumDebit += voucher.getDebit();
                sumCredit += voucher.getCredit();
            }
            result.put("status", Response.Status.OK);
            result.put("message", messageBundle.getProperty("incomeBank.EXPORT_VOUCHER_SUCCESSFULLY"));
            result.put("list", vouchers);
            result.put("total", vouchers.size());
            result.put("sumDebit", sumDebit);
            result.put("sumCredit", sumCredit);

        } catch (SQLException ex) {
            logger.error("خطا در اجرای پکیج صدور سند برای پورسانتاژ و همکاران : " + ex);
            result.put("status", Response.Status.INTERNAL_SERVER_ERROR);
            result.put("message", messageBundle.getProperty("incomeBank.EXPORT_VOUCHER_FAILED"));
        }
        return result;
    }

    public Map<String, Object> getCurrentVouchers(String uuid, SecurityContext securityContext) {
        Map<String, Object> pageData = new HashMap<>();
        TypedQuery<Voucher> query = em.createNamedQuery("getCurrentVouchers", Voucher.class);
        query.setParameter("vouchersGroupId", uuid);
        List<Voucher> vouchers = query.getResultList();
        pageData.put("list", vouchers);
        pageData.put("total", vouchers.size());
        return pageData;
    }

    public Map<String, Object> revertVouchers(List<PoursantajView> bajListHeaders, SecurityContext securityContext) {
        Map<String, Object> result = new HashMap<>();
        try {
            for (PoursantajView c : bajListHeaders) {
                CoWorkersPoursantaj cp = em.find(CoWorkersPoursantaj.class, c.getBajListHeaderPK());
                em.remove(cp);
            }
            result.put("status", Response.Status.OK);
            result.put("message", messageBundle.getProperty("incomeBank.REVERT_VOUCHER_SUCCESSFULLY"));
        } catch (Exception ex) {
            logger.error("revert vouchers error : " + ex);
            result.put("status", Response.Status.INTERNAL_SERVER_ERROR);
            result.put("message", messageBundle.getProperty("incomeBank.REVERT_VOUCHER_FAILED"));
        }
        return result;
    }
}
