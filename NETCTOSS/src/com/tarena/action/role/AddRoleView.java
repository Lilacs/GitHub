package com.tarena.action.role;

import java.util.List;

import com.tarena.DAO.CheckBoxListDAO;
import com.tarena.po.CheckBoxList;

public class AddRoleView {
	private List<CheckBoxList> checkList;
	public String execute() {
		checkList = new CheckBoxListDAO().AllAbility();
		return "success";
	}

	public List<CheckBoxList> getCheckList() {
		return checkList;
	}

	public void setCheckList(List<CheckBoxList> checkList) {
		this.checkList = checkList;
	}

}
