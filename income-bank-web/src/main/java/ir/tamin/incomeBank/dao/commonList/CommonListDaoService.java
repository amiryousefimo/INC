package ir.tamin.incomeBank.dao.commonList;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.commonList.CommonLIst;
import ir.tamin.incomeBank.model.commonList.CommonListSumAmount;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CommonListDaoService extends BaseDAOService<CommonLIst> {

    @Inject
    private EntityManager em;


    @Override
    public Class<CommonLIst> getClazz() {
        return CommonLIst.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public CommonListSumAmount getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<CommonListSumAmount> criteriaQuery = criteriaBuilder.createQuery(CommonListSumAmount.class);

        Root<CommonLIst> commonListRoot = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, commonListRoot, filter, null);

        criteriaQuery.select(criteriaBuilder.construct(
                CommonListSumAmount.class,
                criteriaBuilder.sum(commonListRoot.get("soratPrice1")),
                criteriaBuilder.sum(commonListRoot.get("soratPrice2")),
                criteriaBuilder.count(commonListRoot.get("soratOrdPay"))));

        TypedQuery typedQuery = em.createQuery(criteriaQuery);
        CommonListSumAmount summary = (CommonListSumAmount) typedQuery.getSingleResult();
        return summary;
    }
}
