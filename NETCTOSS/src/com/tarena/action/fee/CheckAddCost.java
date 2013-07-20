package com.tarena.action.fee;

import java.sql.SQLException;

import com.opensymphony.xwork2.ActionContext;
import com.tarena.UtilBag.BaseAware;
import com.tarena.UtilBag.DAOFactory;


public class CheckAddCost extends BaseAware{
	private String name;
	private boolean flag;
	
	public String execute(){
		//ActionContext.getContext().getValueStack().push(name);
		//成员变量是否会自动调入valuestack 
		try {
			flag = new DAOFactory().getCostDAO().find_By_Name(name);
			
			return "success";
			
				
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";	
		}
		
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}
