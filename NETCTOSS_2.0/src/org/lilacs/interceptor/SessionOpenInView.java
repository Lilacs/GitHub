package org.lilacs.interceptor;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SessionOpenInView extends AbstractInterceptor {

	/**
	 * ¿πΩÿ∆˜,”√”⁄opensessioninview,—”≥Ÿº”‘ÿ.
	 */
	public String intercept(ActionInvocation arg0) throws Exception {
		Session session = org.lilacs.util.HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
		try{
			arg0.invoke();
			tr.commit();
			return null;
		}catch(Exception e){
			tr.rollback();
			throw e;
		}finally{
			org.lilacs.util.HibernateSessionFactory.closeSession();
		}
	}
	
}	
