package com.igor.assertion;

import com.igor.bo.DepositBO;
import com.igor.model.CapitalizationType;
import com.igor.model.ReplenishmentType;
import com.igor.model.TermType;
import com.igor.utils.MoneyUtil;
import org.joda.time.DateTime;

import java.math.BigDecimal;

import static com.igor.utils.DepositUtil.getFinalSum;
import static com.igor.utils.DepositUtil.getFinalSumOld;

public class DepositAssertion {
    public static void assertExchangeRate(DepositBO depositBO) {
        BigDecimal amountInSelectedCurrency = depositBO.getInitialSumInSelectedCurrency();
        BigDecimal amountInUAH = depositBO.getInitialSumInUAH();
        BigDecimal rate = depositBO.getRate();
        PriceAssertion.assertPrice(MoneyUtil.convertMoney(amountInSelectedCurrency, rate, 2), amountInUAH, "Rate for initial sum is different");

        amountInSelectedCurrency = depositBO.getSumOfPercentsInSelectedCurrency();
        amountInUAH = depositBO.getSumOfPercentInUAH();
        PriceAssertion.assertPrice(MoneyUtil.convertMoney(amountInSelectedCurrency, rate, 1), MoneyUtil.roundDown(amountInUAH, 1), "Rate for initial sum is different");
    }

    public static void assertTotal(DepositBO depositBO) {
        BigDecimal initialSum = depositBO.getInitialSum();
        BigDecimal finalSum = depositBO.getFinalSum();
        BigDecimal interestRate = depositBO.getInterestRate().divide(BigDecimal.valueOf(100.0), 10, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal replenishmentSum = depositBO.getRegularReplenishmentSum();
        DateTime startDate = depositBO.getStartDate();
        DateTime finishDate = depositBO.getFinishDate();

        CapitalizationType capitalizationType = depositBO.getCapitalizationType();
        ReplenishmentType replenishmentType = depositBO.getReplenishmentType();

        BigDecimal expectedFinalSum = getFinalSum(initialSum, interestRate, startDate, finishDate, capitalizationType, replenishmentType, replenishmentSum);

        PriceAssertion.assertPrice(MoneyUtil.roundDown(finalSum, 1), MoneyUtil.roundDown(expectedFinalSum, 1), "Final sum is incorrect");
    }

    @Deprecated
    public static void assertTotalOld(DepositBO depositBO) {
        BigDecimal initialSum = depositBO.getInitialSum();
        BigDecimal finalSum = depositBO.getFinalSum();
        BigDecimal interestRate = depositBO.getInterestRate().divide(BigDecimal.valueOf(100.0), 5, BigDecimal.ROUND_HALF_UP);
        BigDecimal replenishmentSum = depositBO.getRegularReplenishmentSum();

        CapitalizationType capitalizationType = depositBO.getCapitalizationType();
        TermType termType = depositBO.getTermType();
        int term = depositBO.getTerm();
        ReplenishmentType replenishmentType = depositBO.getReplenishmentType();


        BigDecimal expectedFinalSum = getFinalSumOld(initialSum, interestRate, termType, term, capitalizationType, replenishmentType, replenishmentSum);

        PriceAssertion.assertPrice(finalSum, expectedFinalSum, "Final sum is incorrect");
    }
}
