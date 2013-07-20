package com.tarena.action.fee;

import java.sql.SQLException;

import com.tarena.UtilBag.DAOFactory;



public class Deletcost {
	private int id;
	public String execute(){
		try {
			new DAOFactory().getCostDAO().delete_cost(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "success";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
