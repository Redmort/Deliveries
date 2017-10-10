package exceptions;

public class RoleException extends Exception {

	private static final long serialVersionUID = 5543330642230540808L;

	public RoleException() {
		super();
	}

	public RoleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RoleException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoleException(String message) {
		super(message);
	}

	public RoleException(Throwable cause) {
		super(cause);
	}

	
}
