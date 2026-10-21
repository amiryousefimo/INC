package ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCardsSmartList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author m_goharriz
 * @date 2024.12.18
 */
@Entity()
@Table(name = "vwmoghlist1")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SheetsWithoutBankCardsSmartList implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @Column(name = "rownum")
    private String rowId;

    @Column(name = "EDKOL_CODE")
    private String edareCode;

    @Column(name = "EDKOL_NAME")
    private String edareName;

    @Column(name = "BRCH_CODE")
    private String brchCode;

    @Column(name = "BRCH_NAME")
    private String brchName;

    @Column(name = "ORD_ORDNO")
    private String orderNumber;//شماره برگه پرداخت

    @Column(name = "GL_AMT")
    private BigDecimal glAmount;//مبلغ وصول

    @Column(name = "GL_DATE")
    private String glDate;//تاریخ وصول

    @Transient
    private String glDate2;

    @Column(name = "GL_BANK")
    private String glBank;//نام بانک


    @Column(name = "CUST_ID")
    private String customerId;//کد کارگاه

    @Column(name = "CUST_NAME")
    private String customerName;//نام کارگاه

/*********************** for view *******************/
   /* @Column(name = "YEAR")
    private String year;

    @Column(name = "MON")
    private String month;

    @Column(name = "SORAT_AMT")
    private String soratAmount;

    @Column(name = "DIFF_AMT")
    private String diffAmount;

    @Column(name = "SORAT_DATE")
    private String soratDate;

    @Column(name = "SHOW_BANK")
    private String showBank;

    @Column(name = "SORAT_BANK")
    private String soratBank;

    @Column(name = "SHOW_TYPE")
    private String showType;

    @Column(name = "SHOW_DESC")
    private String showDesc;
*/
}



