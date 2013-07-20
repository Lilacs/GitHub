package org.lilacs.DAO.realize;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.lilacs.DAO.ServiceDAO;
import org.lilacs.po.Service;

public class ServiceDAOimpl implements ServiceDAO {
	// 创建一条业务账号
	public boolean createService(Service service) throws Exception {
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			service.setStatus("0");
			service.setCreateDate(new Date(System.currentTimeMillis()));
			session.save(service);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 删除一条业务账号(设置状态为2)
	public boolean deleteById(int id) throws Exception {
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			Service service = (Service) session.get(Service.class, id);
			service.setCloseDate(new Date(System.currentTimeMillis()));
			service.setStatus("2");
			session.update(service);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 根据id查询单条用户信息
	public Service getServiceById(int id) throws Exception {
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			Service service = (Service) session.load(Service.class, id);
			return service;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//更新一条信息
	public boolean update(Service service) throws Exception{
		try{
			Session session = org.lilacs.util.HibernateSessionFactory.getSession();
			Service s = (Service) session.load(Service.class, service.getId());
			s.setCost(service.getCost());
			session.update(s);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//暂停业务账号(设置状态为1,记载暂停时间)
	public boolean pause(int id)throws Exception{
		try{
			Session session = org.lilacs.util.HibernateSessionFactory.getSession();
			Service service = (Service) session.load(Service.class, id);
			service.setStatus("1");
			service.setPauseDate(new Date(System.currentTimeMillis()));
			session.save(service);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	//开通业务账号
	public boolean open(int id)throws Exception{
		try{
			Session session = org.lilacs.util.HibernateSessionFactory.getSession();
			Service service = (Service) session.load(Service.class, id);
			service.setStatus("0");
			service.setPauseDate(null);
			session.save(service);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	// 分页查询
	public List<Service> getServiceList(String osUserName, String unixHost,
			String idCardNo, String status, int page, int MAX_PAGE)
			throws Exception {
		if (osUserName == null || osUserName == "") {
			osUserName = "%";
		} else {
			osUserName = "%" + osUserName + "%";
		}

		if (unixHost == null || unixHost == "") {
			unixHost = "%";
		} else {
			unixHost = "%" + unixHost + "%";
		}

		if (idCardNo == null || idCardNo == "") {
			idCardNo = "%";
		} else {
			idCardNo = "%" + idCardNo + "%";
		}

		String hql = "from Service s where s.osUsername like :osUserName "
				+ " and s.host.id like :unixHost and s.account.idcardNo like :idCardNo ";
		if (!status.equals("3")) {
			hql = hql + " and s.status = ? ";
		}
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			Query query = session.createQuery(hql);
			query.setString("unixHost", unixHost);
			query.setString("osUserName", osUserName);
			query.setString("idCardNo", idCardNo);
			if (!status.equals("3")) {
				query.setString("status", status);
			}
			int begin = (page - 1) * MAX_PAGE;
			query.setFirstResult(begin);
			query.setMaxResults(MAX_PAGE);
			List<Service> service = query.list();
			return service;
		} catch (Exception e) {
			return null;
		}
	}

	// 根据条件分页查询
	public int getPageCount(String osUserName, String unixHost,
			String idCardNo, String status, int MAX_PAGE) throws Exception {
		if (osUserName == null || osUserName == "") {
			osUserName = "%";
		} else {
			osUserName = "%" + osUserName + "%";
		}

		if (unixHost == null || unixHost == "") {
			unixHost = "%";
		} else {
			unixHost = "%" + unixHost + "%";
		}

		if (idCardNo == null || idCardNo == "") {
			idCardNo = "%";
		} else {
			idCardNo = "%" + idCardNo + "%";
		}

		String hql = "select count(*) from Service s where s.osUsername like :osUserName "
				+ " and s.host.id like :unixHost and s.account.idcardNo like :idCardNo ";
		if (!status.equals("3")) {
			hql = hql + " and s.status = ? ";
		}
		try {
			Session session = org.lilacs.util.HibernateSessionFactory
					.getSession();
			Query query = session.createQuery(hql);
			query.setString("unixHost", unixHost);
			query.setString("osUserName", osUserName);
			query.setString("idCardNo", idCardNo);
			if (!status.equals("3")) {
				query.setString("status", status);
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
