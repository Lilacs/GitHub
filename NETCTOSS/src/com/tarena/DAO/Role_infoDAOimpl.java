package com.tarena.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tarena.UtilBag.BaseDAO;
import com.tarena.UtilBag.DAOFactory;
import com.tarena.po.Role_info;

public class Role_infoDAOimpl extends BaseDAO implements Role_infoDAO {
	private static final String CREATE_ROLE_INFO = "insert into lilacs_ROLE_INFO values(lilacs_role_info_id.nextval,?)";
	private static final String FIND_ROLE_INFO_ID = "select id from lilacs_ROLE_INFO where name = ?";
	private static final String CREATE_ROLE_PRIVILEGE = "insert into lilacs_ROLE_PRIVILEGE values(?,?)";

	private static final String FIND_ROLE_INFO_NAME = "select name from lilacs_role_info where name = ?";

	private static final String UPDATE_ROLE_INFO_NAME = "update lilacs_role_info set name = ? where name = ?";
	private static final String DELETE_ROLE_ROLE_PRIVILEGE = "delete lilacs_role_privilege where role_id = ?";

	private static final String DELETE_ROLE_INFO_BY_ID = "delete lilacs_role_info where id = ?";

	private static final String ALL_LIST_BY_PAGE = "select * from (select rownum r,ri.* from lilacs_role_info ri where rownum <= ?) where r > ?";
	private static final String FIND_ALL_INFO = "select count(*) count from lilacs_role_info";
	//创建角色信息
	public boolean create_Role_info(Role_info info, int[] value)
			throws SQLException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(CREATE_ROLE_INFO);
			ps.setString(1, info.getName());
			ps.executeUpdate();
			ps = conn.prepareStatement(FIND_ROLE_INFO_ID);
			ps.setString(1, info.getName());
			rs = ps.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = rs.getInt("id");
			}
			if (id == 0) {
				rollback();
				return flag;
			}
			for (int i = 0; i < value.length; i++) {
				ps = conn.prepareStatement(CREATE_ROLE_PRIVILEGE);
				ps.setInt(1, id);
				ps.setInt(2, value[i]);
				ps.executeUpdate();
			}
			commit();
		} catch (SQLException e) {
			e.printStackTrace();
			rollback();
			return flag;
		} finally {
			close();
		}
		flag = true;
		return flag;
	}
	//Ajax验证该用户名称是否存在
	public boolean find_Role_info_name(String name) throws SQLException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name2 = "";
		try {
			conn = getConnection();
			ps = conn.prepareStatement(FIND_ROLE_INFO_NAME);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				name2 = rs.getString("name");
			}
			if (name2.equals(name)) {
				return flag;
			} else {
				flag = true;
				return flag;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close();
		}

	}
	//修改角色信息
	public boolean modify_Role_info(String newName, String oldName, int[] num)
			throws SQLException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(UPDATE_ROLE_INFO_NAME);
			ps.setString(1, newName);
			ps.setString(2, oldName);
			ps.executeUpdate();
			ps = conn.prepareStatement(FIND_ROLE_INFO_ID);
			ps.setString(1, newName);
			rs = ps.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = rs.getInt("id");
			}
			if (id == 0) {
				rollback();
				return flag;
			}
			ps = conn.prepareStatement(DELETE_ROLE_ROLE_PRIVILEGE);
			ps.setInt(1, id);
			ps.executeUpdate();
			for (int i = 0; i < num.length; i++) {
				ps = conn.prepareStatement(CREATE_ROLE_PRIVILEGE);
				ps.setInt(1, id);
				ps.setInt(2, num[i]);
				ps.executeUpdate();
			}
			commit();
		} catch (SQLException e) {
			e.printStackTrace();
			rollback();
			return flag;
		} finally {
			close();
		}
		flag = true;
		return flag;
	}
	//删除角色
	public boolean delete_Role_info(int id) throws SQLException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(DELETE_ROLE_INFO_BY_ID);
			ps.setInt(1, id);
			ps.executeUpdate();
			commit();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			rollback();
			return flag;
		} finally {
			close();
		}
		return flag;
	}
	//分页查询 显示页面
	public List<Role_info> getList(int pageValue, int MAX_PAGE)
			throws SQLException {
		List<Role_info> list = new ArrayList<Role_info>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int begin = MAX_PAGE * pageValue;
		int end = begin - MAX_PAGE;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(ALL_LIST_BY_PAGE);
			ps.setInt(1, begin);
			ps.setInt(2, end);
			rs = ps.executeQuery();

			while (rs.next()) {
				Role_info newinfo = new Role_info();
				List<String> ability = new ArrayList<String>();
				int m = rs.getInt("id");
				newinfo.setId(m);// 设置id
				newinfo.setName(rs.getString("name"));
				ability = new DAOFactory().getAbilityDAO().findAbilty(m);
				newinfo.setPrivilege(ability);
				list.add(newinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	//查询一共多少页
	public int howManyPage(int MAX_PAGE) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int pageCount = 0;
		int count = 0;
		try{
			conn= getConnection();
		}catch(SQLException e){
			e.printStackTrace();
		}
			ps = conn.prepareStatement(FIND_ALL_INFO); 
			rs = ps.executeQuery();
			while(rs.next()){
				count = rs.getInt("count");
			}
			close();
			if(count%MAX_PAGE > 0){
				pageCount = count/MAX_PAGE+1;
				return pageCount;
			}else{		
				pageCount = count/MAX_PAGE;
				return pageCount;
			}
			
		
	}
	public String getName(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = null;
		try{
			conn = getConnection();
			ps = conn.prepareStatement("select name from lilacs_role_info where id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				name= rs.getString("name");
			}
			return name;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			close();
		}
	}
	/*
	 * 在新建管理员的时候需要用到该方法
	 * @see com.tarena.DAO.Role_infoDAO#getAllInfo()\
	 * 目的是得到所有角色的id和name用来生成增加管理员页面的多选框
	 */
	public List<Role_info> getAllInfo() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Role_info> list = new ArrayList<Role_info>();
		try{
			 conn = getConnection();
			 ps = conn.prepareStatement("select * from lilacs_role_info");
			 rs = ps.executeQuery();
			 while(rs.next()){
				 Role_info ri = new Role_info();
				 ri.setId(rs.getInt("id"));
				 ri.setName(rs.getString("name"));
				 list.add(ri);
			 }
			 return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close();
		}
		return null;
	}
	//根据角色名称查找角色id
	public int getID(String name) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;
		try{
			conn = getConnection();
			ps = conn.prepareStatement("select id from lilacs_role_info where name = ?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()){
				id = rs.getInt("id");
			}
			return id;
		}catch(SQLException e){
			e.printStackTrace();
			return id;
		}finally{
			close();
		}
	}
	//根据传来的角色名称判断是否已经存在,排除他自己的名称,用户更改信息
	public boolean modiName(String name) throws SQLException {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try{
				conn = getConnection();
				ps = conn.prepareStatement("select * from ( select name from lilacs_role_info where name != ? ) where name = ? ");
				ps.setString(1, name);
				ps.setString(2, name);
				rs = ps.executeQuery();
				if(rs.next()){
					return false;
				}else{
					return true;
				}
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}finally{
				close();
			}
		}

}
