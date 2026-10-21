package ir.tamin.incomeBank.model.workshopDebitReport.workshop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author A_Jamali
 */
@Entity
@Table(name = "vwdrmd_wshacc")
public class WorkshopDebitReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ROWNUM")
    private String rowId;

    @Column(name = "CUST_ID")
    private String workshopId;

    @Column(name = "ORD_NO")
    private String orderNumber;

    @Column(name = "ORD_DATE")
    private String orderDate;

    @Column(name = "ORD_AMT")
    private BigDecimal orderAmount;

    @Column(name = "BANK_DATE")
    private String bankDate;

    @Column(name = "BANK_AMT")
    private BigDecimal bankAmount;

    @Column(name = "STAT")
    private String state;

    @Column(name = "BRCH_CODE")
    private String brchCode;

    @Column(name = "EDKOL_CODE")
    private String edareCode;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(String workshopId) {
        this.workshopId = workshopId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getBankDate() {
        return bankDate;
    }

    public void setBankDate(String bankDate) {
        this.bankDate = bankDate;
    }

    public BigDecimal getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(BigDecimal bankAmount) {
        this.bankAmount = bankAmount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBrchCode() {
        return brchCode;
    }

    public void setBrchCode(String brchCode) {
        this.brchCode = brchCode;
    }

    public String getEdareCode() {
        return edareCode;
    }

    public void setEdareCode(String edareCode) {
        this.edareCode = edareCode;
    }

    @Override
    public String toString() {
        return "workshopDebitSummery{" +
                "rowId='" + rowId + '\'' +
                ", workshopId='" + workshopId + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderAmount=" + orderAmount +
                ", bankDate='" + bankDate + '\'' +
                ", bankAmount=" + bankAmount +
                ", state='" + state + '\'' +
                '}';
    }
}
