package com.tarena.DAO;

import java.sql.SQLException;
import java.util.List;

import com.tarena.po.Account;

public interface AccountDAO {
	/*
	 * �����������˺�ʱ��ajax������û�д��Ƽ������֤��id
	 */
	public boolean ToExists(String IdCard) throws SQLException;
	
	public boolean ToExistsForSer(String IdCard) throws SQLException;

	/*
	 * �����������˺�ʱ��ajax�����Ƽ��˵�id
	 */
	public int findByIdCard(String IdCard) throws SQLException;

	/*
	 * �����µ��ʷ��˻�
	 */
	public boolean createAccountInfo(Account account) throws SQLException;

	/*
	 * ɾ���ʷ���Ϣ ����������ɾ������״̬��Ϊ2
	 */
	public boolean setDelete(int id) throws SQLException;

	/*
	 * ����id�õ�������Ϣ(�����޸�)
	 */
	public Account getInfo(int id) throws SQLException;

	/*
	 * ������Ϣ�޸ĸ�������Ϣ
	 */
	public boolean update(Account account) throws SQLException;

	/*
	 * ����������ҳ����
	 */
	public List<Account> getList(String idcard_no, String real_name,
			String LOGIN_NAME, int status, int pageValue, int MAX_PAGE)
			throws SQLException;
	/*
	 * ����������ѯһ���ж���ҳ
	 */
	public int HowManyPage(String idcard_no, String real_name,
			String LOGIN_NAME, int status,int MAX_PAGE)
			throws SQLException;
	/*
	 * ��ͨ�ʷ��˻�
	 */
	public boolean open(int id)throws SQLException;
	
	/*
	 * ��ͣ�ʷ��˻�
	 */
	public boolean pause(int id)throws SQLException;
	
	/*
	 * �������֤�ŵõ��û��ĵ�¼��
	 */
	public String getLoginName(String idCard)throws SQLException;
	
	/*
	 * ����id�õ�����,���ڱȽϾ������Ƿ���ͬ
	 */
	public String getPWD(int id)throws SQLException;
	
}
