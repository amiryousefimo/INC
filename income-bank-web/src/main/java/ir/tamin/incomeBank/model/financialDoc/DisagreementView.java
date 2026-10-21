package ir.tamin.incomeBank.model.financialDoc;

import ir.tamin.framework.ws.rest.repository.annotation.RESTResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "VWMOGH_HDR")
//@RESTResource(proxy = DisagreementView.class)    proxyClassName
public class DisagreementView implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ROWNUM")
    private String rowId;

    @Column(name = "MOGH_DATE")
    private String moghDate;

    @Column(name = "JARYAN_CNT")
    private String jaryan_cnt;

    @Column(name = "JARYAN_AMT")
    private BigDecimal jaryan_amt;

    @Column(name = "NASHENAS_CNT")
    private String nashenas_cnt;

    @Column(name = "NASHENAS_AMT")
    private BigDecimal nashenas_amt;

    @Column(name = "TEKRARI_CNT")
    private String tekrari_cnt;

    @Column(name = "TEKRARI_AMT")
    private BigDecimal tekrari_amt;

    @Column(name = "CONF_UID")
    private String confirmUserId;

    @Column(name = "CONF_DATE")
    private String confirmDate;

    @Column(name = "MOGH_yy")
    private String moghYear;

    @Column(name = "MOGH_MM")
    private String moghMonth;

    @Column(name = "MOGH_SYSTEM")
    private String moghSystem;//9901 : Intelij   ,   9900 : 3070

    @Column(name = "G72_CNT")
    private String g72_cnt;

    @Column(name = "G72_AMT")
    private BigDecimal g72_amt;

    @Column(name = "JARYAN2_CNT")
    private String jaryan2_cnt;

    @Column(name = "JARYAN2_AMT")
    private BigDecimal jaryan2_amt;

    @Column(name = "bank_gl_cnt")
    private String bankGl_cnt;

    @Column(name = "bank_gl_amt")
    private BigDecimal bankGl_amt;
}
