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
		
		System.out.println("������");
		Admin_info ai = (Admin_info) session.get("user");
		System.out.println("�û���¼�˺�"+ai.getAdmin_code());
		try {
			Admin_info admin = new DAOFactory().getAdmin_infoDAO()
					.getInfoByLoginName(ai.getAdmin_code());
			System.out.println("�����˺Ų鵽��ԭ����:"+admin.getPassword());
			if (oldpwd.equals(admin.getPassword())) {
				System.out.println("�����ж�");
				boolean n1 = new DAOFactory().getAdmin_infoDAO().modiOwnpwd(
						admin.getId(), newpwd);
				System.out.println(n1);
				if (n1) {
					flag = true;
					msg = "�޸ĳɹ�";
					return "success";
				} else {
					flag = false;
					msg = "�޸�ʧ��";
					return "success";
				}
			} else {
				System.out.println("����ʧ��");
				flag = false;
				msg = "������֮ǰ����";
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
