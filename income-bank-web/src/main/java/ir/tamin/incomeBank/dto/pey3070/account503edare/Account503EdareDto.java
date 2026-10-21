package ir.tamin.incomeBank.dto.pey3070.account503edare;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author A_Jamali
 */


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account503EdareDto {

    private String rowNum;
    private String edareCode;
    private String edareName;
    private String ordDate;
    private String ordDate1;
    private BigDecimal sumAmount;
    private BigDecimal mashAmount;
    private BigDecimal ekhtAmount;
    private BigDecimal sarbaziAmt;
    private BigDecimal sakhtemanAmt;
    private BigDecimal kasrMahAmt;
    private BigDecimal countAmt;
    private BigDecimal dolatAmt;

}
