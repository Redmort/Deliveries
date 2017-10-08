package entities.supplier;

import java.util.HashSet;
import java.util.Set;

import entities.onlinePlatforms.OnlinePlatform;
import entities.orders.Order;

public class Supplier {
	private String name;
	
	private static int idNumber = 0;
	private Set<Order> orders;
	private OnlinePlatform site;
	
	public Supplier (String name, OnlinePlatform theSite){
		this.name=name;
		this.site= theSite;
		this.orders= new HashSet <Order>();
		this.idNumber++;
	}
	
	public void addOrder(Order order){
		if(order != null){
			this.orders.add(order);
		}
		
	}
	
	
	public static int getIdNumber() {
		return idNumber;
	}
	
}
