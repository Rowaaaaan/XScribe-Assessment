package com.xscribe.progtest;

/**
 * A string searching class using the Knuth-Morris-Pratt (KMP)
 * string search algorithm.
 *
 * Compared to naive searching, which has O(nm) time complexity,
 * KMP has a time complexity of O(n+m).
 */

public final class KMP {

	/**
	 * Search a substring in the given base string,
	 * and return the index of the first occurrence
	 *
	 * @param pattern Pattern to search from the base string
	 * @param text    Base string to search from
	 *
	 * @return Index of the first occurrence of substring,
	 *         or -1 if substring is not found
	 */
	public static int findIndex(String pattern, String text) {

		if (pattern.length() > text.length()) {
			return -1;
		}

		System.out.printf("Searching [%s] in [%s]...\n", pattern, text);

		int[] lps = preprocessLPSArray(pattern);

		int i = 0;
		int j = 0;

		while (i < text.length()) {
			if (text.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
			} else if (j == 0) {
				// Shift only the search character in the text
				i++;
			} else {
				// We don't need to search the entire string again.
				// We can restart the search at the last character that matched,
				// based on the lps array.
				j = lps[j - 1];
			}
			// i is at the end of the pattern string, so we offset it
			// by the length of the pattern.
			if (j == pattern.length()) {
				return i - pattern.length();
			}
		}

		return -1;
	}

	/**
	 * Create an array of prefix suffixes.
	 *
	 * The output is used by the KMP algorithm to know
	 * what characters can be skipped in the comparison.
	 *
	 * @param pattern
	 * @return Array of indices of all prefix suffixes
	 */
	private static int[] preprocessLPSArray(String pattern) {
		final int patLength = pattern.length();
		int[] lpsArray = new int[patLength];
		int prevLPS = 0;

		for (int i = 1; i < patLength; i++) {
			if (pattern.charAt(i) == pattern.charAt(prevLPS)) {
				lpsArray[i] = prevLPS + 1;
				prevLPS += 1;
			} else {
				if (prevLPS == 0) {
					lpsArray[i] = 0;
				} else {
					prevLPS = lpsArray[prevLPS - 1];
				}
			}
		}

		return lpsArray;
	}

}
