package org.wd.example.date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class DateUtilsTest {

	private static final String GERMAN_DATE_FORMAT = "dd.MM.yyyy";
	private static final Instant FIRST_INSTANT = Instant.now();
	private static final long SOME_DURATION = 10L;

	@Test
	public void isAfterPositiveTest() {
		Instant secondInstant = FIRST_INSTANT.plusSeconds(SOME_DURATION);
		assertThat(DateUtils.isAfter(secondInstant, FIRST_INSTANT),
				equalTo(true));
	}

	@Test
	public void isAfterNegativeTest() {
		Instant secondInstant = FIRST_INSTANT.plusSeconds(-SOME_DURATION);
		assertThat(DateUtils.isAfter(secondInstant, FIRST_INSTANT),
				equalTo(false));
	}

	@Test
	public void getDiferenceSecondsTest() {
		Instant secondInstant = FIRST_INSTANT.plusSeconds(SOME_DURATION);
		assertThat(DateUtils.getDiference(secondInstant, FIRST_INSTANT,
				ChronoUnit.SECONDS), equalTo(SOME_DURATION));
	}

	@Test
	public void formatTest() {
		LocalDate date = LocalDate.now();
		String sollFormatedDate = String.format("%02d.%02d.%04d",
				date.getDayOfMonth(), date.getMonth().getValue(),
				date.getYear());
		assertThat(DateUtils.format(date, GERMAN_DATE_FORMAT),
				equalTo(sollFormatedDate));
	}

}
