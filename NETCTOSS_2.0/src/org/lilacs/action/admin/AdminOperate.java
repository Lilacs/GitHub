package org.lilacs.action.admin;

import java.util.List;
import java.util.Set;

import org.lilacs.po.AdminInfo;
import org.lilacs.po.RoleInfo;
import org.lilacs.util.DAOFactory;

public class AdminOperate {
	private List<RoleInfo> roleInfos;
	private int[] num;
	private AdminInfo adminInfo;
	private int id;
	// 跳转至增加页面
	public String addview() throws Exception {
		roleInfos = new DAOFactory().getRoleInfoDAO().getAllRoleInfo();
		return "toaddview";
	}
	
	// 增加一条新管理员信息
	public String toadd()throws Exception{
		boolean flag = new DAOFactory().getAdminInfoDAO().create(adminInfo, num);
		if(flag)
			return "toadminlist";
		else
			return "error";
	}
	
	//删除管理员
	public String todelete()throws Exception {
		boolean flag = new DAOFactory().getAdminInfoDAO().delete(id);
		if(flag)
			return "toadminlist";
		else
			return "error";
	}
	//跳转至修改页面
	public String tomodi() throws Exception{
		adminInfo = new DAOFactory().getAdminInfoDAO().getAdminInfoById(id);
		roleInfos = new DAOFactory().getRoleInfoDAO().getAllRoleInfo();
		num = new int[roleInfos.size()];
		Set<RoleInfo> roleInfos = adminInfo.getRoleInfos(); 
		int i = 0;
		for(RoleInfo r : roleInfos){
			num[i] = r.getId();
			i=i+1;
		}
		return "tomodiview";
	}
	
	//更新管理员信息
	public String toupdate()throws Exception{
		boolean flag = new DAOFactory().getAdminInfoDAO().update(adminInfo, num);
		if(flag)
			return "toadminlist";
		else
			return "error";
	}
	
	
	public List<RoleInfo> getRoleInfos() {
		return roleInfos;
	}

	public void setRoleInfos(List<RoleInfo> roleInfos) {
		this.roleInfos = roleInfos;
	}

	public int[] getNum() {
		return num;
	}

	public void setNum(int[] num) {
		this.num = num;
	}

	public AdminInfo getAdminInfo() {
		return adminInfo;
	}

	public void setAdminInfo(AdminInfo adminInfo) {
		this.adminInfo = adminInfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
