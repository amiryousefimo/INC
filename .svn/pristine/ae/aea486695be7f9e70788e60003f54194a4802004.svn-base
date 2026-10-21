package ir.tamin.incomeBank.dto.saba.details;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author A_Jamali
 */


@Getter
@Setter
@NoArgsConstructor
@Schema(
        name = "SabaDetailsDto",
        description = "Schema to hold SabaDetails information from view  \"vwsorat_saba_det\""
)
public class SabaDetailsDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String rowNum;
    @Pattern(regexp = "^\\d{4}$", message = "Edare Code must be 4 digits")
    @Schema(
            description = "کد اداره کل", example = "8510"
    )
    private String edareCode;
    @Schema(
            description = "نام اداره کل", example = "اداره کل غرب تهران بزرگ"
    )
    private String edareName;
    @Pattern(regexp = "^\\d{4}$", message = "brchCode must be 4 digits")
    @Schema(
            description = "کد شعبه", example = "0070"
    )
    private String brchCode;
    @Schema(
            description = "نام شعبه", example = "شعبه 7 تهران"
    )
    private String brchName;
    @Schema(
            description = "شماره برگه پرداخت", example = "1234567891011"
    )
    private String soratOrdPay;
    @Pattern(regexp = "^\\d{8}$", message = "Date must be 8 digits")
    @Schema(
            description = "تاریخ اعلامیه ", example = "14020101"
    )
    private String soratDate;
    @Pattern(regexp = "^\\d$", message = "soratPrice1 must be a Number")
    @Schema(
            description = "مبلغ بدهکار ", example = "1234567"
    )
    private BigDecimal soratPrice1;
    @Pattern(regexp = "^\\d$", message = "soratPrice2 must be a Number")
    @Schema(
            description = "مبلغ بستانکار ", example = "1234567"
    )
    private BigDecimal soratPrice2;

    @Pattern(regexp = "^\\d{8}$", message = "Date must be 8 digits")
    @Schema(
            description = "تاريخ موثر وصول ", example = "14020101"
    )
    private String soratEfDate;
    @Pattern(regexp = "^\\d$", message = "bankCode must be digits")
    @Schema(
            description = "کد بانک", example = "03"
    )
    private String bankCode;
    @Schema(
            description = "نام بانک", example = " بانک ملت"
    )
    private String bankName;

}
