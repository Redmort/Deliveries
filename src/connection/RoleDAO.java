package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.roles.Role;
import enums.RoleType;
import exceptions.MyException;
import exceptions.RoleException;

public class RoleDAO implements IRoleDAO {

	private Connection con = DBConnection.getInstance().getConnection();

	@Override
	public void addRole(RoleType type) throws RoleException {

		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO roles VALUES(null, ?)");
			ps.setString(1, type.name());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RoleException("Can't add role", e);
		}

	}

	@Override
	public void removeRole(int roleId) {

		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM roles WHERE id = ?");

			ps.setInt(1, roleId);

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Role getRoleByType(RoleType type) throws MyException {
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM roles WHERE type = ?");
			ps.setString(1, type.name());

			ResultSet result = ps.executeQuery();

			result.next();
			int id = result.getInt(1);
			String roleType = result.getString(2);

			return new Role(id, roleType);

		} catch (SQLException e) {
			throw new MyException("can't get this role");
		}

	}

	public void setRole(RoleType type, int userId) throws MyException {

		int id = this.getRoleByType(type).getId();

		try {
			PreparedStatement ps = con.prepareStatement("UPDATE users as u SET  u.roles_id = ? WHERE u.id = ? ");
			ps.setInt(1, id);
			ps.setInt(2, userId);
			
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
