
import entities.onlinePlatforms.OnlinePlatform;
import entities.products.Product;
import entities.users.User;
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
				
		try {
			onlinePlatform.register("Plamen", "861154");
		} catch (NullRepositoryObjecException | ValidationException | ExistedUserExceprion e) {
			System.out.println(e.getMessage());
		}
		
		try {
			onlinePlatform.register("Plamen1", "861154");
		} catch (NullRepositoryObjecException | ValidationException | ExistedUserExceprion e) {
			System.out.println(e.getMessage());
		}
		
		try {
			User plamen = onlinePlatform.find("Plamen", "861154");
			onlinePlatform.login("Plamen", "861154");
			try {
				try {
					onlinePlatform.remove(plamen, "Plamen1");
				} catch (UnexistUserException | InvalidAccessException e) {
					System.out.println(e.getMessage());
				}
				
				onlinePlatform.AddProduct(new Product(), plamen);
				onlinePlatform.makeOrder(plamen);
			} catch (LoggedException e) {
				System.out.println(e.getMessage());
			}
		} catch (InvalidPasswordEcxeption e) {
			System.out.println(e.getMessage());
		}

		
		
		String debug = "";
	}

}
