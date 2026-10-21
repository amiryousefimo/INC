package ir.tamin.incomeBank.model.smartListBank.effectiveDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author A_Jamali
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmartListBankEffectiveDateSummary {
    private BigDecimal sumPriceDate;
    private BigDecimal sumCntDate;
    private BigDecimal sumPriceEfDate;
    private BigDecimal sumCntEfDate;
}
