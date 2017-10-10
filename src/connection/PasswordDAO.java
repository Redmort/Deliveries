package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.MyException;

public class PasswordDAO {

	private Connection con = DBConnection.getInstance().getConnection();

	public int addPassword(String password) throws Exception {
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO passwords VALUES(null, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, password);

			ps.executeUpdate();

			ResultSet id = ps.getGeneratedKeys();
			id.next();
			return id.getInt(1);

		} catch (SQLException e) {
			throw new Exception(e);
		}
	}

	public void removePassword(int passId) {

		try {
			PreparedStatement ps1 = con.prepareStatement("DELETE FROM passwords WHERE id = ?");
			ps1.setInt(1, passId);
			ps1.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getPassID(int userId) throws MyException {
		try {
			PreparedStatement ps = con.prepareStatement(
					"SELECT p.id, u.id FROM passwords as p JOIN users as u ON (p.id = u.passwords_id) WHERE u.id =?");
			ps.setInt(1, userId);

			ResultSet result = ps.executeQuery();
			result.next();
			return result.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("Can't get this passId");
		}
	
	}
	
	public String getPassword(int userId) throws MyException{
		int passId = this.getPassID(userId);
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT p.password FROM passwords as p WHERE p.id = ?");
			ps.setInt(1, passId);
			
			ResultSet result = ps.executeQuery();
			
			result.next();
			return result.getString(1);
			
		} catch (SQLException e) {
			throw new MyException("Can't get password");
		}
	}

}
