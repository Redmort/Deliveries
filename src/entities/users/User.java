package entities.users;

import java.util.ArrayList;
import java.util.List;

import entities.orders.Order;
import entities.products.Product;
import interfaces.Unique;

public class User implements Unique {

	private static int ID = 0;

	private int id;

	private String username;
	
	private List<Product> products;

	public User(String userName) {

		this.id = ++User.ID;
		this.username = userName;
		this.products = new ArrayList<>();
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
			this.products.add(product);
		}
	}
	
	public Order makeOrder(){
		return new Order(giveProducts());
		
	}
	
	private List<Product> giveProducts(){
		List<Product> addedProducts = new ArrayList<>();
		addedProducts.addAll(this.products);
		this.products.clear();
		return addedProducts;
	}

}
