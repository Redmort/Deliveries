package exceptions;

public class InvalidPasswordEcxeption extends Exception {

	private static final long serialVersionUID = -9216667918733779448L;

	public InvalidPasswordEcxeption() {
		super();
	}

	public InvalidPasswordEcxeption(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidPasswordEcxeption(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidPasswordEcxeption(String arg0) {
		super(arg0);
	}

	public InvalidPasswordEcxeption(Throwable arg0) {
		super(arg0);
	}

	
}
