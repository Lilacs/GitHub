package com.tarena.DAO;

import java.sql.SQLException;
import java.util.List;

import com.tarena.po.CheckBoxList;

public interface AbilityDAO {
	// ����id����Ȩ������
	public String getAbility_ID(int id) throws SQLException;
	
	
	public List<String> findAbilty(int id) throws SQLException;

	//���ҳ�����Ȩ��id�Լ�����
	public List<CheckBoxList> getAll()throws SQLException;
}
