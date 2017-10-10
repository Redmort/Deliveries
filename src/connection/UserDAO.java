package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import entities.users.User;

import exceptions.ExistedUserExceprion;
import exceptions.MyException;

public class UserDAO implements IUserDAO {

	private static final int USER_ROLE_ID = 2;
	private Connection con = DBConnection.getInstance().getConnection();

	@Override
	public int addUser(User user, int passId) throws ExistedUserExceprion{
		
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO users VALUES(null, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getAddress());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getEmail());
			ps.setInt(6, USER_ROLE_ID);
			ps.setInt(7, passId);
			
			ps.executeUpdate();		
			
			ResultSet id = ps.getGeneratedKeys();
			
			id.next();
			
			return id.getInt(1);
			
		} catch (SQLException e) {
			throw new ExistedUserExceprion("Can't add this user", e);			
		}
	}
	
	@Override
	public void removeUser(int id){
		
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id = ?");
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
			System.out.println("User with id: " + id + " deleted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
	
	@Override
	public User getUserById(String username) throws MyException{
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ? ");
			
			ps.setString(1, username);
			
			ResultSet result = ps.executeQuery();
			result.next();
			int userId = result.getInt(1);
			return new User(userId, result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
			
			
		} catch (SQLException e) {
			throw new MyException("Can't get this user");
		}
		
	}
	
	public boolean exists(String username){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT*FROM users WHERE username = ?");
			
			ps.setString(1, username);
			
			ResultSet result = ps.executeQuery();
			
			if(result.next()){
				return true;
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public String getRole(int id) throws MyException {
		
			PreparedStatement ps;
			try {
				ps = con.prepareStatement("SELECT r.type, u.id FROM roles as r JOIN users as u ON ( r.id = u.roles_id) WHERE u.id = ?");
				ps.setInt(1, id);
				
				ResultSet result = ps.executeQuery();
				result.next();
				return result.getString(1);
				
			} catch (SQLException e) {
			
				e.printStackTrace();
				throw new MyException("Can't get this role");
			}
			
		
		
		
	}
	
	
}
