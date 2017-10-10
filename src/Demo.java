import java.util.Scanner;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import entities.onlinePlatforms.OnlinePlatform;
import entities.users.User;
import enums.RoleType;
import exceptions.EmptyShoppingBagException;
import exceptions.ExistedProductExceprion;
import exceptions.ExistedUserExceprion;
import exceptions.InvalidAccessException;
import exceptions.InvalidPasswordEcxeption;
import exceptions.LoggedException;
import exceptions.MyException;
import exceptions.NullRepositoryObjecException;
import exceptions.UnexistUserException;
import exceptions.ValidationException;

public class Demo {

	private static OnlinePlatform onlinePlatform = new OnlinePlatform();
	private static Scanner scanner = new Scanner(System.in);
	private static User user = new User("Default");

	public static void main(String[] args) {

		System.out.println("Insert command:");
		String input = scanner.nextLine();

		while (input.toLowerCase() != "end") {
			switch (input.toLowerCase()) {
			case "register":
				try {
					registerUser();
				} catch (NullRepositoryObjecException | ValidationException | ExistedUserExceprion
						| InvalidPasswordEcxeption e) {
					System.out.println(e.getMessage());
				}
				break;

			case "login":
				try {
					onlinePlatform.logout(user);
					loggin();
				} catch (InvalidPasswordEcxeption | MyException e) {
					System.out.println(e.getMessage());
				}
				break;

			case "remove user":
				try {
					removeUser();
				} catch (LoggedException | UnexistUserException | InvalidPasswordEcxeption | InvalidAccessException
						| ValidationException | MyException e) {
					System.out.println(e.getMessage());
					break;

				}

			case "set role admin":
				try {
					setRole(RoleType.ADMIN);
				} catch (ValidationException | LoggedException | UnexistUserException | MyException
						| InvalidAccessException e) {
					System.out.println(e.getMessage());
				}
				break;

			case "set role user":
				try {
					setRole(RoleType.USER);
				} catch (ValidationException | LoggedException | UnexistUserException | MyException
						| InvalidAccessException e) {
					System.out.println(e.getMessage());
				}
				break;

			case "create product":

				try {
					createProduct();
				} catch (ValidationException | LoggedException | MyException | InvalidAccessException
						| ExistedProductExceprion e) {
					System.out.println(e.getMessage());
				}

				break;

			case "add product":

				try {
					addProduct();
				} catch (LoggedException | ValidationException | MyException | ExistedProductExceprion e) {
					System.out.println(e.getMessage());
				}
				break;

			case "make order":
				try {
					makeOrder();
				} catch (LoggedException | ValidationException | EmptyShoppingBagException e) {
					System.out.println(e.getMessage());
				}
				break;

			case "show products":

				try {
					showProducts();
				} catch (MyException e) {
					System.out.println(e.getMessage());
				}

				break;

			case "show orders":

				try {
					showAllOrders();
				} catch (MyException | UnexistUserException e) {
					System.out.println(e.getMessage());
				}

			case "show user orders":

				try {
					showOrdesByUser();
				} catch (MyException | UnexistUserException e) {
					System.out.println(e.getMessage());
				}

				break;
				
			case "logout":
				
				onlinePlatform.logout(user);
				user = new User("Default");
				
				break;

			case "help":
				System.out.println(
						"Commands:\nshow user orders\nshow orders\nshow products\nmake order\nadd product\ncreate product\nset role user"
								+ "\nset role admin\nremove user\nlogin\nregister\nlogout");
				break;
			default:
				System.out.println("Invalid command");
				break;
			}

			System.out.println("Insert command:");
			input = scanner.nextLine();
		}

	}

	private static void showOrdesByUser() throws MyException, UnexistUserException {

		System.out.println("Insert username:");
		String name = scanner.nextLine();

		onlinePlatform.showOrdersByUsername(name);

	}

	private static void showAllOrders() throws MyException, UnexistUserException {
		onlinePlatform.showAllOrders();

	}

	private static void showProducts() throws MyException {
		onlinePlatform.showAllProducts();

	}

	private static void makeOrder() throws LoggedException, ValidationException, EmptyShoppingBagException {

		onlinePlatform.makeOrder(user);
		System.out.println("Order was sent successfully");

	}

	private static void addProduct() throws LoggedException, ValidationException, MyException, ExistedProductExceprion {
		System.out.println("Insert name Of pizza:");
		String name = scanner.nextLine();
		System.out.println("Insert size Of pizza:");
		String size = scanner.nextLine();

		onlinePlatform.addProduct(name, size, user);
		System.out.println("Product was added successfully");

	}

	private static void createProduct()
			throws ValidationException, LoggedException, MyException, InvalidAccessException, ExistedProductExceprion {

		System.out.println("Insert name Of pizza:");
		String nameOfPizza = scanner.nextLine();
		System.out.println("Insert size Of pizza:");
		String sizeOfPizza = scanner.nextLine();
		System.out.println("Insert price:");
		int price = Integer.parseInt(scanner.nextLine());
		System.out.println("Insert descriprion:");
		String descriprion = scanner.nextLine();

		onlinePlatform.createProduct(user, nameOfPizza, sizeOfPizza, price, descriprion);
		System.out.println("Product was created successfully");

	}

	private static void setRole(RoleType roleType)
			throws ValidationException, LoggedException, UnexistUserException, MyException, InvalidAccessException {
		System.out.println("Insert username:");
		String username = scanner.nextLine();

		onlinePlatform.setRole(user, username, roleType);
		System.out.println("Role was set successfully");

	}

	private static void removeUser() throws LoggedException, UnexistUserException, InvalidPasswordEcxeption,
			InvalidAccessException, ValidationException, MyException {
		System.out.println("Insert username for removing");
		String username = scanner.nextLine();

		onlinePlatform.remove(user, username);

	}

	private static void loggin() throws InvalidPasswordEcxeption, MyException {
		System.out.println("Insert username:");
		String username = scanner.nextLine();
		System.out.println("Insert password:");
		String password = scanner.nextLine();

		onlinePlatform.login(username, password);

		user = onlinePlatform.find(username, password);
		System.out.println("Successful login");

	}

	private static void registerUser()
			throws NullRepositoryObjecException, ValidationException, ExistedUserExceprion, InvalidPasswordEcxeption {
		System.out.println("Insert username:");
		String username = scanner.nextLine();
		System.out.println("Insert password:");
		String password = scanner.nextLine();
		System.out.println("Insert address:");
		String address = scanner.nextLine();
		System.out.println("Insert first name:");
		String fName = scanner.nextLine();
		System.out.println("Insert last name:");
		String lName = scanner.nextLine();
		System.out.println("Insert email:");
		String email = scanner.nextLine();

		onlinePlatform.register(username, password, address, fName, lName, email);
	}

}
