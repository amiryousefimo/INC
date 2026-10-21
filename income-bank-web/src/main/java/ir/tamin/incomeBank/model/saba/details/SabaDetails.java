package ir.tamin.incomeBank.model.saba.details;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name = "vwsorat_saba_det")
@Getter
@Setter
@NoArgsConstructor
public class SabaDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "rownum")
    private String rowNum;
    //select  EDKOL_CODE,   --کد اداره کل

    @Column(name = "EDKOL_CODE")
    private String edareCode;
    //          EDKOL_NAME,  --نام اداره کل
    @Column(name = "EDKOL_NAME")
    private String edareName;
    //          brch_code,   -- کد شعبه
    @Column(name = "brch_code")
    private String brchCode;
    //          BRCH_NAME,  --نام شعبه
    @Column(name = "BRCH_NAME")
    private String brchName;
    //          sorat_ordpay,      --شماره برگه پرداخت
    @Column(name = "sorat_ordpay")
    private String soratOrdPay;
    //          sorat_date,        --تاريخ وصول
    @Column(name = "sorat_date")
    private String soratDate;
    //          sorat_price1,      --مبلغ بدهکار
    @Column(name = "sorat_price1")
    private BigDecimal soratPrice1;
    //          sorat_price2,      --مبلغ بستانکار
    @Column(name = "sorat_price2")
    private BigDecimal soratPrice2;
    //          sorat_efdate,      -- تاريخ موثر وصول
    @Column(name = "sorat_efdate")
    private String soratEfDate;
    //          bank_code,         --کد بانک
    @Column(name = "bank_code")
    private String bankCode;
    //          bank_name          --نام بانک
    @Column(name = "bank_name")
    private String bankName;

}
