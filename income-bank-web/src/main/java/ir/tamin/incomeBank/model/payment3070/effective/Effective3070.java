package ir.tamin.incomeBank.model.payment3070.effective;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "vwsorat_3070_daily")
@Getter
@Setter

public class Effective3070 {

    @Id
    @Column(name = "rownum")
    private String rowNum;

    @Column(name = "sorat_date")
    private String soratDate;//تاریخ

    @Transient
    private String endDate;

    @Column(name = "price_date")
    private BigDecimal priceDate;//مبلغ مانده

    @Column(name = "cnt_date")
    private BigDecimal countDate;//تعداد (واریزو برداشت)

    @Column(name = "price_efdate")
    private BigDecimal priceEfDate;//مبلغ موثر(بین راهی)

    @Column(name = "cnt_efdate")
    private BigDecimal countEfDate;//تعداد موثر(بین راهی)

    @Column(name = "Ssorat_price1")
    private BigDecimal soratPrice1;//مبلغ واریز (وصول)

    @Column(name = "Ssorat_price2")
    private BigDecimal soratPrice2;//مبلغ برداشت


}
