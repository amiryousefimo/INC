package ir.tamin.incomeBank.model.saba.efDateBranch;

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
public class SabaEfDateBranchesSummary {
BigDecimal sumSoratPrice;
BigDecimal sumCountPrice;
}
