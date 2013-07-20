package com.tarena.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tarena.UtilBag.BaseDAO;
import com.tarena.po.Host;

public class HostDAOimpl extends BaseDAO implements HostDAO{
	//找到所有的服务请名称
	public List<Host> getALLtype() throws SQLException {
		List<Host> list = new ArrayList<Host>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			ps = conn.prepareStatement("select id,name from lilacs_host");
			rs = ps.executeQuery();
			while(rs.next()){
				Host host = new Host();
				host.setId(rs.getString("id"));
				host.setName(rs.getString("name"));
				list.add(host);
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
