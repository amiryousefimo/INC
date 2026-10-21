package ir.tamin.incomeBank.dto.pey3070.account503;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Account503Dto implements Serializable {
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
    @Pattern(regexp = "^\\d{4}$", message = "Branch Code must be 4 digits")
    @Schema(
            description = "کد شعبه", example = "0060"
    )
    private String brchCode;

    @Schema(
            description = "نام شعبه", example = "شش تهران"
    )
    private String brchName;
    @Schema(
            description = "تاريخ موثر وصول", example = "14021012"
    )
    private String ordDate;
    @Pattern(regexp = "^\\d{10}$", message = "National Code must be 10 digits")
    @Schema(
            description = "کد ملی", example = "1234567890"
    )
    private String nationalCode;

    @Schema(
            description = " نام وبيمه شده", example = "علی جمالی"
    )
    private String nameOfTheInsured;
    @Schema(
            description = " شناسه پرداخت", example = "99760000006709506"
    )
    private String ordOrderNumber;


    @Schema(
            description = " شرح موضوع پرداخت", example = "دريافت حق بيمه كارگران ساختماني"
    )
    private String debitReasonDesc;

    @Pattern(regexp = "^\\d{8}$", message = "Start Date Of period must be 8 digits")
    @Schema(
            description = " تاریخ شروع دوره", example = "14020101"
    )
    private String startDateOfPeriod;
    @Pattern(regexp = "^\\d{8}$", message = "End Date Of period Date be 8 digits")
    @Schema(
            description = " تاریخ پایان دوره", example = "14020101"
    )
    private String endDateOfPeriod;

    @Pattern(regexp = "^\\d{8}$", message = "End Date Of period must be 8 digits")
    @Schema(
            description = " تاريخ ارسال اطلاعات به سيستم INCOMING", example = "14020101"
    )
    private String sendDateOfInfoToIncoming;
    @Schema(
            description = "کد شرح موضوع پرداخت", example = "82"
    )
    private String debitReasonCode;
    @Schema(
            description = "کمک دولت (flag)", example = "0 or 1"
    )
    private String governmentFlag;

    @Schema(
            description = "کد سيستم مرجع", example = "20"
    )
    private String systemType;
    @Pattern(regexp = "^\\d$", message = "Order Amount must be digits")

    @Schema(
            description = "مبلغ وصول", example = "12345678"
    )
    private BigDecimal orderAmount;

    @Pattern(regexp = "^\\d$", message = "Government Amount must be digits")

    @Schema(
            description = "مبلغ کمک دولت", example = "12345678"
    )
    private BigDecimal governmentAmount;
    @Schema(
            description = "تاريخ وصول", example = "14021012"
    )
    private String soratDate;
    private String documentStatus;

}
