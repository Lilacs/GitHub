package org.lilacs.DAO.realize;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.lilacs.DAO.HostDAO;
import org.lilacs.po.Host;
import org.lilacs.util.BaseDAO;
import org.springframework.stereotype.Repository;
@Repository
public class HostDAOimpl extends BaseDAO implements HostDAO {
	//�ҵ����з�������Ϣ
	public List<Host> getAll() throws Exception{
		String hql = "from Host";
		List<Host> list = this.getHibernateTemplate().find(hql);
		return list;
	}
}
