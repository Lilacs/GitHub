package com.tarena.action.user;

import java.sql.SQLException;

import com.tarena.UtilBag.BaseAware;
import com.tarena.UtilBag.DAOFactory;
import com.tarena.po.Admin_info;

public class UserInfo extends BaseAware {
	private Admin_info adminInfo;

	// 显示修改页面
	public String toview() {
		Admin_info ai = (Admin_info) session.get("user");
		try {
			adminInfo = new DAOFactory().getAdmin_infoDAO().getInfoByLoginName(
					ai.getAdmin_code());
			if (adminInfo != null) {
				return "touserinfo";
			} else {
				return "fail";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}

	// 更新用户信息
	public String update() {
		try {
			boolean flag = new DAOFactory().getAdmin_infoDAO().modiOwnInfo(
					adminInfo);
			if (flag) {
				return "backview";
			} else {
				return "fail";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public Admin_info getAdminInfo() {
		return adminInfo;
	}

	public void setAdminInfo(Admin_info adminInfo) {
		this.adminInfo = adminInfo;
	}

}
