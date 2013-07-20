package com.tarena.action.role;

import java.sql.SQLException;

import com.tarena.UtilBag.DAOFactory;

public class RoleUpdate {
	private int[] newability;
	private String oldname;
	private String name;
	public String execute(){
		try {
			boolean flag = new DAOFactory().getRole_infoDAO().modify_Role_info(name, oldname, newability);
			if(flag){
				return "success";
			}else{
				return "fail";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}
	public int[] getNewability() {
		return newability;
	}
	public void setNewability(int[] newability) {
		this.newability = newability;
	}
	public String getOldname() {
		return oldname;
	}
	public void setOldname(String oldname) {
		this.oldname = oldname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
