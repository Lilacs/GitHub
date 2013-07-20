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
	//��ת������ҳ��
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
	//����
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
	//��ʾ�û�����Ϣdetail
	public String detail(){
		try {
			service = new DAOFactory().getServiceDAO().getInfoByid(id);
			return "todetail";
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}
	//��ת���޸�ҳ��
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
	//�޸�
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
	//ɾ��
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
	//��ͨ
	public String open(){
		System.out.println("��ͨ");
		try {
			boolean flag = new DAOFactory().getServiceDAO().open(id);
			System.out.println("��ͨ�ɹ����:"+flag);
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
	//��ͣ
	public String pause(){
		System.out.println("��ͣ");
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
