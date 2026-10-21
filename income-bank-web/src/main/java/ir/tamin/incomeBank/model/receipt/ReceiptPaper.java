package ir.tamin.incomeBank.model.receipt;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author A_Jamali
 * EditBy @author m_goharriz
 */
@Entity
@Getter
@Setter
@Table(name = "VWDRMD_GL_DET")
public class ReceiptPaper implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ROWNUM")
    private String rowId;

    @Column(name = "ORD_ORDNO")
    private String orderNo;

    @Column(name = "ORD_DATE")
    private String orderDate;

    @Column(name = "BRCH_CODE")
    private String branchCode;

    @Column(name = "BRCH_NAME")
    private String branchName;

    @Column(name = "CUST_ID")
    private String customerId;

    @Column(name = "CUST_NAME")
    private String customerName;

    @Column(name = "ORD_AMT")
    private BigDecimal orderAmount;

    @Column(name = "BANK_CODE")
    private String bankCode;

    @Column(name = "BANK_NAME")
    private String bankName;

    @Column(name = "GOVER_AMT")
    private BigDecimal governmentAmount;

    @Column(name = "COUNT_ISU")
    private Integer countIsu;

    @Column(name = "GOV_FLG")
    private String governmentFlag;

    @Column(name = "ADD_DT")
    private String addDate;

    @Column(name = "ORD_TYPE1")
    private String orderType1;

    @Column(name = "TYPE_AMT1")
    private BigDecimal orderAmount1;

    @Column(name = "ORD_TYPE2")
    private String orderType2;

    @Column(name = "TYPE_AMT2")
    private BigDecimal orderAmount2;

    @Column(name = "ORD_TYPE3")
    private String orderType3;

    @Column(name = "TYPE_AMT3")
    private BigDecimal orderAmount3;

    @Column(name = "ORD_TYPE4")
    private String orderType4;

    @Column(name = "TYPE_AMT4")
    private BigDecimal orderAmount4;

    @Column(name = "ORD_TYPE5")
    private String orderType5;

    @Column(name = "TYPE_AMT5")
    private BigDecimal orderAmount5;

    @Column(name = "COUNT_ISU_GOV")
    private String countIsuGov;

    @Column(name = "EDKOL_CODE")
    private String edareCode;

    @Column(name = "EDKOL_NAME")
    private String edareName;

    @Column(name = "SYSTEM_TYPE")
    private String sysType;

    @Column(name = "SORAT_DATE")
    private String soratDate;

    @Column(name = "LIST_DATE")
    private String listDate;

    @Column(name = "ord_pymseq")
    private String orderPaymentSequence;

    @Column(name = "prcnt_type")
    private String percentType;

    @Column(name = "prcnt_amt")
    private BigDecimal percentAmount;

    @Column(name = "doc_flg")
    private String documentStatus;

    @Column(name = "khazane")
    private String khazane;
//------------------------------------------------------------
    @Column(name = "gov_desc_01")
    private String governmentDesc01;//عنوان کمک دولت

    @Column(name = "gov_type_01")
    private String governmentType01;//

    @Column(name = "gov_amt_emp_01")
    private BigDecimal governmentAmountEmployee01;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_01")
    private String governmentCount01;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_01")
    private BigDecimal governmentAmountInsured01;//مبلغ سهم بیمه شده
    //------------------------------------------------------------
    @Column(name = "gov_desc_02")
    private String governmentDesc02;//عنوان کمک دولت 2

    @Column(name = "gov_amt_emp_02")
    private BigDecimal governmentAmountEmployee02;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_02")
    private String governmentCount02;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_02")
    private BigDecimal governmentAmountInsured02;//مبلغ سهم بیمه شده
//----------------------------------------------------------------

    @Column(name = "gov_desc_03")
    private String governmentDesc03;//عنوان کمک دولت3

    @Column(name = "gov_amt_emp_03")
    private BigDecimal governmentAmountEmployee03;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_03")
    private String governmentCount03;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_03")
    private BigDecimal governmentAmountInsured03;//مبلغ سهم بیمه شده
