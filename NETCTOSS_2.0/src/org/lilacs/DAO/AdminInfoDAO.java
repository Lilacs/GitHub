package org.lilacs.DAO;

import java.util.List;

import org.lilacs.po.AdminInfo;

public interface AdminInfoDAO {

	// 创建一条管理员信息
	public abstract boolean create(AdminInfo adminInfo, int[] num)
			throws Exception;

	// 根据id查询管理员信息
	public AdminInfo getAdminInfoById(int id) throws Exception;

	// 删除一名管理员
	public boolean delete(int id) throws Exception;

	// 更新一条管理员信息
	public boolean update(AdminInfo adminInfo, int[] num) throws Exception;
	
	// 登陆系统是进行验证用户是否存在(用于用户登陆)
	public boolean findUser(AdminInfo user) throws Exception;

	// 分页查询
	public List<AdminInfo> getAdminInfoList(String abilityType,
			String roleName, int page, int MAX_PAGE) throws Exception;

	// 查询一共多少页
	public int getPageCount(String abilityType, String roleName, int MAX_PAGE)
			throws Exception;
}