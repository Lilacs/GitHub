package com.tarena.DAO;

import java.sql.SQLException;
import java.util.List;

import com.tarena.po.Host;

public interface HostDAO {
	/*
	 * �ҵ����еķ�����
	 */
	public List<Host> getALLtype()throws SQLException;
}
