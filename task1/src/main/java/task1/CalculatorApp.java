package task1;

import java.util.Scanner;

import Calculator.Calculator;

/**
 * 
 */

public final class CalculatorApp {
	public static void main(Scanner scanner) {
		Scanner input;
		if (scanner != null) {
			input = scanner;
		} else {
			input = new Scanner(System.in);
		}
		System.out.println("\nThis is the test application for the Calculator\n");
		while (true) {
			System.out.println("Enter a valid mathematical string (ex. 2 + 2). Leave blank to exit: ");
			String expr = input.nextLine();

			if (expr.isEmpty()) {
				break;
			}

			System.out.println("Result: " + Calculator.evaluate(expr));
		}

		if (scanner == null) {
			input.close();
		}
	}

	public static void main() {
		main(null);
	}
}
