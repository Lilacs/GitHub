package org.lilacs.DAO;

import java.sql.SQLException;
import java.util.List;

import org.lilacs.po.Cost;

public interface CostDAO {
	public abstract List<Cost> find_By_Page(int pageValue, int MAX_PAGE)
			throws SQLException;

	// �õ�һ���ж���ҳ
	public abstract int maxPage(int MAX_PAGE) throws SQLException;

	// ɾ��һ���ʷ���Ϣ
	public abstract boolean delete_cost(int id) throws Exception;

	// ����һ���ʷ���Ϣ
	public abstract boolean createCost(Cost cost) throws Exception;

	// ����id�ҵ�һ����Ϣ
	public abstract Cost find_By_Id(int id) throws Exception;

	// ������Ϣ
	public abstract boolean update_info(Cost cost) throws Exception;

	// ���³ɿ�ͨ״̬ ͬʱ���ؿ�ͨʱ��
	public abstract boolean update_status(int id) throws Exception;

	/*
	 * (����ajax�ж���û�и�����)
	 * 
	 * @see com.tarena.DAO.CostDAO#find_By_Name(java.lang.String)
	 */
	public abstract boolean find_By_Name(String name) throws SQLException;

	/**
	 * ����ҵ���˺ŵ���Ϣʱ�õ� ����service�е�cost_id�õ���ص�cost��Ϣ
	 */
	public abstract List<String> getIntroduction(int costId)
			throws SQLException;

	// �ҵ����е��ʷ�����
	public abstract List<Cost> getAlltype() throws SQLException;
}
