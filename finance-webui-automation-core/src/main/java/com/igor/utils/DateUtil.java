package com.igor.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtil {

    public static DateTimeFormatter getFormatter(){
        return DateTimeFormat.forPattern("dd/MM/yy");
    }

    public static DateTime getDateTimeByMonthAndDay(int month, int day) {
        DateTime currentDateTime = new DateTime();

        return currentDateTime
                .withMonthOfYear(month)
                .withDayOfMonth(day);
    }
}
