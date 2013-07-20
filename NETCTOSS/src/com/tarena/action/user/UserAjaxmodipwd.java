package com.tarena.action.user;

import java.sql.SQLException;

import com.tarena.UtilBag.BaseAware;
import com.tarena.UtilBag.DAOFactory;
import com.tarena.po.Admin_info;

public class UserAjaxmodipwd extends BaseAware {
	private String oldpwd;
	private String newpwd;
	private boolean flag;
	private String msg;

	public String execute() {
		
		System.out.println("进入检查");
		Admin_info ai = (Admin_info) session.get("user");
		System.out.println("用户登录账号"+ai.getAdmin_code());
		try {
			Admin_info admin = new DAOFactory().getAdmin_infoDAO()
					.getInfoByLoginName(ai.getAdmin_code());
			System.out.println("根据账号查到的原密码:"+admin.getPassword());
			if (oldpwd.equals(admin.getPassword())) {
				System.out.println("进入判断");
				boolean n1 = new DAOFactory().getAdmin_infoDAO().modiOwnpwd(
						admin.getId(), newpwd);
				System.out.println(n1);
				if (n1) {
					flag = true;
					msg = "修改成功";
					return "success";
				} else {
					flag = false;
					msg = "修改失败";
					return "success";
				}
			} else {
				System.out.println("进入失败");
				flag = false;
				msg = "密码与之前不符";
				return "success";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
			return "success";

		}
	}

	public String getOldpwd() {
		return oldpwd;
	}

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
