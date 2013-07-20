package org.lilacs.action.fee;

import javax.annotation.Resource;

import org.lilacs.DAO.CostDAO;
import org.lilacs.po.Cost;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Scope("prototype")
@Transactional
public class FeeOperate {
	private Cost cost;
	private int id;
	@Resource
	private CostDAO costDAO;

	// 跳转至增加资费类型页面
	public String addview() {
		return "toaddview";
	}

	// 增加一条资费信息
	public String willLoggertoadd() throws Exception {
		// 如果不成功就跳转到错误界面
		try {
			boolean flag = costDAO.create(cost);
			if (flag) {
				return "tofeelist";
			} else {
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// 跳转至查看资费信息界面
	@Transactional(readOnly = true)
	public String todetail() throws Exception {
		cost = costDAO.findById(id);
		return "todetail";
	}

	// 删除一条信息
	public String willLoggertodelete() throws Exception {
		costDAO.delete(id);
		return "tofeelist";

	}

	// 跳转至修改页面
	@Transactional(readOnly = true)
	public String tomodi() throws Exception {
		try {
			cost = costDAO.findById(id);
			return "tomodi";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// 更新资费信息
	public String willLoggertoupdate() throws Exception {
		boolean flag = costDAO.update(cost);
		return "tofeelist";

	}

	// 开通资费
	public String willLoggeropencost() throws Exception {
		try {
			boolean flag = costDAO.openStatus(id);
			if (flag)
				return "tofeelist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
