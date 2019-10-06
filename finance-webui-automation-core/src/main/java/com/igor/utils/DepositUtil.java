package com.igor.utils;

import com.igor.model.CapitalizationType;
import com.igor.model.ReplenishmentType;
import com.igor.model.TermType;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.math.BigDecimal;

import static com.igor.utils.Constants.*;

public class DepositUtil {

    /**
     * method for getting final sum when percent is the same for every day
     *
     * @param initialSum         initial sum of deposit
     * @param interestRate       initial interest rate per year
     * @param startDate          start date of deposit
     * @param finishDate         finish date of deposit
     * @param capitalizationType type of capitalization (monthly, quarterly, yearly, none)
     * @param replenishmentType  type of replenishment (monthly, quarterly, yearly, none)
     * @param replenishmentSum   sum of regular replenishment
     * @return final sum for deposit
     */
    public static BigDecimal getFinalSum(BigDecimal initialSum, BigDecimal interestRate,
                                         DateTime startDate, DateTime finishDate,
                                         CapitalizationType capitalizationType,
                                         ReplenishmentType replenishmentType, BigDecimal replenishmentSum) {
        int termInDays = Days.daysBetween(new LocalDate(startDate), new LocalDate(finishDate)).getDays();
        BigDecimal finalSum;
        if (replenishmentType == ReplenishmentType.MONTHLY) {
            finalSum = getFinalSumByCapitalizationType(initialSum, interestRate, startDate, finishDate, termInDays, capitalizationType, replenishmentSum, MONTH_IN_MONTH);
        } else if (replenishmentType == ReplenishmentType.QUARTERLY) {
            finalSum = getFinalSumByCapitalizationType(initialSum, interestRate, startDate, finishDate, termInDays, capitalizationType, replenishmentSum, MONTH_IN_QUARTER);
        } else if (replenishmentType == ReplenishmentType.YEARLY) {
            finalSum = getFinalSumByCapitalizationType(initialSum, interestRate, startDate, finishDate, termInDays, capitalizationType, replenishmentSum, MONTH_IN_YEAR);
        } else {
            finalSum = getFinalSumByCapitalizationType(initialSum, interestRate, startDate, finishDate, termInDays, capitalizationType, replenishmentSum, termInDays);
        }
        return finalSum;
    }

    private static BigDecimal getFinalSumByCapitalizationType(BigDecimal initialSum,
                                                              BigDecimal interestRate,
                                                              DateTime startDate,
                                                              DateTime finishDate,
                                                              int termInMonth,
                                                              CapitalizationType capitalizationType,
                                                              BigDecimal replenishmentSum,
                                                              int replenishmentPeriodInMonths) {
        BigDecimal finalSum;
        if (capitalizationType == CapitalizationType.MONTHLY) {
            finalSum = getFinalSumByCapitalizationAndReplenishment(initialSum, interestRate, startDate, finishDate, termInMonth, replenishmentSum, replenishmentPeriodInMonths, MONTH_IN_MONTH);
        } else if (capitalizationType == CapitalizationType.QUARTERLY) {
            finalSum = getFinalSumByCapitalizationAndReplenishment(initialSum, interestRate, startDate, finishDate, termInMonth, replenishmentSum, replenishmentPeriodInMonths, MONTH_IN_QUARTER);
        } else if (capitalizationType == CapitalizationType.YEARLY) {
            finalSum = getFinalSumByCapitalizationAndReplenishment(initialSum, interestRate, startDate, finishDate, termInMonth, replenishmentSum, replenishmentPeriodInMonths, MONTH_IN_YEAR);
        } else {
            finalSum = getFinalSumByCapitalizationAndReplenishment(initialSum, interestRate, startDate, finishDate, termInMonth, replenishmentSum, replenishmentPeriodInMonths, termInMonth);
        }
        return finalSum;
    }

