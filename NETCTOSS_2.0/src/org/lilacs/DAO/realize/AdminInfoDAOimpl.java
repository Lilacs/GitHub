package org.lilacs.DAO.realize;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.lilacs.DAO.AdminInfoDAO;
import org.lilacs.po.Ability;
import org.lilacs.po.Account;
import org.lilacs.po.AdminInfo;
import org.lilacs.po.RoleInfo;
import org.lilacs.po.Service;


public class AdminInfoDAOimpl implements AdminInfoDAO {
	// 创建一条管理员信息
	public boolean create(AdminInfo adminInfo, int[] num) throws Exception {
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			Set<RoleInfo> roleInfos = new HashSet<RoleInfo>();
			for (int i : num) {
				RoleInfo ri = (RoleInfo) session.get(RoleInfo.class, i);
				roleInfos.add(ri);
			}
			adminInfo.setRoleInfos(roleInfos);
			adminInfo.setEnrolldate(new Date(System.currentTimeMillis()));
			session.save(adminInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 删除一名管理员
	public boolean delete(int id) throws Exception {
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			AdminInfo adminInfo = (AdminInfo) session.get(AdminInfo.class, id);
			session.delete(adminInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 根据id查询管理员信息
	public AdminInfo getAdminInfoById(int id) throws Exception {
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			AdminInfo adminInfo = (AdminInfo) session.load(AdminInfo.class, id);
			return adminInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 更新一条管理员信息
	public boolean update(AdminInfo adminInfo, int[] num) throws Exception {
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			AdminInfo a = (AdminInfo) session.load(AdminInfo.class,
					adminInfo.getId());
			a.setName(adminInfo.getName());
			a.setEmail(adminInfo.getEmail());
			a.setTelephone(adminInfo.getTelephone());
			Set<RoleInfo> roleInfos = new HashSet<RoleInfo>();
			for (int i : num) {
				RoleInfo roleInfo = (RoleInfo) session.get(RoleInfo.class, i);
				roleInfos.add(roleInfo);
			}
			a.setRoleInfos(roleInfos);
			session.update(a);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 分页查询a join a.roleInfos r where r.name like :roleName
	public List<AdminInfo> getAdminInfoList(String abilityType,
			String roleName, int page, int MAX_PAGE) throws Exception {
		String hql = "select distinct a from AdminInfo a join a.roleInfos r join r.abilitys c where r.name like :roleName";
		try {
			if (abilityType.equals("0")) {
				hql = hql + " and c.id in (1,2,3,4,5,6,7)";
			} else {
				hql = hql + " and c.id = :abilityType";
			}
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			Query query = session.createQuery(hql);
			if (roleName != null) {
				roleName = roleName + "%";
			} else
				roleName = "%";
			query.setString("roleName", roleName);
			if (!abilityType.equals("0")) {
				query.setString("abilityType", abilityType);
			}
			query.setFirstResult((page - 1) * MAX_PAGE);
			query.setMaxResults(MAX_PAGE);
			List<AdminInfo> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 查询一共多少页
	public int getPageCount(String abilityType, String roleName, int MAX_PAGE)
			throws Exception {

		String hql = "select distinct a from AdminInfo a join a.roleInfos r join r.abilitys c where r.name like :roleName";
		try {
			if (abilityType.equals("0")) {
				hql = hql + " and c.id in (1,2,3,4,5,6,7)";
			} else {
				hql = hql + " and c.id = :abilityType";
			}
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			Query query = session.createQuery(hql);
			if (roleName != null) {
				roleName = roleName + "%";
			} else
				roleName = "%";
			query.setString("roleName", roleName);
			if (!abilityType.equals("0")) {
				query.setString("abilityType", abilityType);
			}
			List<AdminInfo> list = query.list();

			int values = list.size();
			System.out.println("打印一共有多少记录数:" + values);
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

	// 登陆系统是进行验证用户是否存在(用于用户登陆)
	public boolean findUser(AdminInfo user) throws Exception {
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		String hql = "from AdminInfo a where a.adminCode = ?";
		Query query = session.createQuery(hql);
		query.setString(0, user.getAdminCode());
		AdminInfo ai = (AdminInfo) query.uniqueResult();
		if(ai == null){
			return false;
		}
		String pwd = ai.getPassword();
		if (pwd.equals(user.getPassword()))
			return true;
		else if (pwd.equals(""))
			return false;
		else
			return false;

	}

}
