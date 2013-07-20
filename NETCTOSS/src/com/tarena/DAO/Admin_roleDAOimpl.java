package com.tarena.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sun.net.www.content.text.plain;

import com.sun.org.apache.regexp.internal.recompile;
import com.tarena.UtilBag.BaseDAO;

public class Admin_roleDAOimpl extends BaseDAO implements Admin_roleDAO {
	//显示管理员的角色信息
	private final static String FIND_ADMIN_ROLE = "select name from(select a1.id c1,a1.name c2, a1.name c3, a1.admin_code c4,a1.telephone c5,a1.email c6,a2.role_id c7 from lilacs_admin_info a1 left join lilacs_admin_role a2 on a1.id = a2.admin_id) a3 left join lilacs_role_info a4 on a4.id = a3.c7 where a3.c1 = ?";
		
	public boolean createAdminRole(int id, int[] num) throws SQLException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			for (int i = 0; i < num.length; i++) {
				ps = conn
						.prepareStatement("insert into lilacs_ADMIN_ROLE values(?,?)");
				ps.setInt(1, id);
				ps.setInt(2, num[i]);
				int a = ps.executeUpdate();
			}
			flag = true;
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
			return flag;
		}
		
	}
	/*
	 * 根据id查出该管理员的角色信息
	 * @see com.tarena.DAO.Admin_roleDAO#getRoleInfo(int)
	 */
	public List<String> getRoleInfo(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try{
			conn = getConnection();
			ps = conn.prepareStatement(FIND_ADMIN_ROLE);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				String name = rs.getString("name");
				list.add(name);
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
			return list;
		}
	}
	/*
	 * (根据管理员的id进行查找他的角色信息)
	 * @see com.tarena.DAO.Admin_roleDAO#findRoleIdByAdminID(int)
	 */
	public List<Integer> findRoleIdByAdminID(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Integer> list = new ArrayList<Integer>();
		try{
			conn = getConnection();
			ps = conn.prepareStatement("select role_id from lilacs_admin_role where admin_id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				int adminrole = rs.getInt("role_id");
				list.add(adminrole);
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
			return list;
		}finally{
			close();
		}
	}
	/*
	 * (不关闭连接)
	 * 与修改管理员角色同为一条事务
	 * @see com.tarena.DAO.Admin_roleDAO#deleteRole(int)
	 */
	public boolean deleteRole(int id) throws SQLException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = getConnection();
			ps = conn.prepareStatement("delete lilacs_admin_role where admin_id = ?");
			ps.setInt(1, id);
			int a = ps.executeUpdate();
			System.out.println(a);
			flag = true;
			return flag;
		}catch(SQLException e){
			e.printStackTrace();
			return flag;
		}
	}
	
}
