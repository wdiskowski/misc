package org.wd.example.text;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SubstitutorTest {

	private String sourceString;
	private String resultString;
	private Map<String, String> substitutions;

	@Test
	public void replaceStaticStringTest() throws SubstitutorException {
		sourceString = "some string";
		resultString = sourceString;
		substitutions = new HashMap<String, String>();
		runTest();
	}

	@Test
	public void replaceSimpleStringTest() throws SubstitutorException {
		sourceString = "some string ${source1}";
		resultString = "some string result1";
		substitutions = new HashMap<String, String>();
		substitutions.put("source1", "result1");
		runTest();
	}

	@Test
	public void replaceMulltiStringTest() throws SubstitutorException {
		sourceString = "some string ${source1} ${source2}";
		resultString = "some string result1 result2";
		substitutions = new HashMap<String, String>();
		substitutions.put("source1", "result1");
		substitutions.put("source2", "result2");
		runTest();
	}

	@Test
	public void replaceReflectiveStringTest() throws SubstitutorException {
		sourceString = "some string ${source1}";
		resultString = "some string result2";
		substitutions = new HashMap<String, String>();
		substitutions.put("source1", "${source2}");
		substitutions.put("source2", "result2");
		runTest();
	}

	@Test
	public void replaceReflectiveStringComplexTest()
			throws SubstitutorException {
		sourceString = "some string ${source1}";
		resultString = "some string result1.1.1 result1.1.2#1.2.2#1.2.1.1 "
				+ "result1.1.2#1.2.2#1.2.1.1 result1.1.2#1.2.2#1.2.1.1";
		substitutions = new HashMap<String, String>();
		substitutions.put("source1", "${source1.1} ${source1.2}");
		substitutions.put("source1.1",
				"${source1.1.1} ${source1.1.2#1.2.2#1.2.1.1}");
		substitutions.put("source1.2",
				"${source1.2.1} ${source1.1.2#1.2.2#1.2.1.1}");
		substitutions.put("source1.1.1", "result1.1.1");
		substitutions.put("source1.2.1", "${source1.1.2#1.2.2#1.2.1.1}");
		substitutions.put("source1.1.2#1.2.2#1.2.1.1",
				"result1.1.2#1.2.2#1.2.1.1");
		runTest();
	}

	@Test(expected = SubstitutorException.class)
	public void replaceReflectiveStringInfiniteLoopTest()
			throws SubstitutorException {
		sourceString = "some string ${source1}";
		substitutions = new HashMap<String, String>();
		substitutions.put("source1", "${source1}");
		Substitutor substitutor = new Substitutor();
		substitutor.setSubstitutions(substitutions);
		substitutor.replace(sourceString);
	}

	@Test
	public void containsSubstitutionTargetNegativeTest() {
		sourceString = "some string";
		substitutions = new HashMap<String, String>();
		substitutions.put("source1", "result1");
		Substitutor substitutor = new Substitutor();
		substitutor.setSubstitutions(substitutions);
		boolean containsTarget = substitutor
				.containsSubstitutionTarget(sourceString);
		assertThat(containsTarget, equalTo(false));
	}

	@Test
	public void containsSubstitutionTargetPositiveTest() {
		sourceString = "some string ${source1}";
		substitutions = new HashMap<String, String>();
		substitutions.put("source1", "result1");
		Substitutor substitutor = new Substitutor();
		substitutor.setSubstitutions(substitutions);
		boolean containsTarget = substitutor
				.containsSubstitutionTarget(sourceString);
		assertThat(containsTarget, equalTo(true));
	}

	private void runTest() throws SubstitutorException {
		Substitutor substitutor = new Substitutor();
		substitutor.setSubstitutions(substitutions);
		String replacedString = substitutor.replace(sourceString);
		assertThat(replacedString, equalTo(resultString));
	}
	
	@Test
	public void failedTest() throws SubstitutorException {
		sourceString = "some string";
		resultString = "some other failed string";
		substitutions = new HashMap<String, String>();
		runTest();
	}
}
