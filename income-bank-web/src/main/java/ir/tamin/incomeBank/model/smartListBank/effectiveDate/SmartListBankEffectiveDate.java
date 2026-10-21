package ir.tamin.incomeBank.model.smartListBank.effectiveDate;

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
@Table(name = "vwsorat_intllist_daily")
public class SmartListBankEffectiveDate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "rownum")
    private String rowNum;
    @Column(name = "sorat_date")
    private String soratDate;
    @Column(name = "price_date")
    private BigDecimal priceDate;
    @Column(name = "cnt_date")
    private BigDecimal cntDate;
    @Column(name = "price_efdate")
    private BigDecimal priceEfDate;
    @Column(name = "cnt_efdate")
    private BigDecimal cntEfDate;

    public String getRowNum() {
        return rowNum;
    }

    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }

    public String getSoratDate() {
        return soratDate;
    }

    public void setSoratDate(String soratDate) {
        this.soratDate = soratDate;
    }

    public BigDecimal getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(BigDecimal priceDate) {
        this.priceDate = priceDate;
    }

    public BigDecimal getCntDate() {
        return cntDate;
    }

    public void setCntDate(BigDecimal cntDate) {
        this.cntDate = cntDate;
    }

    public BigDecimal getPriceEfDate() {
        return priceEfDate;
    }

    public void setPriceEfDate(BigDecimal priceEfDate) {
        this.priceEfDate = priceEfDate;
    }

    public BigDecimal getCntEfDate() {
        return cntEfDate;
    }

    public void setCntEfDate(BigDecimal cntEfDate) {
        this.cntEfDate = cntEfDate;
    }
}
