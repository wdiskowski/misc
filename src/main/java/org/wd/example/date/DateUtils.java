package org.wd.example.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Operations with java-8 Date 
 * @author wd_user
 *
 */
public final class DateUtils {

	private DateUtils() {
	}

	public static boolean isAfter(Instant firstInstant, Instant secondInstant) {
		return firstInstant.isAfter(secondInstant);
	}

	public static Object getDiference(Instant firstInstant,
			Instant secondInstant, ChronoUnit chronoUnit) {
		return secondInstant.until(firstInstant, chronoUnit);
	}

	public static String format(LocalDate date, String pattern) {
		return date.format(DateTimeFormatter.ofPattern(pattern));
	}

}
