package com.igor.bo;


import com.igor.model.CapitalizationType;
import com.igor.model.Currency;
import com.igor.model.ReplenishmentType;
import com.igor.model.TermType;
import com.igor.po.DepositPO;
import com.igor.utils.MoneyUtil;
import org.joda.time.DateTime;

import java.math.BigDecimal;

import static com.igor.utils.DateUtil.getFormatter;

public class DepositBO {
    private DepositPO depositPO;
    private Currency currency;
    private BigDecimal initialSum;
    private BigDecimal interestRate;
    private DateTime startDate;
    private int term;
    private TermType termType;
    private BigDecimal regularReplenishmentSum;
    private ReplenishmentType replenishmentType;
    private CapitalizationType capitalizationType;

    public DepositBO() {
        depositPO = new DepositPO();
    }

    public void setMainDepositInfo(Currency currency, BigDecimal sum, BigDecimal interestRate) {
        depositPO.setCurrency(currency.getCurrency())
                .setSum(sum)
                .setInterestRate(interestRate);
        this.currency = currency;
        this.initialSum = sum;
        this.interestRate = interestRate;
    }

    public void setDateAndTerms(DateTime date, Integer term, TermType termType) {
        depositPO.setStartDate(getFormatter().print(date))
                .setTerm(term)
                .setTermType(termType.getType());
        this.startDate = date;
        this.term = term;
        this.termType = termType;
    }

    public void setAdditionalInfo(BigDecimal sumRegularReplenishment, ReplenishmentType typeReplenishment, CapitalizationType typeOfCapitalization) {
        depositPO.setSumOfRegularReplenishment(sumRegularReplenishment)
                .setReplenishment(typeReplenishment.getType())
                .setTypeOfCapitalization(typeOfCapitalization.getType());
        this.regularReplenishmentSum = sumRegularReplenishment;
        this.replenishmentType = typeReplenishment;
        this.capitalizationType = typeOfCapitalization;
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

    public BigDecimal getInitialSum() {
        return initialSum;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public int getTerm() {
        return term;
    }

    public TermType getTermType() {
        return termType;
    }

    public BigDecimal getRegularReplenishmentSum() {
        return regularReplenishmentSum;
    }

    public ReplenishmentType getReplenishmentType() {
        return replenishmentType;
    }

    public CapitalizationType getCapitalizationType() {
        return capitalizationType;
    }

    public Currency getCurrency() {
        return currency;
    }
}
