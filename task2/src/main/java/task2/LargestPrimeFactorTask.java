package task2;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Utility class for determining largest prime factor of a number asynchronously
 */
public final class LargestPrimeFactorTask {
	private static ExecutorService executors = Executors.newFixedThreadPool(2);

	/**
	 * Calculate the largest prime factor asynchronously.
	 *
	 * @param initialVal Value to calculate LPF from
	 * @return a Future<Long> to the result
	 */
	public static Future<Long> calculate(long initialVal) {
		return executors.submit(() -> {
			Instant start = Instant.now();

			long ans = 0;
			long currentVal = initialVal;
			int i = 2;

			while (i * i < currentVal && !Thread.currentThread().isInterrupted()) {
				// Without this, the function will finish too fast,
				// and the user will not be able to test interrupting the operation
				Thread.sleep(5);
				if (currentVal % i == 0) {
					ans = i;
					while (currentVal % i == 0) {
						currentVal /= i;
					}
				}
				i++;
			}

			if (currentVal > 1) {
				ans = max(ans, currentVal);
			}
			// Output benchmark
			System.out.printf("\nTime taken to compute LPF of %d (ms): %d\n", initialVal,
					ChronoUnit.MILLIS.between(start, Instant.now()));

			System.out.println("Operation successful. Press ENTER to continue...");

			return ans;
		});
	}

	private static long max(long a, long b) {
		return a > b ? a : b;
	}
}
