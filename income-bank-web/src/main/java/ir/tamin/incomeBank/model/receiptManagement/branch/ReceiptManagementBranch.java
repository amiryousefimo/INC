package ir.tamin.incomeBank.model.receiptManagement.branch;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author a_jamali
 */
@Entity
@Table(name = "vwsorat_BRANCH")
@Getter
@Setter
@NoArgsConstructor
public class ReceiptManagementBranch implements Serializable {
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
    @Column(name = "sorat_date")
    private String soratDate;
    @Column(name = "SABA_PRC")
    private BigDecimal sabaPrc;
    @Column(name = "INTLJ_PRC")
    private BigDecimal intljPrc;
    @Column(name = "KARKON_PRC")
    private BigDecimal karkonanPrc;
    @Column(name = "NAGHDI_PRC")
    private BigDecimal naghdiPrc;
    @Column(name = "ORAGH_PRC")
    private BigDecimal oraghPrc;
    @Column(name = "PRC_3070")
    private BigDecimal prc3070;
    @Column(name = "SUM_PRC")
    private BigDecimal sumPrc;
}
