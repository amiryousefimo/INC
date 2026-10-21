package ir.tamin.incomeBank.model.similarBankSheets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author m_goharriz
 * @date 2025.05.17
 */
@Entity
@Table(name = "VWMOGHLIST3070_GOVERMENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimilarBankSheets implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @Column(name = "rownum")
    private String rowId;

    @Column(name = "ORD_ORDNO")
    private String orderNumber;//شماره برگه پرداخت

    @Column(name = "ORD_DATE")
    private String orderDate;//تاریخ موثر

    @Column(name = "SORAT_DATE")
    private String soratDate;//تاریخ وصول

    @Column(name = "calc_goverment_desc")
    private String calculatorGovermentDesc;//شرح محاسبه شده

    @Column(name = "calc_goverment")
    private BigDecimal calculatorGoverment;//محاسبه شده کمک دولت

    @Column(name = "GOVER_AMT")
    private BigDecimal govermentAmount;// کمک دولت دفتر

    @Column(name = "DIFF_GOVERMENT")
    private BigDecimal diffGoverment;//مبلغ اختلاف

    @Column(name = "ORD_AMT")
    private BigDecimal orderAmount;//مبلغ حق بیمه

    @Column(name = "DEBITREASONDESC")
    private String  debitReasonDesc;//نام سیستم

    @Column(name = "CUST_ID")
    private String customerId;//کدملی بیمه شده

    @Column(name = "CUST_NAME")
    private String customerName;//نام و نام خانوادگی بیمه شده

    @Column(name = "EDKOL_CODE")
    private String edareCode;

    @Column(name = "EDKOL_NAME")
    private String edareName;

    @Column(name = "SYSTEM_TYPE")
    private String systemType;//موضوع پرداخت

    @Column(name = "CALC_GOVERMENT_SOLD20")
    private BigDecimal calculatorGovermentSold20;//20 درصد دولت

}
