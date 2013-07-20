package com.tarena.action.role;

import java.sql.SQLException;
import java.util.List;

import com.tarena.UtilBag.DAOFactory;
import com.tarena.po.Ability;
import com.tarena.po.Role_info;
import com.tarena.po.Role_privilege;

public class RoleList {
	/*
	 * ��ʾ��ɫ����Ϣ ����Role_info���е�������ʾid,name Role_privilege���е�Ȩ��id
	 * ����ӵ�е�Ȩ���õ�ability���е�������ʾ
	 */
	private List<Role_info> list_role_info;
	private int pageValue=1;
	private int MAX_PAGE;// TODO ��xml��actionע��
	private int pageCount;
	public String execute() {
		try {
			// �õ�role_info���е�id,name
			list_role_info = new DAOFactory().getRole_infoDAO().getList(
					pageValue, MAX_PAGE);
			pageCount = new DAOFactory().getRole_infoDAO().howManyPage(MAX_PAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "success";

	}

	public List<Role_info> getList_role_info() {
		return list_role_info;
	}

	public void setList_role_info(List<Role_info> list_role_info) {
		this.list_role_info = list_role_info;
	}

	public int getPageValue() {
		return pageValue;
	}

	public void setPageValue(int pageValue) {
		this.pageValue = pageValue;
	}

	public int getMAX_PAGE() {
		return MAX_PAGE;
	}

	public void setMAX_PAGE(int mAX_PAGE) {
		MAX_PAGE = mAX_PAGE;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	

}
