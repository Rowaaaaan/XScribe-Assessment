package task1;

import java.util.Scanner;

import Logger.LogLevel;
import Logger.Logger;

/**
 * Task 1-A: Application to test the Logger utility function
 */
public final class LogApp {
	private static Scanner input;

	/**
	 * Entrypoint for the Logger test application.
	 *
	 * This uses a hacky way to do dependency injection for
	 * getting user input from System.in by checking if a scanner.
	 *
	 * @param scanner Input scanner to use. This should be null if
	 *                this class is called as the main entrypoint.
	 */
	public static void main(Scanner scanner) {

		if (scanner != null) {
			input = scanner;
		} else {
			input = new Scanner(System.in);
		}

		String logMsg = "";

		System.out.println("\nThis is a test application to exhibit the Logger utility class\n");

		while (true) {
			System.out.print("Enter message to log (Leave blank to exit): ");
			try {
				logMsg = input.nextLine();
			} catch (Exception e) {
				System.out.println("Invalid input!");
			}

			if (logMsg.isEmpty()) {
				break;
			}

			setVerbosityLevel();

			Logger.log(logMsg);
		}

		if (scanner == null) {
			input.close();
		}
	}

	/**
	 * Entrypoint for the Logger test application.
	 *
	 * This is a wrapper function for when no scanner is passed into the app.
	 */
	public static void main() {
		main(null);
	}

	private static void setVerbosityLevel() {
		int level = 0;

		do {
			System.out.println("\nSet log verbosity level\n");
			System.out.println("[1] Trace");
			System.out.println("[2] Debug");
			System.out.println("[3] Info");
			System.out.println("[4] Warn");
			System.out.println("[5] Error");
			System.out.println("[6] Fatal");
			System.out.print("\nInput (Default: Info): ");

			String levelInput = input.nextLine();
			if (!levelInput.isEmpty()) {
				level = Integer.parseInt(levelInput);
			} else {
				level = 3;
			}
		} while (level < 1 || level > 6);

		switch (level) {
			case 1:
				Logger.setLevel(LogLevel.TRACE);
				break;
			case 2:
				Logger.setLevel(LogLevel.DEBUG);
				break;
			case 3:
				Logger.setLevel(LogLevel.INFO);
				break;
			case 4:
				Logger.setLevel(LogLevel.WARN);
				break;
			case 5:
				Logger.setLevel(LogLevel.ERROR);
				break;
			case 6:
				Logger.setLevel(LogLevel.FATAL);
				break;
			default:
				Logger.setLevel(LogLevel.INFO);
				break;
		}
	}
}
