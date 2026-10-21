package ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCards3070;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
/**
 * @author m_goharriz
 * @date 2024.12.15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SheetsWithoutBankCardsView implements Serializable {
    private static final long serialVersionUID = 1L;



    private String rowId;

    private String orderNumber;

    private BigDecimal glAmount;

    private String glDate;

    private String glDate2;

    private String glBank;

    private String showType;

    private String brchCode;

    private String systemType;

    private String brchName;

    private String edareCode;

    private String edareName;

    private String customerId;

    private String customerName;

    private String systemDesc;
}
