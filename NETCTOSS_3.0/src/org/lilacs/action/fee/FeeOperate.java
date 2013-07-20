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

	// ��ת�������ʷ�����ҳ��
	public String addview() {
		return "toaddview";
	}

	// ����һ���ʷ���Ϣ
	public String willLoggertoadd() throws Exception {
		// ������ɹ�����ת���������
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

	// ��ת���鿴�ʷ���Ϣ����
	@Transactional(readOnly = true)
	public String todetail() throws Exception {
		cost = costDAO.findById(id);
		return "todetail";
	}

	// ɾ��һ����Ϣ
	public String willLoggertodelete() throws Exception {
		costDAO.delete(id);
		return "tofeelist";

	}

	// ��ת���޸�ҳ��
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

	// �����ʷ���Ϣ
	public String willLoggertoupdate() throws Exception {
		boolean flag = costDAO.update(cost);
		return "tofeelist";

	}

	// ��ͨ�ʷ�
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
