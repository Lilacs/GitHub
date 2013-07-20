package org.lilacs.action.admin;

import java.util.List;

import org.lilacs.po.AdminInfo;
import org.lilacs.util.DAOFactory;

public class AdminList {
	private int page = 1;
	private int MAX_PAGE;
	private int pageCount;
	private List<AdminInfo> adminInfos;
	private String abilityType = "0";
	private String roleName = "";

	public String execute() throws Exception {
		adminInfos = new DAOFactory().getAdminInfoDAO().getAdminInfoList(
				abilityType, roleName, page, MAX_PAGE);
		pageCount = new DAOFactory().getAdminInfoDAO().getPageCount(abilityType, roleName, MAX_PAGE);
		return "success";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
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

	public List<AdminInfo> getAdminInfos() {
		return adminInfos;
	}

	public void setAdminInfos(List<AdminInfo> adminInfos) {
		this.adminInfos = adminInfos;
	}

	public String getAbilityType() {
		return abilityType;
	}

	public void setAbilityType(String abilityType) {
		this.abilityType = abilityType;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
