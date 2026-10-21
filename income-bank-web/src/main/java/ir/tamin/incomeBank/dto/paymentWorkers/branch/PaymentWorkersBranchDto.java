package ir.tamin.incomeBank.dto.paymentWorkers.branch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author m_goharriz
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentWorkersBranchDto {
    private String rowNum;
    private String brchCode;
    private String brchName;
    private String edareCode;
    private String edareName;

    private String orderDate;
    private String orderDate2;

    private BigDecimal sumAmount;
    private BigDecimal countOrdNumber;

    private BigDecimal bimeh7sakht;
    private BigDecimal bimeh20sakht;

    private BigDecimal jarimehSakht;
    private BigDecimal sumAmtGov;

}
