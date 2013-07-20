package com.tarena.po;

import java.io.Serializable;

public class Role_privilege implements Serializable{
	// 角色权限表
	private int role_id;
	private int privilege_id;

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public int getPrivilege_id() {
		return privilege_id;
	}

	public void setPrivilege_id(int privilege_id) {
		this.privilege_id = privilege_id;
	}

}
