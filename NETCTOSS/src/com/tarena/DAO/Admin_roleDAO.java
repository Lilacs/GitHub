package com.tarena.DAO;

import java.sql.SQLException;
import java.util.List;

public interface Admin_roleDAO {
	/*
	 * 创建管理员的时候同时执行该方法(同一个事务)
	 * (不关闭连接)
	 * 返回值为boolean 用来判断是否成功
	 * 要求传进用户的id 和他的角色(角色至少为1个)
	 */
	public boolean createAdminRole(int id,int[] num)throws SQLException;
	
	/*
	 * 根据id查找用户的角色信息
	 * 不关闭连接,是另一个信息的调用层
	 */
	public List<String> getRoleInfo(int id)throws SQLException;
	
	/*
	 * 根据id查找到该管理员的角色id;
	 * (单条事务)
	 */
	public List<Integer> findRoleIdByAdminID(int id)throws SQLException;
	
	/*
	 * (修改管理员信息或者删除)
	 * 根据管理员id删除其所有角色
	 * (重新插入)
	 * (不关闭连接)
	 */
	public boolean deleteRole(int id)throws SQLException;
	
	
}