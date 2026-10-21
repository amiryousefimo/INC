package ir.tamin.incomeBank.service.paymentWorkers.edare;

import ir.tamin.incomeBank.dao.paymentWorkers.branch.PaymentWorkersBranchDAOService;
import ir.tamin.incomeBank.dao.paymentWorkers.edare.PaymentWorkersEdareDAOService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Map;

/**
 * @author m_goharriz
 */
@Stateless
public class PaymentWorkersEdareWithParamService {

    @Inject
    private PaymentWorkersEdareDAOService daoService;

    public Map<String, Object>getBranchByDateRange(String startDate , String endDate , String edareCode){
        return  daoService.getEdareByDateRange(startDate , endDate,edareCode);
    }
}
