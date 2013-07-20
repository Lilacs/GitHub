package com.tarena.action.role;

import java.sql.SQLException;

import com.tarena.UtilBag.DAOFactory;

public class CheckRoleName {
	private boolean flag;
	private String name;
	//�����½�ɫʱ������֤�����Ƿ����
	public String addName() {
		try {
			flag = new DAOFactory().getRole_infoDAO().find_Role_info_name(name);
			return "success";
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}

	// �޸ĵ�ʱ���ж������Ƿ����
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
