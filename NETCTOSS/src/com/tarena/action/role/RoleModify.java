package com.tarena.action.role;

import java.sql.SQLException;
import java.util.List;

import com.tarena.DAO.CheckBoxListDAO;
import com.tarena.UtilBag.DAOFactory;
import com.tarena.po.CheckBoxList;

public class RoleModify {
	private List<CheckBoxList> checkList;
	private int[] newability;
	private int id;
	private String name;
	public String execute() {
		checkList = new CheckBoxListDAO().AllAbility();
		newability = new int[checkList.size()];
		try {
			List<Integer> list = new DAOFactory().getRole_privilegeDAO().getRole_privilege_ID(id);
			for(int i=0;i<list.size();i++){
				newability[i] = list.get(i);
			}
			name = new DAOFactory().getRole_infoDAO().getName(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "success";
	}

	public List<CheckBoxList> getCheckList() {
		return checkList;
	}

	public void setCheckList(List<CheckBoxList> checkList) {
		this.checkList = checkList;
	}

	public int[] getNewability() {
		return newability;
	}

	public void setNewability(int[] newability) {
		this.newability = newability;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	// public static void main(String[] args) {
	// RoleModify r = new RoleModify();
	// r.execute();
	// }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
