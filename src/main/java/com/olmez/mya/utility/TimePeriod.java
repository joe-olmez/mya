package com.olmez.mya.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;

public enum TimePeriod {
    YESTERDAY("Yesterday"),
    LAST_WEEK("Last Week"),
    LAST_MONTH("Last Month"),
    LAST_QUARTER("Last Quarter"),
    LAST_YEAR("Last Year");

    private String name;

    TimePeriod(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static TimePeriod getByTime(LocalDateTime time) {
        if (time == null) {
            return null;
        }
        LocalDate date = time.toLocalDate();
        if (isYesterday(date)) {
            return YESTERDAY;
        } else if (isLastWeek(date)) {
            return LAST_WEEK;
        } else if (isLastMonth(date)) {
            return LAST_MONTH;
        } else if (isLastQuarter(date)) {
            return LAST_QUARTER;
        } else if (isLastYear(date)) {
            return LAST_YEAR;
        }
        return null;
    }

    public static LocalDateTime getTimeByString(String period) {
        if (!StringUtility.isEmpty(period)) {
            LocalDate now = LocalDate.now();
            if (period.equalsIgnoreCase(YESTERDAY.getName())) {
                return now.minusDays(1).atStartOfDay();
            } else if (period.equalsIgnoreCase(LAST_WEEK.getName())) {
                return now.minusWeeks(1).atStartOfDay();
            } else if (period.equalsIgnoreCase(LAST_MONTH.getName())) {
                return now.minusMonths(1).atStartOfDay();
            } else if (period.equalsIgnoreCase(LAST_QUARTER.getName())) {
                return now.minusMonths(3).atStartOfDay();
            } else if (period.equalsIgnoreCase(LAST_YEAR.getName())) {
                return now.minusYears(1).atStartOfDay();
            }
        }
        return null;
    }

    public static LocalDateTime getTimeByTimePeriod(TimePeriod period) {
        if (period == null) {
            return null;
        }

        LocalDate now = LocalDate.now();
        if (period == YESTERDAY) {
            return now.minusDays(1).atStartOfDay();
        } else if (period == LAST_WEEK) {
            return now.minusWeeks(1).atStartOfDay();
        } else if (period == LAST_MONTH) {
            return now.minusMonths(1).atStartOfDay();
        } else if (period == LAST_QUARTER) {
            return now.minusMonths(3).atStartOfDay();
        } else if (period == LAST_YEAR) {
            return now.minusYears(1).atStartOfDay();
        }
        return null;
    }

    private static boolean isYesterday(LocalDate date) {
        return date.isAfter(LocalDate.now().minusDays(2));
    }

    private static boolean isLastWeek(LocalDate date) {
        return date.isAfter(LocalDate.now().minusDays(8));
    }

    private static boolean isLastMonth(LocalDate date) {
        return date.isAfter(LocalDate.now().minusDays(31));
    }

    private static boolean isLastQuarter(LocalDate date) {
        return date.isAfter(LocalDate.now().minusDays(91));
    }

    private static boolean isLastYear(LocalDate date) {
        return date.isAfter(LocalDate.now().minusDays(366));
    }

}
