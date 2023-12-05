package RPNCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("RPNTest")
public class RPNCalculatorTest {

	@Test
	public void givenAdditionExpression_evaluateExpression_andReturnNumber() {
		assertEquals(4, RPNCalculator.evaluate("2 2 +"));
	}

	@Test
	public void givenSubtractionExpression_evaluateExpression_andReturnNumber() {
		assertEquals(0, RPNCalculator.evaluate("2 2 -"));
	}

	@Test
	public void givenMultiplicationExpression_evaluateExpression_andReturnNumber() {
		assertEquals(6, RPNCalculator.evaluate("2 3 *"));
	}

	@Test
	public void givenDivisionExpression_evaluateExpression_andReturnNumber() {
		assertEquals(1, RPNCalculator.evaluate("2 2 /"));
		assertEquals(Double.POSITIVE_INFINITY, RPNCalculator.evaluate("2 0 /"));
	}

	@Test
	public void givenExponentialExpression_evaluateExpression_andReturnNumber() {
		assertEquals(8, RPNCalculator.evaluate("2 3 ^"));
	}

	@Test
	public void givenAnyValidRPNExpression_evaluateAccordingToPemdas_andReturnResult() {
		assertEquals(2, RPNCalculator.evaluate("2 2 3 ^ * 1 - 5 / 2 - 1 +"));
		assertEquals(33, RPNCalculator.evaluate("11 22 +"));
		assertEquals(20, RPNCalculator.evaluate("2 2 + 5 *"));
		assertEquals(4, RPNCalculator.evaluate("2 2 +"));
	}
}
