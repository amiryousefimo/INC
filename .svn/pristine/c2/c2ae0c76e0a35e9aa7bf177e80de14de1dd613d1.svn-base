package ir.tamin.incomeBank.model.financialDoc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "vwmoghbank_sanad")
public class ConflictDoc implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ROWNUM")
    private String rowId;

    @Column(name = "SORAT_YY")
    private String year;

    @Column(name = "SORAT_MM")
    private String month;

    @Column(name = "BANK_NAME")
    private String bankName;

    @Column(name = "BANK_CODE")
    private String bankCode;

    @Column(name = "SORAT_AMT")
    private BigDecimal billAmount;

    @Column(name = "ACC_CODE")
    private String accountCode;

    @Column(name = "ACC_AMT")
    private BigDecimal accountAmount;

    @Column(name = "DIFF_AMT")
    private BigDecimal diffAmount;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    public BigDecimal getDiffAmount() {
        return diffAmount;
    }

    public void setDiffAmount(BigDecimal diffAmount) {
        this.diffAmount = diffAmount;
    }

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }
}
