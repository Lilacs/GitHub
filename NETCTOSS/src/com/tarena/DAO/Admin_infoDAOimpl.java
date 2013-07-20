package com.tarena.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Result;
import com.tarena.UtilBag.BaseDAO;
import com.tarena.UtilBag.DAOFactory;
import com.tarena.po.Admin_info;

public class Admin_infoDAOimpl extends BaseDAO implements Admin_infoDAO {
	// �����û��Ƿ���ڵ�SQL
	private final static String FIND_USER = "select password from lilacs_admin_info where admin_code=?";
	// ��ʾҳ�������Ϣ
	private final static String FIND_ALL_USER_BYPEGE = "select * from (select rownum r,id,name,admin_code,telephone,email,enrolldate from lilacs_admin_info where rownum <= ?) where r > ?";
	// ��ʾ����Ա�Ľ�ɫ��Ϣ
	private final static String FIND_ADMIN_ROLE = "select name from(select a1.id c1,a1.name c2, a1.name c3, a1.admin_code c4,a1.telephone c5,a1.email c6,a2.role_id c7 from lilacs_admin_info a1 left join lilacs_admin_role a2 on a1.id = a2.admin_id) a3 left join lilacs_role_info a4 on a4.id = a3.c7 where a3.c1 = ?";
	// ��������Ա
	private final static String CREATE_INFO = "insert into lilacs_admin_info values(lilacs_admin_info_id.nextval,?,?,?,?,?,sysdate)";
	// ��ѯ��id
	private final static String FIND_ID = "select id from lilacs_admin_info where name =?";
	// ������״̬��Ϊȫ��ʱ����
	private final static String FINDALLINFOBYREQUEST = "select * from ( "
			+ " select rownum r, a5.* from ( "
			+ " select distinct c1,c2,c3,c4,c5,c6 from ( "
			+ " select a1.id c1,a1.name c2, a1.admin_code c3,a1.telephone c4,a1.email c5, a1.enrolldate c6,a2.role_id c7 "
			+ " from lilacs_admin_info a1 left join lilacs_admin_role a2 "
			+ " on a1.id = a2.admin_id  ) a3 left join lilacs_ROLE_PRIVILEGE a4 on a3.c7 = a4.ROLE_ID "
			+ " where a4.privilege_id = ? and a3.c7 = ?) a5 where rownum <= ?) "
			+ " where r > ?";
	// ����״̬Ϊȫ��ʱ����
	private final static String FINDALLINFOBYALL = "select * from ("
			+ " select rownum r, a5.* from ( "
			+ " select distinct c1,c2,c3,c4,c5,c6 from ( "
			+ " select a1.id c1,a1.name c2, a1.admin_code c3,a1.telephone c4,a1.email c5, a1.enrolldate c6,a2.role_id c7 "
			+ " from lilacs_admin_info a1 left join lilacs_admin_role a2 "
			+ " on a1.id = a2.admin_id  ) a3 left join lilacs_ROLE_PRIVILEGE a4 on a3.c7 = a4.ROLE_ID "
			+ " where a4.privilege_id in(1,2,3,4,5,6,7) and a3.c7 = ?) a5 where rownum <= ?) "
			+ "where r > ?";

	private final static String getAllCountByAll = "select count(*) c from ( "
			+ " select distinct c1,c2,c3,c4,c5,c6 from ( "
			+ " select a1.id c1,a1.name c2, a1.admin_code c3,a1.telephone c4,a1.email c5, a1.enrolldate c6,a2.role_id c7 "
			+ " from lilacs_admin_info a1 left join lilacs_admin_role a2 "
			+ " on a1.id = a2.admin_id  ) a3 left join lilacs_ROLE_PRIVILEGE a4 on a3.c7 = a4.ROLE_ID "
			+ " where a4.privilege_id in(1,2,3,4,5,6,7) and a3.c7 = ?) ";
	private final static String getAllCountByOne = "select count(*) c from ( "
			+ " select distinct c1,c2,c3,c4,c5,c6 from ( "
			+ " select a1.id c1,a1.name c2, a1.admin_code c3,a1.telephone c4,a1.email c5, a1.enrolldate c6,a2.role_id c7 "
			+ " from lilacs_admin_info a1 left join lilacs_admin_role a2 "
			+ " on a1.id = a2.admin_id  ) a3 left join lilacs_ROLE_PRIVILEGE a4 on a3.c7 = a4.ROLE_ID "
			+ " where a4.privilege_id = ? and a3.c7 = ?) ";

	// ��½ϵͳ�ǽ�����֤�û��Ƿ����(�����û���½)
	public boolean findUser(Admin_info user) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String pwd = "";
		try {
			conn = getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ps = conn.prepareStatement(FIND_USER);
		ps.setString(1, user.getAdmin_code());
		rs = ps.executeQuery();
		while (rs.next()) {
			pwd = rs.getString("password");
		}
		if (pwd.equals(user.getPassword())) {
			return true;
		} else if (pwd.equals("")) {
			return false;
		} else {
			return false;
		}
	}

