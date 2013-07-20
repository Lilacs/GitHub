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
		// hibernate 写SQL的表名都是类的名字,背后会映射到
		String hql = "from Cost";
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql);
		int begin = (pageValue - 1) * MAX_PAGE;// 参数索引从0开始
		query.setFirstResult(begin);// 设置起始数
		query.setMaxResults(MAX_PAGE);// 设置查询数
		List<Cost> list = query.list();
		return list;
	}

	// 得到一共有多少页
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lilacs.DAO.realize.CostDAO#maxPage(int)
	 */
	public int maxPage(int MAX_PAGE) throws SQLException {
		// hibernate 的sql语句
		String hql = "select count(*) from Cost";
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql);
		// 无法判断返回的是什么类型,所有进行强制转换
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

	// 删除一条资费信息
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lilacs.DAO.realize.CostDAO#delete_cost(int)
	 */
	public boolean delete_cost(int id) throws Exception {
		try{
		// 拿到Hibernate的Session用来对数据库进行连接
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

	// 创建一条资费信息
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

	// 根据id找到一条信息
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lilacs.DAO.realize.CostDAO#find_By_Id(int)
	 */
	public Cost find_By_Id(int id) throws Exception {
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		Cost cost = new Cost();
		cost = (Cost) session.load(Cost.class, id);
		// 如果是load方法的话,延迟加载(在调用getter方法时查询数据库)
		// 通过ActionContext获得session的map类型对象,将session放入struts的session中
		// 在拦截器那边执行完毕显示界面之后关闭该session
		// ActionContext ac = ActionContext.getContext();
		// Map<String,Object> se = ac.getSession();
		// se.put("hsession", session);
		return cost;
	}

	// 更新信息
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
	 * (更新成开通状态 同时记载开通时间)
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
	 * (用于ajax判断有没有该名称)
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

	// 找到所有的资费类型
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
