package entities.products;

import enums.PizzaType;
import enums.SizeType;

public class Product {
	
	private int id;
	private String name;
	private int price;
	private String description;
	private String size;
	
	
	public Product(PizzaType nameOfPizza, SizeType sizeOfPizza) {
		this.name = nameOfPizza.name();
		this.size = sizeOfPizza.name();
		this.price = generatePrice(nameOfPizza,sizeOfPizza);
		this.description = generateDescription(nameOfPizza);
	}
	

	public Product(String name, String size, int price, String description) {
		
		this.name = name;
		this.price = price;
		this.description = description;
		this.size = size;
	}



	public Product(int id,String name, String size, int price, String description) {
		this(name, size, price, description);
		this.id = id;
	}




	private int generatePrice(PizzaType name2, SizeType size2) {
		double normalPrice;
		final double SMALL_HAWAI_PRICE = 5.5;
		final double SMALL_BURGER_PIZZA_PRICE = 5.7;
		final double SMALL_MARGARITA_PRICE = 4.7;
		final double SMALL_TROPICANA_PRICE = 6.0;
		final double SMALL_ITTALENTS_PIZZA_PRICE = 8.5;
		final double SMALL_MEXICANA_PRICE = 6.9;
		final double SMALL_CHEESE_PIZZA_PRICE = 6.3;
		final double SMALL_VEGETARIANA_PRICE = 4.9;
		final double SMALL_TUNA_FISH_PIZZA_PRICE = 7.2;
		
		switch (name2){
			case HAWAI: normalPrice = SMALL_HAWAI_PRICE;
				break;
			case BURGER_PIZZA: normalPrice = SMALL_BURGER_PIZZA_PRICE;
				break;
			case MARGARITA: normalPrice = SMALL_MARGARITA_PRICE;
				break;
			case TROPICANA: normalPrice = SMALL_TROPICANA_PRICE;
				break;
			case ITTALENTS_PIZZA: normalPrice = SMALL_ITTALENTS_PIZZA_PRICE;
				break;
			case MEXICANA: normalPrice = SMALL_MEXICANA_PRICE;
				break;
			case CHEESE_PIZZA: 
				normalPrice = SMALL_CHEESE_PIZZA_PRICE;
				break;
			case VEGETARIANA: 
				normalPrice = SMALL_VEGETARIANA_PRICE;
				break;
			case TUNA_FISH_PIZZA: 
				normalPrice = SMALL_TUNA_FISH_PIZZA_PRICE;
				break;
			default: normalPrice=0;
				break;
		}
		
		if(size2 == SizeType.FAMILY) {
			final int DIFFRENCE_BETWEEN_SMALL_AND_FAMILY = 3;
			return (int) (normalPrice * DIFFRENCE_BETWEEN_SMALL_AND_FAMILY);
		}
		
		if(size2 == SizeType.LARGE) {
			final double DIFFRENCE_BETWEEN_SMALL_AND_LARGE = 2.2;
			return (int) (normalPrice * DIFFRENCE_BETWEEN_SMALL_AND_LARGE);
		}
		
		if(size2 == SizeType.MEDIUM) {
			final double DIFFRENCE_BETWEEN_SMALL_AND_MEDIUM = 1.4;
			return (int) (normalPrice * DIFFRENCE_BETWEEN_SMALL_AND_MEDIUM);
		}

		return (int) normalPrice;
	}
	
	private String generateDescription(PizzaType name2){
		String descrp="";
		
		switch (name2){
		case HAWAI:
			descrp="Exotic pizza with a special engridient - Pineapple ";
			break;
		case BURGER_PIZZA: 
			descrp= "This is for the meat lovers, our new Burger pizza";
			break;
		case MARGARITA: 
			descrp= "The old classical Margarita";
			break;
		case TROPICANA:
			descrp= "Something little bit spicy for the chilly-lovers";
			break;
		case ITTALENTS_PIZZA:
			descrp="If u eat this, you get +10 Java-scills" ;
			break;
		case MEXICANA:
			descrp= "A little bit of Mexico in our bulgarian pizzas";
			break;
		case CHEESE_PIZZA:
			descrp= "For the frenchies, we got some Cheese pizza";
			break;
		case VEGETARIANA: 
			descrp= "We got something for the vegeterians too, don't worry";
			break;
		case TUNA_FISH_PIZZA: 
			descrp= "You are a pirate ? ohh just a fish-lover,okay. We got pizza with Tuna Fish for you";
			break;
		default: descrp = "";
			break;
	}
		
		
		return descrp;
	}
	
	
	
	public int getId() {
		return id;
	}


	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getSize() {
		return size;
	}
	
	
	@Override
	public String toString() {
		return "~~~~~~~~~~~~~~~~~ " + this.name +" ~~~~~~~~~~~~~~~~~~~\n" +"size: "+ this.size +"\n" + this.description + "\n" + "price: " + this.price;
	}
}
