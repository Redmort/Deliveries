package entities.roles;

import enums.RoleType;
import interfaces.Unique;

public class Role implements Unique{

	private int id;
	private RoleType roleType;
	

	public Role(int id, RoleType roleType) {
		this.id = id;
		this.roleType = roleType;
	}


	@Override
	public String getKey() {
		return "" + this.id;
	}


	public RoleType getRoleType() {
		return roleType;
	}
	
	

}
