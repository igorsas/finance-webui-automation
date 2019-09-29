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
    }
}
