package Logger;

/**
 * Verbosity levels for logs
 */
public enum LogLevel {
	/**
	 * Most verbose level.
	 */
	TRACE,

	/**
	 * Second most verbose level.
	 */
	DEBUG,

	/**
	 * Third most verbose level.
	 */
	INFO,

	/**
	 * Third least verbose level.
	 */
	WARN,

	/**
	 * Second least verbose level.
	 */
	ERROR,

	/**
	 * Least verbose level.
	 *
	 * Used only for unrecoverable crashes or bugs.
	 */
	FATAL
}
