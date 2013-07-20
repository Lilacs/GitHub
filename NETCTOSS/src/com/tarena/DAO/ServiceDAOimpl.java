package com.tarena.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tarena.UtilBag.BaseDAO;
import com.tarena.po.Service;

public class ServiceDAOimpl extends BaseDAO implements ServiceDAO {
	private final static String pageValSQL = "select count(*) from lilacs_service";
	private final static String allPageSQL = "select ID, ACCOUNT_ID, "
			+ "UNIX_HOST, OS_USERNAME, LOGIN_PASSWD, "
			+ "CREATE_DATE from (select ID, ACCOUNT_ID, UNIX_HOST, OS_USERNAME, LOGIN_PASSWD, CREATE_DATE, rownum num from SERVICE where rownum<?) where num>=?";
	private final static String insertSQL = "insert into lilacs_service(id,account_id,unix_host,os_username,login_passwd,status,create_date,cost_id) values (?,?,?,?,?,?,default,?)";
	private static final String BEGIN = "select * from ( ";
	private static final String END = " and rownum <= ?) where r > ? ";
	private final static String BASE_SQL = " select rownum r ,a3.*,a4.idcard_no, a4.real_name from ( "
			+ " select a1.id,a1.account_id,a1.os_username,a1.status,a1.unix_host,a1.cost_id,a2.name,a2.descr "
			+ " from lilacs_service a1 left join lilacs_cost a2 "
			+ " on a1.cost_id = a2.id ) a3 left join lilacs_account a4 on a3.account_id = a4.id ";
	private final static String WHERE = " where ";
	private final static String AND = " and ";
	private final static String OSNAME = " os_username like ";
	private final static String UNIX_HOST = " unix_host like ";
	private final static String IDCARD = " idcard_no like ";
	private final static String STATUS = " a3.status = ";
	private final static String PAGESTART = "select count(*) c from ( ";
	private final static String PAGEEND = " ) ";
	private final static String FIANINFOBYID = "select a1.*,a2.name,a2.descr,a3.real_name ,a3.idcard_no " +
			" from lilacs_service a1 left join lilacs_cost a2 " +
			" on a1.cost_id = a2.id left join lilacs_account a3 on a1.account_id = a3.id " +
			" where a1.id= ?";
	// 分页查询 一页 2条 查看 第2页
	public List<Service> findAll(int rowPage, int page) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(allPageSQL);
			int begin = (page - 1) * (rowPage + 1);// 一页第一条记录在数据库中的序号 like: 1
			int end = begin + rowPage;// 一页的最后条记录在数据库中的序号 like : 5
			ps.setInt(1, end);
			ps.setInt(2, begin);
			rs = ps.executeQuery();
			return toList(rs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (!(ps == null))
				ps.close();
			if (!(rs == null))
				rs.close();
		}

	}

	// 共有多少页
	public int getPageValues(int row) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(pageValSQL);
			rs = ps.executeQuery();// 执行查询(查询语句用executeQuery)
			rs.next();
			int rows = rs.getInt(1);
			int values;
			if (rows % row == 0) {
				values = rows / row;
			} else {
				values = rows / row + 1;
			}

			return values;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (!(ps == null))
				ps.close();
			if (!(rs == null))
				rs.close();
		}

	}

	public List<Service> toList(ResultSet rs) throws Exception {
		List list = new ArrayList<Service>();
		while (rs.next()) {
			Service service = new Service();
			service.setId(rs.getInt("ID"));
			service.setAccount_id(rs.getInt("ACCOUNT_ID"));
			service.setOs_username(rs.getString("UNIX_HOST"));
			service.setLogin_passwd(rs.getString("LOGIN_PASSWD"));
			service.setCreate_date(rs.getDate("CREATE_DATE"));
			list.add(service);
		}
		return list;
	}

	public int creatUser(Service service) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(insertSQL);
			ps.setInt(1, service.getId());
			ps.setInt(2, service.getAccount_id());
			ps.setString(3, service.getUnix_host());
			ps.setString(4, service.getOs_username());
			ps.setString(5, service.getLogin_passwd());
			ps.setString(6, service.getStatus());
			// ps.setDate(7, service.getCreate_date());
			// ps.setDate(8, service.getPause_date());
			ps.setInt(7, service.getCost_id());
			int value = ps.executeUpdate();
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (!(ps == null))
				ps.close();
			if (!(rs == null))
				rs.close();
		}
	}

	/*
	 * (根据过滤条件生成页面信息)
	 * 
	 * @see com.tarena.DAO.ServiceDAO#getList(java.lang.String,
	 * java.lang.String, java.lang.String, int, int, int)
	 */
	public List<Service> getList(String osName, String unitHost, String idCard,
			int status, int pageValue, int MAX_PAGE) throws SQLException {
		List<Service> list = new ArrayList<Service>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int begin = MAX_PAGE * pageValue;
		int end = begin - MAX_PAGE;
		String sql = BEGIN + BASE_SQL;
		// 判断状态
		if (status != 3) {
			sql = sql + WHERE + STATUS + status;
		} else {
			sql = sql + WHERE + " 1 = 1 ";
		}
		// 判断os账号
		if (!(osName == null) && osName.length() > 0) {
			osName = "'%" + osName + "%'";
			sql = sql + AND + OSNAME + osName;
		}
		// 判断服务器
		if (!(unitHost == null) && unitHost.length() > 0) {
			unitHost = "'%" + unitHost + "%'";
			sql = sql + AND + UNIX_HOST + unitHost;
		}
		// 判断身份证号
		if (!(idCard == null) && idCard.length() > 0) {
			idCard = "'%" + idCard + "%'";
			sql = sql + AND + IDCARD + idCard;
		}
		sql = sql + END;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, begin);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			while (rs.next()) {
				Service ser = new Service();
				ser.setId(rs.getInt("id"));
				ser.setAccount_id(rs.getInt("account_id"));
				ser.setOs_username(rs.getString("os_username"));
				ser.setStatus(rs.getString("status"));
				ser.setUnix_host(rs.getString("unix_host"));
				ser.setCost_id(rs.getInt("cost_id"));
				ser.setCostName(rs.getString("name"));
				ser.setDescr(rs.getString("descr"));
				ser.setIdCard(rs.getString("idcard_no"));
				ser.setReal_name(rs.getString("real_name"));
				list.add(ser);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		} finally {
			close();
		}
	}

	/*
	 * 查询共有多少页
	 */
	public int getpageCount(String osName, String unitHost, String idCard,
			int status, int MAX_PAGE) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int values = 0;
		int pageCount = 0;
		String sql = PAGESTART + BASE_SQL;
		// 判断状态
		if (status != 3) {
			sql = sql + WHERE + STATUS + status;
		} else {
			sql = sql + WHERE + " 1 = 1 ";
		}
		// 判断os账号
		if (!(osName == null) && osName.length() > 0) {
			osName = "'%" + osName + "%'";
			sql = sql + AND + OSNAME + osName;
		}
		// 判断服务器
		if (!(unitHost == null) && unitHost.length() > 0) {
			unitHost = "'%" + unitHost + "%'";
			sql = sql + AND + UNIX_HOST + unitHost;
		}
		// 判断身份证号
		if (!(idCard == null) && idCard.length() > 0) {
			idCard = "'%" + idCard + "%'";
			sql = sql + AND + IDCARD + idCard;
		}
		sql = sql + PAGEEND;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				values = rs.getInt("c");
			}
			if (values % MAX_PAGE == 0) {
				pageCount = values / MAX_PAGE;
				return pageCount;
			} else {
				pageCount = values / MAX_PAGE + 1;
				return pageCount;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return pageCount;
		} finally {
			close();
		}
	}

	/**
	 * 创建一条新的业务资费账号
	 */
	public boolean createInfo(Service service) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into lilacs_service( "
					+ " id,account_id,unix_host,os_username,login_passwd, "
					+ " status,create_date,cost_id ) "
					+ " values(lilacs_service_id.nextval,?,?,?,?,?,sysdate,?)");
			ps.setInt(1, service.getAccount_id());
			ps.setString(2, service.getUnix_host());
			ps.setString(3, service.getOs_username());
			ps.setString(4, service.getLogin_passwd());
			ps.setString(5, service.getStatus());
			ps.setInt(6, service.getCost_id());
			ps.executeUpdate();
			commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			rollback();
			return false;
		} finally {
			close();
		}
	}
	//预览用户的一条信息
	public Service getInfoByid(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Service service = new Service();
		try{
			conn =getConnection();
			ps = conn.prepareStatement(FIANINFOBYID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				service.setId(rs.getInt("id"));
				service.setAccount_id(rs.getInt("account_id"));
				service.setReal_name(rs.getString("real_name"));
				service.setIdCard(rs.getString("idcard_no"));
				service.setUnix_host(rs.getString("unix_host"));
				service.setOs_username(rs.getString("os_username"));
				service.setStatus(rs.getString("status"));
				service.setCreate_date(rs.getDate("create_date"));
				service.setCost_id(rs.getInt("cost_id"));
				service.setCostName(rs.getString("name"));
				service.setDescr(rs.getString("descr"));
			}
			return service;
		}catch(SQLException e){
			e.printStackTrace();
			return service;
		}finally{
			close();
		}
	}
	//修改用户的资费类型
	public boolean modiCostType(int costType, int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = getConnection();
			ps = conn.prepareStatement("update lilacs_service set cost_id = ? where id = ?");
			ps.setInt(1, costType);
			ps.setInt(2, id);
			ps.executeUpdate();
			ps = conn.prepareStatement("update lilacs_service set PAUSE_DATE = sysdate where id =? ");
			ps.setInt(1, id);
			ps.executeUpdate();
			commit();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			rollback();
			return false;
		}finally{
			close();
		}
	}
	//删除,不是实际删除,将状态设置为2
	public boolean delete(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = getConnection();
			ps = conn.prepareStatement("update lilacs_service set status = '2' where id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			ps = conn.prepareStatement("update lilacs_service set CREATE_DATE = sysdate where id =? ");
			ps.setInt(1, id);
			ps.executeUpdate();
			commit();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			rollback();
			return false;
		}finally{
			close();
		}
	}
	//开通,删除暂停时间
	public boolean open(int id) throws SQLException {
		System.out.println("进入开通的dao");
		System.out.println(id);
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = getConnection();
			ps = conn.prepareStatement("update lilacs_service set status = '0' where id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("第一步执行成功");
			ps = conn.prepareStatement("update lilacs_service set PAUSE_DATE = null where id = ? ");
			ps.setInt(1, id);
			ps.executeUpdate();
			commit();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			rollback();
			return false;
		}finally{
			close();
		}
	}
	//暂停,记载暂停时间
	public boolean pause(int id) throws SQLException {
		System.out.println(id);
		System.out.println("进入暂停的DAO");
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn =  getConnection();
			ps = conn.prepareStatement("update lilacs_service set status = '1' where id = ?");
			System.out.println("执行前的步骤");
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("第一步执行成功");
			ps = conn.prepareStatement("update lilacs_service set PAUSE_DATE = sysdate where id = ? ");
			ps.setInt(1, id);
			ps.executeUpdate();
			commit();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			rollback();
			return false;
		}finally{
			close();
		}
	}
	
	

}
