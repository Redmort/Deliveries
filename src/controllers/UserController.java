package controllers;

import java.util.List;
import java.util.Random;

import connection.IRoleDAO;
import connection.IUserDAO;
import connection.OrderDAO;
import connection.PasswordDAO;
import connection.ProductDAO;
import connection.RoleDAO;
import connection.UserDAO;
import entities.onlinePlatforms.OnlinePlatform;
import entities.orders.Order;
import entities.products.Product;
import entities.users.User;
import enums.PizzaType;
import enums.RoleType;
import enums.SizeType;
import exceptions.EmptyShoppingBagException;
import exceptions.ExistedProductExceprion;
import exceptions.ExistedUserExceprion;
import exceptions.InvalidAccessException;
import exceptions.InvalidPasswordEcxeption;
import exceptions.MyException;
import exceptions.NullRepositoryObjecException;
import exceptions.UnexistUserException;
import exceptions.ValidationException;

public class UserController {

	private String secretPass;
	// private OnlinePlatform platform;
	// private Repository<User> users;
	// private Repository<Password> passwords;
	// private Repository<Role> roles;

	private IUserDAO userDAO = new UserDAO();
	private PasswordDAO passDAO = new PasswordDAO();
	private IRoleDAO roleDAO = new RoleDAO();
	private ProductDAO productDAO = new ProductDAO();
	private OrderDAO orderDAO = new OrderDAO();

	public UserController(OnlinePlatform platform) {
		this.secretPass = this.encodePass(this.generatePass());
		// this.platform = platform;
		// this.users = new Repository<User>(this.decodePass(this.secretPass));
		// this.passwords = new
		// Repository<Password>(this.decodePass(this.secretPass));
		// this.roles = new Repository<Role>(this.decodePass(this.secretPass));
	}

	private String decodePass(String pass) {
		// TODO decode password
		return pass;
	}

	private String encodePass(String pass) {
		// TODO encode password
		return pass;
	}

