package com.tarena.action.fee;

import java.sql.SQLException;

import com.tarena.UtilBag.DAOFactory;
import com.tarena.po.Cost;


public class UpdateCost {
	private Cost cost;
	
	public String execute(){
		System.out.println(cost.getDescr());
		try {
			new DAOFactory().getCostDAO().update_info(cost);
			return "success";
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
		
	}

	public Cost getCost() {
		return cost;
	}


	public void setCost(Cost cost) {
		this.cost = cost;
	}
	
	
}
