package com.tarena.DAO;

import java.sql.SQLException;
import java.util.List;

import com.tarena.po.Admin_info;

public interface Admin_infoDAO {
	/*
	 * 登陆系统是进行验证用户是否存在(用于用户登陆) 传的参数为用户输入的账号和密码 返回true证明用户存在 返回false证明用户不存在
	 */
	public boolean findUser(Admin_info user) throws SQLException;

	/*
	 * 增加新的管理员 不关闭连接,不提交事务 和创建管理员的权限和起来为一个事务
	 */
	public boolean createInfo(Admin_info info) throws SQLException;

	/*
	 * 根据管理员的名称查找id 返回类型为int
	 */
	public int findID(String name) throws SQLException;

	/*
	 * 在页面分页上显示管理员的信息(同时显示他的角色) TODO 增加条件搜索过滤
	 */
	public List<Admin_info> getList(int pageValue, int MAX_PAGE)
			throws SQLException;

	/*
	 * 根据id进行用户信息的查找 用于修改管理员信息显示该管理员以前的角色
	 */
	public Admin_info getAdminInfoById(int id) throws SQLException;

	/*
	 * (以下为一条事务) 更新管理员的信息(更新name,telephone,email) update lilacs_admin_info set
	 * name=?,telephone=?,email=? where id=? 更新管理员的新角色(先删除,之后再增加) 
	 * delete lilacs_admin_role where admin_id = ? insert into lilacs_admin_role
	 * values(?,?)
	 */
	public boolean updateInfo(Admin_info info) throws SQLException;

	/*
	 * 删除管理员信息 同时删除管理员的角色 (不关闭连接)
	 */
	public boolean delete(int id) throws SQLException;

	/**
	 * 根据过滤条件进行搜索(发送搜索时就要发送post请求) 得到三个两个值(模块过滤值(int),角色名称(String)
	 * 条件:模块过滤,角色名称过滤 同时进行分页 (1)根据角色名称查询到角色的id(在role_info中查询) (2)根据角色的id和模块过滤条件
	 * 进行条件查询到所有用户信息(必须同时得到管理员的角色信息) action要同时调用两个方法
	 * findAllInfoByRequest是有模块信息不为全部的条件
	 * findAllInfoByALL是全部模块信息
	 * role_id 是角色所写的名称查询出来的
	 */
	public List<Admin_info> findAllInfoByRequest(int id, int role_id,int pageValue, int MAX_PAGE)
			throws SQLException;

	public List<Admin_info> findAllInfoByALL(int id,int pageValue, int MAX_PAGE) throws SQLException;
	
	/**
	 * 查询出最大页数
	 * 1)全部信息的最大页数(select count(*) from lilacs_admin_info) 
	 * 2)条件为全部权限的最大页数
	 * 3)条件权限不为全部的最大页数
	 * 三种方法各为一条信息
	 */
	//1)全部
	public int getAllCount(int MAX_PAGE)throws SQLException;
	//2权限全部(限制角色id)
	public int getAllCountByAll(int MAX_PAGE,int roleId)throws SQLException;
	//3)权限限制(限制权限参数,角色id)
	public int getAllCountByOne(int MAX_PAGE,int roleId,int abilityId)throws SQLException;
	
	/*
	 * 根据用户的登录账号查找出用户的所有信息
	 */
	public Admin_info getInfoByLoginName(String loginName)throws SQLException;
	/*
	 * 管理员自助修改信息
	 */
	public boolean modiOwnInfo(Admin_info adminInfo)throws SQLException;
	/*
	 * 管理员自助修改密码
	 */
	public boolean modiOwnpwd(int id,String newpwd)throws SQLException;
}
