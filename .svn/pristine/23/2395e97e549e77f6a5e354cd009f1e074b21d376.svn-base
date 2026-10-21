package ir.tamin.incomeBank.model.workshopDebitReport.branches;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author  A_Jamali
 */

@Entity
@Table(name = "VWDRMD_JARI_WSH1")
public class WorkshopDebitReportBranches implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ROWNUM")
    private String rowId;
    @Column(name = "EDKOL_CODE")
    private String edareCode;

    @Column(name = "BRCH_CODE")
    private String brchCode;

    @Column(name = "EDKOL_NAME")
    private String edareName;

    @Column(name = "BRCH_NAME")
    private String brchName;

    @Column(name = "WSH_CODE")
    private String wshCode;

    @Column(name = "WSH_NAME")
    private String wshName;

    @Column(name = "ORD_DATE1")
    private String orderDate1;

    @Column(name = "ORD_ORDNO")
    private String ordOrdNum;

    @Column(name = "ORD_AMT1")
    private BigDecimal amountBed;

    @Column(name = "ORD_AMT2")
    private BigDecimal amountBes;

    @Column(name = "ORD_DATE2")
    private String orderDate2;



    /*****************************************/
    @Transient
    private String fullEdareName;


    public String getFullEdareName() {
        return getEdareName()+"-"+getEdareCode();
    }

    /*****************************************/


    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEdareCode() {
        return edareCode;
    }

    public void setEdareCode(String edareCode) {
        this.edareCode = edareCode;
    }

    public String getBrchCode() {
        return brchCode;
    }

    public void setBrchCode(String brchCode) {
        this.brchCode = brchCode;
    }

    public String getEdareName() {
        return edareName;
    }

    public void setEdareName(String edareName) {
        this.edareName = edareName;
    }

    public String getBrchName() {
        return brchName;
    }

    public void setBrchName(String brchName) {
        this.brchName = brchName;
    }

    public String getWshCode() {
        return wshCode;
    }

    public void setWshCode(String wshCode) {
        this.wshCode = wshCode;
    }

    public String getWshName() {
        return wshName;
    }

    public void setWshName(String wshName) {
        this.wshName = wshName;
    }

    public String getOrderDate1() {
        return orderDate1;
    }

    public void setOrderDate1(String orderDate1) {
        this.orderDate1 = orderDate1;
    }

    public String getOrdOrdNum() {
        return ordOrdNum;
    }

    public void setOrdOrdNum(String ordOrdNum) {
        this.ordOrdNum = ordOrdNum;
    }

    public BigDecimal getAmountBed() {
        return amountBed;
    }

    public void setAmountBed(BigDecimal amountBed) {
        this.amountBed = amountBed;
    }

    public BigDecimal getAmountBes() {
        return amountBes;
    }

    public void setAmountBes(BigDecimal amountBes) {
        this.amountBes = amountBes;
    }

    public String getOrderDate2() {
        return orderDate2;
    }

    public void setOrderDate2(String orderDate2) {
        this.orderDate2 = orderDate2;
    }

    @Override
    public String toString() {
        return "WorkshopDebitReportBranches{" +
                "edareCode='" + edareCode + '\'' +
                ", brchCode='" + brchCode + '\'' +
                ", edareName='" + edareName + '\'' +
                ", brchName='" + brchName + '\'' +
                ", wshCode='" + wshCode + '\'' +
                ", wshName='" + wshName + '\'' +
                ", orderDate1='" + orderDate1 + '\'' +
                ", ordOrdNum='" + ordOrdNum + '\'' +
                ", amountBed=" + amountBed +
                ", amountBes=" + amountBes +
                ", orderDate2='" + orderDate2 + '\'' +
                '}';
    }
}
