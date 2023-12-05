package Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("CalculatorTest")
public class CalculatorTest {

	@Test
	public void givenInfixString_returnResult() {
		assertEquals(4.0, Calculator.evaluate("2 + 2"));
	}

	@Test
	public void givenAnyValidExpression_evaluateAccordingToPemdas_andReturnResult() {
		assertEquals(2, Calculator.evaluate("(2 * ( 2 ^ 3) - 1) / 5 - 2 + 1"));
	}
}
