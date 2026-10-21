package ir.tamin.incomeBank.dao.contradictionReport;

import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.incomeBank.dao.BaseDAOService;
import ir.tamin.incomeBank.model.contradictionReport.ContradictionReport1;
import ir.tamin.incomeBank.model.contradictionReport.ContradictionReport4;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class ContradictionReport4DAOService extends BaseDAOService<ContradictionReport4> {

    @Inject
    private EntityManager em;

    @Override
    public Class<ContradictionReport4> getClazz() {
        return ContradictionReport4.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
