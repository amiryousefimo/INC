package ir.tamin.incomeBank.model.contradictionReport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "VWMOGHLIST4")
public class ContradictionReport4 {

    @Id
    @Column(name = "MOGH_LIST_SEQ")
    private String listSeq;

    @Column(name = "YEAR")
    private String year;

    @Column(name = "MON")
    private String month;

    @Column(name = "EDKOL_CODE")
    private String edareCode;

    @Column(name = "EDKOL_NAME")
    private String edareName;

    @Column(name = "BRCH_CODE")
    private String branchCode;

    @Column(name = "BRCH_NAME")
    private String branchName;

    @Column(name = "ORD_ORDNO")
    private String orderNo;

    @Column(name = "SORAT_DATE")
    private String billDate;

    @Column(name = "SORAT_AMT")
    private BigDecimal billAmount;

    @Column(name = "WSH_ID")
    private String workshopId;

    @Column(name = "WSH_NAME")
    private String workshopName;

    @Column(name = "SORAT_BANK")
    private String billBank;

    @Column(name = "BANK_NAME")
    private String bankName;

    @Column(name = "STAT_MOGH")
    private Character statCode;

    @Column(name = "STAT_DESC")
    private String statDesc;

    public ContradictionReport4() {
    }

    public ContradictionReport4(String listSeq, String year, String month, String edareCode, String edareName, String branchCode, String branchName, String orderNo, String billDate, BigDecimal billAmount, String workshopId, String workshopName, String billBank, String bankName, Character statCode, String statDesc) {
        this.listSeq = listSeq;
        this.year = year;
        this.month = month;
        this.edareCode = edareCode;
        this.edareName = edareName;
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.orderNo = orderNo;
        this.billDate = billDate;
        this.billAmount = billAmount;
        this.workshopId = workshopId;
        this.workshopName = workshopName;
        this.billBank = billBank;
        this.bankName = bankName;
        this.statCode = statCode;
        this.statDesc = statDesc;
    }

    public String getListSeq() {
        return listSeq;
    }

    public void setListSeq(String listSeq) {
        this.listSeq = listSeq;
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

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    public String getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(String workshopId) {
        this.workshopId = workshopId;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public String getBillBank() {
        return billBank;
    }

    public void setBillBank(String billBank) {
        this.billBank = billBank;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Character getStatCode() {
        return statCode;
    }

    public void setStatCode(Character statCode) {
        this.statCode = statCode;
    }

    public String getStatDesc() {
        return statDesc;
    }

    public void setStatDesc(String statDesc) {
        this.statDesc = statDesc;
    }

    @Override
    public String toString() {
        return "ContradictionReport4{" +
                "listSeq='" + listSeq + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", edareCode='" + edareCode + '\'' +
                ", edareName='" + edareName + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", branchName='" + branchName + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", billDate='" + billDate + '\'' +
                ", billAmount=" + billAmount +
                ", workshopId='" + workshopId + '\'' +
                ", workshopName='" + workshopName + '\'' +
                ", billBank='" + billBank + '\'' +
                ", bankName='" + bankName + '\'' +
                ", statCode=" + statCode +
                ", statDesc='" + statDesc + '\'' +
                '}';
    }
}
