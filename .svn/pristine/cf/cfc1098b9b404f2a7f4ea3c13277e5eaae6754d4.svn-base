package ir.tamin.incomeBank.model.paymentWorkers.branch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity()
@Table(name = "VW_PAYKIND_BLD_BRCH")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentWorkersBranch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "rownum")
    private String rowNum;

    @Column(name = "BRCH_CODE")
    private String brchCode;

    @Column(name = "BRCH_NAME")
    private String brchName;

    @Column(name = "EDKOL_CODE")
    private String edareCode;

    @Column(name = "EDKOL_NAME")
    private String edareName;

    @Column(name = "ORD_DATE")
    private String ordDate;

    @Transient
    private String orderDate2;

    @Column(name = "SUM_AMT")
    private BigDecimal sumAmount;//وصولی

    @Column(name = "CNT_ORDNO")
    private BigDecimal countOrderNumber;//تعداد وصول

    @Column(name = "BIMEH7SAKHT")
    private BigDecimal bimeh7sakht;//کارگر

    @Column(name = "BIMEH20SAKHT")
    private BigDecimal bimeh20sakht;//کارفرما

    @Column(name = "JARIMEH_SAKHT")
    private BigDecimal jarimehSakht;//جریمه

    @Column(name = "SUM_AMT_GOV")
    private BigDecimal sumAmtGov;//کمک دولت
}
