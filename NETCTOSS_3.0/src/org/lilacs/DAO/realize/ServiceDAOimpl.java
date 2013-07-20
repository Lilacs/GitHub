package org.lilacs.DAO.realize;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.lilacs.DAO.ServiceDAO;
import org.lilacs.po.Account;
import org.lilacs.po.Cost;
import org.lilacs.po.Host;
import org.lilacs.po.Service;
import org.lilacs.util.BaseDAO;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceDAOimpl extends BaseDAO implements ServiceDAO {
	// 创建一条业务账号
	public boolean create(Service service)  {
		try {
			Account account = (Account)this.getHibernateTemplate().get(Account.class, service.getAccount().getId());
			Host host = (Host) this.getHibernateTemplate().get(Host.class, service.getHost().getId());
			Cost cost = (Cost) this.getHibernateTemplate().get(Cost.class, service.getCost().getId());
			service.setStatus("0");
			service.setCreateDate(new Date(System.currentTimeMillis()));
			service.setAccount(account);
			service.setHost(host);
			service.setCost(cost);
			this.getHibernateTemplate().save(service);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 删除一条业务账号(设置状态为2)
	public boolean delete(int id) throws Exception {
		try {
			Service service = (Service) this.getHibernateTemplate().get(
					Service.class, id);
			service.setCloseDate(new Date(System.currentTimeMillis()));
			service.setStatus("2");
			this.getHibernateTemplate().update(service);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 根据id查询单条用户信息
	public Service getServiceById(int id) throws Exception {
		try {
			Service service = (Service) this.getHibernateTemplate().load(
					Service.class, id);
			return service;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 更新一条信息
	public boolean update(Service service) throws Exception {
		System.out.println("进入DAO操作");
		try {
			Service s = (Service) this.getHibernateTemplate().load(
					Service.class, service.getId());
			Cost cost = (Cost) this.getHibernateTemplate().get(Cost.class, service.getCost().getId());
			s.setCost(cost);
			
			this.getHibernateTemplate().update(s);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 暂停业务账号(设置状态为1,记载暂停时间)
	public boolean pauseStatus(int id) throws Exception {
		try {
			Service service = (Service) this.getHibernateTemplate().load(
					Service.class, id);
			service.setStatus("1");
			service.setPauseDate(new Date(System.currentTimeMillis()));
			this.getHibernateTemplate().save(service);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 开通业务账号
	public boolean openStatus(int id) throws Exception {
		try {
			Service service = (Service) this.getHibernateTemplate().load(
					Service.class, id);
			service.setStatus("0");
			service.setPauseDate(null);
			this.getHibernateTemplate().save(service);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 分页查询
	public List<Service> getServiceList(final String osUserName,
			final String unixHost, final String idCardNo, final String status,
			final int page, final int MAX_PAGE) throws Exception {
		try {
			List<Service> service = (List<Service>) this.getHibernateTemplate()
					.execute(new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							String osUserName1;
							String unixHost1;
							String idCardNo1;
							if (osUserName == null || osUserName == "") {
								osUserName1 = "%";
							} else {
								osUserName1 = "%" + osUserName + "%";
							}

							if (unixHost == null || unixHost == "") {
								unixHost1 = "%";
							} else {
								unixHost1 = "%" + unixHost + "%";
							}

							if (idCardNo == null || idCardNo == "") {
								idCardNo1 = "%";
							} else {
								idCardNo1 = "%" + idCardNo + "%";
							}

							String hql = "from Service s where s.osUsername like :osUserName "
									+ " and s.host.id like :unixHost and s.account.idcardNo like :idCardNo ";
							if (!status.equals("3")) {
								hql = hql + " and s.status = :status ";
							}
							Query query = session.createQuery(hql);
							query.setString("unixHost", unixHost1);
							query.setString("osUserName", osUserName1);
							query.setString("idCardNo", idCardNo1);
							if (!status.equals("3")) {
								query.setString("status", status);
							}
							int begin = (page - 1) * MAX_PAGE;
							query.setFirstResult(begin);
							query.setMaxResults(MAX_PAGE);
							List<Service> service = query.list();
							return service;
						}
					});
			return service;
		} catch (Exception e) {
			return null;
		}
	}

	// 根据条件分页查询
	public int getPageCount(final String osUserName, final String unixHost,
			final String idCardNo, final String status, int MAX_PAGE)
			throws Exception {
		try {
			int values = (Integer) this.getHibernateTemplate().execute(
					new HibernateCallback() {

						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							String osUserName1;
							String unixHost1;
							String idCardNo1;
							if (osUserName == null || osUserName == "") {
								osUserName1 = "%";
							} else {
								osUserName1 = "%" + osUserName + "%";
							}

							if (unixHost == null || unixHost == "") {
								unixHost1 = "%";
							} else {
								unixHost1 = "%" + unixHost + "%";
							}

							if (idCardNo == null || idCardNo == "") {
								idCardNo1 = "%";
							} else {
								idCardNo1 = "%" + idCardNo + "%";
							}

							String hql = "select count(*) from Service s where s.osUsername like :osUserName "
									+ " and s.host.id like :unixHost and s.account.idcardNo like :idCardNo ";
							if (!status.equals("3")) {
								hql = hql + " and s.status = ? ";
							}

							Query query = session.createQuery(hql);
							query.setString("unixHost", unixHost1);
							query.setString("osUserName", osUserName1);
							query.setString("idCardNo", idCardNo1);
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