//----------------------------------------------------------------

    @Column(name = "gov_desc_04")
    private String governmentDesc04;//عنوان کمک دولت4

    @Column(name = "gov_amt_emp_04")
    private BigDecimal governmentAmountEmployee04;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_04")
    private String governmentCount04;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_04")
    private BigDecimal governmentAmountInsured04;//مبلغ سهم بیمه شده
//----------------------------------------------------------------

    @Column(name = "gov_desc_05")
    private String governmentDesc05;//عنوان کمک دولت5

    @Column(name = "gov_amt_emp_05")
    private BigDecimal governmentAmountEmployee05;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_05")
    private String governmentCount05;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_05")
    private BigDecimal governmentAmountInsured05;//مبلغ سهم بیمه شده
//----------------------------------------------------------------

    @Column(name = "gov_desc_06")
    private String governmentDesc06;//عنوان کمک دولت6

    @Column(name = "gov_amt_emp_06")
    private BigDecimal governmentAmountEmployee06;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_06")
    private String governmentCount06;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_06")
    private BigDecimal governmentAmountInsured06;//مبلغ سهم بیمه شده

    //----------------------------------------------------------------

    @Column(name = "gov_desc_07")
    private String governmentDesc07;//عنوان کمک دولت7

    @Column(name = "gov_amt_emp_07")
    private BigDecimal governmentAmountEmployee07;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_07")
    private String governmentCount07;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_07")
    private BigDecimal governmentAmountInsured07;//مبلغ سهم بیمه شده
//----------------------------------------------------------------

    @Column(name = "gov_desc_08")
    private String governmentDesc08;//عنوان کمک دولت8

    @Column(name = "gov_amt_emp_08")
    private BigDecimal governmentAmountEmployee08;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_08")
    private String governmentCount08;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_08")
    private BigDecimal governmentAmountInsured08;//مبلغ سهم بیمه شده
//----------------------------------------------------------------
    @Column(name = "gov_desc_09")
    private String governmentDesc09;//عنوان کمک دولت9

    @Column(name = "gov_amt_emp_09")
    private BigDecimal governmentAmountEmployee09;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_09")
    private String governmentCount09;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_09")
    private BigDecimal governmentAmountInsured09;//مبلغ سهم بیمه شده
//----------------------------------------------------------------

    @Column(name = "gov_desc_10")
    private String governmentDesc10;//عنوان کمک دولت10

    @Column(name = "gov_amt_emp_10")
    private BigDecimal governmentAmountEmployee10;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_10")
    private String governmentCount10;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_10")
    private BigDecimal governmentAmountInsured10;//مبلغ سهم بیمه شده

//----------------------------------------------------------------

    @Column(name = "gov_desc_11")
    private String governmentDesc11;//عنوان کمک دولت11

    @Column(name = "gov_amt_emp_11")
    private BigDecimal governmentAmountEmployee11;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_11")
    private String governmentCount11;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_11")
    private BigDecimal governmentAmountInsured11;//مبلغ سهم بیمه شده

    //----------------------------------------------------------------

    @Column(name = "gov_desc_12")
    private String governmentDesc12;//عنوان کمک دولت12

    @Column(name = "gov_amt_emp_12")
    private BigDecimal governmentAmountEmployee12;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_12")
    private String governmentCount12;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_12")
    private BigDecimal governmentAmountInsured12;//مبلغ سهم بیمه شده

    //----------------------------------------------------------------

    @Column(name = "gov_desc_13")
    private String governmentDesc13;//عنوان کمک دولت13

    @Column(name = "gov_amt_emp_13")
    private BigDecimal governmentAmountEmployee13;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_13")
    private String governmentCount13;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_13")
    private BigDecimal governmentAmountInsured13;//مبلغ سهم بیمه شده
