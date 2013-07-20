package com.tarena.action.role;

import java.sql.SQLException;

import com.tarena.UtilBag.DAOFactory;

public class CheckRoleName {
	private boolean flag;
	private String name;
	//创建新角色时用来验证名称是否存在
	public String addName() {
		try {
			flag = new DAOFactory().getRole_infoDAO().find_Role_info_name(name);
			return "success";
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}

	// 修改的时候判断名称是否存在
	public String midoName() {
		try {
			flag = new DAOFactory().getRole_infoDAO().modiName(name);
			return "success";
		} catch (SQLException e) {
			e.printStackTrace();
			return "success";
		}
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
