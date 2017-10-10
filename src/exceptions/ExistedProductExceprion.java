package exceptions;

public class ExistedProductExceprion extends Exception {

	private static final long serialVersionUID = 7045144479301588238L;

	public ExistedProductExceprion() {
		super();
	}

	public ExistedProductExceprion(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExistedProductExceprion(String message, Throwable cause) {
		super(message, cause);
	}

	public ExistedProductExceprion(String message) {
		super(message);
	}

	public ExistedProductExceprion(Throwable cause) {
		super(cause);
	}
	
	

}
