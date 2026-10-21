package ir.tamin.incomeBank.dto.paymentWorkers.edare;

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
public class PaymentWorkersEdareDto {
    private String rowNum;

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
