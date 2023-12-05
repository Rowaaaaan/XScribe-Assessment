package task2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

public class LargestPrimeFactorTest {

	@Test
	public void givenNumber_returnLargestPrimeFactor() {
		long num = 60085147513L;
		Future<Long> lpfTask = LargestPrimeFactorTask.calculate(num);

		System.out.println("Computing LPF");
		while (!lpfTask.isDone()) {
			if (lpfTask.isDone()) {
				try {
					long expected = 193_387L;
					long actual = lpfTask.get();
					assertEquals(expected, actual);
				} catch (Exception e) {
					System.out.println("Error getting value. " + e.getMessage());
				}
			}
		}

	}
}
