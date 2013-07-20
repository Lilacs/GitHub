package com.tarena.action.role;

import java.sql.SQLException;

import com.tarena.UtilBag.DAOFactory;

public class DeleteRole {
	private int id;
	public String execute(){
		try {
			boolean flag = new DAOFactory().getRole_infoDAO().delete_Role_info(id);
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
