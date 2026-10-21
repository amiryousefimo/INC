package ir.tamin.incomeBank.dto.saba.details;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author A_Jamali
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SabaDetailsSummary {
    private BigDecimal soratPrice1;
    private BigDecimal soratPrice2;
}
