package com.tarena.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tarena.UtilBag.BaseDAO;
import com.tarena.po.CheckBoxList;

public class AbilityDAOimpl extends BaseDAO implements AbilityDAO {
	private static final String FIND_TYPE = "select ablity_type from lilacs_ability where id = ?";
	private static final String FIND_ABILITY = "select ablity_type from lilacs_ability where id in(select privilege_id from lilacs_ROLE_PRIVILEGE where role_id in(select id from lilacs_ROLE_INFO where id = ?))";

	public String getAbility_ID(int id) throws SQLException {
		String name = "";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(FIND_TYPE);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("ablity_type");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

	public List<String> findAbilty(int id) throws SQLException {
		List<String> ability = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = getConnection();
		ps = conn.prepareStatement(FIND_ABILITY);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		while (rs.next()) {
			String a = rs.getString("ablity_type");
			ability.add(a);
		}
		return ability;
	}
	//查找出所有权限的id与名称
	public List<CheckBoxList> getAll() throws SQLException {
		Connection conn = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		List<CheckBoxList> list =  new ArrayList<CheckBoxList>();
		try{
			conn = getConnection();
			ps = conn.prepareStatement("select * from lilacs_ability");
			rs = ps.executeQuery();
			while(rs.next()){
				CheckBoxList cbl = new CheckBoxList(rs.getInt("id"),rs.getString("ablity_type"));
				list.add(cbl);
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
			return list;
		}finally{
			close();
		}
	}

}
