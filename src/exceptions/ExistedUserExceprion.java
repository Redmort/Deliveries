package exceptions;

public class ExistedUserExceprion extends Exception {

	private static final long serialVersionUID = -1547616080773330932L;

	public ExistedUserExceprion() {
		super();
	}

	public ExistedUserExceprion(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExistedUserExceprion(String message, Throwable cause) {
		super(message, cause);
	}

	public ExistedUserExceprion(String message) {
		super(message);
	}

	public ExistedUserExceprion(Throwable cause) {
		super(cause);
	}
	
	

}
