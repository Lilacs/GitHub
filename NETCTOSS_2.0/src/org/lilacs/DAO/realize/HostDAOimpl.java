package org.lilacs.DAO.realize;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.lilacs.DAO.HostDAO;
import org.lilacs.po.Host;

public class HostDAOimpl implements HostDAO {
	//�ҵ����з�������Ϣ
	public List<Host> getAll() throws Exception{
		String hql = "from Host";
		Session session  = org.lilacs.util.HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql);
		List<Host> list = query.list();
		return list;
	}
}
