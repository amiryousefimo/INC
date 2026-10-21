package ir.tamin.incomeBank.model.paymentWorkers.branch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentWorkersBranchSummary implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal sumSumAmount;
    private BigDecimal sumCount;
    private BigDecimal sumBimeh7sakht;
    private BigDecimal sumBimeh20sakht;
    private BigDecimal sumJarimehSakht;
    private BigDecimal sumSumAmtGov;
}
