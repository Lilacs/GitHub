package org.lilacs.action.service;

import java.util.List;

import javax.annotation.Resource;

import org.lilacs.DAO.ServiceDAO;
import org.lilacs.po.Service;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
@Controller
@Scope("prototype")
@Transactional(readOnly=true)
public class ServiceList {
	private String osUserName;
	private String unixHost;
	private String idCardNo;
	private String status = "3";
	private int page = 1;
	private int MAX_PAGE;
	private int pageCount;
	private List<Service> list;
	@Resource
	private ServiceDAO serviceDAO;
	// ∑÷“≥≤È—Øœ‘ æ
	public String execute() throws Exception{
			list = serviceDAO.getServiceList(osUserName,
					unixHost, idCardNo, status, page, MAX_PAGE);
			pageCount = serviceDAO.getPageCount(osUserName, unixHost, idCardNo, status, MAX_PAGE);
		return "success";
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<Service> getList() {
		return list;
	}

	public void setList(List<Service> list) {
		this.list = list;
	}

	public String getOsUserName() {
		return osUserName;
	}

	public void setOsUserName(String osUserName) {
		this.osUserName = osUserName;
	}

	public String getUnixHost() {
		return unixHost;
	}

	public void setUnixHost(String unixHost) {
		this.unixHost = unixHost;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getMAX_PAGE() {
		return MAX_PAGE;
	}

	public void setMAX_PAGE(int mAX_PAGE) {
		MAX_PAGE = mAX_PAGE;
	}

}
