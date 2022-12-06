package com.olmez.mya.utility;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtility {

	public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
	public static final String SHORT_DATETIME_START_MONTH = "MMM d, yyyy HH:mm";
	public static final String DATETIME_START_MONTH = "MMM d, yyyy hh:mm a";
	public static final String DATETIME_WITH_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ssZ";
	public static final String YEAR_MONTH_DAY_DATETIME = "yyyy-MM-dd";

	public static final String YEAR_MONTH_DATETIME = "yyyy-MM";
	public static final String DATE_WITH_DAY_OF_WEEK = "EEE dd, MMM yyyy";
	public static final String DATE_WITH_DAY_OF_WEEK_NO_LEADING_ZERO = "EEE M/d/yy";
	public static final String DATE_WITH_TIMEZONE = "yyyy-MM-ddZ";
	public static final String DATE_SHORT = "MMM d, yyyy";

	public static final String DEFAULT_ZONE_ID_KEY = "America/Toronto";
	public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of(DEFAULT_ZONE_ID_KEY);

	/**
	 * formats the time as "2021-08-17 15:30".
	 */
	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	/**
	 * formats the date as "Aug 17, 2021".
	 */
	public static final DateTimeFormatter DATE_FORMATTER_START_MONTH = DateTimeFormatter.ofPattern("MMM dd, yyyy");
	/**
	 * formats the time as "2021-08-17 03:30 PM".
	 */
	public static final DateTimeFormatter DATE_TIME_AM_PM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a",
			Locale.US);
	/**
	 * formats the time as "03:30 PM".
	 */
	public static final DateTimeFormatter TIME_AM_PM_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a", Locale.US);

	/**
	 * formats the date as "20210817".
	 */
	public static final DateTimeFormatter DATE_FORMATTER_YEAR_MONTH_DAY = DateTimeFormatter.ofPattern("yyyyMMdd");

	/**
	 * formats the date as "2021-08-17".
	 */
	public static final DateTimeFormatter DATE_FORMATTER_YEAR_MONTH_DAY_WITH_DASH = DateTimeFormatter
			.ofPattern(YEAR_MONTH_DAY_DATETIME);

	/**
	 * formats the time as "December 20, 2021 03:45 PM"
	 */
	public static final DateTimeFormatter DATE_TIME_LONG_MONTH_FORMATTER = DateTimeFormatter
			.ofPattern("MMMM dd, yyyy hh:mm a", Locale.US);

	public static LocalDateTime roundLocalDateTime(LocalDateTime time, int intervalMinute) {
		return (time != null) ? time.minusMinutes(time.getMinute() % intervalMinute) : null;
	}

	public static LocalDateTime createDateTime(int year, int month, int day) {
		return createDateTime(year, month, day, 0, 0);
	}

	public static LocalDateTime createDateTime(int year, int month, int day, int hour, int min) {
		return createDateTime(year, month, day, hour, min, 0);
	}

	public static LocalDateTime createDateTime(int year, int month, int day, int hour, int minute, int second) {
		return LocalDateTime.of(year, month, day, hour, minute, second);
	}

	public static Instant createInstant(int year, int month, int day) {
		return createInstant(year, month, day, 0, 0);
	}

	public static Instant createInstant(int year, int month, int day, int hour, int min) {
		return createInstant(year, month, day, hour, min, 0);
	}

	public static Instant createInstant(int year, int month, int day, int hour, int minute, int second) {
		return ZonedDateTime.of(year, month, day, hour, minute, second, 0, ZoneOffset.UTC).toInstant();
	}

	public static LocalDateTime toLocalDateTime(Calendar calendar) {
		return calendar != null ? LocalDateTime.ofInstant(calendar.toInstant(), ZoneOffset.UTC) : null;
	}

	public static LocalDate toLocalDate(Calendar calendar) {
		return calendar != null ? toLocalDateTime(calendar).toLocalDate() : null;
	}

	public static LocalTime toLocalTime(Calendar calendar) {
		return calendar != null ? toLocalDateTime(calendar).toLocalTime() : null;
	}

	public static LocalDateTime toLocalDateTime(Date date) {
		return date != null ? LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.UTC) : null;
	}

	public static LocalDate toLocalDate(Date date) {
		return date != null ? toLocalDateTime(date).toLocalDate() : null;
	}

	public static LocalTime toLocalTime(Date date) {
		return date != null ? toLocalDateTime(date).toLocalTime() : null;
	}

	public static Calendar toCalendar(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(localDateTime.getYear(), localDateTime.getMonthValue() - 1, localDateTime.getDayOfMonth(),
				localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond());
		calendar.set(Calendar.MILLISECOND, localDateTime.getNano());
		return calendar;
	}

	public static Calendar toCalendar(LocalTime localTime) {
		return localTime != null ? toCalendar(localTime.atDate(LocalDate.of(1970, 1, 1))) : null;
	}

	public static Calendar toCalendar(LocalDate localDate) {
		return localDate != null ? toCalendar(localDate.atStartOfDay()) : null;
	}

	public static Date toDate(LocalTime localTime, int year, int month, int day) {
		return localTime != null ? toDate(localTime.atDate(LocalDate.of(year, month, day))) : null;
	}

	public static Date toDate(LocalTime localTime) {
		return toDate(localTime, 1970, 1, 1);
	}

	public static Date toDate(LocalDate localDate) {
		return localDate != null ? toDate(localDate.atStartOfDay()) : null;
	}

	public static Date toDate(LocalDateTime localDateTime) {
		return toDate(localDateTime, ZoneOffset.UTC);
	}

	public static Date toDate(LocalTime localTime, int year, int month, int day, ZoneOffset zoneOffset) {
		return localTime != null ? toDate(localTime.atDate(LocalDate.of(year, month, day)), zoneOffset) : null;
	}

	public static Date toDate(LocalTime localTime, int year, int month, int day, ZoneId zoneId) {
		return localTime != null ? toDate(localTime.atDate(LocalDate.of(year, month, day)), zoneId) : null;
	}

	public static Date toDate(LocalDate localDate, ZoneOffset zoneOffset) {
		return localDate != null ? toDate(localDate.atStartOfDay(), zoneOffset) : null;
	}

	public static Date toDate(LocalDate localDate, ZoneId zoneId) {
		return localDate != null ? toDate(localDate.atStartOfDay(), zoneId) : null;
	}

	public static Date toDate(LocalDateTime localDateTime, ZoneId zoneId) {
		return localDateTime != null ? toDate(localDateTime.atZone(zoneId)) : null;
	}

	public static Date toDate(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
		return localDateTime != null ? toDate(localDateTime.atOffset(zoneOffset)) : null;
	}

	public static Date toDate(OffsetDateTime offsetDateTime) {
		return offsetDateTime != null ? Date.from(offsetDateTime.toInstant()) : null;
	}

	public static Date toDate(ZonedDateTime zonedDateTime) {
		return zonedDateTime != null ? Date.from(zonedDateTime.toInstant()) : null;
	}

	public static boolean isBetweenDateRange(LocalDateTime date, LocalDateTime start, LocalDateTime end) {
		// Checking with '!' to not use equals and make it complicated
		return !date.isBefore(start) && !date.isAfter(end);
	}

	public static boolean isBetweenDateRange(LocalDate date, LocalDate startIncluded, LocalDate endIncluded) {
		return !date.isBefore(startIncluded) && !date.isAfter(endIncluded);
	}

	public static boolean isBetweenDateRange(LocalDateTime dateTime, LocalDate startIncluded, LocalDate endIncluded) {
		if (dateTime == null) {
			return false;
		}
		return !dateTime.toLocalDate().isBefore(startIncluded) && !dateTime.toLocalDate().isAfter(endIncluded);
	}

	/**
	 * Combines the given date with {@link LocalTime#MAX} to create a
	 * {@link LocalDateTime} at the end of the given date
	 *
	 * @param date
	 * @return a {@link LocalDateTime} at the end of the given date
	 */
	public static LocalDateTime atEndOfDay(LocalDate date) {
		return LocalDateTime.of(date, LocalTime.MAX);
	}

	public static List<String> getTimeZoneList() {
		var list = new ArrayList<>(ZoneId.getAvailableZoneIds());
		Collections.sort(list);
		return list;
	}

	public static LocalDate getLastDayOfMonth(LocalDate date) {
		return YearMonth.from(date).atEndOfMonth();
	}

	public static LocalDate getLastDayOfMonth(LocalDateTime time) {
		return YearMonth.from(time).atEndOfMonth();
	}

	public static long toEpochMilliSecond(LocalDate date, ZoneId zoneId) {
		if (zoneId == null) {
			return toEpochMilliSecond(date);
		}
		Instant instant = date.atStartOfDay(zoneId).toInstant();
		return instant.toEpochMilli();
	}

	public static long toEpochMilliSecond(LocalDate date) {
		Instant instant = date.atStartOfDay(DateUtility.DEFAULT_ZONE_ID).toInstant();
		return instant.toEpochMilli();
	}

	public static LocalDateTime toLocalDateTime(long timeInMillis, ZoneId zoneId) {
		if (zoneId == null) {
			return toLocalDateTime(timeInMillis);
		}
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeInMillis), zoneId);
	}

	public static LocalDateTime toLocalDateTime(long timeInMillis) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeInMillis), DateUtility.DEFAULT_ZONE_ID);
	}

	public static long toEpochMilliSecond(LocalDateTime time, ZoneId zoneId) {
		if (zoneId == null) {
			return toEpochMilliSecond(time);
		}
		Instant instant = time.atZone(DateUtility.DEFAULT_ZONE_ID).toInstant();
		return instant.toEpochMilli();
	}

	public static long toEpochMilliSecond(LocalDateTime time) {
		Instant instant = time.atZone(DateUtility.DEFAULT_ZONE_ID).toInstant();
		return instant.toEpochMilli();
	}

}
