package entities.orders;

import java.util.List;

import entities.products.Product;
import entities.users.User;


public class Order {

	private User user;
	private List<Product> products;

	public Order(List<Product> products, User user) {
		this.products = products;
		this.user = user;
		
	}

	public User getUser() {
		return user;
	}

	public List<Product> getProducts() {
		return products;
	}
	
	

}
