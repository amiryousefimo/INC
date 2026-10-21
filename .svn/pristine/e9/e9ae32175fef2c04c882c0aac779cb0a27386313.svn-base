package ir.tamin.incomeBank.dto.monthlyComparisonReportBankAndOfficeStatement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class MonthlyComparisonReportBankAndOfficeStatementDto {

    private String rowNum;


    @Pattern(regexp = "(^$|[0-9]{6})", message = "Date must be 6 digits")
    @Schema(
            description = "سال و ماه", example = "140212"
    )
    private String date;

    @Schema(
            description = "مبلغ کارت بانک و اوراق", example = "1234567890"
    )
    private BigDecimal sumCard;
    @Schema(
            description = "مبلغ اعلامیه های ارسال شده ", example = "1234567890"
    )
    private BigDecimal sumLetter;
}
