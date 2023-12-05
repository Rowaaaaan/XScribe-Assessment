package task1;

import java.util.Scanner;

/**
 * Task 1
 */
public class App {
	final static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		String choice = new String();
		System.out.println("\nXScribe Programming Test 2, Task 1\n");

		while (!choice.equals("4")) {
			System.out.println();
			System.out.println("[1] Task A - Logger Utility Class");
			System.out.println("[2] Task C - Calculator Utility Class");
			System.out.println("[3] Task D - RPN Math Evaluator Class");
			System.out.println("[4] Exit\n");
			System.out.print("Enter Task to test: ");

			choice = input.nextLine();

			try {
				if (!choice.isEmpty()) {
					switch (Integer.parseInt(choice)) {
						case 1: {
							LogApp.main(input);
							break;
						}
						case 2: {
							CalculatorApp.main(input);
							break;
						}
						case 3: {
							RPNCalculatorApp.main(input);
							break;
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}

		input.close();

		System.exit(0);
	}
}
