package com.tarena.UtilBag;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BaseConnection {
	private static Configuration con;
	private static SessionFactory sf;
	private static Session session;
	static {
		// �������ö���,���������ļ�,�õ�SessionFactory����,Ȼ��õ�Session����
		con = new Configuration();
		con.configure("/hibernate.cfg.xml");
		sf = con.buildSessionFactory();
	}

	public static Session getSession() {
		return sf.openSession();
	}
}
