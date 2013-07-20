package org.lilacs.action.service;

import java.util.List;

import org.lilacs.po.Cost;
import org.lilacs.po.Host;
import org.lilacs.po.Service;
import org.lilacs.util.DAOFactory;

public class ServiceOperate {
	private List<Cost> cost;
	private List<Host> host;
	private Service service;
	private int id;

	// 跳转至增加业务账号页面
	public String addview() throws Exception {
		try {
			host = new DAOFactory().getHostDAO().getAll();
			cost = new DAOFactory().getCostDAO().getAlltype();
			return "toaddview";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// 增加业务账号
	public String toadd() {
		try {
			boolean flag = new DAOFactory().getServiceDAO().createService(
					service);
			if (flag)
				return "toservicelist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// 删除一条业务账号(把状态号设置为2)
	public String todelete() throws Exception {
		try {
			boolean flag = new DAOFactory().getServiceDAO().deleteById(id);
			if(flag)
				return "toservicelist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// 跳转至单条查看业务账号
	public String todetail() throws Exception {
		try {
			service = new DAOFactory().getServiceDAO().getServiceById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "todetail";
	}

	// 跳转至修改页面
	public String tomodi() throws Exception {
		try {
			host = new DAOFactory().getHostDAO().getAll();
			cost = new DAOFactory().getCostDAO().getAlltype();
			service = new DAOFactory().getServiceDAO().getServiceById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tomodi";
	}

	// 修改更新的信息
	public String toupdate() throws Exception{
		try {
			boolean flag = new DAOFactory().getServiceDAO().update(service);
			if(flag)
				return "toservicelist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	// 暂停业务
	public String pause() {
		try {
			boolean flag = new DAOFactory().getServiceDAO().pause(id);
			if(flag)
				return "toservicelist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// 开通业务
	public String open() {
		try {
			boolean flag = new DAOFactory().getServiceDAO().open(id);
			if(flag)
				return "toservicelist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public List<Cost> getCost() {
		return cost;
	}

	public void setCost(List<Cost> cost) {
		this.cost = cost;
	}

	public List<Host> getHost() {
		return host;
	}

	public void setHost(List<Host> host) {
		this.host = host;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
