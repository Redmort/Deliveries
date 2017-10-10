package connection;

import entities.users.User;
import exceptions.ExistedUserExceprion;
import exceptions.MyException;

public interface IUserDAO {

	int addUser(User user, int passId) throws  ExistedUserExceprion;

	void removeUser(int id);

	User getUserById(String username) throws MyException;
	
	boolean exists(String username);
	
	String getRole(int id) throws MyException;

}