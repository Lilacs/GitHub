package com.tarena.action.fee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import com.sun.tools.internal.ws.processor.model.Response;
import com.tarena.UtilBag.BaseAware;
import com.tarena.UtilBag.DAOFactory;
import com.tarena.po.Cost;


public class Create_cost extends BaseAware {
	private String name;
	private int costtype;
	private int base_duration;
	private double base_cost;
	private double unit_cost;
	private String descr;

	public String execute() {
		if(descr == null || descr.equals("")){
			descr = "没有声明备注";
		}
		if (name == null || name.equals("")) {
			try {
				// httpServletResponse.sendRedirect("fee_add");
				return "fail";
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Cost cost = new Cost();
			cost.setName(name);
			cost.setCosttype(costtype);
			cost.setBase_duration(base_duration);
			cost.setBase_cost(base_cost);
			cost.setUnit_cost(unit_cost);
			cost.setDescr(descr);
			cost.setCreatime(new Date(System.currentTimeMillis()));
			System.out.println(cost.getCreatime());
			try {
				new DAOFactory().getCostDAO().createCost(cost);
				return "success";
			} catch (SQLException e) {
				e.printStackTrace();
				return "fail";
			}
		}
		return "fail";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCosttype() {
		return costtype;
	}

	public void setCosttype(int costtype) {
		this.costtype = costtype;
	}

	public int getBase_duration() {
		return base_duration;
	}

	public void setBase_duration(int base_duration) {
		this.base_duration = base_duration;
	}

	public double getBase_cost() {
		return base_cost;
	}

	public void setBase_cost(double base_cost) {
		this.base_cost = base_cost;
	}

	public double getUnit_cost() {
		return unit_cost;
	}

	public void setUnit_cost(double unit_cost) {
		this.unit_cost = unit_cost;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

}
