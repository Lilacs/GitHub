package org.lilacs.action.fee;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.lilacs.DAO.CostDAO;
import org.lilacs.po.Cost;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Scope("prototype")
@Transactional(readOnly=true)
public class FeeList {
	private List<Cost> costs;
	private int page = 1;
	private int MAX_PAGE;
	private int page_count;
	@Resource
	private CostDAO costDAO;

	public String execute() {
		try {
			costs = costDAO.findByPage(page, MAX_PAGE);
			page_count = costDAO.maxPage(MAX_PAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "success";
	}

	public List<Cost> getCosts() {
		return costs;
	}

	public void setCosts(List<Cost> costs) {
		this.costs = costs;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getMAX_PAGE() {
		return MAX_PAGE;
	}

	public void setMAX_PAGE(int mAX_PAGE) {
		MAX_PAGE = mAX_PAGE;
	}

	public int getPage_count() {
		return page_count;
	}

	public void setPage_count(int page_count) {
		this.page_count = page_count;
	}

}
