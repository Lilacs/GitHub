package com.tarena.action.admin;

import java.sql.SQLException;
import java.util.List;

import com.tarena.UtilBag.DAOFactory;
import com.tarena.po.Admin_info;

public class AdminList {
	private List<Admin_info> adminInfo;
	private int pageValue = 1;
	private int MAX_PAGE;
	private int searchprivilege;
	private String searchname;
	private int pageCount;
	public String all() {
		try {
			adminInfo = new DAOFactory().getAdmin_infoDAO().getList(pageValue, MAX_PAGE);
			pageCount = new DAOFactory().getAdmin_infoDAO().getAllCount(MAX_PAGE);
			return "success";
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}
	public String search(){
		if(searchprivilege == 0){
			try {
				int id = new DAOFactory().getRole_infoDAO().getID(searchname);
				adminInfo = new DAOFactory().getAdmin_infoDAO().findAllInfoByALL(id, pageValue, MAX_PAGE);
				pageCount = new DAOFactory().getAdmin_infoDAO().getAllCountByAll(MAX_PAGE,id);
				return "success";
			} catch (SQLException e) {
				e.printStackTrace();
				return "fail";
			}
		}else {
			try {
				int id = new DAOFactory().getRole_infoDAO().getID(searchname);
				adminInfo = new DAOFactory().getAdmin_infoDAO().findAllInfoByRequest(searchprivilege, id, pageValue, MAX_PAGE);
				pageCount = new DAOFactory().getAdmin_infoDAO().getAllCountByOne(MAX_PAGE, id, searchprivilege);
				return "success";
			} catch (SQLException e) {
				e.printStackTrace();
				return "fail";
			}
		}
	}

	public List<Admin_info> getAdminInfo() {
		return adminInfo;
	}

	public void setAdminInfo(List<Admin_info> adminInfo) {
		this.adminInfo = adminInfo;
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
	public int getSearchprivilege() {
		return searchprivilege;
	}
	public void setSearchprivilege(int searchprivilege) {
		this.searchprivilege = searchprivilege;
	}
	public String getSearchname() {
		return searchname;
	}
	public void setSearchname(String searchname) {
		this.searchname = searchname;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	

}
