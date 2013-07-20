package com.tarena.action.account;

import java.sql.SQLException;
import java.util.List;

import com.tarena.UtilBag.DAOFactory;
import com.tarena.po.Account;
import com.tarena.po.AccountSearch;

public class AccountList {
	private String idcard_no = "";
	private String real_name = "";
	private String LOGIN_NAME = "";
	private int status = 4;
	private int pageValue = 1;
	private int MAX_PAGE;
	private int pageCount;
	private List<Account> account;
	public String execute(){
		try {
			account = new DAOFactory().getAccountDAO().getList(idcard_no, real_name, LOGIN_NAME, status, pageValue, MAX_PAGE);
			pageCount = new DAOFactory().getAccountDAO().HowManyPage(idcard_no, real_name, LOGIN_NAME, status,MAX_PAGE);
			System.out.println(pageCount);
			return "success";
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}
	public String getIdcard_no() {
		return idcard_no;
	}
	public void setIdcard_no(String idcard_no) {
		this.idcard_no = idcard_no;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getLOGIN_NAME() {
		return LOGIN_NAME;
	}
	public void setLOGIN_NAME(String lOGIN_NAME) {
		LOGIN_NAME = lOGIN_NAME;
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
	public List<Account> getAccount() {
		return account;
	}
	public void setAccount(List<Account> account) {
		this.account = account;
	}
	
}
