package com.igor.assertion;

import com.igor.utils.MoneyUtil;
import org.testng.Reporter;
import org.testng.asserts.Assertion;

import java.math.BigDecimal;
import java.util.List;

import static com.igor.utils.PriceHelper.*;


public class PriceAssertion {

    private static Assertion assertion;

    public static void assertPriceZero(Object actual, String msg) {
        BigDecimal act = convertToBigDecimal(actual);
        assertPriceZero(act, msg);
    }

    static void assertPriceZero(BigDecimal actual, String msg) {
        assertion = new Assertion();
        assertion.assertTrue(isPriceZero(actual), String.format("%s is NOT Zero, value %s", msg, actual));
    }

    static void assertPriceOverZero(BigDecimal actual, String msg) {
        assertion = new Assertion();
        assertion.assertTrue(isPriceOverZero(actual), String.format("%s is Zero or Less, value %s", msg, actual));
    }

    static void assertPriceLower(BigDecimal actual, BigDecimal max, String msg) {
        assertion = new Assertion();
        assertion.assertTrue(isPriceLower(actual, max), String.format("%s actual='%s' is not lower then max='%s'", msg, actual, max));
    }

    static void assertPriceIn(BigDecimal actual, List<BigDecimal> expected, String msg) {
        assertion = new Assertion();
        assertion.assertTrue(isPriceIn(actual, expected), String.format("%s is %s, expect %s", msg, actual, expected));
    }

    static void assertPrice(Object actual, Object expected, String msg) {
        BigDecimal act = convertToBigDecimal(actual);
        BigDecimal exp = convertToBigDecimal(expected);
        assertPrice(act, exp, msg);
    }

    static void assertPrice(BigDecimal actual, BigDecimal expected, String msg) {
        assertion = new Assertion();
        //TODO: should be review accuracy have to be 2
        BigDecimal act = MoneyUtil.roundValue(convertNullToZeroValue(actual), 1);
        BigDecimal exp = MoneyUtil.roundValue(convertNullToZeroValue(expected), 1);
        Reporter.log(String.format("Assert price.\nActual: %s, expected: %s", act, exp));
        assertion.assertTrue(isPriceSame(act, exp), String.format("%s is actual %s, expect %s", msg, actual, expected));
    }

    private static BigDecimal convertToBigDecimal(Object value) {
        try {
            return value == null ? BigDecimal.ZERO : new BigDecimal(String.valueOf(value));
        } catch (Throwable t) {
            throw new AssertionError(String.format("Cannot case value='%s' to BigDecimal", value), t);
        }
    }

}