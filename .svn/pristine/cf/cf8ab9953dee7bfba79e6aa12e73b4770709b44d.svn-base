package ir.tamin.incomeBank.service.financialDoc;

import ir.tamin.framework.cdi.util.MessageBundle;
import ir.tamin.framework.cdi.util.WebProperties;
import ir.tamin.framework.core.util.Bundle;
import ir.tamin.framework.ws.rest.json.Filter;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.dao.financialDoc.ConflictDocDAOService;
import ir.tamin.incomeBank.model.financialDoc.ConflictDoc;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConflictDocService {

    @Inject
    private ConflictDocDAOService daoService;

    @Inject
    @MessageBundle
    Bundle messageBundle;

    @Inject
    @WebProperties
    Bundle webBundle;

    @Inject
    EntityManager entityManager;


    public Map<String, Object> getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws Exception {
        String branchCode = "9901";
        String year = null;
        String month = null;

        Object[] filterArray = filterWrapper.getFilters().toArray();
        for (int i = 0; i < filterArray.length; i++) {
            Filter filter = (Filter) filterArray[i];
            String field = filter.getProperty();
            Object value = filter.getValue();

            if (field.equals("year")) {
                year = value.toString();
            }
            if (field.equals("month")) {
                month = value.toString();
            }

        }
        Map<String, Object> map = new HashMap<>();
        List<Object[]> list;
        List<ConflictDoc> finalList = new ArrayList<>();
        list = getEntityManager().createNativeQuery("select * from the(select cast (Pck_Telproc_Core.GetMOGH_SANAD(?1,?2,?3) as DIFF_SANAD_TBL) from dual)")
                .setParameter(1, branchCode)
                .setParameter(2, year)
                .setParameter(3, month)
                .getResultList();

        for (Object[] obja : list) {

            ConflictDoc conflictDoc = new ConflictDoc();

            conflictDoc.setYear(obja[0] != null ? obja[0].toString() : "");
            conflictDoc.setMonth(obja[1] != null ? obja[1].toString() : "");
            conflictDoc.setBankName(obja[2] != null ? obja[2].toString() : "");
            conflictDoc.setBankCode(obja[3] != null ? obja[3].toString() : "");
            conflictDoc.setBillAmount(obja[4] != null ? new BigDecimal(obja[4].toString()) : BigDecimal.ZERO);
            conflictDoc.setAccountCode(obja[5] != null ? obja[5].toString() : "");
            conflictDoc.setAccountAmount(obja[6] != null ? new BigDecimal(obja[6].toString()) : BigDecimal.ZERO);
            conflictDoc.setDiffAmount(obja[7] != null ? new BigDecimal(obja[7].toString()) : BigDecimal.ZERO);

            finalList.add(conflictDoc);
        }
        map.put("list", finalList);
        map.put("total", finalList.size());
        return map;


    }


    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
