package ir.tamin.incomeBank.model.saba.efDateBranch;


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
@Table(name = "vwsorat_efdate_brch")
public class SabaEfDateBranches implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "rownum")
    private String rowNum;
    @Column(name = "EDKOL_CODE")
    private String edareCode;

    @Column(name = "EDKOL_NAME")
    private String edareName;
    @Column(name = "brch_code")
    private String brchCode;

    @Column(name = "brch_name")
    private String brchName;

    @Column(name = "SORAT_EFDATE")
    private String soratEfDate;
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

    public String getSoratEfDate() {
        return soratEfDate;
    }

    public void setSoratEfDate(String soratEfDate) {
        this.soratEfDate = soratEfDate;
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
