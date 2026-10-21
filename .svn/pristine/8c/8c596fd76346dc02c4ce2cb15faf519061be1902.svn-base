package ir.tamin.incomeBank.model.payment3070.total;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
/**
 * @author m_goharriz
 * Date : 1404.02.10
 * کارت بانک 3070-کلیات
 */

@Entity
@Table(name = "vwsorat_3070_total")
@Getter
@Setter
public class Total3070 {

    @Id
    @Column(name = "rownum")
    private String rowNum;

    @Column(name = "sorat_date")
    private String soratDate;//تاریخ

    @Column(name = "starat_amount")// موجودی ابتدای دوره
    private BigDecimal soratAmount;

    @Column(name = "Ssorat_price1")//مبلغ واریز
    private BigDecimal soratPrice1;

    @Column(name = "Ssorat_price2")// مبلغ برداشت
    private BigDecimal soratPrice2;

    @Column(name = "price_date")//مبلغ مانده
    private BigDecimal priceDate;

    @Column(name = "cnt_date")//تعداد
    private BigDecimal countDate;

    @Column(name = "price_efdate")//مبلغ موثر بین راهی
    private BigDecimal priceEfDate;

}
