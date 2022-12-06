package com.olmez.mya.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TimePeriod {

	public static final String YESTERDAY = "Yesterday";
	public static final String LAST_WEEK = "Last Week";
	public static final String LAST_MONTH = "Last Month";
	public static final String LAST_QUARTER = "Last Quarter";

	private TimePeriod() {

	}

	public static String getByTime(LocalDateTime time) {
		if (time != null) {
			LocalDate date = time.toLocalDate();
			LocalDate now = LocalDate.now();
			if (date.isAfter(now.minusDays(2))) {
				return YESTERDAY;
			} else if (date.isAfter(now.minusDays(8))) {
				return LAST_WEEK;
			} else if (date.isAfter(now.minusDays(31))) {
				return LAST_MONTH;
			} else if (date.isAfter(now.minusDays(91))) {
				return LAST_QUARTER;
			}
		}
		return null;
	}

	public static LocalDateTime getByString(String label) {
		if (!StringUtility.isEmpty(label)) {
			LocalDate now = LocalDate.now();
			if (label.equals(YESTERDAY)) {
				return now.minusDays(1).atStartOfDay();
			} else if (label.equals(LAST_WEEK)) {
				return now.minusDays(7).atStartOfDay();
			} else if (label.equals(LAST_MONTH)) {
				return now.minusDays(30).atStartOfDay();
			} else if (label.equals(LAST_QUARTER)) {
				return now.minusDays(90).atStartOfDay();
			}
		}
		return null;
	}

	public static List<String> getTimePeriodList() {
		return Arrays.asList(YESTERDAY, LAST_WEEK, LAST_MONTH, LAST_QUARTER);
	}

}
