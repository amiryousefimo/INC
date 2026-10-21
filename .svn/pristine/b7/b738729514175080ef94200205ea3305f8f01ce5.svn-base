package ir.tamin.incomeBank.model.commonList;

import java.math.BigDecimal;

public class CommonListSumAmount {


    private BigDecimal sumSoratPrice1;
    private BigDecimal sumSoratPrice2;
    private Long countOrdPay;

    private BigDecimal remainAmount;

    public CommonListSumAmount() {
    }


    public CommonListSumAmount(BigDecimal sumSoratPrice1, BigDecimal sumSoratPrice2, Long countOrdPay) {
        this.sumSoratPrice1 = sumSoratPrice1;
        this.sumSoratPrice2 = sumSoratPrice2;
        this.countOrdPay = countOrdPay;
    }

    public BigDecimal getSumSoratPrice1() {
        return sumSoratPrice1;
    }

    public void setSumSoratPrice1(BigDecimal sumSoratPrice1) {
        this.sumSoratPrice1 = sumSoratPrice1;
    }

    public BigDecimal getSumSoratPrice2() {
        return sumSoratPrice2;
    }

    public void setSumSoratPrice2(BigDecimal sumSoratPrice2) {
        this.sumSoratPrice2 = sumSoratPrice2;
    }

    public Long getCountOrdPay() {
        return countOrdPay;
    }

    public void setCountOrdPay(Long countOrdPay) {
        this.countOrdPay = countOrdPay;
    }

    public BigDecimal getRemainAmount() {
        if (sumSoratPrice2 != null || sumSoratPrice1 != null) {
            return sumSoratPrice1.add(sumSoratPrice2.negate());
        }
        return BigDecimal.valueOf(0);
    }

    public void setRemainAmount(BigDecimal remainAmount) {
        this.remainAmount = remainAmount;
    }
}
