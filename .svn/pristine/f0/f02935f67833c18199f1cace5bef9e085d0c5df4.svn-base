package ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCards3070;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
/**
 * @author m_goharriz
 * @date 2024.12.15
 */
@Entity()
@Table(name = "vwmoghlist3070_1")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SheetsWithoutBankCards3070 implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @Column(name = "rownum")
    private String rowId;

    @Column(name = "ORD_ORDNO")
    private String orderNumber;//شماره برگه پرداخت

    @Column(name = "GL_AMT")
    private BigDecimal glAmount;//مبلغ وصول

    @Column(name = "GL_DATE")
    private String glDate;//تاریخ وصول

    @Transient
    private String glDate2;

    @Column(name = "GL_BANK")
    private String glBank;//؟؟؟؟؟؟

    @Column(name = "SHOW_TYPE")
    private String showType;//؟؟؟؟؟؟

    @Column(name = "BRCH_CODE")
    private String brchCode;

    @Column(name = "SYSTEM_TYPE")
    private String systemType;

    @Column(name = "BRCH_NAME")
    private String brchName;

    @Column(name = "EDKOL_CODE")
    private String edareCode;

    @Column(name = "EDKOL_NAME")
    private String edareName;

    @Column(name = "CUST_ID")
    private String customerId;//کدملی بیمه شده

    @Column(name = "CUST_NAME")
    private String customerName;//نام و نام خانوادگی بیمه شده

    @Column(name = "SYSTEM_DESC")
    private String systemDesc;

}
