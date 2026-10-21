package ir.tamin.incomeBank.model.payment3070.account503branch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author A_Jamali
 */

@Entity
@Table(name = "vwdrmd_gl3070_dailyBRCH")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account503Branch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "rownum")
    private String rowNum;
    @Column(name = "EDKOL_CODE")
    private String edareCode;

    @Column(name = "EDKOL_NAME")
    private String edareName;

    @Column(name = "BRCH_CODE")
    private String brchCode;
    @Column(name = "BRCH_NAME")
    private String brchName;

    @Column(name = "ord_date")
    private String ordDate;

    @Transient
    private String ordDate1;

    @Column(name = "sum_amt")
    private BigDecimal sumAmount;

    @Column(name = "MASH_AMT")
    private BigDecimal mashAmount;

    @Column(name = "EKHT_AMT")
    private BigDecimal ekhtAmount;

    @Column(name = "sarbazi_amt")
    private BigDecimal sarbaziAmt;

    @Column(name = "SAKHTEMAN_AMT")
    private BigDecimal sakhtemanAmt;

    @Column(name = "KASR_MAH_AMT")
    private BigDecimal kasrMahAmt;

    @Column(name = "CNT_AMT")
    private BigDecimal countAmt;

    @Column(name = "DOLATI_AMT")
    private BigDecimal dolatAmt;



}
