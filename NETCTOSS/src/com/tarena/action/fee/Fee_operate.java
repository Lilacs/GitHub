package com.tarena.action.fee;

import java.sql.SQLException;

import com.tarena.UtilBag.DAOFactory;
import com.tarena.po.Cost;

public class Fee_operate {
	private Cost cost;
	private int id;
	public String add(){
		return "add";
		
	}
	public String modi(){
		try {
			cost = new DAOFactory().getCostDAO().find_By_Id(id);
			return "modi";
		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
		}
		
	}
	public String detail(){
		try {
			cost = new DAOFactory().getCostDAO().find_By_Id(id);
			return "detail";
		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
		}
	}
	public Cost getCost() {
		return cost;
	}
	public void setCost(Cost cost) {
		this.cost = cost;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
