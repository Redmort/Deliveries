
import java.sql.Connection;

import connection.DBConnection;
import connection.UserDAO;
import entities.onlinePlatforms.OnlinePlatform;
import entities.products.Product;
import entities.supplier.Supplier;
import entities.users.User;
import enums.PizzaType;
import enums.SizeType;
import exceptions.EmptyShoppingBagException;
import exceptions.ExistedUserExceprion;
import exceptions.InvalidAccessException;
import exceptions.InvalidPasswordEcxeption;
import exceptions.LoggedException;
import exceptions.NullRepositoryObjecException;
import exceptions.SupplierException;
import exceptions.UnexistUserException;
import exceptions.ValidationException;


public class Main {

	public static void main(String[] args) throws Exception {
		
		UserDAO userDAO = new UserDAO();
		
		
		OnlinePlatform onlinePlatform = new OnlinePlatform();
		try {
			onlinePlatform.addSupplier(new Supplier("Mitko", onlinePlatform));
		} catch (SupplierException e1) {
			System.out.println(e1.getMessage());
		}
		
		
//		User ivan = new User("Gencho2", "sofia", "gencho", "pitonq", "pitonq@abv.bg2");	
//		User ivan1 = new User("Gencho1", "sofia", "gencho", "pitonq", "pitonq@abv.bg1");	
		
	
//		userDAO.addUser(ivan);
		
		
//		int id = userDAO.addUser(ivan);
		
	
		User gencho = userDAO.getUserById("Gencho2");
		int id = gencho.getID();
		userDAO.removeUser(id);
		
		try {
			onlinePlatform.register("Plamen", "111111");
		} catch (NullRepositoryObjecException | ValidationException | ExistedUserExceprion | InvalidPasswordEcxeption e) {
			System.out.println(e.getMessage());
		}
		
		try {
			onlinePlatform.register("Plamen1", "111111");
		} catch (NullRepositoryObjecException | ValidationException | ExistedUserExceprion | InvalidPasswordEcxeption e) {
			System.out.println(e.getMessage());
		}
		
		try {
			User plamen = onlinePlatform.find("Plamen", "111111");
			onlinePlatform.login("Plamen", "111111");
			try {
				try {
					onlinePlatform.remove(plamen, "Plamen1");
				} catch (UnexistUserException | InvalidAccessException | ValidationException e) {
					System.out.println(e.getMessage());
				}
				
				onlinePlatform.addProduct(PizzaType.BURGER_PIZZA.name(), SizeType.FAMILY.name(), plamen);
				onlinePlatform.makeOrder(plamen);
			} catch (LoggedException | ValidationException | EmptyShoppingBagException e) {
				System.out.println(e.getMessage());
			}
		} catch (InvalidPasswordEcxeption e) {
			System.out.println(e.getMessage());
		}

		
		
		String debug = "";
	}

}
