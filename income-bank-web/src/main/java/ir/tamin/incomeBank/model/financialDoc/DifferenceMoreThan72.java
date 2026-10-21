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
@Table(name = "vwmoghlist3070_6")

public class DifferenceMoreThan72 implements Serializable {
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
    private BigDecimal soratPrice1;


}
