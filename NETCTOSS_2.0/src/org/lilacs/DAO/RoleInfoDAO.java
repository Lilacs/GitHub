package org.lilacs.DAO;

import java.util.List;

import org.lilacs.po.RoleInfo;

public interface RoleInfoDAO {

	// 创建一条角色信息
	public abstract boolean createRoleInfo(RoleInfo roleInfo, int[] num)
			throws Exception;
	// 删除一条角色信息
	public boolean delete(int id) throws Exception;
	
	// 根据id查找出角色信息
	public RoleInfo getRoleInfoById(int id)throws Exception;
	
	// 修改角色信息
	public boolean modify(RoleInfo roleInfo, int[] num) throws Exception;
		
	// 分页查询
	public List<RoleInfo> getRoleInfoList(int page, int MAX_PAGE)
				throws Exception;
	
	// 查询一共有多少页
	public int getPageCount(int MAX_PAGE)throws Exception;
	
	//查询所有的角色信息
	public List<RoleInfo> getAllRoleInfo()throws Exception;

}