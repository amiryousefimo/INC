package ir.tamin.incomeBank.model.financialDoc;

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
public class DifferenceBetweenBankOfficeSumAmount {

    private BigDecimal soratPrice1;//مبلغ بانک
    private BigDecimal orderAmount;//مبلغ دفتر
    private BigDecimal diffrentAmount;//اختلاف مبلغ ها

}
