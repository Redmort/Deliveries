package exceptions;

public class UnexistUserException extends Exception {

	private static final long serialVersionUID = 2648618632339378396L;

	public UnexistUserException() {
		super();
	}

	public UnexistUserException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnexistUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnexistUserException(String message) {
		super(message);
	}

	public UnexistUserException(Throwable cause) {
		super(cause);
	}
	
	

}
