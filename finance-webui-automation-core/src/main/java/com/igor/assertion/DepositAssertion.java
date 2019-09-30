package com.igor.assertion;

import com.igor.bo.DepositBO;
import com.igor.utils.MoneyUtil;

import java.math.BigDecimal;

public class DepositAssertion {
    public static void assertExchangeRate(DepositBO depositBO){
        BigDecimal amountInSelectedCurrency = depositBO.getInitialSumInSelectedCurrency();
        BigDecimal amountInUAH = depositBO.getInitialSumInUAH();
        BigDecimal rate = depositBO.getRate();
        PriceAssertion.assertPrice(MoneyUtil.convertMoney(amountInSelectedCurrency, rate, 2), amountInUAH, "Rate for initial sum is different");

        amountInSelectedCurrency = depositBO.getSumOfPercentsInSelectedCurrency();
        amountInUAH = depositBO.getSumOfPercentInUAH();
        PriceAssertion.assertPrice(MoneyUtil.convertMoney(amountInSelectedCurrency, rate, 2), amountInUAH, "Rate for initial sum is different");
    }

    public static void assertTotal(DepositBO depositBO){
        BigDecimal initialSum = depositBO.getInitialSum();
        BigDecimal finalSum = depositBO.getFinalSum();
        BigDecimal interestRate = depositBO.getInterestRate();

//        PriceAssertion.assertPrice();
    }
}
