package org.wd.example.lambdas;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.text.WordUtils;

public class StringTransformer {

	public List<String> capitalize(List<String> upperCaseWeekdays) {
		return upperCaseWeekdays.stream().map(WordUtils::capitalizeFully)
				.collect(Collectors.toList());
	}

}
