package com.tarena.po;

public class AccountSearch {
	/**
	 * 如果没用着可以等着删除
	 */
	private String idcard_no;
	private String real_name;
	private String LOGIN_NAME;
	private String status;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
