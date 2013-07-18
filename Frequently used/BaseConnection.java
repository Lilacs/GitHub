package com.tarena.UtilBag;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BaseConnection {
	private static Configuration con;
	private static SessionFactory sf;
	private static Session session;
	static {
		// 创建配置对象,加载配置文件,得到SessionFactory对象,然后得到Session对象
		con = new Configuration();
		con.configure("/hibernate.cfg.xml");
		sf = con.buildSessionFactory();
	}

	public static Session getSession() {
		return sf.openSession();
	}
}
