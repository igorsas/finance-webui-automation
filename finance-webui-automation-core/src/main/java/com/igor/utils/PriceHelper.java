package com.igor.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PriceHelper {

    public static boolean isPriceZero(BigDecimal value) {
        return isPriceSame(value, BigDecimal.ZERO);
    }

    public static boolean isPriceOverZero(BigDecimal value) {
        return isPriceHigher(value, BigDecimal.ZERO);
    }

    public static boolean isPriceSame(BigDecimal val1, BigDecimal val2) {
        BigDecimal val1Scaled = MoneyUtil.roundValue(convertNullToZeroValue(val1), 2);
        BigDecimal val2Scaled = MoneyUtil.roundValue(convertNullToZeroValue(val2), 2);
        return val1Scaled.compareTo(val2Scaled) == 0;
    }

    static boolean isPriceHigher(BigDecimal val1, BigDecimal val2) {
        BigDecimal val1Scaled = MoneyUtil.roundValue(convertNullToZeroValue(val1), 2);
        BigDecimal val2Scaled = MoneyUtil.roundValue(convertNullToZeroValue(val2), 2);
        return val1Scaled.compareTo(val2Scaled) > 0;
    }

    public static boolean isPriceLower(BigDecimal val1, BigDecimal val2) {
        BigDecimal val1Scaled = MoneyUtil.roundValue(convertNullToZeroValue(val1), 2);
        BigDecimal val2Scaled = MoneyUtil.roundValue(convertNullToZeroValue(val2), 2);
        return val1Scaled.compareTo(val2Scaled) < 0;
    }

    public static BigDecimal convertNullToZeroValue(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }

    public static boolean isPriceIn(BigDecimal val1, List<BigDecimal> val2) {
        BigDecimal val1Scaled = MoneyUtil.roundValue(convertNullToZeroValue(val1), 2);
        List<BigDecimal> val2Scaled = new ArrayList<>();
        for (BigDecimal value : val2) {
            val2Scaled.add(MoneyUtil.roundValue(convertNullToZeroValue(value), 2));
        }
        return val2Scaled.contains(val1Scaled);
    }

    static BigDecimal getPriceDifference(BigDecimal val1, BigDecimal val2) {
        BigDecimal val1Scaled = scaleValue(val1);
        BigDecimal val2Scaled = scaleValue(val2);
        return MoneyUtil.roundValue(val1Scaled.subtract(val2Scaled), 2);
    }

    static BigDecimal scaleValue(BigDecimal val) {
        return MoneyUtil.roundValue(convertNullToZeroValue(val), 2);
    }

    static BigDecimal roundUp(BigDecimal val, int accuracy) {
        return convertNullToZeroValue(val).setScale(accuracy, BigDecimal.ROUND_UP);
    }

}