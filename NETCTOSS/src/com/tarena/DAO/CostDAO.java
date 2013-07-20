package com.tarena.DAO;

import java.sql.SQLException;
import java.util.List;

import com.tarena.po.Cost;

public interface CostDAO {
	// 查询当前页数
	public List<Cost> find_By_Page(int pageValue, int MAX_PAGE)
			throws SQLException;

	// 查询共有记录数
	public int maxPage(int MAX_PAGE) throws SQLException;

	// 删除cost表指定的记录
	public void delete_cost(int id) throws SQLException;

	// 创建单条cost表中的的资费类型
	public void createCost(Cost cost) throws SQLException;

	// 根据id查找记录
	public Cost find_By_Id(int id) throws SQLException;

	// 根据id更新cost表中的记录
	public void update_info(Cost cost) throws SQLException;

	// 更改cost表单条数据的状态
	public void update_status(int id) throws SQLException;

	// 新建资费信息时检查是否存在此name
	public boolean find_By_Name(String name) throws SQLException;
	
	/*
	 * 根据cost_id查找到资费类型名称与资费信息介绍
	 */
	public List<String> getIntroduction(int costId)throws SQLException;
	/*
	 * 找到所有的资费类型
	 */
	public List<Cost> getAlltype()throws SQLException;
}