	private String generatePass() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			sb.append((char) (new Random().nextInt(75) + 48));
		}
		return sb.toString();
	}

	public void create(String... args)
			throws NullRepositoryObjecException, ValidationException, ExistedUserExceprion, InvalidPasswordEcxeption {

		// try {
		// this.users.checkForExisting(args[0]);
		// } catch (UnexistUserException e) {
		// User user = new User(args[0]);
		// this.users.add(user, this.secretPass);
		// this.passwords.add(new Password(user.getID(), args[1]),
		// this.secretPass);
		// this.roles.add(new Role(user.getID(), RoleType.USER),
		// this.secretPass);
		// System.out.println("User: " + args[0] + " was created successfully");
		// return;
		// }

		if (this.userDAO.exists(args[0])) {
			throw new ExistedUserExceprion("User with user name " + args[0] + " already exist");
		}

		User user = new User(args[0], args[2], args[3], args[4], args[5]);

		int passId;
		try {
			passId = this.passDAO.addPassword(args[1]);
			this.userDAO.addUser(user, passId);
			System.out.println("User with username: " + user.getUsername() + " created");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public User identify(String username, String password) throws InvalidPasswordEcxeption, MyException {
		// User user;
		// try {
		// user = this.users.find(username, this.secretPass);
		// } catch (UnexistUserException e) {
		//
		// throw new InvalidPasswordEcxeption("Invalid password or username",
		// e);
		// }

		if (!this.userDAO.exists(username)) {
			throw new InvalidPasswordEcxeption("Invalid password or username");
		}

		User user1 = this.userDAO.getUserById(username);

		String pass1 = this.passDAO.getPassword(user1.getID());
		if (!pass1.equals(password)) {
			throw new InvalidPasswordEcxeption("Invalid password or username");
		}

		// try {
		// Password pass = this.passwords.find("" + user.getID(),
		// this.secretPass);
		// if (!pass.getPassword().equals(password)) {
		// throw new InvalidPasswordEcxeption("Invalid password or username");
		// }
		// } catch (UnexistUserException e) {
		//
		// throw new InvalidPasswordEcxeption("Invalid password or username",
		// e);
		// }

		return user1;

	}

	public void remove(User admin, String username) throws UnexistUserException, InvalidAccessException, MyException {
		// this.users.checkForExisting(username);
		if (!this.userDAO.exists(username)) {
			throw new UnexistUserException("User with username: " + username + "doesn't exist");
		}

		// int userId = this.users.find(username, secretPass).getID();
		User user = this.userDAO.getUserById(username);
		String roleType = this.userDAO.getRole(admin.getID());

		// if((admin.getID() != user.getID()) &&
		// (!RoleType.ADMIN.equals(this.roles.find("" + userId,
		// this.secretPass)))) {
		// throw new InvalidAccessException("Access denied");
		// }

		if ((admin.getID() != user.getID()) && (!RoleType.ADMIN.name().equals(roleType))) {
			throw new InvalidAccessException("Access denied");
		}

		// this.users.remove(username, this.secretPass);
		// this.passwords.remove("" + userId, this.secretPass);
		// this.roles.remove("" + userId, this.secretPass);

		int passId = this.passDAO.getPassID(user.getID());
		this.userDAO.removeUser(user.getID());
		this.passDAO.removePassword(passId);

		System.out.println("User with username " + username + " was removed successfully");
	}

	public void addProduct(String name, String size, User user) throws MyException, ExistedProductExceprion {
		// TODO Auto-generated method stub
		
		user.addProduct(this.productDAO.getProduct(name, size));

	}

	public Order makeOrder(User user) throws EmptyShoppingBagException {
		
		Order order = user.makeOrder();
		
		this.orderDAO.addOrder(order);
		
		return order;
	}

	public void setRole(User admin, String username, RoleType type)
			throws UnexistUserException, MyException, InvalidAccessException {
		if (!this.userDAO.exists(username)) {
			throw new UnexistUserException("User with username: " + username + "doesn't exist");
		}

		User user = this.userDAO.getUserById(username);
		String roleType = this.userDAO.getRole(admin.getID());

		if (!RoleType.ADMIN.name().equals(roleType)) {
			throw new InvalidAccessException("Access denied");
		}

		this.roleDAO.setRole(type, user.getID());

	}

	public void createProduct(User admin, PizzaType nameOfPizza, SizeType sizeOfPizza)
			throws MyException, InvalidAccessException, ExistedProductExceprion {

		String role = this.userDAO.getRole(admin.getID());

		if (!RoleType.ADMIN.name().equals(role)) {
			throw new InvalidAccessException("Access denied");
		}

		if (this.productDAO.exists(nameOfPizza.name(), sizeOfPizza.name())) {
			throw new ExistedProductExceprion("This product already exist");
		}

		Product product = new Product(nameOfPizza, sizeOfPizza);

		this.productDAO.addProduct(product);

	}

	public void createProduct(User admin, String nameOfPizza, String sizeOfPizza, int price, String descriprion)
			throws MyException, InvalidAccessException, ExistedProductExceprion {

		String role = this.userDAO.getRole(admin.getID());

		if (!RoleType.ADMIN.name().equals(role)) {
			throw new InvalidAccessException("Access denied");
		}

		if (this.productDAO.exists(nameOfPizza, sizeOfPizza)) {
			throw new ExistedProductExceprion("This product already exist");
		}

		Product product = new Product(nameOfPizza, sizeOfPizza, price, descriprion);

		this.productDAO.addProduct(product);

	}

	public void deleteProduct(User admin, String nameOfPizza, String sizeOfPizza) throws MyException, InvalidAccessException, ExistedProductExceprion {
		String role = this.userDAO.getRole(admin.getID());

		if (!RoleType.ADMIN.name().equals(role)) {
			throw new InvalidAccessException("Access denied");
		}
		
		this.productDAO.removeProduct(nameOfPizza, sizeOfPizza);

	}

	public List<Product> getAllProducts() throws MyException {
		return this.productDAO.getAllProducts();
		
	}

	public void showOrdersByUsername(String name) throws MyException, UnexistUserException {
		
		if (!this.userDAO.exists(name)) {
			throw new UnexistUserException("User with username: " + name + "doesn't exist");
		}
		
		User user = this.userDAO.getUserById(name);
		
		this.orderDAO.showAllOrdersByUser(user);
		
	}
	
	public void showAllOrders() throws MyException, UnexistUserException{
		List<String> usernames = this.orderDAO.getAllUsersMadeOrders();
		
		for (String name : usernames) {
			System.out.println("----------------- " + name + " ---------------------");
			this.showOrdersByUsername(name);
			System.out.println("-----------------------------------------------");
			System.out.println();
		}
	}

}
