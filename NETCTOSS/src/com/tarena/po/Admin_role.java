package com.tarena.po;

import java.io.Serializable;

public class Admin_role implements Serializable {
	// 管理员角色表
	private int admin_id;
	private int role_id;

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

}
