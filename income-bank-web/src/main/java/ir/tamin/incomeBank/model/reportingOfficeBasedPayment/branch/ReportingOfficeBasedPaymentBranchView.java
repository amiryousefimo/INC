package ir.tamin.incomeBank.model.reportingOfficeBasedPayment.branch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportingOfficeBasedPaymentBranchView implements Serializable {
    private static final long serialVersionUID = 1L;


    private String brchCode;

    private String brchName;

    private String edareCode;

    private String edareName;


    private String ordDate1;
    private String ordDate2;

    private String payKind;
    private String payKindDSC;

    private BigDecimal sumAmount;

    private BigDecimal countOrderNumber;
}
