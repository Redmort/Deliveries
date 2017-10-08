package exceptions;

public class EmptyShoppingBagException extends Exception {

	private static final long serialVersionUID = 4344954832346893415L;

	public EmptyShoppingBagException() {
		super();
	}

	public EmptyShoppingBagException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public EmptyShoppingBagException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public EmptyShoppingBagException(String arg0) {
		super(arg0);
	}

	public EmptyShoppingBagException(Throwable arg0) {
		super(arg0);
	}
	
	

}
