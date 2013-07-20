package com.tarena.action.service;

import java.sql.SQLException;

import com.tarena.UtilBag.DAOFactory;

public class ServiceAjax {
	private String idCard;
	private boolean flag;
	private String loginName;
	private int id;

	public String getAccId() {
		System.out.println("ÊÇ·ñ½øÈë");
		try {
			flag = new DAOFactory().getAccountDAO().ToExistsForSer(idCard);
			System.out.println(flag);
			if (flag) {
				loginName = new DAOFactory().getAccountDAO().getLoginName(
						idCard);
				id = new DAOFactory().getAccountDAO().findByIdCard(idCard);
			}
			return "getit";
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

}
