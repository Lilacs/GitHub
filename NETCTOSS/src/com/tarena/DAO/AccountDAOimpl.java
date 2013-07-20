package com.tarena.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.tarena.UtilBag.BaseDAO;
import com.tarena.po.Account;

public class AccountDAOimpl extends BaseDAO implements AccountDAO {
	private static final String PAGECOUNT = "select count(*) c from lilacs_account ";
	private static final String BEGIN = "select * from (";
	private static final String END = " rownum <= ?) where r > ? ";
	private static final String START = " select rownum r,a1.* from lilacs_account a1 ";
	private static final String AND = " and ";
	private static final String WHERE = " where ";
	private static final String REAL_NAME = " real_name like ";
	private static final String IDCARD_NO = " idcard_no like ";
	private static final String LOGIN_NAME = " LOGIN_NAME like ";
	private static final String STATUS = " status = ";

	/*
	 * (创建新账务账号时用ajax查找有没有此推荐人身份证的id)
	 * 
	 * @see com.tarena.DAO.AccountDAO#ToExists(int)
	 */
	public boolean ToExists(String IdCard) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn
					.prepareStatement("select id from lilacs_account where IDCARD_NO = ?");
			ps.setString(1, IdCard);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			close();
		}
	}

	/*
	 * (创建新账务账号时用ajax生成推荐人的id)
	 * 
	 * @see com.tarena.DAO.AccountDAO#findByIdCard(int)
	 */
	public int findByIdCard(String IdCard) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int recommenderId = 0;
		try {
			conn = getConnection();
			ps = conn
					.prepareStatement("select id from lilacs_account where IDCARD_NO = ?");
			ps.setString(1, IdCard);
			rs = ps.executeQuery();
			while (rs.next()) {
				recommenderId = rs.getInt("id");
			}
			return recommenderId;
		} catch (SQLException e) {
			e.printStackTrace();
			return recommenderId;
		} finally {
			close();
		}
	}

	/*
	 * (创建新的资费账户) 创建即开通,记载创建时间 status为0 开通 create_date 为当前日期
	 * 
	 * @see com.tarena.DAO.AccountDAO#createAccountInfo(com.tarena.po.Account)
	 */
	public boolean createAccountInfo(Account account) throws SQLException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn
					.prepareStatement("insert into lilacs_account(id,real_name,"
							+ " IDCARD_NO,LOGIN_NAME,LONGIN_PASSWD,GENDER,TELEPHONE,RECOMMENDER_ID,"
							+ " BIRTHDATE,EMAIL,OCCUPATION,MAILADDRESS,ZIPCOOE,QQ,CREATE_DATE,"
							+ " STATUS) values(lilacs_account_id.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,0)");
			ps.setString(1, account.getReal_name());
			ps.setString(2, account.getIdcard_no());
			ps.setString(3, account.getLogin_name());
			ps.setString(4, account.getLogin_passwd());
			ps.setString(5, account.getGender());
			ps.setString(6, account.getTelephone());
			if (account.getRecommender_id() == null) {
				System.out.println("进入为空状态");
				ps.setNull(7, Types.INTEGER);
			} else {
				System.out.println("进入不为空状态");
				ps.setInt(7, account.getRecommender_id());
			}
			ps.setDate(8, new Date(account.getBirthdate().getTime()));
			ps.setString(9, account.getEmail());
			ps.setString(10, account.getOccupation());
			ps.setString(11, account.getMailaddress());
			ps.setString(12, account.getZipcode());
			ps.setString(13, account.getQq());
			ps.executeUpdate();
			commit();
			flag = true;
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
			rollback();
			return flag;
		} finally {
			close();
		}
	}

	// 删除资费信息
	public boolean setDelete(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn
					.prepareStatement("update lilacs_account set status = 2 where id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			ps = conn
					.prepareStatement("update lilacs_service set status = 2 where account_id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			close();
		}

	}

	// 根据id得到资费信息
	public Account getInfo(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Account account = new Account();
		try {
			conn = getConnection();
			ps = conn
					.prepareStatement("select * from lilacs_account where id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				account.setId(id);
				account.setReal_name(rs.getString("real_name"));
				account.setLogin_name(rs.getString("login_name"));
				account.setIdcard_no(rs.getString("idcard_no"));
				account.setTelephone(rs.getString("telephone"));
				account.setLogin_passwd(rs.getString("longin_passwd"));
				account.setRecommender_id(rs.getInt("recommender_id"));
				account.setEmail(rs.getString("email"));
				account.setOccupation(rs.getString("occupation"));
				account.setPause_date(rs.getDate("pause_date"));
				account.setGender(rs.getString("gender"));
				account.setBirthdate(rs.getDate("birthdate"));
				account.setQq(rs.getString("qq"));
				account.setMailaddress(rs.getString("mailaddress"));
				account.setZipcode(rs.getString("zipcooe"));
			}
			return account;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close();
		}

	}

	/*
	 * (修改资费账户(account)的信息)
	 * 
	 * @see com.tarena.DAO.AccountDAO#update(com.tarena.po.Account)
	 */
	public boolean update(Account account) throws SQLException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// TODO 修改信息,这是更新的信息
			if (account.getLogin_passwd() != null && account.getLogin_passwd().length() > 0) {
				System.out.println("进入验证有密码");
				ps = conn
						.prepareStatement("update lilacs_account set real_name = ?, "
								+ " telephone = ?, OCCUPATION = ?, GENDER=?, MAILADDRESS = ?, "
								+ " ZIPCOOE=?, QQ = ?, email = ?, recommender_id = ? ,longin_passwd = ?"
								+ "where id = ? ");
				ps.setString(1, account.getReal_name());
				ps.setString(2, account.getTelephone());
				ps.setString(3, account.getOccupation());
				ps.setString(4, account.getGender());
				ps.setString(5, account.getMailaddress());
				ps.setString(6, account.getZipcode());
				ps.setString(7, account.getQq());
				ps.setString(8, account.getEmail());
				if (account.getRecommender_id() == null) {
					ps.setNull(9, Types.INTEGER);
				} else {
					ps.setInt(9, account.getRecommender_id());
				}
				ps.setString(10, account.getLogin_passwd());
				ps.setInt(11, account.getId());
				ps.executeUpdate();
				commit();
				flag = true;
				return flag;
			} else {
				System.out.println("进入验证无密码");
				ps = conn
						.prepareStatement("update lilacs_account set real_name = ?, "
								+ " telephone = ?, OCCUPATION = ?, GENDER=?, MAILADDRESS = ?, "
								+ " ZIPCOOE=?, QQ = ?, email = ?, recommender_id = ? "
								+ " where id = ? ");
				ps.setString(1, account.getReal_name());
				ps.setString(2, account.getTelephone());
				ps.setString(3, account.getOccupation());
				ps.setString(4, account.getGender());
				ps.setString(5, account.getMailaddress());
				ps.setString(6, account.getZipcode());
				ps.setString(7, account.getQq());
				ps.setString(8, account.getEmail());
				if (account.getRecommender_id() == null) {
					ps.setNull(9, Types.INTEGER);
				} else {
					ps.setInt(9, account.getRecommender_id());
				}
				ps.setInt(10, account.getId());
				ps.executeUpdate();
				commit();
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

	// 根据条件分页搜索
	public List<Account> getList(String idcard_no, String real_name,
			String login_name, int status, int pageValue, int MAX_PAGE)
			throws SQLException {
		List<Account> list = new ArrayList<Account>();
		int begin = MAX_PAGE * pageValue;
		int end = begin - MAX_PAGE;
		System.out.println(begin);
		System.out.println(end);
		String sql = BEGIN + START;
		// 判断状态
		if (status == 4) {
			sql = sql + WHERE + " 1 = 1 ";
		} else {
			sql = sql + WHERE + STATUS + status;
		}
		// 判断身份证
		if (!(idcard_no == null) && idcard_no.length() > 0) {
			idcard_no = "'%" + idcard_no + "%'";
			sql = sql + AND + IDCARD_NO + idcard_no;
		} else {
			idcard_no = "'%%'";
			sql = sql + AND + IDCARD_NO + idcard_no;
		}
		// 判断姓名
		if (!(real_name == null) && real_name.length() > 0) {
			real_name = "'%" + real_name + "%'";
			sql = sql + AND + REAL_NAME + real_name;
		} else {
			real_name = "'%%'";
			sql = sql + AND + REAL_NAME + real_name;
		}
		// 判断登录名
		if (!(login_name == null) && login_name.length() > 0) {
			login_name = "'%" + login_name + "%'";
			sql = sql + AND + LOGIN_NAME + login_name;
		} else {
			login_name = "'%%'";
			sql = sql + AND + LOGIN_NAME + login_name;
		}
		sql = sql + AND + END;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, begin);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			while (rs.next()) {
				Account account = new Account();
				account.setId(rs.getInt("id"));
				account.setReal_name(rs.getString("real_name"));
				account.setIdcard_no(rs.getString("idcard_no"));
				account.setLogin_name(rs.getString("login_name"));
				account.setStatus(rs.getString("status"));
				account.setCreate_date(rs.getDate("create_date"));
				account.setLast_login_time(rs.getDate("last_login_time"));
				list.add(account);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		} finally {
			close();
		}
	}

	// 根据条件查询一共有多少页
	public int HowManyPage(String idcard_no, String real_name,
			String login_name, int status, int MAX_PAGE) throws SQLException {
		String sql = PAGECOUNT;
		int pageCount = 0;
		int values = 0;
		// 判断状态
		if (status == 4) {
			sql = sql + WHERE + " 1 = 1 ";
		} else {
			sql = sql + WHERE + STATUS + status;
		}
		// 判断身份证
		if (!(idcard_no == null) && idcard_no.length() > 0) {
			idcard_no = "'%" + idcard_no + "%'";
			sql = sql + AND + IDCARD_NO + idcard_no;
		} else {
			idcard_no = "'%%'";
			sql = sql + AND + IDCARD_NO + idcard_no;
		}
		// 判断姓名
		if (!(real_name == null) && real_name.length() > 0) {
			real_name = "'%" + real_name + "%'";
			sql = sql + AND + REAL_NAME + real_name;
		} else {
			real_name = "'%%'";
			sql = sql + AND + REAL_NAME + real_name;
		}
		// 判断登录名
		if (!(login_name == null) && login_name.length() > 0) {
			login_name = "'%" + login_name + "%'";
			sql = sql + AND + LOGIN_NAME + login_name;
		} else {
			login_name = "'%%'";
			sql = sql + AND + LOGIN_NAME + login_name;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
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

	// 开通资费
	public boolean open(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn
					.prepareStatement("update lilacs_account set status = 0 where id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			ps = conn
					.prepareStatement("update lilacs_account set PAUSE_DATE = null where id = ?");
			ps.setInt(1, id);
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

	// 暂停时间
	public boolean pause(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn
					.prepareStatement("update lilacs_account set status = 1 where id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			ps = conn
					.prepareStatement("update lilacs_service set status = 1 where account_id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			ps = conn
					.prepareStatement("update lilacs_account set PAUSE_DATE = sysdate where id = ?");
			ps.setInt(1, id);
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

	/*
	 * (根据身份证得到用户的登录名)
	 * 
	 * @see com.tarena.DAO.AccountDAO#getLoginName(java.lang.String)
	 */
	public String getLoginName(String idCard) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String loginName = "";
		try {
			conn = this.getConnection();
			ps = conn
					.prepareStatement("select login_name from lilacs_account where IDCARD_NO = ?");
			ps.setString(1, idCard);
			rs = ps.executeQuery();
			while (rs.next()) {
				loginName = rs.getString("login_name");
			}
			return loginName;
		} catch (SQLException e) {
			e.printStackTrace();
			return loginName;
		} finally {
			close();
		}
	}

	public boolean ToExistsForSer(String IdCard) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn
					.prepareStatement("select id from lilacs_account where status = 0 and IDCARD_NO = ?");
			ps.setString(1, IdCard);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			close();
		}
	}

	/*
	 * (验证旧密码是否相同)
	 */
	public String getPWD(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String pwd = "";
		try {
			conn = getConnection();
			ps = conn
					.prepareStatement("select longin_passwd from lilacs_account where id = ? ");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				pwd = rs.getString("longin_passwd");
			}
			return pwd;
		} catch (SQLException e) {
			e.printStackTrace();
			return pwd;
		} finally {
			close();
		}
	}
}
