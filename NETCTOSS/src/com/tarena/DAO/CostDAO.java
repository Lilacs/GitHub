package com.tarena.DAO;

import java.sql.SQLException;
import java.util.List;

import com.tarena.po.Cost;

public interface CostDAO {
	// ��ѯ��ǰҳ��
	public List<Cost> find_By_Page(int pageValue, int MAX_PAGE)
			throws SQLException;

	// ��ѯ���м�¼��
	public int maxPage(int MAX_PAGE) throws SQLException;

	// ɾ��cost��ָ���ļ�¼
	public void delete_cost(int id) throws SQLException;

	// ��������cost���еĵ��ʷ�����
	public void createCost(Cost cost) throws SQLException;

	// ����id���Ҽ�¼
	public Cost find_By_Id(int id) throws SQLException;

	// ����id����cost���еļ�¼
	public void update_info(Cost cost) throws SQLException;

	// ����cost�������ݵ�״̬
	public void update_status(int id) throws SQLException;

	// �½��ʷ���Ϣʱ����Ƿ���ڴ�name
	public boolean find_By_Name(String name) throws SQLException;
	
	/*
	 * ����cost_id���ҵ��ʷ������������ʷ���Ϣ����
	 */
	public List<String> getIntroduction(int costId)throws SQLException;
	/*
	 * �ҵ����е��ʷ�����
	 */
	public List<Cost> getAlltype()throws SQLException;
}
