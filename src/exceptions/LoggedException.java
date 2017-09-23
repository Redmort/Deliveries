package exceptions;

public class LoggedException extends Exception {

	private static final long serialVersionUID = 2082278091559813300L;

	public LoggedException() {
		super();
	}

	public LoggedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LoggedException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoggedException(String message) {
		super(message);
	}

	public LoggedException(Throwable cause) {
		super(cause);
	}
	
	

}
