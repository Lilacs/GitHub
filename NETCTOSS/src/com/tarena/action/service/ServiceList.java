package com.tarena.action.service;

import java.sql.SQLException;
import java.util.List;

import com.tarena.UtilBag.DAOFactory;
import com.tarena.po.Service;

public class ServiceList {
	private List<Service> list;
	private String osName;
	private String unitHost;
	private String idCard;
	private int status = 3;
	private int pageValue = 1;
	private int MAX_PAGE;
	private int pageCount;
	public String execute() {
		System.out.println("进入业务");
		try {
			list = new DAOFactory().getServiceDAO().getList(osName, unitHost, idCard, status, pageValue, MAX_PAGE);
			pageCount = new DAOFactory().getServiceDAO().getpageCount(osName, unitHost, idCard, status, MAX_PAGE);
			return "success";
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
		

	}
	public List<Service> getList() {
		return list;
	}
	public void setList(List<Service> list) {
		this.list = list;
	}
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
	public String getUnitHost() {
		return unitHost;
	}
	public void setUnitHost(String unitHost) {
		this.unitHost = unitHost;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPageValue() {
		return pageValue;
	}
	public void setPageValue(int pageValue) {
		this.pageValue = pageValue;
	}
	public int getMAX_PAGE() {
		return MAX_PAGE;
	}
	public void setMAX_PAGE(int mAX_PAGE) {
		MAX_PAGE = mAX_PAGE;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
}
