package org.lilacs.action.common;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.lilacs.po.AdminInfo;
import org.lilacs.util.BaseAware;
import org.lilacs.util.DAOFactory;

import com.opensymphony.xwork2.ActionContext;

public class Check extends BaseAware {
	private String user;
	private String pwd;
	private String checkcode;
	private String usererror;

	public String execute() throws Exception {
		System.out.println(user);
		System.out.println(pwd);
		System.out.println(checkcode);
		AdminInfo luser = new AdminInfo();
		luser.setAdminCode(user);
		luser.setPassword(pwd);
		String imgCode = (String) session.get("checkcode");
		if (!checkcode.equalsIgnoreCase(imgCode)) {
			usererror = "验证码错误";
			return "error";
		}
		try {
			boolean flag = new DAOFactory().getAdminInfoDAO().findUser(luser);
			System.out.println(flag);
			if (flag) {
				session.put("user", luser);
				return "success";
			} else {
				usererror = "用户名或者密码错误";
				return "error";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String getUsererror() {
		return usererror;
	}

	public void setUsererror(String usererror) {
		this.usererror = usererror;
	}

}
