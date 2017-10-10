package entities.users;

import java.util.ArrayList;
import java.util.List;

import entities.orders.Order;
import entities.products.Product;
import exceptions.EmptyShoppingBagException;
import interfaces.Unique;

public class User implements Unique {

	// private static int ID = 0;

	private int id;
	private String username;
	private String address;
	private String firstName;
	private String lastName;
	private String email;
	

	private List<Product> shoppingBag;

	public User(String username) {
		super();
		this.username = username;
		this.shoppingBag = new ArrayList<>();
	}

	public User(String username, String address, String firstName, String lastName, String email) {

		this.username = username;
		this.address = address;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;

		this.shoppingBag = new ArrayList<>();
	}

	public User(int id, String username, String address, String firstName, String lastName, String email) {
		this(username, address, firstName, lastName, email);
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public String getKey() {
		return this.username;
	}

	public int getID() {
		return this.id;
	}

	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		if (product != null) {
			this.shoppingBag.add(product);
		}
	}

	public Order makeOrder() throws EmptyShoppingBagException {
		return new Order(giveProducts(), this);

	}

	private List<Product> giveProducts() throws EmptyShoppingBagException {

		if (this.shoppingBag.isEmpty()) {
			throw new EmptyShoppingBagException("Your shopping bag is empty");
		}

		List<Product> products = new ArrayList<>();
		products.addAll(this.shoppingBag);
		this.shoppingBag.clear();
		return products;
	}

	@Override
	public String toString() {

		return this.address + this.username;
	}

}
