package task1;

import java.util.Scanner;

import RPNCalculator.RPNCalculator;

/**
 * 
 */

public final class RPNCalculatorApp {
	public static void main(Scanner scanner) {
		Scanner input;
		if (scanner != null) {
			input = scanner;
		} else {
			input = new Scanner(System.in);
		}
		System.out.println("\nThis is the test application for the RPN Calculator\n");
		while (true) {
			System.out.println("Enter a valid RPN mathematical string (ex. 2 2 +). Leave blank to exit: ");
			String expr = input.nextLine();

			if (expr.isEmpty()) {
				break;
			}

			System.out.println("Result: " + RPNCalculator.evaluate(expr));
		}

		if (scanner == null) {
			input.close();
		}
	}

	public static void main() {
		main(null);
	}
}
