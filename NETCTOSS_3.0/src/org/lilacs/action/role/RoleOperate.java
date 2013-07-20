package org.lilacs.action.role;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.lilacs.DAO.AbilityDAO;
import org.lilacs.DAO.RoleInfoDAO;
import org.lilacs.po.Ability;
import org.lilacs.po.RoleInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
@Controller
@Scope("prototype")
@Transactional
public class RoleOperate {
	private List<Ability> abilitys;
	private int[] num;
	private RoleInfo roleInfo;
	private int id;
	@Resource
	private AbilityDAO abilityDAO;
	@Resource
	private RoleInfoDAO roleInfoDAO;
	// 跳转至增加页面
	@Transactional(readOnly=true)
	public String addview() {
		abilitys = abilityDAO.getAbilitys();
		return "toaddview";
	}

	// 增加一条信息
	public String willLoggertoadd() throws Exception{
		try {
			boolean flag = roleInfoDAO.create(roleInfo, num);
			if(flag)
				return "torolelist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	// 删除一条角色信息
	public String willLoggertodelete() throws Exception{
		try {
			boolean flag = roleInfoDAO.delete(id);
			if(flag)
				return "torolelist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}
	
	//跳转至修改页面
	@Transactional(readOnly=true)
	public String tomodi() throws Exception{
		abilitys = abilityDAO.getAbilitys();
		num = new int[abilitys.size()];
		roleInfo = roleInfoDAO.getRoleInfoById(id);
		Set<Ability> abilitys = roleInfo.getAbilitys();
		int i = 0;
		for(Ability ability : abilitys){
			num[i] = ability.getId();
			i=i+1;
		}
		return "tomodi";
	}
	
	//修改角色信息
	public String willLoggertoupdate() throws Exception{
		System.out.println("进入更新action");
		boolean flag = roleInfoDAO.update(roleInfo, num);
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
