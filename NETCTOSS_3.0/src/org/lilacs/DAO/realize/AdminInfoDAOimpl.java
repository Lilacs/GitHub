package org.lilacs.DAO.realize;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.lilacs.DAO.AdminInfoDAO;
import org.lilacs.po.Ability;
import org.lilacs.po.AdminInfo;
import org.lilacs.po.RoleInfo;
import org.lilacs.util.BaseDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class AdminInfoDAOimpl extends BaseDAO implements AdminInfoDAO {
	// ����һ������Ա��Ϣ
	public boolean create(AdminInfo adminInfo, int[] num) throws Exception {
		try {
			Set<RoleInfo> roleInfos = new HashSet<RoleInfo>();
			for (int i : num) {
				RoleInfo ri = (RoleInfo) this.getHibernateTemplate().get(
						RoleInfo.class, i);
				roleInfos.add(ri);
			}
			adminInfo.setRoleInfos(roleInfos);
			adminInfo.setEnrolldate(new Date(System.currentTimeMillis()));
			this.getHibernateTemplate().save(adminInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// ɾ��һ������Ա
	public boolean delete(int id) throws Exception {
		try {
			AdminInfo adminInfo = (AdminInfo) this.getHibernateTemplate().get(
					AdminInfo.class, id);
			this.getHibernateTemplate().delete(adminInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// ����id��ѯ����Ա��Ϣ
	public AdminInfo getAdminInfoById(int id) throws Exception {
		try {
			AdminInfo adminInfo = (AdminInfo) this.getHibernateTemplate().load(
					AdminInfo.class, id);
			return adminInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ����һ������Ա��Ϣ
	public boolean update(AdminInfo adminInfo, int[] num) throws Exception {
		try {
			AdminInfo a = (AdminInfo) this.getHibernateTemplate().load(
					AdminInfo.class, adminInfo.getId());
			a.setName(adminInfo.getName());
			a.setEmail(adminInfo.getEmail());
			a.setTelephone(adminInfo.getTelephone());
			Set<RoleInfo> roleInfos = new HashSet<RoleInfo>();
			for (int i : num) {
				RoleInfo roleInfo = (RoleInfo) this.getHibernateTemplate().get(
						RoleInfo.class, i);
				roleInfos.add(roleInfo);
			}
			a.setRoleInfos(roleInfos);
			this.getHibernateTemplate().update(a);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// ��ҳ��ѯa join a.roleInfos r where r.name like :roleName
	public List<AdminInfo> getAdminInfoList(final String abilityType,
			final String roleName, final int page, final int MAX_PAGE)
			throws Exception {
		try {
			List<AdminInfo> list = (List<AdminInfo>) this
					.getHibernateTemplate().execute(new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							String hql = "select distinct a from AdminInfo a join a.roleInfos r join r.abilitys c where r.name like :roleName";
							String roleName1;
							if (abilityType.equals("0")) {
								hql = hql + " and c.id in (1,2,3,4,5,6,7)";
							} else {
								hql = hql + " and c.id = :abilityType";
							}
							Query query = session.createQuery(hql);
							if (roleName != null) {
								roleName1 = roleName + "%";
							} else
								roleName1 = "%";
							query.setString("roleName", roleName1);
							if (!abilityType.equals("0")) {
								query.setString("abilityType", abilityType);
							}
							query.setFirstResult((page - 1) * MAX_PAGE);
							query.setMaxResults(MAX_PAGE);
							List<AdminInfo> list = query.list();
							return list;
						}
					});
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ��ѯһ������ҳ
	public int getPageCount(final String abilityType, final String roleName,
			int MAX_PAGE) throws Exception {
		try {
			int values = (Integer) this.getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							String hql = "select distinct a from AdminInfo a join a.roleInfos r join r.abilitys c where r.name like :roleName";
							String roleName1;
							if (abilityType.equals("0")) {
								hql = hql + " and c.id in (1,2,3,4,5,6,7)";
							} else {
								hql = hql + " and c.id = :abilityType";
							}
							Query query = session.createQuery(hql);
							if (roleName != null) {
								roleName1 = roleName + "%";
							} else
								roleName1 = "%";
							query.setString("roleName", roleName1);
							if (!abilityType.equals("0")) {
								query.setString("abilityType", abilityType);
							}
							List<AdminInfo> list = query.list();
							int values = list.size();
							return values;
						}
					});
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

	// ��½ϵͳ�ǽ�����֤�û��Ƿ����(�����û���½)
	public boolean findUser(AdminInfo user) throws Exception {
		String hql = "from AdminInfo a where a.adminCode = ?";
		List<AdminInfo> list = this.getHibernateTemplate().find(hql,
				user.getAdminCode());
		if (list.size() > 0) {
			AdminInfo ai = list.get(0);
			if (ai == null) {
				return false;
			}
			String pwd = ai.getPassword();
			if (pwd.equals(user.getPassword()))
				return true;
			else if (pwd.equals(""))
				return false;
			else
				return false;
		} else
			return false;
	}

	// �ҵ��û�������Ȩ����Ϣ
	public HashSet<Integer> getAbilitys(final String adminCode) {
		HashSet<Integer> abilitys = (HashSet<Integer>) this
				.getHibernateTemplate().execute(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "from AdminInfo where adminCode = ?";
						Query query = session.createQuery(hql);
						query.setString(0, adminCode);
						List list = query.list();
						AdminInfo adminInfo = (AdminInfo) list.get(0);
						HashSet<Integer> abilitys = new HashSet<Integer>();
						Set<RoleInfo> roleInfos = adminInfo.getRoleInfos();
						for (RoleInfo ri : roleInfos) {
							for (Ability ai : ri.getAbilitys()) {
								abilitys.add(ai.getId());
							}
						}
						return abilitys;
					}
				});

		return abilitys;
	}

	// ������Ϣ�ҵ��ý�ɫ��������Ϣ
	public AdminInfo getAdminInfo(final String adminCode) {
		AdminInfo adminInfo = (AdminInfo) this.getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "from AdminInfo where adminCode = ?";
						Query query = session.createQuery(hql);
						query.setString(0, adminCode);
						AdminInfo adminInfo = (AdminInfo) query.uniqueResult();
						return adminInfo;
					}
				});
		return adminInfo;
	}

	// �޸ĸ�����Ϣ
	public boolean update(AdminInfo adminInfo) {
		System.out.println("����DAO����");
		try {
			AdminInfo adminInfo1 = (AdminInfo) this.getHibernateTemplate().get(
					AdminInfo.class, adminInfo.getId());
			adminInfo1.setName(adminInfo.getName());
			adminInfo1.setEmail(adminInfo.getEmail());
			adminInfo1.setTelephone(adminInfo.getTelephone());
			this.getHibernateTemplate().update(adminInfo1);
			return true;
		} catch (DataAccessException e) {
			return false;
		}
	}

}
