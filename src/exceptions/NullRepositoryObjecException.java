package exceptions;

public class NullRepositoryObjecException extends Exception {

	private static final long serialVersionUID = -1977724384011133405L;

	public NullRepositoryObjecException() {
		super();
	}

	public NullRepositoryObjecException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public NullRepositoryObjecException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NullRepositoryObjecException(String arg0) {
		super(arg0);
	}

	public NullRepositoryObjecException(Throwable arg0) {
		super(arg0);
	}
	
	

}
