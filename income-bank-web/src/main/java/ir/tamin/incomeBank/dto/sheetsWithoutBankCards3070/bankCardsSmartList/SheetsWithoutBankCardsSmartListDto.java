package ir.tamin.incomeBank.dto.sheetsWithoutBankCards3070.bankCardsSmartList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SheetsWithoutBankCardsSmartListDto {


    private String rowId;

    private String edareCode;
    private String edareName;

    private String brchCode;
    private String brchName;

    private String orderNumber;

    private BigDecimal glAmount;

    private String glDate;
    private String glDate2;

    private String glBank;

    private String customerId;
    private String customerName;


}
