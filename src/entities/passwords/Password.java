package entities.passwords;

import exceptions.ValidationException;
import interfaces.Unique;

public class Password implements Unique {
	
	private final int id;
	private String salt;
	private String password;
	
	public Password(int id, String password) throws ValidationException {
		this.id = id;
		this.setPassword(password);
	}

	@Override
	public String getKey() {
		return "" + this.id;
	}

	public String getPassword() {
		return this.password;
	}

	private void setPassword(String password) throws ValidationException {
		if(password == null){
			throw new ValidationException("Invalid input");
		}
		this.password = password;
	}
	
	

}
