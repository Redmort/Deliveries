package entities.roles;

import interfaces.Unique;

public class Role implements Unique{

	private int id;
	private String roleType;
	

	public Role(int id, String roleType) {
		this.id = id;
		this.roleType = roleType;
	}


	@Override
	public String getKey() {
		return "" + this.id;
	}


	public String getRoleType() {
		return roleType;
	}


	public int getId() {
		return id;
	}
	
	
	
	

}
