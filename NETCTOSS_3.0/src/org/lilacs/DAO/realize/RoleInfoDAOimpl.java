package org.lilacs.DAO.realize;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.lilacs.DAO.RoleInfoDAO;
import org.lilacs.po.Ability;
import org.lilacs.po.RoleInfo;
import org.lilacs.util.BaseDAO;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
@Repository
public class RoleInfoDAOimpl extends BaseDAO implements RoleInfoDAO {
	// 创建一条角色信息
	public boolean create(RoleInfo roleInfo, int[] num)
			throws Exception {
		try {
			Set<Ability> abilitys = new HashSet<Ability>();
			for (int i : num) {
				String hql = "from Ability a where a.id = ? ";
				Ability ability = (Ability) this.getHibernateTemplate().get(Ability.class, i);
				abilitys.add(ability);
			}
			roleInfo.setAbilitys(abilitys);
			this.getHibernateTemplate().save(roleInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 删除一条角色信息
	public boolean delete(int id) throws Exception {
		try {
			RoleInfo ri = (RoleInfo) this.getHibernateTemplate().load(RoleInfo.class, id);
			this.getHibernateTemplate().delete(ri);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 根据id查找出角色信息
	public RoleInfo getRoleInfoById(int id) throws Exception {
		try {
			
			RoleInfo ri = (RoleInfo) this.getHibernateTemplate().load(RoleInfo.class, id);
			return ri;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 修改角色信息
	public boolean update(RoleInfo roleInfo, int[] num) throws Exception {
		try {
			RoleInfo r = (RoleInfo) this.getHibernateTemplate().load(RoleInfo.class,
					roleInfo.getId());
			r.setName(roleInfo.getName());
			Set<Ability> abilitys = new HashSet<Ability>();
			for (int i : num) {
				Ability ability = (Ability) this.getHibernateTemplate().get(Ability.class, i);
				abilitys.add(ability);
			}
			r.setAbilitys(abilitys);
			this.getHibernateTemplate().update(r);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 分页查询
	public List<RoleInfo> getRoleInfoList(final int page, final int MAX_PAGE)
			throws Exception {
		try {
			List<RoleInfo> list = (List<RoleInfo>) this.getHibernateTemplate().execute(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					String hql = "from RoleInfo";
					Query query = session.createQuery(hql);
					int begin = (page - 1) * MAX_PAGE;
					query.setFirstResult(begin);
					query.setMaxResults(MAX_PAGE);
					List<RoleInfo> list = query.list();
					return list;
				}
			});
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 查询一共有多少页
	public int getPageCount(int MAX_PAGE) throws Exception {
		try {
			String hql = "select count(*) from RoleInfo";
			List list = this.getHibernateTemplate().find(hql);
			int values = Integer.parseInt(list.get(0).toString());
			if (values % MAX_PAGE == 0) {
				int pageCount = values / MAX_PAGE;
				return pageCount;
			} else {
				int pageCount = values / MAX_PAGE + 1;
				return pageCount;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
		
	
	//查询所有的角色信息
	public List<RoleInfo> getAllRoleInfo() throws Exception{
		String hql = "from RoleInfo";
		List<RoleInfo> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
}
