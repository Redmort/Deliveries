package entities.users;

import java.util.ArrayList;
import java.util.List;

import entities.orders.Order;
import entities.products.Product;
import exceptions.EmptyShoppingBagException;
import interfaces.Unique;

public class User implements Unique {

	private static int ID = 0;

	private int id;

	private String username;
	
	
	
	
	private List<Product> shoppingBag;

	public User(String userName) {

		this.id = ++User.ID;
		this.username = userName;
		this.shoppingBag = new ArrayList<>();
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
	
	public Order makeOrder() throws EmptyShoppingBagException{
		return new Order(giveProducts());
		
	}
	
	private List<Product> giveProducts() throws EmptyShoppingBagException{
		
		if(this.shoppingBag.isEmpty()){
			throw new EmptyShoppingBagException("Your shopping bag is empty");
		}
		
		List<Product> products = new ArrayList<>();
		products.addAll(this.shoppingBag);
		this.shoppingBag.clear();
		return products;
	}

}
