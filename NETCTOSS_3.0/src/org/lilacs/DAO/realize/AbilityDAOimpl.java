package org.lilacs.DAO.realize;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.lilacs.DAO.AbilityDAO;
import org.lilacs.po.Ability;
import org.lilacs.util.BaseDAO;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
@Repository
public class AbilityDAOimpl extends BaseDAO implements AbilityDAO {
	//得到所有的权限信息
	public List<Ability> getAbilitys(){
		
		List<Ability> list = (List<Ability>) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql = "from Ability";
				Query query = session.createQuery(hql);
				List<Ability> abilitys = query.list();
				return abilitys;
			}
		});
		return list;
	}
}
