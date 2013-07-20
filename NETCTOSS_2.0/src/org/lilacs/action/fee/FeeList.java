package org.lilacs.action.fee;

import java.sql.SQLException;
import java.util.List;

import org.lilacs.po.Cost;
import org.lilacs.util.DAOFactory;




public class FeeList {
	private List<Cost> costs;
	private int page=1;
	private int MAX_PAGE;
	private int page_count;
	
	public String execute(){
		try {
			costs = new DAOFactory().getCostDAO().find_By_Page(page,MAX_PAGE);
			page_count = new DAOFactory().getCostDAO().maxPage(MAX_PAGE);
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
