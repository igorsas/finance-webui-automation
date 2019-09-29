package com.igor.bo;


import com.igor.po.DepositPO;
import com.igor.utils.MoneyUtil;

import java.math.BigDecimal;

public class DepositBO {
    private DepositPO depositPO;

    public DepositBO() {
        depositPO = new DepositPO();
    }

    public void setMainDepositInfo(String currency, BigDecimal sum, BigDecimal interestRate) {
        depositPO.setCurrency(currency)
                .setSum(sum)
                .setInterestRate(interestRate);
    }

    public void setDateAndTerms(String date, Integer term, String termType) {
        depositPO.setStartDate(date)
                .setTerm(term)
                .setTermType(termType);
    }

    public void setAdditionalInfo(BigDecimal sumRegularReplenishment, String typeReplenishment, String typeOfCapitalization) {
        depositPO.setSumOfRegularReplenishment(sumRegularReplenishment)
                .setReplenishment(typeReplenishment)
                .setTypeOfCapitalization(typeOfCapitalization);
    }

    public void submit() {
        depositPO.calculate();
    }

    public BigDecimal getInitialSumInSelectedCurrency() {
        return depositPO.getInitialSumInSelectedCurrency();
    }

    public BigDecimal getInitialSumInUAH() {
        return depositPO.getInitialSumInUAH();
    }

    public BigDecimal getSumOfPercentsInSelectedCurrency() {
        return depositPO.getSumOfPercentsInSelectedCurrency();
    }

    public BigDecimal getSumOfPercentInUAH() {
        return depositPO.getSumOfPercentInUAH();
    }

    public BigDecimal getFinalSum() {
        return depositPO.getFinalSum();
    }

    public BigDecimal getRate() {
        String rateInfo = depositPO.getInfoAboutRate();
        rateInfo = rateInfo.substring(rateInfo.indexOf('('), rateInfo.indexOf(')'));
        rateInfo = rateInfo.replaceAll("[A-Z()]", "");
        return new BigDecimal(MoneyUtil.clearTextForConverting(rateInfo));
    }
}
