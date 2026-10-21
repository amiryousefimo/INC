package ir.tamin.incomeBank.dto.receiptManagement.edare;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author a_jamali
 */

@Getter
@Setter
@NoArgsConstructor
public class ReceiptManagementEdareDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String rowNum;
    @Pattern(regexp ="^\\d{4}$", message = "Edare Code must be 4 digits")
    @Schema(
            description = "کد اداره کل", example = "8510"
    )

    private String edareCode;

    @Schema(
            description = "نام اداره کل", example = "اداره کل غرب تهران بزرگ"
    )
    private String edareName;


    @Pattern(regexp = "(^$|[0-9]{8})", message = "Date must be 8 digits")
    @Schema(
            description = "تاریخ از", example = "14021201"
    )
    private String soratDate;

    @Pattern(regexp = "(^$|[0-9]{8})", message = "Date must be 8 digits")
    @Schema(
            description = "تاریخ تا", example = "14021201"
    )
    private String soratDate2;
    @Schema(
            description = "کارت بانک سیستم سبا", example = "12345678"
    )
    private BigDecimal sabaPrc;

    @Schema(
            description = "دفتر لیست هوشمند", example = "12345678"
    )
    private BigDecimal intljPrc;

    @Schema(
            description = "دفتر اعلامیه اوراق خزانه ", example = "12345678"
    )
    private BigDecimal karkonanPrc;

    @Schema(
            description = "دفتر اعلامیه واریز نقدی", example = "12345678"
    )
    private BigDecimal naghdiPrc;

    @Schema(
            description = "دفتر اعلامیه کارکنان دولت", example = "12345678"
    )
    private BigDecimal oraghPrc;

    @Schema(
            description = "دفتر 3070", example = "12345678"
    )
    private BigDecimal prc3070;
    @Schema(
            description = "جمع مبالغ وصول", example = "12345678"
    )
    private BigDecimal sumPrc;

}
