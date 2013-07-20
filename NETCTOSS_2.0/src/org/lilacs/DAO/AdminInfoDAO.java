package org.lilacs.DAO;

import java.util.List;

import org.lilacs.po.AdminInfo;

public interface AdminInfoDAO {

	// ����һ������Ա��Ϣ
	public abstract boolean create(AdminInfo adminInfo, int[] num)
			throws Exception;

	// ����id��ѯ����Ա��Ϣ
	public AdminInfo getAdminInfoById(int id) throws Exception;

	// ɾ��һ������Ա
	public boolean delete(int id) throws Exception;

	// ����һ������Ա��Ϣ
	public boolean update(AdminInfo adminInfo, int[] num) throws Exception;
	
	// ��½ϵͳ�ǽ�����֤�û��Ƿ����(�����û���½)
	public boolean findUser(AdminInfo user) throws Exception;

	// ��ҳ��ѯ
	public List<AdminInfo> getAdminInfoList(String abilityType,
			String roleName, int page, int MAX_PAGE) throws Exception;

	// ��ѯһ������ҳ
	public int getPageCount(String abilityType, String roleName, int MAX_PAGE)
			throws Exception;
}