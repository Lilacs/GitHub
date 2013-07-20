package com.tarena.UtilBag;

import com.tarena.DAO.*;

public class DAOFactory {
	private static CostDAO cost = new CostDAOimpl();
	private static Admin_infoDAO admin_info = new Admin_infoDAOimpl();
	private static Admin_roleDAO adminRole = new Admin_roleDAOimpl();
	private static Role_infoDAO role_info = new Role_infoDAOimpl();
	private static Role_privilegeDAO role_privilege = new Role_privilegeDAOimpl();
	private static AbilityDAO ability = new AbilityDAOimpl();
	private static AccountDAO account = new AccountDAOimpl();
	private static ServiceDAO service = new ServiceDAOimpl();
	private static HostDAO host = new HostDAOimpl();

	public CostDAO getCostDAO() {
		return cost;
	}

	public Admin_infoDAO getAdmin_infoDAO() {
		return admin_info;
	}

	public Role_infoDAO getRole_infoDAO() {
		return role_info;
	}

	public Role_privilegeDAO getRole_privilegeDAO() {
		return role_privilege;
	}

	public AbilityDAO getAbilityDAO() {
		return ability;
	}
	public HostDAO getHostDAO(){
		return host;
	}

	public Admin_roleDAO getAdmin_roleDAO() {
		return adminRole;
	}

	public AccountDAO getAccountDAO() {
		return account;
	}

	public ServiceDAO getServiceDAO() {
		return service;
	}
}
