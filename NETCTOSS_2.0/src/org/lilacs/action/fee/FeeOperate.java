package org.lilacs.action.fee;

import java.sql.SQLException;

import org.lilacs.po.Cost;
import org.lilacs.util.DAOFactory;

public class FeeOperate {
	private Cost cost;
	private int id;

	// ��ת�������ʷ�����ҳ��
	public String addview() {
		return "toaddview";
	}

	// ����һ���ʷ���Ϣ
	public String toadd() throws Exception {
		// ������ɹ�����ת���������
		try {
			boolean flag = new DAOFactory().getCostDAO().createCost(cost);
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
	public String todetail() throws Exception {
		cost = new DAOFactory().getCostDAO().find_By_Id(id);
		return "todetail";
	}

	// ɾ��һ����Ϣ
	public String todelete() throws Exception {
		try {
			boolean flag = new DAOFactory().getCostDAO().delete_cost(id);
			if(flag)
				return "tofeelist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	//��ת���޸�ҳ��
	public String tomodi() throws Exception{
		try {
			cost = new DAOFactory().getCostDAO().find_By_Id(id);
			return "tomodi";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}	
	}
	//�����ʷ���Ϣ
	public String toupdate() throws Exception{
		try {
			boolean flag = new DAOFactory().getCostDAO().update_info(cost);
			if(flag)
				return "tofeelist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	//��ͨ�ʷ�
	public String opencost() throws Exception{
		try {
			boolean flag = new DAOFactory().getCostDAO().update_status(id);
			if(flag)
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
