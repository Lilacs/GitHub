package com.tarena.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.sun.crypto.provider.RSACipher;
import com.tarena.UtilBag.BaseDAO;
import com.tarena.po.Cost;

public class CostDAOimpl extends BaseDAO implements CostDAO {
	private static final String FIND_BY_PAGE = "select * from (select rownum r, c.* from lilacs_cost c where rownum <= ?) where r > ?";
	private static final String MAX_PAGE_SIZE = "select count(*) count from lilacs_cost";
	private static final String DELETE_BY_USER = "delete from lilacs_cost where id=?";
	private static final String CREATE_COST_TYPE = "insert into lilacs_cost(id,name,base_duration,base_cost,unit_cost,status,descr,creattime,costtype) values(lilacs_cost_id.nextval,?,?,?,?,?,?,sysdate,?)";
	private static final String FIND_BY_ID = "select * from lilacs_cost where id =?";
	private static final String UPDATE_COST_BY_ID = "update lilacs_cost set id=?,name=?,base_duration=?,base_cost=?,unit_cost=?,descr=?,costtype=? where id= ?";
	private static final String UPDATE_COST_STATUS = "update lilacs_cost set status=1,startime=sysdate where id =?";
	private static final String FIND_BY_NAME = "select name from lilacs_cost where name=?";
	public List<Cost> find_By_Page(int pageValue, int MAX_PAGE)
			throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Cost> list = new ArrayList<Cost>();
		int begin = MAX_PAGE * pageValue;
		int end = begin - MAX_PAGE;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(FIND_BY_PAGE);
			ps.setInt(1, begin);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			while (rs.next()) {
				Cost c = new Cost();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setBase_duration(rs.getInt("base_duration"));
				c.setBase_cost(rs.getDouble("base_cost"));
				c.setUnit_cost(rs.getDouble("unit_cost"));
				c.setCreatime(rs.getDate("creattime"));
				c.setStartime(rs.getDate("startime"));
				c.setStatus(rs.getString("status"));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public int maxPage(int MAX_PAGE) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int page_size;
		try {
			conn = getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ps = conn.prepareStatement(MAX_PAGE_SIZE);
		rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt("count");
		if (count % MAX_PAGE != 0) {
			page_size = count / MAX_PAGE + 1;
			return page_size;
		} else {
			page_size = count / MAX_PAGE;
			return page_size;
		}
	}

	public void delete_cost(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(DELETE_BY_USER);
			ps.setInt(1, id);
			int a = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			rollback();
		} finally {
			commit();
			close();

		}

	}

	public void createCost(Cost cost) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(CREATE_COST_TYPE);
			ps.setString(1, cost.getName());
			ps.setInt(2, cost.getBase_duration());
			ps.setDouble(3, cost.getBase_cost());
			ps.setDouble(4, cost.getUnit_cost());
			ps.setString(5, "0");
			ps.setString(6, cost.getDescr());
			ps.setInt(7, cost.getCosttype());
			ps.executeUpdate();

		} catch (SQLException e) {
			rollback();
			e.printStackTrace();
		} finally {
			commit();
			close();
		}

	}

	public Cost find_By_Id(int id) throws SQLException {
		Cost cost = new Cost();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		ps = conn.prepareStatement(FIND_BY_ID);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		while (rs.next()) {
			cost.setId(id);
			cost.setName(rs.getString("name"));
			cost.setBase_duration(rs.getInt("base_duration"));
			cost.setBase_cost(rs.getDouble("base_cost"));
			cost.setUnit_cost(rs.getDouble("unit_cost"));
			cost.setDescr(rs.getString("descr"));
			cost.setCosttype(rs.getInt("costtype"));
			cost.setStatus(rs.getString("status"));
			cost.setCreatime(new Date(rs.getDate("creattime").getTime()));
			if (rs.getDate("startime") != null) {
				cost.setStartime(new Date(rs.getDate("startime").getTime()));
			}
		}
		close();
		return cost;
	}

	public void update_info(Cost cost) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(UPDATE_COST_BY_ID);
			ps.setInt(1, cost.getId());
			ps.setString(2, cost.getName());
			ps.setInt(3, cost.getBase_duration());
			ps.setDouble(4, cost.getBase_cost());
			ps.setDouble(5, cost.getUnit_cost());
			ps.setString(6, cost.getDescr());
			ps.setInt(7, cost.getCosttype());
			ps.setInt(8, cost.getId());
			ps.executeUpdate();
			commit();
		} catch (SQLException e) {
			e.printStackTrace();
			rollback();
		} finally {
			close();

		}

	}

	public void update_status(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(UPDATE_COST_STATUS);
			ps.setInt(1, id);
			ps.executeUpdate();
			commit();
		} catch (SQLException e) {
			e.printStackTrace();
			rollback();
		} finally {
			close();

		}

	}

	public boolean find_By_Name(String name) throws SQLException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = "";
		try {
			conn = getConnection();
			ps = conn.prepareStatement(FIND_BY_NAME);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()){
				result = rs.getString("name");
			}
			if(name.equals(result)){
				return flag;
			}else{
				flag = true;
				return flag;				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return flag;
		} finally {
			close();

		}
	}
	/**
	 * 生成业务账号的信息时用到
	 * 根据service中的cost_id得到相关的cost信息
	 */
	public List<String> getIntroduction(int costId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> list =new ArrayList<String>();
		try{
			conn = this.getConnection();
			ps = conn.prepareStatement("select name,DESCR from lilacs_cost where id = ?");
			rs = ps.executeQuery();
			while(rs.next()){
				String n1 = rs.getString("name");
				String n2 = rs.getString("descr");
				list.add(n1);
				list.add(n2);
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
			return list;
		}
	}
	//找到所有的资费类型
	public List<Cost> getAlltype() throws SQLException {
		List<Cost> list = new ArrayList<Cost>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			ps = conn.prepareStatement("select id,name from lilacs_cost");
			rs = ps.executeQuery();
			while(rs.next()){
				Cost cost = new Cost();
				cost.setId(rs.getInt("id"));
				cost.setName(rs.getString("name"));
				list.add(cost);
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
