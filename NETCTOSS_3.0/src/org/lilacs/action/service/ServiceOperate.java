package org.lilacs.action.service;

import java.util.List;

import javax.annotation.Resource;

import org.lilacs.DAO.CostDAO;
import org.lilacs.DAO.HostDAO;
import org.lilacs.DAO.ServiceDAO;
import org.lilacs.po.Cost;
import org.lilacs.po.Host;
import org.lilacs.po.Service;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Scope("prototype")
@Transactional(propagation=Propagation.REQUIRED)
public class ServiceOperate {
	private List<Cost> cost;
	private List<Host> host;
	private Service service;
	private int id;
	@Resource
	private HostDAO hostDAO;
	@Resource
	private CostDAO costDAO;
	@Resource
	private ServiceDAO serviceDAO;

	// 跳转至增加业务账号页面
	@Transactional(readOnly = true)
	public String addview() throws Exception {
		try {
			host = hostDAO.getAll();
			cost = costDAO.getAlltype();
			return "toaddview";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// 增加业务账号
	public String willLoggertoadd() {
		boolean flag = serviceDAO.create(service);
		System.out.println(flag);
		return "toservicelist";
	}

	// 删除一条业务账号(把状态号设置为2)
	public String willLoggertodelete() throws Exception {
		try {
			boolean flag = serviceDAO.delete(id);
			if (flag)
				return "toservicelist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// 跳转至单条查看业务账号
	@Transactional(readOnly = true)
	public String todetail() throws Exception {
		try {
			service = serviceDAO.getServiceById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "todetail";
	}

	// 跳转至修改页面
	@Transactional(readOnly = true)
	public String tomodi() throws Exception {
		try {
			host = hostDAO.getAll();
			cost = costDAO.getAlltype();
			service = serviceDAO.getServiceById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tomodi";
	}

	// 修改更新的信息
	public String willLoggertoupdate() throws Exception {
		// TODO service修改有错误,调试DAO!
		System.out.println(service.getCost().getId());
		serviceDAO.update(service);
		return "toservicelist";

	}

	// 暂停业务
	public String willLoggertopause() {
		try {
			boolean flag = serviceDAO.pauseStatus(id);
			if (flag)
				return "toservicelist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// 开通业务
	public String willLoggertoopen() {
		try {
			boolean flag = serviceDAO.openStatus(id);
			if (flag)
				return "toservicelist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public List<Cost> getCost() {
		return cost;
	}

	public void setCost(List<Cost> cost) {
		this.cost = cost;
	}

	public List<Host> getHost() {
		return host;
	}

	public void setHost(List<Host> host) {
		this.host = host;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
