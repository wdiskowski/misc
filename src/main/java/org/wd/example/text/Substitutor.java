package org.wd.example.text;

import java.util.Collections;
import java.util.Map;

public class Substitutor {

	/**
	 * Default prefix for substitution target.
	 */
	private static final String DEFAULT_PREFIX_RE = "\\$\\{";

	private static final String DEFAULT_SUFFIX_RE = "}";

	private static final String WILDCARD = ".*";

	private static final int MAX_DEEP = 4;

	private final String prefix;

	private final String suffix;

	private Map<String, String> substitutions;

	public Substitutor() {
		prefix = DEFAULT_PREFIX_RE;
		suffix = DEFAULT_SUFFIX_RE;
	}

	public void setSubstitutions(Map<String, String> substitutions) {
		this.substitutions = Collections.unmodifiableMap(substitutions);
	}

	public String replace(final String sourceString)
			throws SubstitutorException {
		return replace(sourceString, 0);
	}

	public String replace(final String sourceString, int deep)
			throws SubstitutorException {
		if (deep > MAX_DEEP) {
			throw new SubstitutorException("Too deep or cyclic reference.");
		}
		String resultString = sourceString;
		for (final String key : substitutions.keySet()) {
			resultString = resultString.replaceAll(prefix + key + suffix,
					substitutions.get(key).replaceAll("\\$", "\\\\\\$"));
		}
		if (containsSubstitutionTarget(resultString)) {
			resultString = replace(resultString, deep + 1);
		}
		return resultString;
	}

	protected boolean containsSubstitutionTarget(String sourceString) {
		return substitutions
				.keySet()
				.stream()
				.anyMatch(
						s -> sourceString.matches(WILDCARD + prefix + s
								+ suffix + WILDCARD));
	}

}
