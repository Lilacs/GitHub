package org.lilacs.DAO.realize;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.lilacs.DAO.CostDAO;
import org.lilacs.po.Cost;

public class CostDAOimpl implements CostDAO {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lilacs.DAO.realize.CostDAO#find_By_Page(int, int)
	 */
	public List<Cost> find_By_Page(int pageValue, int MAX_PAGE)
			throws SQLException {
		// hibernate дSQL�ı��������������,�����ӳ�䵽
		String hql = "from Cost";
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql);
		int begin = (pageValue - 1) * MAX_PAGE;// ����������0��ʼ
		query.setFirstResult(begin);// ������ʼ��
		query.setMaxResults(MAX_PAGE);// ���ò�ѯ��
		List<Cost> list = query.list();
		return list;
	}

	// �õ�һ���ж���ҳ
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lilacs.DAO.realize.CostDAO#maxPage(int)
	 */
	public int maxPage(int MAX_PAGE) throws SQLException {
		// hibernate ��sql���
		String hql = "select count(*) from Cost";
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql);
		// �޷��жϷ��ص���ʲô����,���н���ǿ��ת��
		Object obj = query.uniqueResult();
		int count = Integer.parseInt(obj.toString());
		if (count % MAX_PAGE != 0) {
			int page_size = count / MAX_PAGE + 1;
			return page_size;
		} else {
			int page_size = count / MAX_PAGE;
			return page_size;
		}
	}

	// ɾ��һ���ʷ���Ϣ
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lilacs.DAO.realize.CostDAO#delete_cost(int)
	 */
	public boolean delete_cost(int id) throws Exception {
		try{
		// �õ�Hibernate��Session���������ݿ��������
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		Cost cost = new Cost();
		cost.setId(id);
		session.delete(cost);
		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	// ����һ���ʷ���Ϣ
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lilacs.DAO.realize.CostDAO#createCost(org.lilacs.po.Cost)
	 */
	public boolean createCost(Cost cost) throws Exception {
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			cost.setCreattime(new Date(System.currentTimeMillis()));
			cost.setStatus("0");
			session.save(cost);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// ����id�ҵ�һ����Ϣ
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lilacs.DAO.realize.CostDAO#find_By_Id(int)
	 */
	public Cost find_By_Id(int id) throws Exception {
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		Cost cost = new Cost();
		cost = (Cost) session.load(Cost.class, id);
		// �����load�����Ļ�,�ӳټ���(�ڵ���getter����ʱ��ѯ���ݿ�)
		// ͨ��ActionContext���session��map���Ͷ���,��session����struts��session��
		// ���������Ǳ�ִ�������ʾ����֮��رո�session
		// ActionContext ac = ActionContext.getContext();
		// Map<String,Object> se = ac.getSession();
		// se.put("hsession", session);
		return cost;
	}

	// ������Ϣ
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lilacs.DAO.realize.CostDAO#update_info(org.lilacs.po.Cost)
	 */
	public boolean update_info(Cost cost) throws Exception {
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			Cost c1 = (Cost) session.get(Cost.class, cost.getId());
			c1.setName(cost.getName());
			c1.setBaseCost(cost.getBaseCost());
			c1.setBaseDuration(cost.getBaseDuration());
			c1.setDescr(cost.getDescr());
			c1.setCosttype(cost.getCosttype());
			session.update(c1);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * (���³ɿ�ͨ״̬ ͬʱ���ؿ�ͨʱ��)
	 */
	public boolean update_status(int id) throws Exception {
		try{
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		Cost cost = (Cost) session.get(Cost.class, id);
		cost.setStatus("1");
		cost.setStartime(new Date(System.currentTimeMillis()));
		// session.beginTransaction();
		session.update(cost);
		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * (����ajax�ж���û�и�����)
	 */
	public boolean find_By_Name(String name) throws SQLException {
		String sql = "from Cost where name = ?";
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		Query query = session.createQuery(sql);
		query.setString(0, name);
		Cost cost = (Cost) query.uniqueResult();
		System.out.println(cost.getName());
		if (cost != null) {
			String result = "";
			result = cost.getName();
			if (name.equals(result)) {
				return false;
			} else {
				return true;
			}
		} else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lilacs.DAO.realize.CostDAO#getIntroduction(int)
	 */
	public List<String> getIntroduction(int costId) throws SQLException {
		String hql = "from Cost";
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql);
		List<Cost> cost = query.list();
		List<String> list = new ArrayList<String>();
		for (Cost c : cost) {
			list.add(c.getName());
			list.add(c.getDescr());
		}
		return list;
	}

	// �ҵ����е��ʷ�����
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lilacs.DAO.realize.CostDAO#getAlltype()
	 */
	public List<Cost> getAlltype() throws SQLException {
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Cost");
		List<Cost> list = new ArrayList<Cost>();
		list = query.list();
		return list;
	}
}
