package org.lilacs.DAO;

import java.sql.SQLException;
import java.util.List;

import org.lilacs.po.Cost;

public interface CostDAO {
	public abstract List<Cost> findByPage(int pageValue, int MAX_PAGE)
			throws SQLException;

	// 得到一共有多少页
	public abstract int maxPage(int MAX_PAGE) throws SQLException;

	// 删除一条资费信息
	public abstract boolean delete(int id) throws Exception;

	// 创建一条资费信息
	public abstract boolean create(Cost cost) throws Exception;

	// 根据id找到一条信息
	public abstract Cost findById(int id) throws Exception;

	// 更新信息
	public abstract boolean update(Cost cost) throws Exception;

	// 更新成开通状态 同时记载开通时间
	public abstract boolean openStatus(int id) throws Exception;

	/*
	 * (用于ajax判断有没有该名称)
	 * 
	 * @see com.tarena.DAO.CostDAO#find_By_Name(java.lang.String)
	 */
	public abstract boolean findByName(String name) throws SQLException;

	/**
	 * 生成业务账号的信息时用到 根据service中的cost_id得到相关的cost信息
	 */
	public abstract List<String> getIntroduction(int costId)
			throws SQLException;

	// 找到所有的资费类型
	public abstract List<Cost> getAlltype() throws SQLException;
}
