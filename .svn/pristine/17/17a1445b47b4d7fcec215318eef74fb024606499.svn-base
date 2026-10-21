package ir.tamin.incomeBank.dto.announcement.edare;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;



@Data
@Schema(
        name = "AnnouncementEdareKolDto",
        description = "Schema to hold AnnouncementEdareKol information from view  \"vwletter_edkol\""
)
public class AnnouncementEdareKolDto {

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

    @Pattern(regexp ="^\\d{8}$", message = "Date must be 8 digits")
    @Schema(
            description = "تاریخ اعلامیه ", example = "14020101"
    )
    private String letterDate;

    @Pattern(regexp ="^\\d$", message = "employees must be a Number")
    @Schema(
            description = "مبلغ حق بیمه کارکنان دولت ", example = "1234567"
    )
    private BigDecimal employees;

    @Pattern(regexp ="^\\d$", message = "employeesCount Code must be 4 digits")
    @Schema(
            description = "تعداد اعلامیه کارکنان دولت ", example = "1234567"
    )
    @PositiveOrZero
    private BigDecimal employeesCount;

    @Pattern(regexp ="^\\d$", message = "peyman must be digits")
    @Schema(
            description = "مبلغ حق بیمه پیمانکاران ذی حسابی ", example = "1234567"
    )
    private BigDecimal peyman;

    @Pattern(regexp ="^\\d$", message = "peymanCount must be 4 digits")
    @Schema(
            description = "تعداد اعلامیه پیمانکاران ذی حسابی ", example = "1234567"
    )
    @PositiveOrZero
    private BigDecimal peymanCount;

    @Pattern(regexp ="^\\d$", message = "sumAmount must be Number")
    @Schema(
            description = "جمع مبالغ ", example = "12345678"
    )
    private BigDecimal sumAmount;

    @PositiveOrZero
    @Pattern(regexp ="^\\d$", message = "sumCount must be Number")
    @Schema(
            description = "تعداد اعلامیه", example = "1234"
    )
    private BigDecimal sumCount;
}
