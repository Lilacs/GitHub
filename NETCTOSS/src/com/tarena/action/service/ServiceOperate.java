package com.tarena.action.service;

import java.sql.SQLException;
import java.util.List;

import com.tarena.UtilBag.DAOFactory;
import com.tarena.po.Cost;
import com.tarena.po.Host;
import com.tarena.po.Service;

public class ServiceOperate {
	private List<Cost> cost;
	private List<Host> host;
	private Service service;
	private int id;
	//跳转到增加页面
	public String addview() {
		try {
			cost = new DAOFactory().getCostDAO().getAlltype();
			host = new DAOFactory().getHostDAO().getALLtype();
			return "toaddview";
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}
	//创建
	public String create() {
		service.setStatus("0");
		try {
			boolean flag = new DAOFactory().getServiceDAO().createInfo(service);
			if(flag){
				return "toservicelist";
			}else{
				return "fail";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}
	//显示用户的信息detail
	public String detail(){
		try {
			service = new DAOFactory().getServiceDAO().getInfoByid(id);
			return "todetail";
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}
	//跳转到修改页面
	public String modi(){
		try {
			service = new DAOFactory().getServiceDAO().getInfoByid(id);
			cost = new DAOFactory().getCostDAO().getAlltype();
			return "tomodiview";
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
		
	}
	//修改
	public String update(){
		try {
			boolean flag = new DAOFactory().getServiceDAO().modiCostType(service.getCost_id(), service.getId());
			if(flag){
				return "toservicelist";
			}else{
				return "fail";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
		
	}
	//删除
	public String delete(){
		try {
			boolean flag = new DAOFactory().getServiceDAO().delete(id);
			if(flag){
				return "toservicelist";
			}else{
				return "fail";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";		
		}
	}
	//开通
	public String open(){
		System.out.println("开通");
		try {
			boolean flag = new DAOFactory().getServiceDAO().open(id);
			System.out.println("开通成功与否:"+flag);
			if(flag){
				return "toservicelist";
			}else{
				return "fail";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";		
		}
	}
	//暂停
	public String pause(){
		System.out.println("暂停");
		try {
			boolean flag = new DAOFactory().getServiceDAO().pause(id);
			if(flag){
				return "toservicelist";
			}else{
				return "fail";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";		
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
