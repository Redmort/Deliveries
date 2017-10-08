package entities.onlinePlatforms;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import controllers.UserController;
import entities.orders.Order;
import entities.products.Product;
import entities.supplier.Supplier;
import entities.users.User;
import exceptions.EmptyShoppingBagException;
import exceptions.ExistedUserExceprion;
import exceptions.InvalidAccessException;
import exceptions.InvalidPasswordEcxeption;
import exceptions.LoggedException;
import exceptions.NullRepositoryObjecException;
import exceptions.SupplierException;
import exceptions.UnexistUserException;
import exceptions.ValidationException;
import interfaces.IDelivarable;

public class OnlinePlatform {
	private Set<IDelivarable> shops;

	private UserController userController;	
	private Map<String, User> loggedUsers;
	private Map <Integer , Supplier> suppliers;
	

	public OnlinePlatform() {

		this.userController = new UserController(this);

		this.loggedUsers = new HashMap<>();
		this.suppliers= new HashMap<>();
	}
	
	public void addSupplier(Supplier sp) throws SupplierException{
		if (sp != null){
			this.suppliers.put(sp.getIdNumber(), sp);
		}
		else{
			throw new SupplierException ("Your supplier doesnt exsist");
		}
	
	}

	public void login(String username, String password) throws InvalidPasswordEcxeption {

		User user = this.userController.identify(username, password);

		this.loggedUsers.put(user.getKey(), user);
	}

	public void register(String... args)
			throws NullRepositoryObjecException, ValidationException, ExistedUserExceprion, InvalidPasswordEcxeption {
		this.userController.create(args);
	}

	public void remove(User admin, String username)
			throws LoggedException, UnexistUserException, InvalidPasswordEcxeption, InvalidAccessException, ValidationException {

		this.valideteInput(admin, username);
		
		this.checkForloggin(admin);

		this.userController.remove(admin, username);
		
		this.loggedUsers.remove(username);
	}

	public void addProduct(Product product, User user) throws LoggedException, ValidationException {

		this.valideteInput(product, user);
		
		this.checkForloggin(user);

		// TODO check product

		this.userController.addProduct(product, user);

	}

	// TODO method
	public void makeOrder(User user) throws LoggedException, ValidationException, EmptyShoppingBagException {

		this.valideteInput(user);
		
		this.checkForloggin(user);

		Order order = this.userController.makeOrder(user);

		Supplier sp = this.suppliers.get((int) (Math.random() * this.suppliers.size() + 1));
		
		sp.addOrder(order);
		System.out.println("User("+ user.getKey() + ") made a order");

	}

	// TODO delete method
	public User find(String username, String password) throws InvalidPasswordEcxeption {
		return this.userController.identify(username, password);
	}

	private void checkForloggin(User user) throws LoggedException {

		if (!this.loggedUsers.containsKey(user.getKey())) {
			throw new LoggedException("You have to log in");
		}
	}

	private void valideteInput(Object... object) throws ValidationException {
		
		for (Object obj : object) {
			if (obj == null) {
				throw new ValidationException("Null");
			}
		}		
	}
}
