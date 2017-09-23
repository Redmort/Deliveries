package controllers;

import java.util.Random;

import entities.onlinePlatforms.OnlinePlatform;
import entities.orders.Order;
import entities.passwords.Password;
import entities.products.Product;
import entities.roles.Role;
import entities.users.User;
import enums.RoleType;
import exceptions.ExistedUserExceprion;
import exceptions.InvalidAccessException;
import exceptions.InvalidPasswordEcxeption;
import exceptions.LoggedException;
import exceptions.NullRepositoryObjecException;
import exceptions.UnexistUserException;
import exceptions.ValidationException;
import repositories.Repository;

public class UserController {

	private String secretPass;
	private OnlinePlatform platform;
	private Repository<User> users;
	private Repository<Password> passwords;
	private Repository<Role> roles;

	public UserController(OnlinePlatform platform) {
		this.secretPass = this.encodePass(this.generatePass());
		this.platform = platform;
		this.users = new Repository<User>(this.decodePass(this.secretPass));
		this.passwords = new Repository<Password>(this.decodePass(this.secretPass));
		this.roles = new Repository<Role>(this.decodePass(this.secretPass));
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

	public void create(String... args) throws NullRepositoryObjecException, ValidationException, ExistedUserExceprion {

		try {
			this.users.checkForExisting(args[0]);
		} catch (UnexistUserException e) {
			User user = new User(args[0]);
			this.users.add(user);
			this.passwords.add(new Password(user.getID(), args[1]));
			this.roles.add(new Role(user.getID(), RoleType.USER));
			System.out.println("User: " + args[0] + " was created successfully");
			return;
		}

		throw new ExistedUserExceprion("User with user name " + args[0] + " already exist");
	}

	public User identify(String username, String password) throws InvalidPasswordEcxeption {
		User user;
		try {
			user = this.users.find(username, this.secretPass);
		} catch (UnexistUserException e) {

			throw new InvalidPasswordEcxeption("Invalid password or user name", e);
		}
		try {
			Password pass = this.passwords.find("" + user.getID(), this.secretPass);
			if (!pass.getPassword().equals(password)) {
				throw new InvalidPasswordEcxeption("Invalid password or user name");
			}
		} catch (UnexistUserException e) {

			throw new InvalidPasswordEcxeption("Invalid password or user name", e);
		}

		return user;

	}

	public void remove(User admin, String userName)
			throws UnexistUserException, InvalidPasswordEcxeption, InvalidAccessException {
		this.users.checkForExisting(userName);

		int userId = this.users.find(userName, secretPass).getID();

		if((admin.getID() != userId) && (!RoleType.ADMIN.equals(this.roles.find("" + userId, this.secretPass)))) {
			throw new InvalidAccessException("Access denied");
		}
		
		this.users.remove(userName);
		this.passwords.remove("" + userId);
		this.roles.remove("" + userId);
		System.out.println("User with username " + userName + " was removed successfully");
	}

	
	public void addProduct(Product product, User user) {
		// TODO Auto-generated method stub
		
		user.addProduct(product);
		
	}

	public Order makeOrder(User user){
		return user.makeOrder();		
	}
	
	
}
