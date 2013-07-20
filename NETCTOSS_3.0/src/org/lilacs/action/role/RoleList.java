package org.lilacs.action.role;

import java.util.List;

import javax.annotation.Resource;

import org.lilacs.DAO.RoleInfoDAO;
import org.lilacs.po.RoleInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
@Controller
@Scope("prototype")
@Transactional(readOnly=true)
public class RoleList {
	private List<RoleInfo> roleInfos;
	private int page = 1;
	private int MAX_PAGE;
	private int pageCount;
	@Resource
	private RoleInfoDAO roleInfoDAO;
	public String execute() throws Exception{
		try {
			roleInfos = roleInfoDAO.getRoleInfoList(page, MAX_PAGE);
			pageCount = roleInfoDAO.getPageCount(MAX_PAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public List<RoleInfo> getRoleInfos() {
		return roleInfos;
	}

	public void setRoleInfos(List<RoleInfo> roleInfos) {
		this.roleInfos = roleInfos;
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
	
}
