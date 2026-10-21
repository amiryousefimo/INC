package ir.tamin.incomeBank.model.workshopDebitReport.country;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author A_Jamali
 */

@Entity
@Table(name = "VWDRMD_JARI_WSH3")
public class WorkshopDebitReportCountry implements Serializable {

    private static final long serialVersionUID = 25654545L;
    @Id
    @Column(name = "ROWNUM")
    private String rowId;

    @Column(name = "EDKOL_CODE")
    private String edareCode;

    @Column(name = "EDKOL_NAME")
    private String edareName;
    @Column(name = "ORD_AMT1")
    private BigDecimal amountBed;

    @Column(name = "ORD_AMT2")
    private BigDecimal amountBes;


    @Column(name = "REM_AMT")
    private BigDecimal remainAmount;



    @Column(name = "CNT_WSH")
    private BigDecimal countWSH;

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


    public String getEdareName() {
        return edareName;
    }

    public void setEdareName(String edareName) {
        this.edareName = edareName;
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

    public BigDecimal getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(BigDecimal remainAmount) {
        this.remainAmount = remainAmount;
    }

    public BigDecimal getCountWSH() {
        return countWSH;
    }

    public void setCountWSH(BigDecimal countWSH) {
        this.countWSH = countWSH;
    }
}
