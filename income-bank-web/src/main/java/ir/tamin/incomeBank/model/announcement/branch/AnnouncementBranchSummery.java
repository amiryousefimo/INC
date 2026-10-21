package ir.tamin.incomeBank.model.announcement.branch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementBranchSummery {
    private BigDecimal sumEmployees;
    private BigDecimal sumEmployeesCount;
    private BigDecimal sumPeyman;
    private BigDecimal sumPeymanCount;
    private BigDecimal sumSumAmount;
    private BigDecimal sumCount;
}
