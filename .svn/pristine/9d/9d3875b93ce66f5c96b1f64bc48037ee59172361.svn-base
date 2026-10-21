package ir.tamin.incomeBank.model.announcement.monthlyComparisonReportBankAndOfficeStatement;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "vwletter_card_compare")
public class MonthlyComparisonReportBankAndOfficeStatement implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "rownum")
    private String rowNum;

    @Column(name = "dat")
    private String date;

    @Column(name = "sum_card")
    private BigDecimal sumCard;

    @Column(name = "sum_letter")
    private BigDecimal sumLetter;


    public String getRowNum() {
        return rowNum;
    }

    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getSumCard() {
        return sumCard;
    }

    public void setSumCard(BigDecimal sumCard) {
        this.sumCard = sumCard;
    }

    public BigDecimal getSumLetter() {
        return sumLetter;
    }

    public void setSumLetter(BigDecimal sumLetter) {
        this.sumLetter = sumLetter;
    }
}
