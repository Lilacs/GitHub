package com.tarena.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tarena.UtilBag.DAOFactory;
import com.tarena.po.CheckBoxList;

public class CheckBoxListDAO {
	public List<CheckBoxList> AllAbility(){
		List<CheckBoxList> list = null;
		try {
			list = new DAOFactory().getAbilityDAO().getAll();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
	}
}
