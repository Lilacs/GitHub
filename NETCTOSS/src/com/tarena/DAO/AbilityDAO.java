package com.tarena.DAO;

import java.sql.SQLException;
import java.util.List;

import com.tarena.po.CheckBoxList;

public interface AbilityDAO {
	// 根据id查找权限名称
	public String getAbility_ID(int id) throws SQLException;
	
	
	public List<String> findAbilty(int id) throws SQLException;

	//查找出所有权限id以及名称
	public List<CheckBoxList> getAll()throws SQLException;
}
