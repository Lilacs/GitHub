package com.tarena.DAO;

import java.sql.SQLException;
import java.util.List;

import com.tarena.po.Admin_info;

public interface Admin_infoDAO {
	/*
	 * ��½ϵͳ�ǽ�����֤�û��Ƿ����(�����û���½) ���Ĳ���Ϊ�û�������˺ź����� ����true֤���û����� ����false֤���û�������
	 */
	public boolean findUser(Admin_info user) throws SQLException;

	/*
	 * �����µĹ���Ա ���ر�����,���ύ���� �ʹ�������Ա��Ȩ�޺�����Ϊһ������
	 */
	public boolean createInfo(Admin_info info) throws SQLException;

	/*
	 * ���ݹ���Ա�����Ʋ���id ��������Ϊint
	 */
	public int findID(String name) throws SQLException;

	/*
	 * ��ҳ���ҳ����ʾ����Ա����Ϣ(ͬʱ��ʾ���Ľ�ɫ) TODO ����������������
	 */
	public List<Admin_info> getList(int pageValue, int MAX_PAGE)
			throws SQLException;

	/*
	 * ����id�����û���Ϣ�Ĳ��� �����޸Ĺ���Ա��Ϣ��ʾ�ù���Ա��ǰ�Ľ�ɫ
	 */
	public Admin_info getAdminInfoById(int id) throws SQLException;

	/*
	 * (����Ϊһ������) ���¹���Ա����Ϣ(����name,telephone,email) update lilacs_admin_info set
	 * name=?,telephone=?,email=? where id=? ���¹���Ա���½�ɫ(��ɾ��,֮��������) 
	 * delete lilacs_admin_role where admin_id = ? insert into lilacs_admin_role
	 * values(?,?)
	 */
	public boolean updateInfo(Admin_info info) throws SQLException;

	/*
	 * ɾ������Ա��Ϣ ͬʱɾ������Ա�Ľ�ɫ (���ر�����)
	 */
	public boolean delete(int id) throws SQLException;

	/**
	 * ���ݹ���������������(��������ʱ��Ҫ����post����) �õ���������ֵ(ģ�����ֵ(int),��ɫ����(String)
	 * ����:ģ�����,��ɫ���ƹ��� ͬʱ���з�ҳ (1)���ݽ�ɫ���Ʋ�ѯ����ɫ��id(��role_info�в�ѯ) (2)���ݽ�ɫ��id��ģ���������
	 * ����������ѯ�������û���Ϣ(����ͬʱ�õ�����Ա�Ľ�ɫ��Ϣ) actionҪͬʱ������������
	 * findAllInfoByRequest����ģ����Ϣ��Ϊȫ��������
	 * findAllInfoByALL��ȫ��ģ����Ϣ
	 * role_id �ǽ�ɫ��д�����Ʋ�ѯ������
	 */
	public List<Admin_info> findAllInfoByRequest(int id, int role_id,int pageValue, int MAX_PAGE)
			throws SQLException;

	public List<Admin_info> findAllInfoByALL(int id,int pageValue, int MAX_PAGE) throws SQLException;
	
	/**
	 * ��ѯ�����ҳ��
	 * 1)ȫ����Ϣ�����ҳ��(select count(*) from lilacs_admin_info) 
	 * 2)����Ϊȫ��Ȩ�޵����ҳ��
	 * 3)����Ȩ�޲�Ϊȫ�������ҳ��
	 * ���ַ�����Ϊһ����Ϣ
	 */
	//1)ȫ��
	public int getAllCount(int MAX_PAGE)throws SQLException;
	//2Ȩ��ȫ��(���ƽ�ɫid)
	public int getAllCountByAll(int MAX_PAGE,int roleId)throws SQLException;
	//3)Ȩ������(����Ȩ�޲���,��ɫid)
	public int getAllCountByOne(int MAX_PAGE,int roleId,int abilityId)throws SQLException;
	
	/*
	 * �����û��ĵ�¼�˺Ų��ҳ��û���������Ϣ
	 */
	public Admin_info getInfoByLoginName(String loginName)throws SQLException;
	/*
	 * ����Ա�����޸���Ϣ
	 */
	public boolean modiOwnInfo(Admin_info adminInfo)throws SQLException;
	/*
	 * ����Ա�����޸�����
	 */
	public boolean modiOwnpwd(int id,String newpwd)throws SQLException;
}
