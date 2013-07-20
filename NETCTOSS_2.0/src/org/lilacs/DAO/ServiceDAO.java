package org.lilacs.DAO;

import java.util.List;

import org.lilacs.po.Service;

public interface ServiceDAO {

	//创建一条业务账号
	public abstract boolean createService(Service service) throws Exception;
	
	//删除一条业务账号,只是设置成2状态
	public boolean deleteById(int id) throws Exception;
	
	// 根据id查询单条用户信息
	public Service getServiceById(int id) throws Exception;
	
	//更新一条信息
	public boolean update(Service service) throws Exception;
	
	//开通业务账号
	public boolean open(int id)throws Exception;
	
	//暂停业务账号(设置状态为1,记载暂停时间)
	public boolean pause(int id)throws Exception;
		
	//分页查询
	public List<Service> getServiceList(String osUserName, String unixHost,
			String idCardNo, String status, int page, int MAX_PAGE)
			throws Exception;
	
	//根据条件查询页数
	public int getPageCount(String osUserName, String unixHost,
			String idCardNo, String status, int MAX_PAGE) throws Exception;
}