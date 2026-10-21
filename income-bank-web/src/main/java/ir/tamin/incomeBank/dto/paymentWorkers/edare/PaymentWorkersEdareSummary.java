package ir.tamin.incomeBank.dto.paymentWorkers.edare;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentWorkersEdareSummary {
    private BigDecimal sumAmount;
    private BigDecimal countOrdNumber;

    private BigDecimal bimeh7sakht;
    private BigDecimal bimeh20sakht;

    private BigDecimal jarimehSakht;
    private BigDecimal sumAmtGov;
}
