package ir.tamin.incomeBank.model.basicTables.debtTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * @author m_goharriz
 * date 2025.01.26
 */
@Entity()
@Table(name = "VWTB_DEBITTYPE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DebtTypesTable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "rownum")
    private String rowNum;

    @Column(name = "DEBITCODE")
    private String debitCode;

    @Column(name = "DEBITDESC")
    private String debitDescription ;

    @Column(name = "CODE_KOL")
    private String codeKol;

    @Column(name = "CODE_MOI")
    private String codeMoin;

    @Column(name = "CODE_TAF")
    private String codeTafsili;

}
