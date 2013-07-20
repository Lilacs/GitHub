package org.lilacs.action.common;

import java.sql.SQLException;
import java.util.HashSet;

import javax.annotation.Resource;

import org.lilacs.DAO.AdminInfoDAO;
import org.lilacs.po.AdminInfo;
import org.lilacs.util.BaseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Scope("prototype")
@Transactional
public class Check extends BaseAware {
	private String user;
	private String pwd;
	private String checkcode;
	private String usererror;
	@Resource
	private AdminInfoDAO adminInfoDAO;

	public String execute() throws Exception {
		AdminInfo luser = new AdminInfo();
		luser.setAdminCode(user);
		luser.setPassword(pwd);
		String imgCode = (String) session.get("checkcode");
		if (!checkcode.equalsIgnoreCase(imgCode)) {
			usererror = "验证码错误";
			return "error";
		}
		try {
			boolean flag = adminInfoDAO.findUser(luser);
			if (flag) {
				HashSet<Integer> abilitys = adminInfoDAO.getAbilitys(user);
				session.put("user", luser);
				session.put("abilitys", abilitys);
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
