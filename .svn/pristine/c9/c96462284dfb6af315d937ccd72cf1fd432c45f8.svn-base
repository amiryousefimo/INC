package ir.tamin.incomeBank.dto.announcement.newspaper;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author A_Jamali
 */
@Getter
@Setter
@NoArgsConstructor
public class AnnouncementNewspaperDto {

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
    @Pattern(regexp ="^\\d{4}$", message = "Edare Code must be 4 digits")
    @Schema(
            description = "کد شعبه", example = "0060"
    )

    private String brchCode;
    @Schema(
            description = "نام شعبه", example = "شش تهران"
    )

    private String brchName;
    @Schema(
            description = "نوع اعلامیه", example = " نوع اعلامیه"
    )

    private String elamKind;

    private String elamDesc;
    @Schema(
            description = "شرح اعلامیه", example = "شرح نوع اعلامیه"
    )

    private BigDecimal letterPrice1;
    @Schema(
            description = "نام کارگاه", example = "12345678"
    )

    private String rwshId;
    @Schema(
            description = "شماره کارگاه", example = "12345678"
    )

    private String letterName;
    @Pattern(regexp = "(^$|[0-9]{8})", message = "Date must be 8 digits")
    @Schema(
            description = "تاریخ وصول", example = "14021201"
    )

    private String letterDate;
    @Pattern(regexp = "(^$|[0-9]{8})", message = "Date must be 8 digits")
    @Schema(
            description = "تاریخ موثر", example = "14021201"
    )

    private String letterLDate;
    @Schema(
            description = "شماره چک", example = "12345678"
    )

    private String chqNo;
    @Schema(
            description = "شماره چک", example = "12345678"
    )

    private String ordNo;
    @Schema(
            description = "شماره برگه پرداخت", example = "12345678"
    )

    private String functionalDate;
   /* @Schema(
            description = "تاریخ عملکرد لیست", example = "140108"
    )*/
}
