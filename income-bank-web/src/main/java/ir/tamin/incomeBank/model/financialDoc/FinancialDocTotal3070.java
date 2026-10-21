/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.tamin.incomeBank.model.financialDoc;

import lombok.AllArgsConstructor;
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
 * @author m_goharriz
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vwdrmd_Acc3070_grp")
public class FinancialDocTotal3070 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ROWNUM")
    private String rowId;

    @Column(name = "DRMD_YY")
    private String daramadYear;

    @Column(name = "DRMD_MM")
    private String daramadMonth;

    @Column(name = "ACC_CODE")
    private String accountCode;

    @Column(name = "ACC_DESC")
    private String accountDesc;

    @Column(name = "BED_AMT")
    private BigDecimal debtAmount;

    @Column(name = "BES_AMT")
    private BigDecimal creditAmount;

    @Column(name = "CRT_DATE")
    private String createDate;

    @Column(name = "FLG_DOC")
    private String flagDocument;

    @Column(name = "brch_code")
    private String brchCode;

    @Column(name = "DOC_ROW")
    private String docRow;

    @Column(name = "DOC_TYPE")
    private String docType;

    @Column(name = "value_shenaseh")
    private String valueShenaseh;


}
