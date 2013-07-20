package org.lilacs.DAO.realize;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.lilacs.DAO.CostDAO;
import org.lilacs.po.Cost;
import org.lilacs.util.BaseDAO;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
@Repository
public class CostDAOimpl extends BaseDAO implements CostDAO {
	
	public List<Cost> findByPage(final int pageValue, final int MAX_PAGE)
			throws SQLException {
		List<Cost> list = (List<Cost>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "from Cost";
						Query query = session.createQuery(hql);
						query.setFirstResult((pageValue - 1) * MAX_PAGE);
						query.setMaxResults(MAX_PAGE);
						return query.list();
					}
				});
		return list;
	}

	// 得到一共有多少页
	public int maxPage(int MAX_PAGE) throws SQLException {
		String hql = "select count(*) from Cost";
		List list = this.getHibernateTemplate().find(hql);
		int values = Integer.parseInt(list.get(0).toString());
		if (values % MAX_PAGE == 0) {
			int pageCount = values / MAX_PAGE;
			return pageCount;
		} else {
			int pageCount = values / MAX_PAGE + 1;
			return pageCount;
		}
	}

	// 删除一条资费信息
	public boolean delete(int id) throws Exception {
		try {
			Cost cost = (Cost) this.getHibernateTemplate().get(Cost.class, id);
			this.getHibernateTemplate().delete(cost);
			return true;
		} catch (Throwable e) {
			return false;
		}
	}

	// 创建一条资费信息
	public boolean create(Cost cost) throws Exception {
		try {
			cost.setCreattime(new Date(System.currentTimeMillis()));
			cost.setStatus("0");
			this.getHibernateTemplate().save(cost);
			return true;
		} catch (Throwable e) {
			return false;
		}
	}

	// 根据id找到一条信息
	public Cost findById(int id) throws Exception {
		Cost cost = (Cost) this.getHibernateTemplate().get(Cost.class, id);
		return cost;
	}

	// 更新信息
	public boolean update(Cost cost) throws Exception {
		try {
			Cost cost1 = (Cost) this.getHibernateTemplate().get(Cost.class, cost.getId());
			cost1.setName(cost.getName());
			cost1.setBaseCost(cost.getBaseCost());
			cost1.setBaseDuration(cost.getBaseDuration());
			cost1.setDescr(cost.getDescr());
			cost1.setCosttype(cost.getCosttype());
			this.getHibernateTemplate().update(cost);
			return true;
		} catch (Throwable e) {
			return false;
		}
	}

	/*
	 * (更新成开通状态 同时记载开通时间)
	 */
	public boolean openStatus(int id) throws Exception {
		try {
			Cost cost = (Cost) this.getHibernateTemplate().get(Cost.class, id);
			cost.setStatus("1");
			cost.setStartime(new Date(System.currentTimeMillis()));
			this.getHibernateTemplate().update(cost);
			return true;
		} catch (Throwable e) {
			return false;
		}
	}

	/*
	 * (用于ajax判断有没有该名称)
	 */
	public boolean findByName(String name) throws SQLException {
		String hql = "from Cost where name = ?";
		Cost cost = (Cost) this.getHibernateTemplate().find(hql,name);
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

	
	public List<String> getIntroduction(int costId) throws SQLException {
		String hql = "from Cost";
		List<Cost> cost = this.getHibernateTemplate().find(hql);
		List<String> list = new ArrayList<String>();
		for (Cost c : cost) {
			list.add(c.getName());
			list.add(c.getDescr());
		}
		return list;
	}

	// 找到所有的资费类型
	public List<Cost> getAlltype() throws SQLException {
		List<Cost> list = this.getHibernateTemplate().find("from Cost");
		return list;
	}
}
