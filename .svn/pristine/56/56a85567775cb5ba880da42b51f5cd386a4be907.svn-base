package ir.tamin.incomeBank.dao.announcement.newspaper;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.dto.announcement.newspaper.AnnouncementNewspaperSummary;
import ir.tamin.incomeBank.model.announcement.newspaper.AnnouncementNewspaper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class AnnouncementNewspaperDao extends BaseDAOService<AnnouncementNewspaper> {


    @Inject
    private EntityManager em;


    public AnnouncementNewspaperSummary getSummaryPrice(FilterWrapper filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<AnnouncementNewspaper> root = criteriaQuery.from(getClazz());
        criteriaQuery = getFilteredQuery(criteriaQuery, root, filter, null);
        criteriaQuery.select(criteriaBuilder.construct(AnnouncementNewspaperSummary.class,
                criteriaBuilder.sum(root.<BigDecimal>get("letterPrice1"))));
        TypedQuery createQuery = em.createQuery(criteriaQuery);
        AnnouncementNewspaperSummary summery = (AnnouncementNewspaperSummary) createQuery.getSingleResult();
        return summery;
    }

    @Override
    public Class<AnnouncementNewspaper> getClazz() {
        return AnnouncementNewspaper.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
