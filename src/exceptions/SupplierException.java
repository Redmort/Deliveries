package exceptions;

public class SupplierException extends Exception {

	private static final long serialVersionUID = 201633276868460104L;

	public SupplierException() {
		super();
	}

	public SupplierException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public SupplierException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public SupplierException(String arg0) {
		super(arg0);
	}

	public SupplierException(Throwable arg0) {
		super(arg0);
	}

}
