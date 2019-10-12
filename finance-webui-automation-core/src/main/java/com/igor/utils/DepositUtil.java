package com.igor.utils;

import com.igor.model.daoModel.ReplenishmentType;
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
                                         ReplenishmentType capitalizationType,
                                         ReplenishmentType replenishmentType, BigDecimal replenishmentSum) {
        int termInDays = Days.daysBetween(new LocalDate(startDate), new LocalDate(finishDate)).getDays();
        BigDecimal finalSum;
        if (replenishmentType.getType().equals(MONTHLY_STR)) {
            finalSum = getFinalSumByCapitalizationType(initialSum, interestRate, startDate, finishDate, termInDays, capitalizationType, replenishmentSum, MONTH_IN_MONTH);
        } else if (replenishmentType.getType().equals(QUARTERLY_STR)) {
            finalSum = getFinalSumByCapitalizationType(initialSum, interestRate, startDate, finishDate, termInDays, capitalizationType, replenishmentSum, MONTH_IN_QUARTER);
        } else if (replenishmentType.getType().equals(YEARLY_STR)) {
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
                                                              ReplenishmentType capitalizationType,
                                                              BigDecimal replenishmentSum,
                                                              int replenishmentPeriodInMonths) {
        BigDecimal finalSum;
        if (capitalizationType.getType().equals(MONTHLY_STR)) {
            finalSum = getFinalSumByCapitalizationAndReplenishment(initialSum, interestRate, startDate, finishDate, termInMonth, replenishmentSum, replenishmentPeriodInMonths, MONTH_IN_MONTH);
        } else if (capitalizationType.getType().equals(QUARTERLY_STR)) {
            finalSum = getFinalSumByCapitalizationAndReplenishment(initialSum, interestRate, startDate, finishDate, termInMonth, replenishmentSum, replenishmentPeriodInMonths, MONTH_IN_QUARTER);
        } else if (capitalizationType.getType().equals(YEARLY_STR)) {
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
                                            int term, ReplenishmentType capitalizationType,
                                            ReplenishmentType replenishmentType, BigDecimal replenishmentSum) {
        BigDecimal finalSum;
        if (replenishmentType.getType().equals(MONTHLY_STR)) {
            finalSum = getFinalSumByCapitalizationTypeOld(initialSum, interestRate, term, capitalizationType, replenishmentSum, MONTH_IN_MONTH);
        } else if (replenishmentType.getType().equals(QUARTERLY_STR)) {
            finalSum = getFinalSumByCapitalizationTypeOld(initialSum, interestRate, term, capitalizationType, replenishmentSum, MONTH_IN_QUARTER);
        } else if (replenishmentType.getType().equals(YEARLY_STR)) {
            finalSum = getFinalSumByCapitalizationTypeOld(initialSum, interestRate, term, capitalizationType, replenishmentSum, MONTH_IN_YEAR);
        } else {
            finalSum = getFinalSumByCapitalizationTypeOld(initialSum, interestRate, term, capitalizationType, replenishmentSum, term);
        }
        return finalSum;
    }

    private static BigDecimal getFinalSumByCapitalizationTypeOld(BigDecimal initialSum,
                                                                 BigDecimal interestRate,
                                                                 int termInMonth,
                                                                 ReplenishmentType capitalizationType,
                                                                 BigDecimal replenishmentSum,
                                                                 int replenishmentPeriodInMonths) {
        BigDecimal finalSum;
        if (capitalizationType.getType().equals(MONTHLY_STR)) {
            interestRate = interestRate.divide(BigDecimal.valueOf(MONTH_IN_YEAR), 5, BigDecimal.ROUND_HALF_UP);
            finalSum = getFinalSumByCapitalizationAndReplenishmentOld(initialSum, interestRate, termInMonth, replenishmentSum, replenishmentPeriodInMonths, MONTH_IN_MONTH);
        } else if (capitalizationType.getType().equals(QUARTERLY_STR)) {
            interestRate = interestRate.divide(BigDecimal.valueOf(QUARTER_IN_YEAR), 5, BigDecimal.ROUND_HALF_UP);
            finalSum = getFinalSumByCapitalizationAndReplenishmentOld(initialSum, interestRate, termInMonth, replenishmentSum, replenishmentPeriodInMonths, MONTH_IN_QUARTER);
        } else if (capitalizationType.getType().equals(YEARLY_STR)) {
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
