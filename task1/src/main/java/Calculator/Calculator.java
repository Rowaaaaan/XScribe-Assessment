package Calculator;

import RPNCalculator.RPNCalculator;

public final class Calculator {
	/**
	 * Calculator utility class that takes a valid mathematical expression
	 * and returns the value.
	 *
	 * @param expr Expression to evaluate
	 * @return Result of mathematical expression
	 */
	public static double evaluate(String expr) {
		try {
			expr = MathParser.infixToPostfix(expr);
			return RPNCalculator.evaluate(expr);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
