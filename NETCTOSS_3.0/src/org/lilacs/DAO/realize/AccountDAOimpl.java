package org.lilacs.DAO.realize;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.lilacs.DAO.AccountDAO;
import org.lilacs.po.Account;
import org.lilacs.po.Service;
import org.lilacs.util.BaseDAO;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOimpl extends BaseDAO implements AccountDAO {

	// ����һ���µ��ʷ��˻�
	public boolean create(Account account) throws Exception {
		try {
			account.setCreateDate(new Date(System.currentTimeMillis()));
			account.setStatus("0");
			this.getHibernateTemplate().save(account);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	// ����һ����Ϣ
	public boolean update(Account account) throws Exception {
		try {
			Account a = (Account) this.getHibernateTemplate().get(
					Account.class, account.getId());
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
			this.getHibernateTemplate().save(a);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// �鿴һ���û���Ϣ
	public Account getAccountById(int id) throws Exception {
		Account account = (Account) this.getHibernateTemplate().get(
				Account.class, id);
		return account;
	}

	// ��ͨ�ʷ�
	public boolean openStatus(int id) throws Exception {
		try {
			Account account = (Account) this.getHibernateTemplate().get(
					Account.class, id);
			account.setStatus("0");
			account.setPauseDate(null);
			this.getHibernateTemplate().save(account);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// ��ͣʱ�� ������ͣʱ�� ����״̬Ϊ1(��ͣ״̬),ͬʱ�ر��˺��µ�ҵ��
	public boolean pauseStatus(int id) throws Exception {
		try {
			Account account = (Account) this.getHibernateTemplate().get(
					Account.class, id);
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
			this.getHibernateTemplate().save(account);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// ɾ���û���Ϣ(��״̬����Ϊ2)(ͬʱɾ�����˻��µ�ҵ���˺�)
	public Boolean delete(int id) throws Exception {
		try {
			Account account = (Account) this.getHibernateTemplate().get(
					Account.class, id);
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
			this.getHibernateTemplate().save(account);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	// ��֤�������Ƿ���ͬ
	public String getPWD(int id) throws Exception {
		Account account = (Account) this.getHibernateTemplate().get(
				Account.class, id);
		String pwd = account.getLoginPasswd();
		return pwd;
	}

	// �������֤�õ��û��ĵ�¼��
	public String getLoginName(String idCard) throws Exception {
		try {

			String hql = "from Account a where a.idcard_no = ?";
			Account account = (Account) this.getHibernateTemplate().find(hql,
					idCard);
			return account.getLoginName();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// ��ҳ��ѯ
	public List<Account> getAccountList(final String idCard,
			final String realName, final String loginName, final int status,
			final int page, final int MAX_PAGE) throws Exception {
		try {
			List<Account> account = (List<Account>) this.getHibernateTemplate()
					.execute(new HibernateCallback() {

						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							String idCard1;
							String realName1;
							String loginName1;
							if (idCard == null || idCard == "") {
								idCard1 = "%";
							} else {
								idCard1 = "%" + idCard + "%";
							}

							if (realName == null || realName == "") {
								realName1 = "%";
							} else {
								realName1 = "%" + realName + "%";
							}

							if (loginName == null || loginName == "") {
								loginName1 = "%";
							} else {
								loginName1 = "%" + loginName + "%";
							}

							String hql = "from Account a where a.idcardNo like :idcardNo "
									+ " and a.loginName like :loginName and a.realName like :realName ";
							if (status != 4) {
								hql = hql + "and a.status = :status";
							}
							Query query = session.createQuery(hql);
							query.setString("idcardNo", idCard1);
							query.setString("loginName", loginName1);
							query.setString("realName", realName1);
							if (status != 4) {
								query.setInteger("status", status);
							}
							// ���÷�ҳ��ѯ
							int begin = (page - 1) * MAX_PAGE;
							query.setFirstResult(begin);
							query.setMaxResults(MAX_PAGE);
							List<Account> account = query.list();
							return account;
						}
					});
			return account;
		} catch (Exception e) {
			return null;
		}
	}

	// ��ѯһ���ж���ҳ
	public int getPageCount(final String idCard, final String realName,
			final String loginName, final int status, int MAX_PAGE)
			throws Exception {
		try {
			int values = (Integer) this.getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							String idCard1;
							String realName1;
							String loginName1;
							if (idCard == null || idCard == "") {
								idCard1 = "%";
							} else {
								idCard1 = "%" + idCard + "%";
							}

							if (realName == null || realName == "") {
								realName1 = "%";
							} else {
								realName1 = "%" + realName + "%";
							}

							if (loginName == null || loginName == "") {
								loginName1 = "%";
							} else {
								loginName1 = "%" + loginName + "%";
							}

							String hql = "select count(*) from Account a where a.idcardNo like :idcardNo "
									+ " and a.loginName like :loginName and a.realName like :realName ";
							if (status != 4) {
								hql = hql + "and a.status = :status";
							}
							Query query = session.createQuery(hql);
							query.setString("idcardNo", idCard1);
							query.setString("loginName", loginName1);
							query.setString("realName", realName1);
							if (status != 4) {
								query.setInteger("status", status);
							}
							Object obj = query.uniqueResult();
							int values = Integer.parseInt(obj.toString());
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
			return 0;
		}
	}

	
}