	/*
	 * ��������Ա ��Ϊǣ��������Ա�Ľ�����Ҫͬʱ�����ű��������,���Բ�Ҫ�ر����� ��Ҫ��������,����һ��ȥ����
	 * 
	 * @see com.tarena.DAO.Admin_infoDAO#createInfo(com.tarena.po.Admin_info)
	 */
	public boolean createInfo(Admin_info info) throws SQLException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(CREATE_INFO);
			ps.setString(1, info.getAdmin_code());
			ps.setString(2, info.getPassword());
			ps.setString(3, info.getName());
			ps.setString(4, info.getTelephone());
			ps.setString(5, info.getEmail());
			ps.executeUpdate();
			flag = true;
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
			return flag;
		}
	}

	/*
	 * �����û�������id(��Ҫ�ر�����) �Ժ�Ҫ����id��������ԱȨ�ޱ� û�йر�����Ϊ�ʹ����û�Ϊһ��ҵ��,������ɹ�������һ��rollback;
	 * 
	 * @see com.tarena.DAO.Admin_infoDAO#findID(java.lang.String)
	 */
	public int findID(String name) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(FIND_ID);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
			}
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return id;
		}
	}

	/*
	 * ��ҳ��ʾ�û�����Ϣ(��ʾ��ɫ����) ��ɫ����Ҫ���ж���ѯ,���ɶ��������Ĺ���Ա��Ϣ TODO �����������й�������
	 * 
	 * @see com.tarena.DAO.Admin_infoDAO#getList(int, int)
	 */
	public List<Admin_info> getList(int pageValue, int MAX_PAGE)
			throws SQLException {
		List<Admin_info> list = new ArrayList<Admin_info>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int begin = MAX_PAGE * pageValue;
		int end = begin - MAX_PAGE;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(FIND_ALL_USER_BYPEGE);
			ps.setInt(1, begin);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			while (rs.next()) {
				Admin_info ai = new Admin_info();
				List<String> rolename = new ArrayList<String>();
				int id = rs.getInt("id");
				ai.setId(id);
				ai.setName(rs.getString("name"));
				ai.setAdmin_code(rs.getString("admin_code"));
				ai.setTelephone(rs.getString("telephone"));
				ai.setEmail(rs.getString("email"));
				ai.setEnrolldate(rs.getDate("enrolldate"));
				rolename = new DAOFactory().getAdmin_roleDAO().getRoleInfo(id);
				ai.setAdminrole(rolename);
				list.add(ai);
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
	 * (�޸�ʱ��ʾ����Ա��ǰ����Ϣ(�����¼�)
	 * 
	 * @see com.tarena.DAO.Admin_infoDAO#getAdminInfoById(int)
	 */
	public Admin_info getAdminInfoById(int id) throws SQLException {
		Admin_info ai = new Admin_info();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn
					.prepareStatement("select * from lilacs_admin_info where id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				ai.setName(rs.getString("name"));
				ai.setTelephone(rs.getString("telephone"));
				ai.setEmail(rs.getString("email"));
				ai.setAdmin_code(rs.getString("admin_code"));
			}
			return ai;
		} catch (SQLException e) {
			e.printStackTrace();
			return ai;
		} finally {
			close();
		}
	}

	/*
	 * (���¹���Ա������Ϣ(���ر�����)) ��������Ҫ���¹���Ա��ɫ���е�����
	 */
	public boolean updateInfo(Admin_info info) throws SQLException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn
					.prepareStatement("update lilacs_admin_info set name=?,telephone=?,email=? where id=?");
			ps.setString(1, info.getName());
			ps.setString(2, info.getTelephone());
			ps.setString(3, info.getEmail());
			ps.setInt(4, info.getId());
			ps.executeUpdate();
			flag = true;
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
			return flag;
		}
	}

	public boolean delete(int id) throws SQLException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("delete lilacs_admin_info where id =?");
			ps.setInt(1, id);
			ps.executeUpdate();
			flag = true;
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
			return flag;
		}
	}

	/*
	 * (����) ��������ȫ�� ��ѯ���û���ظ���Ϣ
	 * 
	 * @see com.tarena.DAO.Admin_infoDAO#findAllInfoByRequest(int, int, int,
	 * int)
	 */
	public List<Admin_info> findAllInfoByRequest(int id, int role_id,
			int pageValue, int MAX_PAGE) throws SQLException {
		List<Admin_info> list = new ArrayList<Admin_info>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int begin = MAX_PAGE * pageValue;
		int end = begin - MAX_PAGE;		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(FINDALLINFOBYREQUEST);
			ps.setInt(1, id);
			ps.setInt(2, role_id);
			ps.setInt(3, begin);
			ps.setInt(4, end);
			rs = ps.executeQuery();
			while (rs.next()) {
				Admin_info ai = new Admin_info();
				List<String> rolename = new ArrayList<String>();
				int newid = rs.getInt("c1");
				ai.setId(newid);
				ai.setName(rs.getString("c2"));
				ai.setAdmin_code(rs.getString("c3"));
				ai.setTelephone(rs.getString("c4"));
				ai.setEmail(rs.getString("c5"));
				ai.setEnrolldate(rs.getDate("c6"));
				rolename = new DAOFactory().getAdmin_roleDAO().getRoleInfo(
						newid);
				ai.setAdminrole(rolename);
				list.add(ai);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		} finally {
			close();
		}
	}

	public List<Admin_info> findAllInfoByALL(int id, int pageValue, int MAX_PAGE)
			throws SQLException {
		List<Admin_info> list = new ArrayList<Admin_info>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int begin = MAX_PAGE * pageValue;
		int end = begin - MAX_PAGE;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(FINDALLINFOBYALL);
			ps.setInt(1, id);
			ps.setInt(2, begin);
			ps.setInt(3, end);
			rs = ps.executeQuery();
			while (rs.next()) {
				int newid = rs.getInt("c1");
				Admin_info ai = new Admin_info();
				List<String> rolename = new ArrayList<String>();
				ai.setId(newid);
				ai.setName(rs.getString("c2"));
				ai.setAdmin_code(rs.getString("c3"));
				ai.setTelephone(rs.getString("c4"));
				ai.setEmail(rs.getString("c5"));
				ai.setEnrolldate(rs.getDate("c6"));
				rolename = new DAOFactory().getAdmin_roleDAO().getRoleInfo(
						newid);
				ai.setAdminrole(rolename);
				list.add(ai);
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
	 * (�õ������û�������)
	 * 
	 * @see com.tarena.DAO.Admin_infoDAO#getAllCount(int)
	 */
	public int getAllCount(int MAX_PAGE) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int values = 0;
		int pageCount = 0;
		try {
			conn = getConnection();
			ps = conn
					.prepareStatement("select count(*) c from lilacs_admin_info");
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
		}
	}

	/*
	 * (�õ�ָ����ɫ���Ƶ������û�����)
	 * 
	 * @see com.tarena.DAO.Admin_infoDAO#getAllCountByAll(int, int)
	 */
	public int getAllCountByAll(int MAX_PAGE, int roleId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int values = 0;
		int pageCount = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(getAllCountByAll);
			ps.setInt(1, roleId);
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

	/*
	 * (�õ�ָ����ɫ����,ָ��Ȩ�޵������û�����)
	 * 
	 * @see com.tarena.DAO.Admin_infoDAO#getAllCountByOne(int, int, int)
	 */
	public int getAllCountByOne(int MAX_PAGE, int roleId, int abilityId)
			throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int values = 0;
		int pageCount = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(getAllCountByOne);
			ps.setInt(1, abilityId);
			ps.setInt(2, roleId);
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

	// �����û��ĵ�½�˺Ų��ҳ��û��Ļ�����Ϣ
	public Admin_info getInfoByLoginName(String loginName) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Admin_info ai = new Admin_info();
		try {
			conn = getConnection();
			ps = conn
					.prepareStatement("select * from lilacs_admin_info where admin_code = ?");
			ps.setString(1, loginName);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				List<String> rolename = new ArrayList<String>();
				rolename = new DAOFactory().getAdmin_roleDAO().getRoleInfo(id);
				ai.setId(id);
				ai.setAdmin_code(rs.getString("admin_code"));
				ai.setPassword(rs.getString("password"));
				ai.setAdminrole(rolename);
				ai.setName(rs.getString("name"));
				ai.setTelephone(rs.getString("telephone"));
				ai.setEmail(rs.getString("email"));
				ai.setEnrolldate(rs.getDate("enrolldate"));
			}
			return ai;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// ����Ա�����޸��Լ��Ļ�����Ϣ
	public boolean modiOwnInfo(Admin_info adminInfo) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn
					.prepareStatement("update lilacs_admin_info set name = ?, telephone = ?, email = ? where id = ?");
			ps.setString(1, adminInfo.getName());
			ps.setString(2, adminInfo.getTelephone());
			ps.setString(3, adminInfo.getEmail());
			ps.setInt(4, adminInfo.getId());
			ps.executeUpdate();
			commit();
			return true;
		} catch (SQLException e) {
			rollback();
			e.printStackTrace();
			return false;
		} finally {
			close();
		}
	}

	// ����Ա�����޸�����
	public boolean modiOwnpwd(int id, String newpwd) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn
					.prepareStatement("update lilacs_admin_info set password = ? where id = ?");
			ps.setString(1, newpwd);
			ps.setInt(2, id);
			ps.executeUpdate();
			commit();
			return true;
		} catch (SQLException e) {
			rollback();
			e.printStackTrace();
			return false;
		} finally {
			close();
		}
	}

}
