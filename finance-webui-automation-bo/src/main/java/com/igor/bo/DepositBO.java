package com.igor.bo;

import com.igor.po.DepositPO;

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
}
