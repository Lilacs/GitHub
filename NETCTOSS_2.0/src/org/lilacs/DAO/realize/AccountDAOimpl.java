package org.lilacs.DAO.realize;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.lilacs.DAO.AccountDAO;
import org.lilacs.po.Account;
import org.lilacs.po.Service;

public class AccountDAOimpl implements AccountDAO {

	// 创建一条新的资费账户
	public boolean createAccount(Account account) throws Exception {
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			account.setCreateDate(new Date(System.currentTimeMillis()));
			account.setStatus("0");
			session.save(account);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	// 更新一条信息
	public boolean update(Account account) throws Exception {
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			Account a = (Account) session.get(Account.class, account.getId());
			a.setRealName(account.getRealName());
			a.setTelephone(account.getTelephone());
			a.setOccupation(account.getOccupation());
			a.setGender(account.getGender());
			a.setEmail(account.getEmail());
			a.setMailaddress(account.getMailaddress());
			a.setZipcode(account.getZipcode());
			a.setQq(account.getQq());
			if (account.getLoginPasswd() != null
					&& account.getLoginPasswd().length() > 0) {
				a.setLoginPasswd(account.getLoginPasswd());
			}
			session.save(a);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 查看一条用户信息
	public Account getAccountById(int id) throws Exception {
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		Account account = (Account) session.load(Account.class, id);
		return account;
	}

	// 开通资费
	public boolean open(int id) throws Exception {
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		try {
			Account account = (Account) session.get(Account.class, id);
			account.setStatus("0");
			account.setPauseDate(null);
			session.save(account);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 暂停时间 记载暂停时间 设置状态为1(暂停状态),同时关闭账号下的业务
	public boolean pause(int id) throws Exception {
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		try {
			Account account = (Account) session.get(Account.class, id);
			if (account.getServices() != null) {
				Set<org.lilacs.po.Service> services = account.getServices();
				Set<Service> services1 = new HashSet<Service>();
				for (Service s : services) {
					s.setStatus("1");
					services1.add(s);
				}
				account.setServices(services1);
			}
			account.setStatus("1");
			account.setPauseDate(new Date(System.currentTimeMillis()));
			session.save(account);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 删除用户信息(将状态设置为2)(同时删除其账户下得业务账号)
	public Boolean delete(int id) throws Exception {
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			Account account = (Account) session.get(Account.class, id);
			if (account.getServices() != null) {
				Set<Service> services = account.getServices();
				Set<Service> s = new HashSet<Service>();
				for (Service i : services) {
					Service j = i;
					j.setStatus("2");
					s.add(j);
				}
				account.setServices(s);
			}
			account.setStatus("2");
			session.save(account);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	// 验证旧密码是否相同
	public String getPWD(int id) throws Exception {
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		Account account = (Account) session.get(Account.class, id);
		String pwd = account.getLoginPasswd();
		return pwd;
	}

	// 根据身份证得到用户的登录名
	public String getLoginName(String idCard) throws Exception {
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			String hql = "from Account a where a.idcard_no = ?";
			Query query = session.createQuery(hql);
			query.setString(0, idCard);
			Account account = (Account) query.uniqueResult();
			System.out.println(account.getLoginName());
			return account.getLoginName();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 分页查询
	public List<Account> getAccountList(String idCard, String realName,
			String loginName, int status, int page, int MAX_PAGE)
			throws Exception {
		if (idCard == null || idCard == "") {
			idCard = "%";
		} else {
			idCard = "%" + idCard + "%";
		}

		if (realName == null || realName == "") {
			realName = "%";
		} else {
			realName = "%" + realName + "%";
		}

		if (loginName == null || loginName == "") {
			loginName = "%";
		} else {
			loginName = "%" + loginName + "%";
		}

		String hql = "from Account a where a.idcardNo like :idcardNo "
				+ " and a.loginName like :loginName and a.realName like :realName ";
		if (status != 4) {
			hql = hql + "and a.status = :status";
		}
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			Query query = session.createQuery(hql);
			query.setString("idcardNo", idCard);
			query.setString("loginName", loginName);
			query.setString("realName", realName);
			if (status != 4) {
				query.setInteger("status", status);
			}
			// 设置分页查询
			int begin = (page - 1) * MAX_PAGE;
			query.setFirstResult(begin);
			query.setMaxResults(MAX_PAGE);
			List<Account> account = query.list();
			return account;
		} catch (Exception e) {
			return null;
		}
	}

	// 查询一共有多少页
	public int getPageCount(String idCard, String realName, String loginName,
			int status, int MAX_PAGE) throws Exception {
		if (idCard == null || idCard == "") {
			idCard = "%";
		} else {
			idCard = "%" + idCard + "%";
		}

		if (realName == null || realName == "") {
			realName = "%";
		} else {
			realName = "%" + realName + "%";
		}

		if (loginName == null || loginName == "") {
			loginName = "%";
		} else {
			loginName = "%" + loginName + "%";
		}

		String hql = "select count(*) from Account a where a.idcardNo like :idcardNo "
				+ " and a.loginName like :loginName and a.realName like :realName ";
		if (status != 4) {
			hql = hql + "and a.status = :status";
		}
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			Query query = session.createQuery(hql);
			query.setString("idcardNo", idCard);
			query.setString("loginName", loginName);
			query.setString("realName", realName);
			if (status != 4) {
				query.setInteger("status", status);
			}
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
			return 0;
		}
	}
	
}
