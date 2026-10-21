package ir.tamin.incomeBank.model.payment3070.account503;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author a_jamali
 */

@Entity
@Table(name = "vwdrmd_gl3070_det")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account503 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "rownum")
    private String rowNum;

    @Column(name = "EDKOL_CODE")
    private String edareCode;

    @Column(name = "EDKOL_NAME")
    private String edareName;

    @Column(name = "brch_code")
    private String brchCode;

    @Column(name = "BRCH_NAME")
    private String brchName;

    @Column(name = "ord_date")
    private String ordDate;//تاریخ موثر

    @Column(name = "CUST_ID")
    private String nationalCode;

    @Column(name = "CUST_NAME")
    private String nameOfTheInsured;

    @Column(name = "DEBITREASONDESC")
    private String debitReasonDesc;

    @Column(name = "ORD_SDATE")
    private String startDateOfPeriod;

    @Column(name = "ORD_EDATE")
    private String endDateOfPeriod;

    @Column(name = "ADD_DT")
    private String sendDateOfInfoToIncoming;

    @Column(name = "DEBITCRTREASONCODE")
    private String debitReasonCode;

    @Column(name = "GOV_FLG")
    private String governmentFlag;

    @Column(name = "SYSTEM_TYPE")
    private String systemType;

    @Column(name = "ORD_ORDNO")
    private String  ordOrderNumber;

    @Column(name = "ORD_AMT")
    private BigDecimal orderAmount;

    @Column(name = "GOVER_AMT")
    private BigDecimal governmentAmount;

    @Column(name = "SORAT_DATE")
    private String soratDate;//تاریخ وصول

    @Column(name = "doc_flg")
    private String documentStatus;

    //TODO-------------------
    @Transient
    private String documentStatusDesc;

}
