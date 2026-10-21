package ir.tamin.incomeBank.model.smartlist.edare;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
/**
 * @author a_jamali
 */
@Entity
@Table(name = "vwdrmd_gl_dailyedkol")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SmartListEdare implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "rownum")
    private String rowNum;
    @Column(name = "EDKOL_CODE")
    private String edareCode;
    @Column(name = "EDKOL_NAME")
    private String edareName;
    @Column(name = "ord_date")
    private String orderDate;
    @Transient
    private String orderDate2;
    @Column(name = "sum_amt")
    private BigDecimal sumAmount;
    @Column(name ="sum_isu" )
    private BigDecimal sumISu;
    @Column(name = "sum_amt_gov")
    private BigDecimal sumAmountGov;
    @Column(name = "sum_isu_gov")
    private BigDecimal sumIsuGov;
    @Column(name = "cnt_ordno")
    private BigDecimal countOrdNumber;
    @Column(name = "HAGHE_BIMEH")
    private BigDecimal hagheBimeh;
    @Column(name = "HAGHE_BIMEHGOV")
    private BigDecimal hagheBimehGov;
    @Column(name = "BIKARI")
    private BigDecimal bikari;
    @Column(name = "JARIMEH")
    private BigDecimal jarimeh;

}
