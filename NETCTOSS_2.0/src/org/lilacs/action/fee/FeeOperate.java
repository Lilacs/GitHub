package org.lilacs.action.fee;

import java.sql.SQLException;

import org.lilacs.po.Cost;
import org.lilacs.util.DAOFactory;

public class FeeOperate {
	private Cost cost;
	private int id;

	// 跳转至增加资费类型页面
	public String addview() {
		return "toaddview";
	}

	// 增加一条资费信息
	public String toadd() throws Exception {
		// 如果不成功就跳转到错误界面
		try {
			boolean flag = new DAOFactory().getCostDAO().createCost(cost);
			if (flag) {
				return "tofeelist";
			} else {
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// 跳转至查看资费信息界面
	public String todetail() throws Exception {
		cost = new DAOFactory().getCostDAO().find_By_Id(id);
		return "todetail";
	}

	// 删除一条信息
	public String todelete() throws Exception {
		try {
			boolean flag = new DAOFactory().getCostDAO().delete_cost(id);
			if(flag)
				return "tofeelist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	//跳转至修改页面
	public String tomodi() throws Exception{
		try {
			cost = new DAOFactory().getCostDAO().find_By_Id(id);
			return "tomodi";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}	
	}
	//更新资费信息
	public String toupdate() throws Exception{
		try {
			boolean flag = new DAOFactory().getCostDAO().update_info(cost);
			if(flag)
				return "tofeelist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	//开通资费
	public String opencost() throws Exception{
		try {
			boolean flag = new DAOFactory().getCostDAO().update_status(id);
			if(flag)
				return "tofeelist";
			else
				return "error";
		} catch (Exception e) {
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
