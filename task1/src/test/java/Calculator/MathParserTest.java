package Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("MathParser")
public final class MathParserTest {

	@Test
	public void givenInfixString_returnRPNString() throws Exception {
		assertEquals("2 2 3 ^ * 1 - 5 / 2 - 1 +", MathParser.infixToPostfix("(2 * ( 2 ^ 3) - 1) / 5 - 2 + 1"));
		assertEquals("5 2 3 8 - 5 2 ^ ^ / +", MathParser.infixToPostfix("5+2/(3-8)^5^2"));
		assertEquals("11 22 +", MathParser.infixToPostfix("11+22"));
		assertEquals("2 2 + 5 *", MathParser.infixToPostfix("(2 + 2) * 5"));
		assertEquals("2 2 +", MathParser.infixToPostfix("2 + 2"));
	}

	@Test
	public void givenInfixStringWithoutClosingParens_throwError() {
		Exception e = assertThrows(Exception.class, () -> {
			MathParser.infixToPostfix("(2 + 2 * 2");
		});

		System.out.println("Exception thrown: " + e.getMessage());
		assertTrue(e.getMessage()
				.contains("Closing parenthesis not found!"));
	}

	@Test
	public void givenInfixStringWithoutOpeningParens_throwException() {
		Exception e = assertThrows(Exception.class, () -> {
			MathParser.infixToPostfix("2 + 2) * 2");
		});

		System.out.println("Exception thrown: " + e.getMessage());
		assertTrue(e.getMessage()
				.contains("Opening parenthesis without closing parenthesis found!"));
	}

	/**
	 * Handle the cases such as (2 + 2)(2 + 2),
	 * where the result should be (4)(4) => 4 * 4 => 4 4
	 *
	 * @throws Exception When math parser fails
	 */
	@Test
	public void givenTwoExpressionsInParens_multiply() throws Exception {
		assertEquals("2 2 + 2 2 + *", MathParser.infixToPostfix("(2 + 2) (2 + 2)"));
	}
}
