package com.tarena.DAO;

import java.sql.SQLException;
import java.util.List;

import com.tarena.po.Role_info;

public interface Role_privilegeDAO {
	public List<Integer> getRole_privilege_ID(int id)throws SQLException;
	
}
