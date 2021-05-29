package skroba.utils.logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Implementation of {@link Logger}.
 */
public class LoggerImpl implements Logger {
	private boolean log = true;
	private final BufferedWriter file;
	
	public static LoggerImpl getConsoleLogger() {
		return new LoggerImpl(new BufferedWriter(new OutputStreamWriter(System.out)));
	}
	
	public LoggerImpl(final String fileName, final boolean log) {
		try {
			this.log = log;
			file = Files.newBufferedWriter(Paths.get(fileName));
		} catch (IOException ex) {
			throw new RuntimeException("Can't open logging file: " + ex.getMessage());
		}
	}
	
	public LoggerImpl(final Path path, final boolean log) {
		try {
			this.log = log;
			file = Files.newBufferedWriter(path);
		} catch (IOException ex) {
			throw new RuntimeException("Can't open logging file: " + ex.getMessage());
		}
	}
	
	public LoggerImpl(final BufferedWriter writer, final boolean log) {
		this.log = log;
		file = writer;
	}
	
	public LoggerImpl(final String fileName) {
		this(fileName, true);
	}
	
	public LoggerImpl(final Path path) {
		this(path, true);
	}
	
	public LoggerImpl(final BufferedWriter writer) {
		this(writer, true);
	}
	
	/**
	 * Set needing logging parameter.
	 * @param log - if need log.
	 */
	public void setLog(boolean log) {
		this.log = log;
	}
	
	@Override
	public void log(final String message) {
		if (!log) return;
		try {
			file.write(message);
		} catch (IOException ex) {
			System.err.println("Can't write in log file: " + ex.getMessage());
		}
	}
	
	@Override
	public void close() {
		try {
			file.flush();
			file.close();
		} catch (IOException ex) {
			throw new RuntimeException("Can't close logging file: " + ex.getMessage());
		}
	}
}
