package org.wd.example.date;

import java.time.Instant;
import java.time.temporal.ChronoField;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void testName() {
		Instant instant = Instant.now();
		System.out.println(instant.get(ChronoField.MILLI_OF_SECOND));
	}
}
