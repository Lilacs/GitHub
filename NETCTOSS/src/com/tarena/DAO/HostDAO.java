package com.tarena.DAO;

import java.sql.SQLException;
import java.util.List;

import com.tarena.po.Host;

public interface HostDAO {
	/*
	 * 找到所有的服务器
	 */
	public List<Host> getALLtype()throws SQLException;
}
