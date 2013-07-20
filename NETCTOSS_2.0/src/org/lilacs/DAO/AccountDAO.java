package org.lilacs.DAO;

import java.sql.SQLException;
import java.util.List;

import org.lilacs.po.Account;

public interface AccountDAO {

	// 创建一条新的资费账户
	public abstract boolean createAccount(Account account) throws Exception;

	// 更新一条信息
	public abstract boolean update(Account account) throws Exception;

	// 查看一条信息根据id
	public Account getAccountById(int id) throws Exception;

	// 开通资费
	public abstract boolean open(int id) throws Exception;

	// 暂停时间 记载暂停时间 设置状态为1(暂停状态),同时关闭账号下的业务
	public abstract boolean pause(int id) throws Exception;

	// 删除一条信息 状态设置为2 同时设置账户下得业务账号为删除状态(2)
	public Boolean delete(int id) throws Exception;

	// 验证旧密码是否相同
	public abstract String getPWD(int id) throws Exception;

	// 根据身份证得到用户的登录名
	public abstract String getLoginName(String idCard) throws Exception;

	// 分页查询
	public List<Account> getAccountList(String idCard, String realName,
			String loginName, int status, int page, int MAX_PAGE)
			throws Exception;

	// 查询页数
	public int getPageCount(String idCard, String realName, String loginName,
			int status, int MAX_PAGE) throws Exception;
}