package org.lilacs.action.admin;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.lilacs.DAO.AdminInfoDAO;
import org.lilacs.DAO.RoleInfoDAO;
import org.lilacs.po.AdminInfo;
import org.lilacs.po.RoleInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
@Controller
@Scope("prototype")
@Transactional
public class AdminOperate {
	private List<RoleInfo> roleInfos;
	private int[] num;
	private AdminInfo adminInfo;
	private int id;
	@Resource
	private RoleInfoDAO roleInfoDAO;
	@Resource
	private AdminInfoDAO adminInfoDAO;
	// 跳转至增加页面
	@Transactional(readOnly=true)
	public String addview() throws Exception {
		roleInfos = roleInfoDAO.getAllRoleInfo();
		return "toaddview";
	}
	
	// 增加一条新管理员信息
	public String willLoggertoadd()throws Exception{
		boolean flag = adminInfoDAO.create(adminInfo, num);
		if(flag)
			return "toadminlist";
		else
			return "error";
	}
	
	//删除管理员
	public String willLoggertodelete()throws Exception {
		boolean flag = adminInfoDAO.delete(id);
		if(flag)
			return "toadminlist";
		else
			return "error";
	}
	//跳转至修改页面
	@Transactional(readOnly=true)
	public String tomodi() throws Exception{
		adminInfo = adminInfoDAO.getAdminInfoById(id);
		roleInfos = roleInfoDAO.getAllRoleInfo();
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
	public String willLoggertoupdate()throws Exception{
		boolean flag = adminInfoDAO.update(adminInfo, num);
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
