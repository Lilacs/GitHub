package com.tarena.action.account;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tarena.UtilBag.DAOFactory;
import com.tarena.po.Account;

public class AccountOPerate {
	private Account account;
	private String birthdate;
	private int id;

	public String addview() {
		return "turntoaddpage";
	}

	public String append() {
		try {
			System.out.println(account.getRecommender_id());
			Date date = AccountOPerate.strToDate(birthdate);
			account.setBirthdate(date);
			boolean flag = new DAOFactory().getAccountDAO().createAccountInfo(
					account);
			if (flag) {
				return "backtolistpage";
			} else {
				return "fail";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}

	}

	public String modiview() {
		try {
			account = new DAOFactory().getAccountDAO().getInfo(id);
			return "truntomodipage"; 
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
		
	}

	public String modi() {
		try {
			boolean flag = new DAOFactory().getAccountDAO().update(account);
			if(flag){
				return "backtolistpage";
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
		try {
			boolean flag = new DAOFactory().getAccountDAO().open(id);
			if(flag){
				return "backtolistpage";
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
		try {
			boolean flag = new DAOFactory().getAccountDAO().pause(id);
			if(flag){
				return "backtolistpage";
			}else{
				return "fail";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	//删除账户
	public String delete() {
		try {
			boolean flag = new DAOFactory().getAccountDAO().setDelete(id);
			if(flag){
				return "backtolistpage";
			}else{
				return "fail";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}
	//查看账户信息
	public String detail(){
		try {
			account = new DAOFactory().getAccountDAO().getInfo(id);
			return "todetail";
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
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

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

}
