package com.tarena.DAO;

import java.sql.SQLException;
import java.util.List;

import com.tarena.po.Account;

public interface AccountDAO {
	/*
	 * 创建新账务账号时用ajax查找有没有此推荐人身份证的id
	 */
	public boolean ToExists(String IdCard) throws SQLException;
	
	public boolean ToExistsForSer(String IdCard) throws SQLException;

	/*
	 * 创建新账务账号时用ajax生成推荐人的id
	 */
	public int findByIdCard(String IdCard) throws SQLException;

	/*
	 * 创建新的资费账户
	 */
	public boolean createAccountInfo(Account account) throws SQLException;

	/*
	 * 删除资费信息 不是真正的删除设置状态号为2
	 */
	public boolean setDelete(int id) throws SQLException;

	/*
	 * 根据id得到所有信息(用于修改)
	 */
	public Account getInfo(int id) throws SQLException;

	/*
	 * 根据信息修改该账务信息
	 */
	public boolean update(Account account) throws SQLException;

	/*
	 * 根据条件分页搜索
	 */
	public List<Account> getList(String idcard_no, String real_name,
			String LOGIN_NAME, int status, int pageValue, int MAX_PAGE)
			throws SQLException;
	/*
	 * 根据条件查询一共有多少页
	 */
	public int HowManyPage(String idcard_no, String real_name,
			String LOGIN_NAME, int status,int MAX_PAGE)
			throws SQLException;
	/*
	 * 开通资费账户
	 */
	public boolean open(int id)throws SQLException;
	
	/*
	 * 暂停资费账户
	 */
	public boolean pause(int id)throws SQLException;
	
	/*
	 * 根据身份证号得到用户的登录名
	 */
	public String getLoginName(String idCard)throws SQLException;
	
	/*
	 * 根据id得到密码,用于比较旧密码是否相同
	 */
	public String getPWD(int id)throws SQLException;
	
}
