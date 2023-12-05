package com.xscribe.progtest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	/**
	 * @test Check if the search algorithm actually returns the correct
	 *       index of the first occurrence of the substring in the base string.
	 */
	@Test
	public void kmpReturnsCorrectIndexOfFirstMatch() {
		String[] testSearchStrings = { "abc", "bcde", "zf1", "foo" };
		int[] expectedLocations = { 0, 12, 6, -1 };
		String baseString = "abcbcdzf1abcbcdefod";

		for (int i = 0; i < testSearchStrings.length; i++) {
			assertEquals(expectedLocations[i], KMP.findIndex(testSearchStrings[i], baseString));
		}
	}

	@Test
	public void givenPatternLongerThanText_returnNegativeOne() {
		assertEquals(-1, KMP.findIndex("abcd", "abc"));
	}

}
