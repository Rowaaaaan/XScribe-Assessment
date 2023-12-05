package com.xscribe.progtest;

import java.util.Scanner;

/**
 * Entry point for Task 3 - String searching
 */
public class App {
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println("\nXScribe Programming Test 2, Task 3 - String Searching\n");

		while (true) {
			System.out.println("\nEnter base string to search from (Leave blank to quit): ");
			String baseString = input.nextLine();

			if (baseString.isEmpty()) {
				break;
			}

			System.out.println("\nEnter string to search (Leave blank to cancel): ");
			String searchString = input.nextLine();

			if (!searchString.isEmpty()) {
				System.out.println("\nPattern found at index: " + KMP.findIndex(searchString, baseString));
			}
		}

		input.close();

		System.exit(0);
	}
}
