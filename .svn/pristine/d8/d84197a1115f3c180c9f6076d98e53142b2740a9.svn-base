package ir.tamin.incomeBank.model.specialInsuredsReport;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author m_goharriz
 */
@Entity()
@Table(name = "vwmoghlist3070_2")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialInsuredsReport implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "rownum")
    private String rowNum;

    @Column(name = "SORAT_ORDPAY")
    private String soratOrdPayment;//شناسه پرداخت

    @Column(name = "SORAT_DATE")
    private String soratDate;//تاریخ وصول

    @Transient
    private String soratDate2;

    @Column(name = "SORAT_PRICE1")
    private BigDecimal soratPrice1;//مبلغ وصول

    @Column(name = "SORAT_PAYMENT_SEQ")
    private String soratPaymentSeq;//شماره برگه پرداخت

    @Column(name = "SORAT_EFDATE")
    private String soratEfDate;//تاریخ؟؟؟؟؟

    @Column(name = "SYSTEM_TYPE")
    private String systemType;//نوع سیستم

    @Column(name = "SYSTEM_DESC")
    private String systemDesc;//نام سیستم


}
