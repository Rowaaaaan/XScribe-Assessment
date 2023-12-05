package Calculator;

import java.util.ArrayDeque;

/**
 * Math expr utility class for converting infix math expressions
 * into postfix expressions, or Reverse Polish Notation (RPN)
 * using Djikstra's Shunting Yard Algorithm.
 * ex. "2 + 2" => "2 2 +"
 *
 * This makes it easier to parse the expression using a stack for
 * when the expression is run through the RPNCalculator.
 */
public final class MathParser {

	/**
	 * Convert infix math expressions to RPN.
	 *
	 * @param infixMathExpr Infix math expression to convert to RPN
	 * @return RPN string
	 * @throws Exception
	 */
	public static String infixToPostfix(String infixMathExpr) throws Exception {
		ArrayDeque<String> tokenQueue = new ArrayDeque<>();

		ArrayDeque<String> operatorStack = new ArrayDeque<>();
		String topOpsStackToken = new String();

		infixMathExpr = preprocessMathString(infixMathExpr);

		String[] tokenArray = infixMathExpr.split(" ");

		// Used to keep track if two expression groups are
		// next to each other
		//
		// ex. (2 + 2) (2 + 2)
		boolean consecutiveExprGroup = false;
		String prevToken = new String();

		for (String currentToken : tokenArray) {

			topOpsStackToken = operatorStack.peek();

			if (Character.isDigit(currentToken.charAt(0))) {
				tokenQueue.offer(currentToken);
			} else {

				switch (currentToken.charAt(0)) {
					case '+':
					case '-':
					case '*':
					case '/':
					case '^':
						if (topOpsStackToken != null) {
							if (topOpsStackTokenHasPrecedence(topOpsStackToken, currentToken)) {
								tokenQueue.offer(operatorStack.pop());
							}
						}
						operatorStack.push(currentToken);
						break;
					case '(': {
						if (prevToken.equals(")")) {
							consecutiveExprGroup = true;
						}
						operatorStack.push(currentToken);
						break;
					}

					case ')': {
						// Make sure it has an accompanying opening parenthesis
						if (!operatorStack.contains("(")) {
							throw new Exception(
									"Invalid expression! Opening parenthesis without closing parenthesis found!");
						}

						while (topOpsStackToken != null) {
							topOpsStackToken = operatorStack.peek();
							if (topOpsStackToken.equals("(")) {
								operatorStack.pop();
								break;
							} else {
								tokenQueue.offer(operatorStack.pop());
							}
						}

						// Multiply consecutive expression groups
						if (consecutiveExprGroup) {
							tokenQueue.offer("*");
							consecutiveExprGroup = false;
						}
						break;
					}
				}
			}

			prevToken = currentToken;
		}

		// Closing parenthesis should have been handled in the tokenizing
		// loop before execution gets to this point.
		if (operatorStack.contains("(")) {
			throw new Exception("Invalid expression! Closing parenthesis not found!");
		}

		// Clear remaining operators in the stack
		while (operatorStack.peek() != null) {
			tokenQueue.offer(operatorStack.pop());
		}

		return createRpnStringFromQueue(tokenQueue);
	}

	private static boolean topOpsStackTokenHasPrecedence(String topStackToken, String currentToken) {
		return getPrecedence(currentToken) < getPrecedence(topStackToken)
				|| (getPrecedence(currentToken) == getPrecedence(topStackToken)
						&& isLeftAssociative(topStackToken));
	}

	private static boolean isLeftAssociative(String token) {
		switch (token) {
			case "+":
			case "-":
			case "*":
			case "/":
				return true;
			default:
				return false;
		}
	}

	/**
	 * Preprocessor for math expression strings
	 *
	 * This makes sure that the expression can be parsed even if
	 * the expression has an inconsistent number of spaces,
	 * or no spaces at all.
	 * ex. "1+2 *3 / 4" => "1 + 2 * 3 / 4"
	 * ex. "(2 + 2) * 5" => "( 2 + 2 ) * 5"
	 *
	 * This also handles strings of numbers being allowed to be parsed.
	 * Without preprocessing them, it would be possible for the parser to
	 * parse a token like "1234" as separate digits like "[1, 2, 3, 4]"
	 * instead of the whole number "[1234]".
	 * 
	 * @param expr Expression to preprocess
	 * @return The preprocessed math expression
	 */
	private static String preprocessMathString(String expr) {
		StringBuilder sb = new StringBuilder(expr);
		for (int i = 0; i < sb.length(); i++) {

			// Check if current character and the next character are digits.
			// If so, ignore it. Otherwise, insert a space in the next position.
			if (Character.isDigit(sb.charAt(i))
					&& i + 1 < sb.length()
					&& Character.isDigit(sb.charAt(i + 1))) {
				continue;
			} else if (Character.isWhitespace(sb.charAt(i))) {

				// Clear all whitespaces except one between tokens
				// ex. "2.........+ 2" => "2 + 2"
				while (i + 1 < sb.length()
						&& Character.isWhitespace(sb.charAt(i + 1))) {
					sb.deleteCharAt(i + 1);
				}
			} else {
				if (i + 1 < sb.length()) {
					sb.insert(i + 1, ' ');
				}
			}
		}

		return sb.toString();
	}

	private static String createRpnStringFromQueue(ArrayDeque<String> rpnQueue) {
		// Create finalized RPN expression
		String rpnExpr = new String();
		while (!rpnQueue.isEmpty()) {
			rpnExpr += rpnQueue.poll();
			if (!rpnQueue.isEmpty()) {
				rpnExpr += " ";
			}
		}

		return rpnExpr;
	}

	/**
	 * Take a mathematical operator and check its precedence.
	 *
	 * @param string Operator to get precedence from
	 * @return Precedence index of the operator
	 */
	private static int getPrecedence(String token) {
		switch (token) {
			case "+":
			case "-":
				return 1;
			case "*":
			case "/":
				return 2;
			case "^":
				return 3;
			default:
				return -1;

		}
	}
}
