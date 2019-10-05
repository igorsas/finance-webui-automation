package com.igor.assertion;

import com.igor.bo.DepositBO;
import com.igor.model.CapitalizationType;
import com.igor.model.ReplenishmentType;
import com.igor.model.TermType;
import com.igor.utils.MoneyUtil;

import java.math.BigDecimal;

import static com.igor.utils.Constants.*;

public class DepositAssertion {
    public static void assertExchangeRate(DepositBO depositBO) {
        BigDecimal amountInSelectedCurrency = depositBO.getInitialSumInSelectedCurrency();
        BigDecimal amountInUAH = depositBO.getInitialSumInUAH();
        BigDecimal rate = depositBO.getRate();
        PriceAssertion.assertPrice(MoneyUtil.convertMoney(amountInSelectedCurrency, rate, 2), amountInUAH, "Rate for initial sum is different");

        amountInSelectedCurrency = depositBO.getSumOfPercentsInSelectedCurrency();
        amountInUAH = depositBO.getSumOfPercentInUAH();
        PriceAssertion.assertPrice(MoneyUtil.convertMoney(amountInSelectedCurrency, rate, 2), amountInUAH, "Rate for initial sum is different");
    }

    public static void assertTotal(DepositBO depositBO) {
        BigDecimal initialSum = depositBO.getInitialSum();
        BigDecimal finalSum = depositBO.getFinalSum();
        BigDecimal interestRate = depositBO.getInterestRate().divide(BigDecimal.valueOf(100.0), 5, BigDecimal.ROUND_HALF_UP);
        BigDecimal replenishmentSum = depositBO.getRegularReplenishmentSum();

        CapitalizationType capitalizationType = depositBO.getCapitalizationType();
        TermType termType = depositBO.getTermType();
        int term = depositBO.getTerm();
        ReplenishmentType replenishmentType = depositBO.getReplenishmentType();


        BigDecimal expectedFinalSum = getFinalSum(initialSum, interestRate, termType, term, capitalizationType, replenishmentType, replenishmentSum);

        PriceAssertion.assertPrice(finalSum, expectedFinalSum, "Final sum is incorrect");
    }


    private static BigDecimal getFinalSum(BigDecimal initialSum, BigDecimal interestRate,
                                          TermType termType, int term, CapitalizationType capitalizationType,
                                          ReplenishmentType replenishmentType, BigDecimal replenishmentSum) {
        term = termType == TermType.YEARS ? term * MONTH_IN_YEAR : term;
        BigDecimal finalSum;
        if (replenishmentType == ReplenishmentType.MONTHLY) {
            finalSum = getFinalSumByCapitalizationType(initialSum, interestRate, term, capitalizationType, replenishmentSum, MONTH_IN_MONTH);
        } else if (replenishmentType == ReplenishmentType.QUARTERLY) {
            finalSum = getFinalSumByCapitalizationType(initialSum, interestRate, term, capitalizationType, replenishmentSum, MONTH_IN_QUARTER);
        } else if(replenishmentType == ReplenishmentType.YEARLY){
            finalSum = getFinalSumByCapitalizationType(initialSum, interestRate, term, capitalizationType, replenishmentSum, MONTH_IN_YEAR);
        }else {
            finalSum = getFinalSumByCapitalizationType(initialSum, interestRate, term, capitalizationType, replenishmentSum, term);
        }
        return finalSum;
    }

    private static BigDecimal getFinalSumByCapitalizationType(BigDecimal initialSum,
                                                              BigDecimal interestRate,
                                                              int termInMonth,
                                                              CapitalizationType capitalizationType,
                                                              BigDecimal replenishmentSum,
                                                              int replenishmentPeriodInMonths) {
        BigDecimal finalSum;
        if (capitalizationType == CapitalizationType.MONTHLY) {
            interestRate = interestRate.divide(BigDecimal.valueOf(MONTH_IN_YEAR), 5, BigDecimal.ROUND_HALF_UP);
            finalSum = getFinalSumByCapitalizationAndReplenishment(initialSum, interestRate, termInMonth, replenishmentSum, replenishmentPeriodInMonths, MONTH_IN_MONTH);
        } else if (capitalizationType == CapitalizationType.QUARTERLY) {
            interestRate = interestRate.divide(BigDecimal.valueOf(QUARTER_IN_YEAR), 5, BigDecimal.ROUND_HALF_UP);
            finalSum = getFinalSumByCapitalizationAndReplenishment(initialSum, interestRate, termInMonth, replenishmentSum, replenishmentPeriodInMonths, MONTH_IN_QUARTER);
        } else if (capitalizationType == CapitalizationType.YEARLY) {
            finalSum = getFinalSumByCapitalizationAndReplenishment(initialSum, interestRate, termInMonth, replenishmentSum, replenishmentPeriodInMonths, MONTH_IN_YEAR);
        } else {
            finalSum = getFinalSumByCapitalizationAndReplenishment(initialSum, interestRate, termInMonth, replenishmentSum, replenishmentPeriodInMonths, termInMonth);
        }
        return finalSum;
    }

    private static BigDecimal getFinalSumByCapitalizationAndReplenishment(BigDecimal initialSum,
                                                                          BigDecimal interestRateInPeriod,
                                                                          int termInMonth,
                                                                          BigDecimal replenishmentSum,
                                                                          int replenishmentPeriodInMonths,
                                                                          int capitalizationPeriodInMonths) {
        BigDecimal finalSum = initialSum;

        for (int i = 1; i <= termInMonth; i++) {
            if (i >= capitalizationPeriodInMonths && i % capitalizationPeriodInMonths == 0) {
                finalSum = finalSum.add(finalSum.multiply(interestRateInPeriod));
            }
            if (i >= replenishmentPeriodInMonths && i % replenishmentPeriodInMonths == 0) {
                finalSum = finalSum.add(replenishmentSum);
            }
        }

        return finalSum;
    }
}
