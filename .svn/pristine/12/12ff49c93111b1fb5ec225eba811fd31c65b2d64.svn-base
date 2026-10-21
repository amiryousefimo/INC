package ir.tamin.incomeBank.model.financialDoc;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
@Entity
@Getter
@Setter
@Table(name = "vwmoghlist3070_4")

public class RepeatedDeposits implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "ROWNUM")
    private String rowId;

    @Column(name = "SORAT_ORDPAY")
    private String soratOrderPay;

    @Column(name = "SORAT_DATE")
    private String soratDate;

    @Column(name = "sorat_payment_seq")
    private String soratPaymentSeq;//شناسه پرداخت

    @Column(name = "SORAT_PRICE1")
    private BigDecimal soratPrice1;
}
