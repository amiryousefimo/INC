package ir.tamin.incomeBank.model.specialInsuredsReport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialInsuredsReportView implements Serializable {
    private static final long serialVersionUID = 1L;

    private String rowNum;


    private String soratOrdPayment;//شناسه پرداخت


    private String soratDate1;//تاریخ وصول از

    private String soratDate2;//تا

    private BigDecimal soratPrice1;//مبلغ وصول


    private String soratPaymentSeq;//شماره برگه پرداخت


    private String soratEfDate;//تاریخ؟؟؟؟؟


    private String systemType;//نوع سیستم


    private String systemDesc;//نام سیستم

}
