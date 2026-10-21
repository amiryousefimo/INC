package ir.tamin.incomeBank.model.announcement.setad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementEdareKolSummery implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal sumEmployees;
    private BigDecimal sumEmployeesCount;
    private BigDecimal sumPeyman;
    private BigDecimal sumPeymanCount;
    private BigDecimal sumSumAmount;
    private BigDecimal sumCount;
}
