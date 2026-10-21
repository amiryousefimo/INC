package ir.tamin.incomeBank.model.reportingOfficeBasedPayment.branch;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity()
@Table(name = "VWDRMD_PAYKIND_BRCH")
public class ReportingOfficeBasedPaymentBranch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "rownum")
    private String rowNum;

    @Column(name = "BRCH_CODE")
    private String brchCode;

    @Column(name = "BRCH_NAME")
    private String brchName;

    @Column(name = "EDKOL_CODE")
    private String edareCode;

    @Column(name = "EDKOL_NAME")
    private String edareName;


    @Column(name = "ORD_DATE")
    private String ordDate;


    @Column(name = "PAY_KIND")
    private String payKind;

    @Column(name = "PAY_KINDDSC")
    private String payKindDSC;


    @Column(name = "SUM_AMT")
    private BigDecimal sumAmount;

    @Column(name = "CNT_ORDNO")
    private BigDecimal countOrderNumber;

    public String getRowNum() {
        return rowNum;
    }

    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }

    public String getBrchCode() {
        return brchCode;
    }

    public void setBrchCode(String brchCode) {
        this.brchCode = brchCode;
    }

    public String getBrchName() {
        return brchName;
    }

    public void setBrchName(String brchName) {
        this.brchName = brchName;
    }

    public String getEdareCode() {
        return edareCode;
    }

    public void setEdareCode(String edareCode) {
        this.edareCode = edareCode;
    }

    public String getEdareName() {
        return edareName;
    }

    public void setEdareName(String edareName) {
        this.edareName = edareName;
    }

    public String getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(String ordDate) {
        this.ordDate = ordDate;
    }

    public String getPayKind() {
        return payKind;
    }

    public void setPayKind(String payKind) {
        this.payKind = payKind;
    }

    public String getPayKindDSC() {
        return payKindDSC;
    }

    public void setPayKindDSC(String payKindDSC) {
        this.payKindDSC = payKindDSC;
    }

    public BigDecimal getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(BigDecimal sumAmount) {
        this.sumAmount = sumAmount;
    }

    public BigDecimal getCountOrderNumber() {
        return countOrderNumber;
    }

    public void setCountOrderNumber(BigDecimal countOrderNumber) {
        this.countOrderNumber = countOrderNumber;
    }
}
