package org.wd.example.lambdas;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;

public class StringTransformerTest {

	private StringTransformer stringTransformer = new StringTransformer();

	@Test
	public void capitalizeTest() {
		List<String> capitalizedWeekdays = new ArrayList<String>();
		List<String> upperCaseWeekdays = new ArrayList<String>();
		LocalDate localDate = LocalDate.now();
		localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		for (int i = 0; i < DayOfWeek.values().length; i++) {
			DayOfWeek dayOfWeek = localDate.getDayOfWeek();
			upperCaseWeekdays.add(dayOfWeek.toString());
			capitalizedWeekdays.add(WordUtils.capitalizeFully(dayOfWeek
					.toString()));
		}
		assertThat(stringTransformer.capitalize(upperCaseWeekdays),
				equalTo(capitalizedWeekdays));
	}
}
