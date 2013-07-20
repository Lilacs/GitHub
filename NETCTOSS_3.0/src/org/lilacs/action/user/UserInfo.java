package org.lilacs.action.user;

import java.util.Set;

import javax.annotation.Resource;

import org.lilacs.DAO.AdminInfoDAO;
import org.lilacs.po.AdminInfo;
import org.lilacs.po.RoleInfo;
import org.lilacs.util.BaseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Scope("prototype")
public class UserInfo extends BaseAware {
	private AdminInfo adminInfo;
	private int id;
	private String name;
	private String telephone;
	private String email;
	private boolean flag;
	@Resource
	private AdminInfoDAO adminInfoDAO;

	// 跳转至个人修改信息页面
	public String modiview() {
		AdminInfo adminInfo1 = (AdminInfo) this.session.get("user");
		adminInfo = adminInfoDAO.getAdminInfo(adminInfo1.getAdminCode());
		Set<RoleInfo> roleInfos = adminInfo.getRoleInfos();
		for (RoleInfo r : roleInfos) {
			System.out.println(r.getName());
		}
		return "tomodiview";
	}

	// 提交个人修改信息页面更新信息
	@Transactional
	public String update() {
		AdminInfo admin = new AdminInfo();
		admin.setId(id);
		admin.setName(name);
		admin.setEmail(email);
		admin.setTelephone(telephone);
		flag = adminInfoDAO.update(admin);
		return "success";
	}

	// 跳转至个人修改密码页面
	public String modiPWD() {
		return "tomodiPWD";
	}

	public AdminInfo getAdminInfo() {
		return adminInfo;
	}

	public void setAdminInfo(AdminInfo adminInfo) {
		this.adminInfo = adminInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
