package org.lilacs.DAO.realize;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.lilacs.DAO.AbilityDAO;
import org.lilacs.po.Ability;

public class AbilityDAOimpl implements AbilityDAO {
	//得到所有的权限信息
	public List<Ability> getAbilitys(){
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		String hql = "from Ability";
		Query query = session.createQuery(hql);
		List<Ability> abilitys = query.list();
		return abilitys;
	}
}
