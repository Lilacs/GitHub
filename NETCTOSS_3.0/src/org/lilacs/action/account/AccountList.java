package org.lilacs.action.account;

import java.util.List;

import javax.annotation.Resource;

import org.lilacs.DAO.AccountDAO;
import org.lilacs.po.Account;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
@Controller
@Scope("prototype")
@Transactional(readOnly=true)
public class AccountList {
	private int page = 1;
	private int MAX_PAGE;
	private int pageCount;
	private String idCard;
	private String realName;
	private String loginName;
	private int status = 4;
	private List<Account> list;
	@Resource
	private AccountDAO accountDAO;
	public String execute() {
		System.out.println("����Action");
		try {
			list = accountDAO.getAccountList(idCard, realName, loginName, status, page, MAX_PAGE);
			pageCount = accountDAO.getPageCount(idCard, realName, loginName, status, MAX_PAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
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

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Account> getList() {
		return list;
	}

	public void setList(List<Account> list) {
		this.list = list;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	
}
