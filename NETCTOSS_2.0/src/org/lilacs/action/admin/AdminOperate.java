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
	// ��ת������ҳ��
	public String addview() throws Exception {
		roleInfos = new DAOFactory().getRoleInfoDAO().getAllRoleInfo();
		return "toaddview";
	}
	
	// ����һ���¹���Ա��Ϣ
	public String toadd()throws Exception{
		boolean flag = new DAOFactory().getAdminInfoDAO().create(adminInfo, num);
		if(flag)
			return "toadminlist";
		else
			return "error";
	}
	
	//ɾ������Ա
	public String todelete()throws Exception {
		boolean flag = new DAOFactory().getAdminInfoDAO().delete(id);
		if(flag)
			return "toadminlist";
		else
			return "error";
	}
	//��ת���޸�ҳ��
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
	
	//���¹���Ա��Ϣ
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
