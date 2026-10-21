package ir.tamin.incomeBank.dto.financialDoc;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
public class DisagreementViewDto {

    private String rowId;

    @Pattern(regexp = "(^$|[0-9]{6})", message = "Date must be 6 digits")
    @Schema(
            description = "سال و ماه", example = "140312"
    )
    private String moghDate;

    private String jaryanCount;

    private BigDecimal jaryanAmount;

    private String nashenasCount;

    private BigDecimal nashenasAmount;

    private String tekrariCount;

    private BigDecimal tekrariAmount;

    private String confirmUserId;

    private String confirmDate;
}
