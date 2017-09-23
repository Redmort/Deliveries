package interfaces;

import entities.catalogs.Catalog;
import entities.orders.Order;
import entities.products.Product;

public interface IDelivarable extends Iterable<Product> {

	void registerOrder(Order order);
	
	Catalog giveCatalog();
}
