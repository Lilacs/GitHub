package com.tarena.DAO;

import java.sql.SQLException;
import java.util.List;

import com.tarena.po.Role_info;

public interface Role_infoDAO {
	//创建角色
	public boolean create_Role_info(Role_info info,int[] value) throws SQLException;
	//Ajax查询角色名称是否存在
	public boolean find_Role_info_name(String name) throws SQLException;
	//修改角色信息
	public boolean modify_Role_info(String newName,String oldName,int[] num) throws SQLException;
	//删除角色信息
	public boolean delete_Role_info(int id) throws SQLException;
	//显示角色
	public List<Role_info> getList(int pageValue, int MAX_PAGE)throws SQLException;
	//查询一共多少页
	public int howManyPage(int MAX_PAGE)throws SQLException;
	//根据id查找角色名称
	public String getName(int id)throws SQLException;
	//查找所有的角色表
	public List<Role_info> getAllInfo()throws SQLException;
	//根据角色名称查询id
	public int getID(String name)throws SQLException;
	/*
	 * 根据传来的角色名称判断是否已经存在,排除他自己的名称,用户更改信息
	 */
	public boolean modiName(String name)throws SQLException;

}	