//----------------------------------------------------------------

    @Column(name = "gov_desc_14")
    private String governmentDesc14;//عنوان کمک دولت14

    @Column(name = "gov_amt_emp_14")
    private BigDecimal governmentAmountEmployee14;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_14")
    private String governmentCount14;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_14")
    private BigDecimal governmentAmountInsured14;//مبلغ سهم بیمه شده

    //----------------------------------------------------------------

    @Column(name = "gov_desc_15")
    private String governmentDesc15;//عنوان کمک دولت15

    @Column(name = "gov_amt_emp_15")
    private BigDecimal governmentAmountEmployee15;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_15")
    private String governmentCount15;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_15")
    private BigDecimal governmentAmountInsured15;//مبلغ سهم بیمه شده
//----------------------------------------------------------------
    @Column(name = "gov_desc_16")
    private String governmentDesc16;//عنوان کمک دولت16

    @Column(name = "gov_amt_emp_16")
    private BigDecimal governmentAmountEmployee16;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_16")
    private String governmentCount16;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_16")
    private BigDecimal governmentAmountInsured16;//مبلغ سهم بیمه شده

    //----------------------------------------------------------------

    @Column(name = "gov_desc_17")
    private String governmentDesc17;//عنوان کمک دولت17

    @Column(name = "gov_amt_emp_17")
    private BigDecimal governmentAmountEmployee17;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_17")
    private String governmentCount17;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_17")
    private BigDecimal governmentAmountInsured17;//مبلغ سهم بیمه شده

    //----------------------------------------------------------------

    @Column(name = "gov_desc_18")
    private String governmentDesc18;//عنوان کمک دولت18

    @Column(name = "gov_amt_emp_18")
    private BigDecimal governmentAmountEmployee18;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_18")
    private String governmentCount18;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_18")
    private BigDecimal governmentAmountInsured18;//مبلغ سهم بیمه شده
//----------------------------------------------------------------

    @Column(name = "gov_desc_19")
    private String governmentDesc19;//عنوان کمک دولت19

    @Column(name = "gov_amt_emp_19")
    private BigDecimal governmentAmountEmployee19;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_19")
    private String governmentCount19;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_19")
    private BigDecimal governmentAmountInsured19;//مبلغ سهم بیمه شده

    //----------------------------------------------------------------

    @Column(name = "gov_desc_20")
    private String governmentDesc20;//عنوان کمک دولت20

    @Column(name = "gov_amt_emp_20")
    private BigDecimal governmentAmountEmployee20;// مبلغ سهم کارفرما

    @Column(name = "gov_cnt_20")
    private String governmentCount20;//تعداد بیمه شده مشمول

    @Column(name = "gov_amt_isd_20")
    private BigDecimal governmentAmountInsured20;//مبلغ سهم بیمه شده

    @Column(name = "goverment_3")
    private BigDecimal government3;//سه درصد دولت

    @Column(name = "isd27")
    private BigDecimal isd27;//27 درصد مبلغ حق بیمه
//----------------------------------------------------------------

    @Override
    public String toString() {
        return "ReceiptPaper{" +
                "rowId='" + rowId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", branchName='" + branchName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", orderAmount=" + orderAmount +
                ", bankCode='" + bankCode + '\'' +
                ", bankName='" + bankName + '\'' +
                ", governmentAmount=" + governmentAmount +
                ", countIsu=" + countIsu +
                ", governmentFlag='" + governmentFlag + '\'' +
                ", addDate='" + addDate + '\'' +
                ", orderType1='" + orderType1 + '\'' +
                ", orderAmount1='" + orderAmount1 + '\'' +
                ", orderType2='" + orderType2 + '\'' +
                ", orderAmount2='" + orderAmount2 + '\'' +
                ", orderType3='" + orderType3 + '\'' +
                ", orderAmount3='" + orderAmount3 + '\'' +
                ", orderType4='" + orderType4 + '\'' +
                ", orderAmount4='" + orderAmount4 + '\'' +
                ", soratDate='" + soratDate + '\'' +
                '}';
    }


}
