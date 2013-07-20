package com.tarena.action.role;

import java.sql.SQLException;
import java.util.List;

import com.tarena.UtilBag.DAOFactory;
import com.tarena.po.Role_info;

public class Create_role {
	private Role_info info;
	private int[] checkValue;

	public String execute() {
		try {
			boolean flag = new DAOFactory().getRole_infoDAO().create_Role_info(
					info, checkValue);
			if (flag) {
				return "success";
			}else 
				return "fail";
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}

	}

	public Role_info getInfo() {
		return info;
	}

	public void setInfo(Role_info info) {
		this.info = info;
	}

	public int[] getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(int[] checkValue) {
		this.checkValue = checkValue;
	}

}
