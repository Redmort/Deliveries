
import entities.onlinePlatforms.OnlinePlatform;
import entities.products.Product;
import entities.users.User;
import exceptions.EmptyShoppingBagException;
import exceptions.ExistedUserExceprion;
import exceptions.InvalidAccessException;
import exceptions.InvalidPasswordEcxeption;
import exceptions.LoggedException;
import exceptions.NullRepositoryObjecException;
import exceptions.UnexistUserException;
import exceptions.ValidationException;


public class Main {

	public static void main(String[] args) {
		
		OnlinePlatform onlinePlatform = new OnlinePlatform();
		User ivan = new User("Ivan");		
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
				
				onlinePlatform.addProduct(new Product(), plamen);
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
