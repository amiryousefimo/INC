package ir.tamin.incomeBank.model.financialDoc.newFinancialDoc3070;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
@Entity
@Table(name = "vwdrmd_acc3070_efect")
public class FinancialDocEfect3070 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ROWNUM")
    private String rowId;

    @Column(name = "DRMD_YY")
    private String daramadYear;

    @Column(name = "DRMD_MM")
    private String daramadMonth;

    @Column(name = "ACC_CODE")
    private String accountCode;

    @Column(name = "ACC_DESC")
    private String accountDesc;

    @Column(name = "BED_AMT")
    private BigDecimal debtAmount;

    @Column(name = "BES_AMT")
    private BigDecimal creditAmount;

    @Column(name = "crt_date")
    private String createDate;

    @Column(name = "FLG_DOC")
    private String flagDocument;

    @Column(name = "BRCH_CODE")
    private String branchCode;
    @Column(name = "doc_row")
    private String documentRow;

    @Column(name = "DOC_TYPE")
    private String docType;

    @Column(name = "VALUE_SHENASEH")
    private String valueShenaseh;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getDaramadYear() {
        return daramadYear;
    }

    public void setDaramadYear(String daramadYear) {
        this.daramadYear = daramadYear;
    }

    public String getDaramadMonth() {
        return daramadMonth;
    }

    public void setDaramadMonth(String daramadMonth) {
        this.daramadMonth = daramadMonth;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountDesc() {
        return accountDesc;
    }

    public void setAccountDesc(String accountDesc) {
        this.accountDesc = accountDesc;
    }

    public BigDecimal getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(BigDecimal debtAmount) {
        this.debtAmount = debtAmount;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getFlagDocument() {
        return flagDocument;
    }

    public void setFlagDocument(String flagDocument) {
        this.flagDocument = flagDocument;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getDocumentRow() {
        return documentRow;
    }

    public void setDocumentRow(String documentRow) {
        this.documentRow = documentRow;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getValueShenaseh() {
        return valueShenaseh;
    }

    public void setValueShenaseh(String valueShenaseh) {
        this.valueShenaseh = valueShenaseh;
    }

    @Override
    public String toString() {
        return "FinancialDoc{" +
                "rowId='" + rowId + '\'' +
                ", accountCode='" + accountCode + '\'' +
                ", accountDesc='" + accountDesc + '\'' +
                ", debtAmount=" + debtAmount +
                ", creditAmount=" + creditAmount +
                ", daramadYear='" + daramadYear + '\'' +
                ", daramadMonth='" + daramadMonth + '\'' +
                ", createDate='" + createDate + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", flagDocument='" + flagDocument + '\'' +
                ", documentRow='" + documentRow + '\'' +
                ", docType ='" + docType + '\'' +
                '}';
    }
}
