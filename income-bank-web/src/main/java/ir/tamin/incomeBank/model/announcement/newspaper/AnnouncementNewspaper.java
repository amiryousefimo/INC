package ir.tamin.incomeBank.model.announcement.newspaper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author A_Jamali
 */

@Entity
@Table(name = "vwletter_det")
@Getter
@Setter
@NoArgsConstructor
public class AnnouncementNewspaper implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "rownum")
    private String rowNum;

    @Column(name = "EDKOL_CODE")
    private String edareCode;

    @Column(name = "EDKOL_NAME")
    private String edareName;

    @Column(name = "BRCH_CODE")
    private String brchCode;

    @Column(name = "BRCH_NAME")
    private String brchName;

    @Column(name = "ELAM_KIND")
    private String elamKind;

    @Column(name = "ELAM_DESC")
    private String elamDesc;

    @Column(name = "LETTER_PRICE1")
    private BigDecimal letterPrice1;

    @Column(name = "RWSHID")
    private String rwshId;

    @Column(name = "LETTER_NAM")
    private String letterName;

    @Column(name = "LETTER_DATE")
    private String letterDate;

    @Column(name = "LETTER_LDATE")
    private String letterLDate;

    @Column(name = "CHQ_NO")
    private String chqNo;

    @Column(name = "ORD_NO")
    private String ordNo;

    @Column(name = "BES_FUNCTIONDATE")
    private String functionalDate;

}



