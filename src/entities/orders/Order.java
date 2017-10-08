package entities.orders;

import java.util.List;

import entities.products.Product;


public class Order {

	private List<Product> products;

	public Order(List<Product> products) {
		this.products = products;
		
	}

}
