package com.tarena.action.account;

import java.sql.SQLException;

import com.tarena.UtilBag.DAOFactory;

public class AccountAjax {
	private int recommenderId;
	private boolean flag;
	private String idCard;
	private String oldpwd;
	private int id;

	public String toExists() {
		try {
			flag = new DAOFactory().getAccountDAO().ToExists(idCard);
			return "toexists";
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}

	}

	public String makeIdForAdd() {
		try {
			recommenderId = new DAOFactory().getAccountDAO().findByIdCard(
					idCard);
			return "has";
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String toEquals() {
		try {
			String opwd = new DAOFactory().getAccountDAO().getPWD(id);
			System.out.println(opwd);
			if (opwd.equals(oldpwd)) {
				flag = true;
			} else {
				flag = false;
			}
			return "equals";
		} catch (SQLException e) {
			e.printStackTrace();
			return "equals";
		}
	}

	public int getRecommenderId() {
		return recommenderId;
	}

	public void setRecommenderId(int recommenderId) {
		this.recommenderId = recommenderId;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getOldpwd() {
		return oldpwd;
	}

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
