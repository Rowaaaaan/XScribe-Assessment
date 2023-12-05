package Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Event logger with varying levels of verbosity.
 *
 * This is an event logger that uses the singleton design pattern.
 */
public final class Logger {
	private static Logger instance = new Logger();
	private static LogLevel level;

	/**
	 * Format a given log message, then add it to the log buffer,
	 * and return formatted log message.
	 *
	 * @param log Message to be added to the log buffer
	 * @return Log message string
	 *
	 * @note Log functions are stubs that only append their log level
	 *       to the message, as there is no standard way to write differing
	 *       verbosity levels
	 */
	public static String log(String log) {
		String formattedLog = "";
		// Logs are in their own separate functions
		// in case there is a requirement for each level
		// to do something else--e.g. send an email to an
		// external mail server for error and fatal logs
		//
		// However, logs will still get printed to the console regardless.
		switch (level) {
			case TRACE: {
				formattedLog = trace(log);
				break;
			}
			case DEBUG: {
				formattedLog = debug(log);
				break;
			}
			case INFO: {
				formattedLog = info(log);
				break;
			}
			case WARN: {
				formattedLog = warn(log);
				break;
			}
			case ERROR: {
				formattedLog = error(log);
				break;
			}
			case FATAL: {
				formattedLog = fatal(log);
				break;
			}
		}
		System.out.println(formattedLog);
		flushLog(formattedLog);
		return log;
	}

	/**
	 * Wrapper function for log that sets a new log level
	 * and writes the log string.
	 *
	 * @param logMsg Log message string
	 * @param level  New log level to set the Logger to
	 * @return Formatted log message string
	 */
	public static String log(String logMsg, LogLevel level) {
		setLevel(level);
		return log(logMsg);
	}

	/**
	 * Get current log level of the Logger
	 *
	 * @return Current log level
	 */
	public static LogLevel getLevel() {
		return level;
	}

	/**
	 * Set Logger to a new log level
	 *
	 * @param newLevel New log level to set Logger to
	 */
	public static void setLevel(LogLevel newLevel) {
		level = newLevel;
	}

	private static String error(String log) {
		return String.format("[%s] [ERROR] %s", LocalDateTime.now().toString(), log);
	}

	private static String warn(String log) {
		return String.format("[%s] [WARN] %s", LocalDateTime.now().toString(), log);
	}

	private static String info(String log) {
		return String.format("[%s] [INFO] %s", LocalDateTime.now().toString(), log);
	}

	private static String fatal(String log) {
		return String.format("[%s] [FATAL] %s", LocalDateTime.now().toString(), log);
	}

	private static String debug(String log) {
		return String.format("[%s] [DEBUG] %s", LocalDateTime.now().toString(), log);
	}

	private static String trace(String log) {
		return String.format("[%s] [TRACE] %s", LocalDateTime.now().toString(), log);
	}

	/**
	 * Flush log message to a file.
	 *
	 * Spawn a new thread to create new file and write the log message to it,
	 * or append log message to log file if it exists.
	 *
	 * @param log Log message to flush to file
	 * @throws InterruptedException
	 */
	private static void flushLog(final String log) {
		// Create a thread that writes to a log file
		Thread flushLogWorker = new Thread(new Runnable() {
			// Actual function that flushes the log to a file
			@Override
			public void run() {
				File logFile = new File(LocalDate.now().toString() + ".log");

				try {
					if (logFile.createNewFile()) {
						createNewLogFile(logFile, log);
					} else if (logFile.exists()) {
						appendExistingLogFile(logFile, log);
					}
				} catch (IOException ie) {
					System.out.println("Failed to write to log!");
					ie.printStackTrace();
				} catch (Exception e) {
					System.out.println("Unexpected exception occurred!");
					e.printStackTrace();
				}
			}
		});

		try {
			flushLogWorker.start();
			flushLogWorker.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	/**
	 * Create new log file and write log message to it.
	 *
	 * @param logFile File to write logs to
	 * @param log     Log message to write to log
	 * @throws IOException
	 */
	private static void createNewLogFile(File logFile, String log) throws IOException {
		FileWriter fw = new FileWriter(logFile.getName());
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(log);
		bw.newLine();

		bw.close();
	}

	/**
	 * Append log message to existing log file.
	 *
	 * @param logFile File to write logs to
	 * @param log     Log message to write log
	 * @throws IOException
	 */
	private static void appendExistingLogFile(File logFile, String log) throws IOException {
		FileWriter fw = new FileWriter(logFile.getName(), true);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(log);
		bw.newLine();

		bw.close();
	}

	private Logger() {
		level = LogLevel.INFO;
	}
}
