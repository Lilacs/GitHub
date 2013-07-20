package org.lilacs.action.account;

import java.util.Date;

import org.lilacs.po.Account;
import org.lilacs.util.DAOFactory;

public class AccountOperate {
	private Account account;
	private String birthdate;
	private int id;

	// 跳转至增加页面
	public String addview() {
		return "toaddview";
	}

	// 增加一条资费账户
	public String toadd() throws Exception {
		Date date = org.lilacs.util.UtilBag.strToDate(birthdate);
		account.setBirthdate(date);
		try {
			boolean flag = new DAOFactory().getAccountDAO().createAccount(
					account);
			if (flag)
				return "toaccountlist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// 跳转至查看页面
	public String todetail() throws Exception {
		try {
			account = new DAOFactory().getAccountDAO().getAccountById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "todetail";
	}

	// 跳转至修改界面
	public String tomodi() throws Exception {
		try {
			account = new DAOFactory().getAccountDAO().getAccountById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "tomodi";
	}

	// 更新一条信息
	public String toupdate() throws Exception {
		try {
			boolean flag = new DAOFactory().getAccountDAO().update(account);
			if (flag)
				return "toaccountlist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// 更新一条信息
	public String todelete() throws Exception {
		try {
			boolean flag = new DAOFactory().getAccountDAO().delete(id);
			if (flag)
				return "toaccountlist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// 暂停资费账号
	public String topause() throws Exception{
		try {
			boolean flag = new DAOFactory().getAccountDAO().pause(id);
			if(flag)
				return "toaccountlist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}
	
	// 开通资费账号
	public String toopen() throws Exception{
		System.out.println("来了");
		try {
			boolean flag = new DAOFactory().getAccountDAO().open(id);
			if(flag)
				return "toaccountlist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
