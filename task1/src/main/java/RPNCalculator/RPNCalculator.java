package RPNCalculator;

import java.util.ArrayDeque;

// Djikstra's Shunting Yard Algorithm
// 2 + 2 + 3 + 3
// Stack of operators
// Queue of operands

public final class RPNCalculator {
	/**
	 * A calculator that evaluates a mathematical expression
	 * written in reverse polish notation.
	 *
	 * @param expr Expression to be evaluated
	 * @return Result of expression
	 *
	 */
	public static double evaluate(String expr) {
		ArrayDeque<Double> stack = new ArrayDeque<>();

		for (String token : expr.split(" ")) {
			if (Character.isDigit(token.charAt(0))) {
				stack.push(Double.parseDouble(token));
			} else if (isValidOperator(token)) {
				Double b = stack.pop();
				Double a = stack.pop();

				stack.push(operate(a, b, token));
			}
		}

		return stack.pop();
	}

	/**
	 * @param op Operator to validate
	 * @return True, if valid operator. False, if not.
	 */
	private static boolean isValidOperator(String op) {
		switch (op) {
			case "+":
			case "-":
			case "*":
			case "/":
			case "^":
				return true;
			default:
				return false;
		}
	}

	/**
	 * Execute operation on the two operands given.
	 *
	 * @param a  1st operand
	 * @param b  2nd operant
	 * @param op Operation to execute
	 * @return Result of operation
	 */
	private static double operate(double a, double b, String op) {
		double result = 0;
		try {
			switch (op) {
				case "+": {
					result = a + b;
					break;
				}
				case "-": {
					result = a - b;
					break;
				}
				case "*": {
					result = a * b;
					break;
				}
				case "/": {
					result = a / b;
					break;
				}
				case "^": {
					result = Math.pow(a, b);
					break;
				}
			}
		} catch (ArithmeticException ae) {
			System.out.println("Failed to run operation!");
			ae.printStackTrace();
		}
		return result;
	}
}
