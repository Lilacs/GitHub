package org.lilacs.DAO.realize;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.lilacs.DAO.RoleInfoDAO;
import org.lilacs.po.Ability;
import org.lilacs.po.RoleInfo;

public class RoleInfoDAOimpl implements RoleInfoDAO {
	// ����һ����ɫ��Ϣ
	public boolean createRoleInfo(RoleInfo roleInfo, int[] num)
			throws Exception {
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			Set<Ability> abilitys = new HashSet<Ability>();
			for (int i : num) {
				String hql = "from Ability a where a.id = ? ";
				Ability ability = (Ability) session.get(Ability.class, i);
				abilitys.add(ability);
			}
			roleInfo.setAbilitys(abilitys);
			session.save(roleInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// ɾ��һ����ɫ��Ϣ
	public boolean delete(int id) throws Exception {
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			RoleInfo ri = (RoleInfo) session.load(RoleInfo.class, id);
			session.delete(ri);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// ����id���ҳ���ɫ��Ϣ
	public RoleInfo getRoleInfoById(int id) throws Exception {
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			RoleInfo ri = (RoleInfo) session.load(RoleInfo.class, id);
			return ri;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// �޸Ľ�ɫ��Ϣ
	public boolean modify(RoleInfo roleInfo, int[] num) throws Exception {
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			RoleInfo r = (RoleInfo) session.load(RoleInfo.class,
					roleInfo.getId());
			r.setName(roleInfo.getName());
			Set<Ability> abilitys = new HashSet<Ability>();
			for (int i : num) {
				String hql = "from Ability a where a.id = ? ";
				Ability ability = (Ability) session.get(Ability.class, i);
				abilitys.add(ability);
			}
			r.setAbilitys(abilitys);
			session.update(r);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// ��ҳ��ѯ
	public List<RoleInfo> getRoleInfoList(int page, int MAX_PAGE)
			throws Exception {
		try {
			String hql = "from RoleInfo";
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			Query query = session.createQuery(hql);
			int begin = (page - 1) * MAX_PAGE;
			query.setFirstResult(begin);
			query.setMaxResults(MAX_PAGE);
			List<RoleInfo> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ��ѯһ���ж���ҳ
	public int getPageCount(int MAX_PAGE) throws Exception {
		try {
			String hql = "select count(*) from RoleInfo";
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			Query query = session.createQuery(hql);
			Object obj = query.uniqueResult();
			int values = Integer.parseInt(obj.toString());
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
		
	
	//��ѯ���еĽ�ɫ��Ϣ
	public List<RoleInfo> getAllRoleInfo() throws Exception{
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		String hql = "from RoleInfo";
		Query query = session.createQuery(hql);
		List<RoleInfo> list = query.list();
		return list;
	}
	
}
