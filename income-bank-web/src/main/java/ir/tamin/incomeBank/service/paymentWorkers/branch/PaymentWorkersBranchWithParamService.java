package ir.tamin.incomeBank.service.paymentWorkers.branch;

import ir.tamin.incomeBank.dao.paymentWorkers.branch.PaymentWorkersBranchDAOService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Map;

/**
 * @author m_goharriz
 */
@Stateless
public class PaymentWorkersBranchWithParamService {

    @Inject
    private PaymentWorkersBranchDAOService daoService;

    public Map<String, Object>getBranchByDateRange(String startDate , String endDate , String edareCode, String brchCode){
        return  daoService.getBranchByDateRange(startDate , endDate,edareCode,brchCode);
    }
}
