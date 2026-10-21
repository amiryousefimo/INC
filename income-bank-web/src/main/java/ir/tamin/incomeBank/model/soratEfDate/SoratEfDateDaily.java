package ir.tamin.incomeBank.model.soratEfDate;


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
@Table(name = "vwsorat_efdate_daily")
public class SoratEfDateDaily implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "rownum")
    private String rowNum;
    @Column(name = "SORAT_DATE")
    private String soratDate;

    @Column(name = "SORAT_KIND")
    private String soratKind;
    @Column(name = "SORAT_DESC")
    private String soratDesc;

    @Column(name = "SORAT_PRICE")
    private BigDecimal soratPrice;
    @Column(name = "CNT_PRICE")
    private BigDecimal countPrice;

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

    public String getSoratKind() {
        return soratKind;
    }

    public void setSoratKind(String soratKind) {
        this.soratKind = soratKind;
    }

    public String getSoratDesc() {
        return soratDesc;
    }

    public void setSoratDesc(String soratDesc) {
        this.soratDesc = soratDesc;
    }

    public BigDecimal getSoratPrice() {
        return soratPrice;
    }

    public void setSoratPrice(BigDecimal soratPrice) {
        this.soratPrice = soratPrice;
    }

    public BigDecimal getCountPrice() {
        return countPrice;
    }

    public void setCountPrice(BigDecimal countPrice) {
        this.countPrice = countPrice;
    }
}
