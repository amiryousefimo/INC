package ir.tamin.incomeBank.model.financialDoc;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "vwmoghlist3070_5")

public class Collection3070 implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "ROWNUM")
    private String rowId;

    @Column(name = "SORAT_ORDPAY")
    private String soratOrderPay;

    @Column(name = "SORAT_DATE")
    private String soratDate;

    @Column(name = "SORAT_EFDATE")
    private BigDecimal soratEfDate;

    @Column(name = "SORAT_PRICE1")
    private String soratPrice1;


}
