package ir.tamin.incomeBank.model.payment3070.effective;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Effective3070Summary {
    private BigDecimal priceDate;
    private BigDecimal countDate;
    private BigDecimal priceEfDate;
    private BigDecimal countEfDate;
    private BigDecimal soratPrice1;
    private BigDecimal soratPrice2;
}
