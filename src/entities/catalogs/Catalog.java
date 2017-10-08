package entities.catalogs;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import entities.products.Product;
import enums.PizzaType;
import enums.SizeType;

public class Catalog {
	private Set <PizzaType> catalog;
	private Set <Product> catalogByPrice=new TreeSet<Product>((p1,p2)-> (int)(p1.getPrice() * 100) - (int)(p2.getPrice()*100)); 
	private Set <Product> catalogByName= new TreeSet<Product>((p1,p2)-> p1.getName().compareTo(p2.getName()));
	
	public Catalog() {
		this.catalog = new HashSet<>();
	}
	
	public Set<Product> CatalogByPrice(){
		return catalogByPrice;
	}
	
	public Set<Product> CatalogByNames(){
		return catalogByName;
	}
	
	
	
	
}
