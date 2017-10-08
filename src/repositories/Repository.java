package repositories;

import java.util.HashMap;
import java.util.Map;

import exceptions.InvalidPasswordEcxeption;
import exceptions.NullRepositoryObjecException;
import exceptions.UnexistUserException;
import interfaces.Unique;

public class Repository<T extends Unique> {

	private String secretPass;
	private Map<String, T> objects;

	public Repository(String secretPass) {
		this.secretPass = this.encodePass(secretPass);
		this.objects = new HashMap<>();
	}

	private String encodePass(String secretPass) {
		// TODO encode method
		return secretPass;
	}
	

	public void add(T object, String password) throws NullRepositoryObjecException, InvalidPasswordEcxeption{
		
		this.checkPassword(password);
		
		if(object == null){
			throw new NullRepositoryObjecException("Added object mustn't be Null");
		}
		
		if(!this.objects.containsKey(object.getKey())){
			this.objects.put(object.getKey(), object);
		}
	}

	public void remove(String key, String password) throws UnexistUserException, InvalidPasswordEcxeption{
		
		this.checkPassword(password);
		
		this.checkForExisting(key);
		
		this.objects.remove(key);
	}

	public T find(String key, String password) throws UnexistUserException, InvalidPasswordEcxeption{
		
		this.checkPassword(password);
		
		this.checkForExisting(key);
		
		return this.objects.get(key);
	}
	
	public void checkForExisting(String key) throws UnexistUserException{
		if(!this.objects.containsKey(key)){
			throw new UnexistUserException("User(" + key + ") didnt exist");
		}
	}
	
	private void checkPassword(String password) throws InvalidPasswordEcxeption{
		if(!this.secretPass.equals(this.encodePass(password))){
			throw new InvalidPasswordEcxeption("Access denied");
		}
	}
	
}
