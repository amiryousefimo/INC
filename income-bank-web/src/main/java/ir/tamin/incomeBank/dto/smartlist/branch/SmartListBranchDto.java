package ir.tamin.incomeBank.dto.smartlist.branch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author a_jamali
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SmartListBranchDto {
    private String rowNum;
    private String edareCode;
    private String edareName;
    private String brchCode;
    private String brchName;
    private String orderDate;
    private String orderDate2;
    private BigDecimal sumAmount;
    private BigDecimal sumISu;
    private BigDecimal sumAmountGov;
    private BigDecimal sumIsuGov;
    private BigDecimal countOrdNumber;
    private BigDecimal hagheBimeh;
    private BigDecimal hagheBimehGov;
    private BigDecimal bikari;
    private BigDecimal jarimeh;
}
