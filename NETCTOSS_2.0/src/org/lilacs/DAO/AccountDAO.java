package org.lilacs.DAO;

import java.sql.SQLException;
import java.util.List;

import org.lilacs.po.Account;

public interface AccountDAO {

	// ����һ���µ��ʷ��˻�
	public abstract boolean createAccount(Account account) throws Exception;

	// ����һ����Ϣ
	public abstract boolean update(Account account) throws Exception;

	// �鿴һ����Ϣ����id
	public Account getAccountById(int id) throws Exception;

	// ��ͨ�ʷ�
	public abstract boolean open(int id) throws Exception;

	// ��ͣʱ�� ������ͣʱ�� ����״̬Ϊ1(��ͣ״̬),ͬʱ�ر��˺��µ�ҵ��
	public abstract boolean pause(int id) throws Exception;

	// ɾ��һ����Ϣ ״̬����Ϊ2 ͬʱ�����˻��µ�ҵ���˺�Ϊɾ��״̬(2)
	public Boolean delete(int id) throws Exception;

	// ��֤�������Ƿ���ͬ
	public abstract String getPWD(int id) throws Exception;

	// �������֤�õ��û��ĵ�¼��
	public abstract String getLoginName(String idCard) throws Exception;

	// ��ҳ��ѯ
	public List<Account> getAccountList(String idCard, String realName,
			String loginName, int status, int page, int MAX_PAGE)
			throws Exception;

	// ��ѯҳ��
	public int getPageCount(String idCard, String realName, String loginName,
			int status, int MAX_PAGE) throws Exception;
}