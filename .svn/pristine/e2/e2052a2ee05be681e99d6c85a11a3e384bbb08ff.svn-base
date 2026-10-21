package ir.tamin.incomeBank.model.financialDoc.newFinancialDocSpec;

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
@Table(name = "vwmoghlist_list_7")

public class DifferenceBetweenBankOfficeDocSpec implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "ROWNUM")
    private String rowId;

    @Column(name = "SORAT_ORDPAY")
    private String soratOrderPay;//شماره برگه پرداخت

    @Column(name = "ORD_DATE")
    private String orderDate;//تاریخ موثر دفتر

    @Column(name = "SORAT_DATE")
    private String soratDate;//تاریخ وصول بانک

    @Column(name = "sorat_price1")
    private BigDecimal soratPrice1;//مبلغ بانک

    @Column(name = "ord_amt")
    private BigDecimal orderAmount;//مبلغ دفتر

    @Column(name = "SYSTEM_DESC")//نوع حق بیمه
    private String systemDesc;

    @Column(name = "DIFF_AMT")
    private BigDecimal diffrentAmount;//اختلاف

    /*@Column(name = "sorat_row")
    private String soratRow;

    @Column(name = "ord_type1")
    private String orderType1;

    @Column(name = "type_amt1")
    private BigDecimal typeAmount1;

    @Column(name = "ord_type2")
    private String orderType2;

    @Column(name = "type_amt2")
    private BigDecimal typeAmount2;

    @Column(name = "ord_type3")
    private String orderType3;

    @Column(name = "type_amt3")
    private BigDecimal typeAmount3;

    @Column(name = "SYSTEM_DESC")
    private String systemDesc;

    @Column(name = "DIFF_AMT")
    private BigDecimal diffrentAmount;
*/







}
