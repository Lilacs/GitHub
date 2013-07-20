package org.lilacs.action.role;

import java.util.List;
import java.util.Set;

import org.lilacs.po.Ability;
import org.lilacs.po.RoleInfo;
import org.lilacs.util.DAOFactory;

public class RoleOperate {
	private List<Ability> abilitys;
	private int[] num;
	private RoleInfo roleInfo;
	private int id;
	// ��ת������ҳ��
	public String addview() {
		abilitys = new DAOFactory().getAbilityDAO().getAbilitys();
		return "toaddview";
	}

	// ����һ����Ϣ
	public String toadd() throws Exception{
		try {
			boolean flag = new DAOFactory().getRoleInfoDAO().createRoleInfo(roleInfo, num);
			if(flag)
				return "torolelist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	// ɾ��һ����ɫ��Ϣ
	public String todelete() throws Exception{
		try {
			boolean flag = new DAOFactory().getRoleInfoDAO().delete(id);
			if(flag)
				return "torolelist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}
	
	//��ת���޸�ҳ��
	public String tomodi() throws Exception{
		abilitys = new DAOFactory().getAbilityDAO().getAbilitys();
		num = new int[abilitys.size()];
		roleInfo = new DAOFactory().getRoleInfoDAO().getRoleInfoById(id);
		Set<Ability> abilitys = roleInfo.getAbilitys();
		int i = 0;
		for(Ability ability : abilitys){
			num[i] = ability.getId();
			i=i+1;
		}
		return "tomodi";
	}
	
	//�޸Ľ�ɫ��Ϣ
	public String toupdate() throws Exception{
		System.out.println("�������action");
		boolean flag = new DAOFactory().getRoleInfoDAO().modify(roleInfo, num);
		if(flag)
			return "torolelist";
		else
			return "error";
	}
	
	public List<Ability> getAbilitys() {
		return abilitys;
	}

	public void setAbilitys(List<Ability> abilitys) {
		this.abilitys = abilitys;
	}

	public int[] getNum() {
		return num;
	}

	public void setNum(int[] num) {
		this.num = num;
	}

	public RoleInfo getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(RoleInfo roleInfo) {
		this.roleInfo = roleInfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
