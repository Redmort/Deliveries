package connection;

import entities.roles.Role;
import enums.RoleType;
import exceptions.MyException;
import exceptions.RoleException;

public interface IRoleDAO {

	void addRole(RoleType type) throws RoleException;

	void removeRole(int roleId);

	Role getRoleByType(RoleType type) throws Exception;
	
	void setRole(RoleType type, int userId) throws MyException; 

}