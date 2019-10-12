package com.igor.bo;


import com.igor.model.daoModel.Currency;
import com.igor.model.daoModel.ReplenishmentType;
import com.igor.model.daoModel.Term;
import com.igor.po.DepositPO;
import com.igor.utils.MoneyUtil;
import org.joda.time.DateTime;

import java.math.BigDecimal;

import static com.igor.utils.Constants.MONTH_IN_YEAR;
import static com.igor.utils.Constants.YEARS_STR;
import static com.igor.utils.DateUtil.getFormatter;

public class DepositBO {
    private DepositPO depositPO;
    private Currency currency;
    private BigDecimal initialSum;
    private BigDecimal interestRate;
    private DateTime startDate;
    private DateTime finishDate;
    private int term;
    private String termType;
    private BigDecimal regularReplenishmentSum;
    private ReplenishmentType replenishmentType;
    private ReplenishmentType capitalizationType;

    public DepositBO() {
        depositPO = new DepositPO();
    }

    public void setMainDepositInfo(Currency currency, BigDecimal sum, BigDecimal interestRate) {
        depositPO.setCurrency(currency.getCode())
                .setSum(sum)
                .setInterestRate(interestRate);
        this.currency = currency;
        this.initialSum = sum;
        this.interestRate = interestRate;
    }

    public void setDateAndTerms(Term termModel) {
        this.startDate = new DateTime(termModel.getStartDate().getTime());
        this.term = termModel.getPeriod();
        this.termType = termModel.getPeriodType();
        depositPO.setStartDate(getFormatter().print(startDate))
                .setTerm(term)
                .setTermType(termType);
        term = termType.equals(YEARS_STR) ? term * MONTH_IN_YEAR : term;
        this.finishDate = this.startDate.plusMonths(term);
    }

    public void setAdditionalInfo(BigDecimal sumRegularReplenishment, ReplenishmentType typeReplenishment, ReplenishmentType typeOfCapitalization) {
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

    public DateTime getFinishDate() {
        return finishDate;
    }

    public int getTerm() {
        return term;
    }

    public String getTermType() {
        return termType;
    }

    public BigDecimal getRegularReplenishmentSum() {
        return regularReplenishmentSum;
    }

    public ReplenishmentType getReplenishmentType() {
        return replenishmentType;
    }

    public ReplenishmentType getCapitalizationType() {
        return capitalizationType;
    }

    public Currency getCurrency() {
        return currency;
    }
}
