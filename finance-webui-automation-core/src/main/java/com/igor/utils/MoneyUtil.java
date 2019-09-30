package com.igor.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyUtil {
    public static BigDecimal convertMoney(BigDecimal sum, BigDecimal rate, int scale) {
        BigDecimal notRoundedValue = rate.multiply(sum);
        return roundUp(notRoundedValue, scale);
    }

    public static String clearTextForConverting(String text) {
        return text.replaceAll("[ ]", "")
                .replaceAll("[,]", ".");
    }

    public static BigDecimal roundValue(BigDecimal value, int accuracy) {
        return value.setScale(accuracy, BigDecimal.ROUND_HALF_UP);
    }

    private static BigDecimal roundUp(BigDecimal value, int accuracy){
        return value.setScale(accuracy, RoundingMode.UP);
    }
}
