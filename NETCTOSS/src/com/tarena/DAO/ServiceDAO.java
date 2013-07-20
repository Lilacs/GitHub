package com.tarena.DAO;

import java.sql.SQLException;
import java.util.List;

import com.tarena.po.Service;

public interface ServiceDAO {
	public int getPageValues(int rows) throws Exception;

	public List<Service> findAll(int rowPage, int page) throws Exception;

	public int creatUser(Service service) throws Exception;

	/**
	 * 生成主界面的信息
	 */
	public List<Service> getList(String osName, String unitHost, String idCard,
			int status,  int pageValue, int MAX_PAGE) throws SQLException;
	/*
	 * 查询页数
	 */
	public int getpageCount(String osName, String unitHost, String idCard,
			int status, int MAX_PAGE)throws SQLException;
	/**
	 * 创建一条业务账号信息
	 */
	public boolean createInfo(Service service)throws SQLException;
	
	/*
	 * 预览一条用户的信息
	 */
	public Service getInfoByid(int id)throws SQLException;
	/*
	 * 更改用户的资费类型
	 */
	public boolean modiCostType(int costType,int id)throws SQLException;
	
	/*
	 * 删除一条用户业务信息(不实际删除,设置状态为2)
	 */
	public boolean delete(int id)throws SQLException;
	
	/*
	 * 开通
	 */
	public boolean open(int id)throws SQLException;
	
	/*
	 * 暂停
	 */
	public boolean pause(int id)throws SQLException;
}
