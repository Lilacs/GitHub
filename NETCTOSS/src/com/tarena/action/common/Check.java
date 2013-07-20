package com.tarena.action.common;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.tarena.UtilBag.BaseAware;
import com.tarena.UtilBag.DAOFactory;
import com.tarena.po.Admin_info;




public class Check extends BaseAware{
	private String user;
	private String pwd;
	private String checkcode;
	private String usererror;

	public String execute() {
		boolean flag = false;
		Admin_info luser = new Admin_info();
		luser.setAdmin_code(user);
		luser.setPassword(pwd);
		String imgCode = (String) session.get("checkcode");
		if(!checkcode.equalsIgnoreCase(imgCode)){
			usererror = "��֤�����";
			return "wrong";
		}
		try {
			flag = new DAOFactory().getAdmin_infoDAO().findUser(luser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.put("user", luser);
		//ͨ��ActionContext���(Map)session
		//ActionContext ac = ActionContext.getContext();
		//Map<String,Object> session = ac.getSession();
		//session.put("user", luser);
		//ͨ��ServletActionContext�����ǰ��request����,Ȼ��ͨ��request���session����
		//HttpServletRequest request =  ServletActionContext.getRequest();
		//HttpSession session2 = request.getSession();
		if(flag){
			return "success";
		}else{
			usererror = "�û��������������";
			return "wrong";
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
