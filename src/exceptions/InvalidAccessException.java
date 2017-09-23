package exceptions;

public class InvalidAccessException extends Exception {

	private static final long serialVersionUID = -8578695442659650399L;

	public InvalidAccessException() {
		super();
	}

	public InvalidAccessException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidAccessException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidAccessException(String arg0) {
		super(arg0);
	}

	public InvalidAccessException(Throwable arg0) {
		super(arg0);
	}
	
	

}
