package com.tarena.DAO;

import java.sql.SQLException;
import java.util.List;

public interface Admin_roleDAO {
	/*
	 * ��������Ա��ʱ��ͬʱִ�и÷���(ͬһ������)
	 * (���ر�����)
	 * ����ֵΪboolean �����ж��Ƿ�ɹ�
	 * Ҫ�󴫽��û���id �����Ľ�ɫ(��ɫ����Ϊ1��)
	 */
	public boolean createAdminRole(int id,int[] num)throws SQLException;
	
	/*
	 * ����id�����û��Ľ�ɫ��Ϣ
	 * ���ر�����,����һ����Ϣ�ĵ��ò�
	 */
	public List<String> getRoleInfo(int id)throws SQLException;
	
	/*
	 * ����id���ҵ��ù���Ա�Ľ�ɫid;
	 * (��������)
	 */
	public List<Integer> findRoleIdByAdminID(int id)throws SQLException;
	
	/*
	 * (�޸Ĺ���Ա��Ϣ����ɾ��)
	 * ���ݹ���Աidɾ�������н�ɫ
	 * (���²���)
	 * (���ر�����)
	 */
	public boolean deleteRole(int id)throws SQLException;
	
	
}