    private static BigDecimal getFinalSumByCapitalizationAndReplenishment(BigDecimal initialSum,
                                                                          BigDecimal interestRate,
                                                                          DateTime startDate,
                                                                          DateTime finishDate,
                                                                          int termInDays,
                                                                          BigDecimal replenishmentSum,
                                                                          int replenishmentPeriodInMonths,
                                                                          int capitalizationPeriodInMonths) {
        BigDecimal finalSum = initialSum;
        DateTime currentDate = startDate;
        int daysInYear = 365;
        BigDecimal interestRatePerDay = interestRate.divide(BigDecimal.valueOf(daysInYear), 10, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal currentRate = BigDecimal.valueOf(0.0);

        int daysInCurrentCapitalizationPeriod = Days.daysBetween(new LocalDate(currentDate),
                new LocalDate(currentDate.plusMonths(capitalizationPeriodInMonths))).getDays();

        int daysInCurrentReplenishmentPeriod = Days.daysBetween(new LocalDate(currentDate),
                new LocalDate(currentDate.plusMonths(replenishmentPeriodInMonths))).getDays();

        for (int i = 0, currentCapitalizationDay = 0, currentReplenishmentDay = 0;
             i <= termInDays;
             i++, currentCapitalizationDay++, currentReplenishmentDay++) {

            if (currentCapitalizationDay == daysInCurrentCapitalizationPeriod) {
                currentCapitalizationDay = 0;
                daysInCurrentCapitalizationPeriod = Days.daysBetween(new LocalDate(currentDate),
                        new LocalDate(currentDate.plusMonths(capitalizationPeriodInMonths))).getDays();
                finalSum = MoneyUtil.roundValue(finalSum.add(finalSum.multiply(currentRate)), 2);
                currentRate = BigDecimal.valueOf(0.0);
            }

            if (currentReplenishmentDay == daysInCurrentReplenishmentPeriod) {
                if (currentDate.equals(finishDate)) {
                    break;
                }
                currentReplenishmentDay = 0;
                daysInCurrentReplenishmentPeriod = Days.daysBetween(new LocalDate(currentDate),
                        new LocalDate(currentDate.plusMonths(replenishmentPeriodInMonths))).getDays();
                finalSum = finalSum.add(replenishmentSum);
            }

            currentDate = currentDate.plusDays(1);
            currentRate = currentRate.add(interestRatePerDay);
        }

        return finalSum;
    }

    @Deprecated
    /**
     * method for getting final sum when percent is the same for every month
     */
    public static BigDecimal getFinalSumOld(BigDecimal initialSum, BigDecimal interestRate,
                                            TermType termType, int term, CapitalizationType capitalizationType,
                                            ReplenishmentType replenishmentType, BigDecimal replenishmentSum) {
        term = termType == TermType.YEARS ? term * MONTH_IN_YEAR : term;
        BigDecimal finalSum;
        if (replenishmentType == ReplenishmentType.MONTHLY) {
            finalSum = getFinalSumByCapitalizationTypeOld(initialSum, interestRate, term, capitalizationType, replenishmentSum, MONTH_IN_MONTH);
        } else if (replenishmentType == ReplenishmentType.QUARTERLY) {
            finalSum = getFinalSumByCapitalizationTypeOld(initialSum, interestRate, term, capitalizationType, replenishmentSum, MONTH_IN_QUARTER);
        } else if (replenishmentType == ReplenishmentType.YEARLY) {
            finalSum = getFinalSumByCapitalizationTypeOld(initialSum, interestRate, term, capitalizationType, replenishmentSum, MONTH_IN_YEAR);
        } else {
            finalSum = getFinalSumByCapitalizationTypeOld(initialSum, interestRate, term, capitalizationType, replenishmentSum, term);
        }
        return finalSum;
    }

    private static BigDecimal getFinalSumByCapitalizationTypeOld(BigDecimal initialSum,
                                                                 BigDecimal interestRate,
                                                                 int termInMonth,
                                                                 CapitalizationType capitalizationType,
                                                                 BigDecimal replenishmentSum,
                                                                 int replenishmentPeriodInMonths) {
        BigDecimal finalSum;
        if (capitalizationType == CapitalizationType.MONTHLY) {
            interestRate = interestRate.divide(BigDecimal.valueOf(MONTH_IN_YEAR), 5, BigDecimal.ROUND_HALF_UP);
            finalSum = getFinalSumByCapitalizationAndReplenishmentOld(initialSum, interestRate, termInMonth, replenishmentSum, replenishmentPeriodInMonths, MONTH_IN_MONTH);
        } else if (capitalizationType == CapitalizationType.QUARTERLY) {
            interestRate = interestRate.divide(BigDecimal.valueOf(QUARTER_IN_YEAR), 5, BigDecimal.ROUND_HALF_UP);
            finalSum = getFinalSumByCapitalizationAndReplenishmentOld(initialSum, interestRate, termInMonth, replenishmentSum, replenishmentPeriodInMonths, MONTH_IN_QUARTER);
        } else if (capitalizationType == CapitalizationType.YEARLY) {
            finalSum = getFinalSumByCapitalizationAndReplenishmentOld(initialSum, interestRate, termInMonth, replenishmentSum, replenishmentPeriodInMonths, MONTH_IN_YEAR);
        } else {
            finalSum = getFinalSumByCapitalizationAndReplenishmentOld(initialSum, interestRate, termInMonth, replenishmentSum, replenishmentPeriodInMonths, termInMonth);
        }
        return finalSum;
    }

    private static BigDecimal getFinalSumByCapitalizationAndReplenishmentOld(BigDecimal initialSum,
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
