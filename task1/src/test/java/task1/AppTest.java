package task1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import Logger.LogLevel;
import Logger.Logger;

/**
 * Unit test for simple App.
 */
@Tag("Logger")
public class AppTest {
	private final File logFile = new File(LocalDate.now().toString() + ".log");

	@BeforeEach
	public void setUp() {
		Logger.setLevel(LogLevel.INFO);
	}

	@AfterEach
	public void tearDown() {
		Logger.setLevel(LogLevel.INFO);

		if (logFile.exists()) {
			logFile.delete();
		}
	}

	/**
	 * @test Test to check if initial logger level is correct.
	 */
	@Test
	public void initialLoggerHasInfoVerbosityLevel() {
		assertEquals(LogLevel.INFO, Logger.getLevel());
	}

	/**
	 * @test Check to see if Logger.setLogLevel() sets the correct log level
	 */
	@Test
	public void setLogLevelSetsCorrectLogLevel() {
		for (LogLevel l : LogLevel.values()) {
			Logger.setLevel(l);
			assertEquals(l, Logger.getLevel());
		}
	}

	/**
	 * Check if a new log file gets created, provide one does not already exist
	 * and check if the log file contains the test log string
	 * 
	 * @throws FileNotFoundException If log file doesn't exist
	 */
	@Test
	public void flushLog_createsNewFile_ifFileNotExists() throws FileNotFoundException {
		Logger.log("Test log 1");

		Scanner logScan = new Scanner(logFile);

		if (logScan.hasNextLine()) {
			String actualValue = logScan.nextLine();
			assertTrue(actualValue.contains("Test log 1"));
		} else {
			fail("Log file either doesn't exist, or is empty");
		}

		logScan.close();
	}

	/**
	 * Check if log messages are just appended to an existing log file,
	 * and check if the log file contains the test log strings
	 *
	 * @throws FileNotFoundException If log file doesn't exist
	 */
	@Test
	public void flushLog_appendsToFile_ifFileExists() throws IOException {
		if (logFile.createNewFile()) {
			// Write 10 log lines to the log file
			for (int i = 0; i < 10; i++) {
				Logger.log("Test log " + (i + 1));
			}

			Scanner logScan = new Scanner(logFile);

			// Test the 10 log lines in the log file and check if they exist
			for (int x = 0; x < 10; x++) {
				if (!logScan.hasNextLine()) {
					fail("Log file does not have the same amount of written lines in it");
				} else {
					assertTrue(logScan.nextLine().contains("Test log " + (x + 1)));
				}

			}

			logScan.close();
		}
	}
}
