package ir.tamin.incomeBank.model.workshopDebitReport.branches;

import java.math.BigDecimal;


/**
 * @author A_Jamali
 */
public class WorkshopDebitReportSummeryBranches {
    private BigDecimal sumAmountBed;
    private BigDecimal sumAmountBes;

    public WorkshopDebitReportSummeryBranches() {
    }

    public WorkshopDebitReportSummeryBranches(BigDecimal sumAmountBed, BigDecimal sumAmountBes) {
        this.sumAmountBed = sumAmountBed;
        this.sumAmountBes = sumAmountBes;
    }

    public BigDecimal getSumAmountBed() {
        return sumAmountBed;
    }

    public void setSumAmountBed(BigDecimal sumAmountBed) {
        this.sumAmountBed = sumAmountBed;
    }

    public BigDecimal getSumAmountBes() {
        return sumAmountBes;
    }

    public void setSumAmountBes(BigDecimal sumAmountBes) {
        this.sumAmountBes = sumAmountBes;
    }
}
