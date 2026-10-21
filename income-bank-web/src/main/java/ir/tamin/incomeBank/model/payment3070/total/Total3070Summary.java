package ir.tamin.incomeBank.model.payment3070.total;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
/**
 * @author m_goharriz
 * Date : 1404.02.10
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Total3070Summary {

    private BigDecimal soratPrice1;// جمع واریز
    private BigDecimal soratPrice2;//جمع برداشت

}
