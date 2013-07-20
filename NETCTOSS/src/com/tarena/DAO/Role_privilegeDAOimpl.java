package com.tarena.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tarena.UtilBag.BaseDAO;
import com.tarena.po.Role_info;

public class Role_privilegeDAOimpl extends BaseDAO implements Role_privilegeDAO {
	// 根据id查找权限
	private static final String FIND_BY_INFO_ID = "select PRIVILEGE_ID from lilacs_ROLE_PRIVILEGE where ROLE_ID = ?";

	public List<Integer> getRole_privilege_ID(int id) throws SQLException {
		List<Integer> list = new ArrayList<Integer>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(FIND_BY_INFO_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				int privilegeid = rs.getInt("PRIVILEGE_ID");
				list.add(privilegeid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			close();

		}
		return list;
	}

}
