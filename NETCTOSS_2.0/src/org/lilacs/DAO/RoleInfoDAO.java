package org.lilacs.DAO;

import java.util.List;

import org.lilacs.po.RoleInfo;

public interface RoleInfoDAO {

	// ����һ����ɫ��Ϣ
	public abstract boolean createRoleInfo(RoleInfo roleInfo, int[] num)
			throws Exception;
	// ɾ��һ����ɫ��Ϣ
	public boolean delete(int id) throws Exception;
	
	// ����id���ҳ���ɫ��Ϣ
	public RoleInfo getRoleInfoById(int id)throws Exception;
	
	// �޸Ľ�ɫ��Ϣ
	public boolean modify(RoleInfo roleInfo, int[] num) throws Exception;
		
	// ��ҳ��ѯ
	public List<RoleInfo> getRoleInfoList(int page, int MAX_PAGE)
				throws Exception;
	
	// ��ѯһ���ж���ҳ
	public int getPageCount(int MAX_PAGE)throws Exception;
	
	//��ѯ���еĽ�ɫ��Ϣ
	public List<RoleInfo> getAllRoleInfo()throws Exception;

}