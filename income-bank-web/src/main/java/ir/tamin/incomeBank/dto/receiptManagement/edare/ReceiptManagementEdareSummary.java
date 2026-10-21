package ir.tamin.incomeBank.dto.receiptManagement.edare;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


/**
 * @author a_jamali
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptManagementEdareSummary {
    private BigDecimal sabaPrc;
    private BigDecimal intljPrc;
    private BigDecimal karkonanPrc;
    private BigDecimal naghdiPrc;
    private BigDecimal oraghPrc;
    private BigDecimal prc3070;
    private BigDecimal sumPrc;
}
