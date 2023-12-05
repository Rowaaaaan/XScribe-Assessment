package task2;

import java.util.Scanner;
import java.util.concurrent.Future;

/**
 * Implementation for Task 2 of the XScribe Programming test
 */
public class Task2 {
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		System.out.println("\nXScribe Programming Test 2, Task 2 - Largest Prime Factor\n");
		long num = 60085147513L;

		while (true) {
			System.out.print(
					"\nEnter number to find largest prime factor from (Enter '-1' to quit) [Default: 60085147513]: ");
			String numStr = input.nextLine();

			System.out.println(numStr);
			if (numStr.contains("-1")) {
				System.out.println("Exiting...");
				break;
			} else if (numStr.isEmpty()) {
				num = 60085147513L;
			} else {
				try {
					num = Long.parseLong(numStr);
				} catch (NumberFormatException nfe) {
					System.out.println("Invalid string given!\n");
				}
			}

			Future<Long> lpfTask = LargestPrimeFactorTask.calculate(num);

			System.out.println("\nComputing LPF of " + num);
			System.out.println("Press ENTER to cancel computing LPF...");
			input.nextLine();

			if (lpfTask.isDone()) {
				try {
					long ans = lpfTask.get();
					System.out.printf("LPF of %d: %d\n", num, ans);
				} catch (Exception e) {
					System.out.println("Error getting output from LPF task. " + e.getMessage());
				}
			} else {
				lpfTask.cancel(true);
				System.out.println("Task cancelled.");
			}

		}

		input.close();

		System.exit(0);
	}
}
