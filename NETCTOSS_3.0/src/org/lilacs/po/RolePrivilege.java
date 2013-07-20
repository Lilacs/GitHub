package org.lilacs.po;

/**
 * ZpRolePrivilege entity. @author MyEclipse Persistence Tools
 */

public class RolePrivilege implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private Integer privilegeId;
	
	public RolePrivilege(){
		
	}
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}

}