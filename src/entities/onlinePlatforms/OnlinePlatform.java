package entities.onlinePlatforms;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import controllers.UserController;
import entities.orders.Order;
import entities.products.Product;
import entities.users.User;
import exceptions.ExistedUserExceprion;
import exceptions.InvalidAccessException;
import exceptions.InvalidPasswordEcxeption;
import exceptions.LoggedException;
import exceptions.NullRepositoryObjecException;
import exceptions.UnexistUserException;
import exceptions.ValidationException;
import interfaces.IDelivarable;

public class OnlinePlatform {

	private Set<IDelivarable> shops;
	
	private UserController userController;
	
	private Map<String, User> leggedUsers;

	public OnlinePlatform() {
		this.userController = new UserController(this);
		this.leggedUsers = new HashMap<>();
	}
	
	public void login(String username, String password) throws InvalidPasswordEcxeption{
		
		User user = this.userController.identify(username, password);
		
		this.leggedUsers.put(user.getKey(), user);
	}
	
	public void register(String... args) throws NullRepositoryObjecException, ValidationException, ExistedUserExceprion {
		this.userController.create(args);
	}
	
	public void remove(User admin, String username) throws LoggedException, UnexistUserException, InvalidPasswordEcxeption, InvalidAccessException{
		
		this.checkForloggin(admin);
		
		this.userController.remove(admin, username);
	}
	
	
	public void AddProduct(Product product, User user) throws LoggedException{
		
		this.checkForloggin(user);
		
		//TODO check product
		
		this.userController.addProduct(product, user);
		
	}
	
	//TODO method
	public void makeOrder(User user) throws LoggedException{
		this.checkForloggin(user);
		
		Order order = this.userController.makeOrder(user);
		System.out.println("User("+ user.getKey() + ") made a order");
	}
	
	//TODO delete method
	public User find(String username, String password) throws InvalidPasswordEcxeption{
		return this.userController.identify(username, password);
	}
	
	private void checkForloggin(User user) throws LoggedException{
		
		if(!this.leggedUsers.containsKey(user.getKey())){
			throw new LoggedException("You have to logged in");
		}
	}
}
