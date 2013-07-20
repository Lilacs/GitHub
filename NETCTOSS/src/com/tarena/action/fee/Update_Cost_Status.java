package com.tarena.action.fee;

import java.sql.SQLException;

import com.tarena.UtilBag.DAOFactory;

public class Update_Cost_Status {
	private int id;
	public String execute(){
		System.out.println(id);
		try {
			new DAOFactory().getCostDAO().update_status(id);
			return "success";
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
