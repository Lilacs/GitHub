package org.lilacs.util;

import org.lilacs.DAO.*;
import org.lilacs.DAO.realize.*;

public class DAOFactory {
	private static CostDAO cost = new CostDAOimpl();
	private static AccountDAO account = new AccountDAOimpl();
	private static HostDAO host = new HostDAOimpl();
	private static ServiceDAO service = new ServiceDAOimpl();
	private static AbilityDAO ability = new AbilityDAOimpl();
	private static RoleInfoDAO roleInfo = new RoleInfoDAOimpl();
	private static AdminInfoDAO adminInfo = new AdminInfoDAOimpl();

	public CostDAO getCostDAO() {
		return cost;
	}

	public AccountDAO getAccountDAO() {
		return account;
	}

	public HostDAO getHostDAO() {
		return host;
	}

	public ServiceDAO getServiceDAO() {
		return service;
	}

	public AbilityDAO getAbilityDAO() {
		return ability;
	}

	public RoleInfoDAO getRoleInfoDAO() {
		return roleInfo;
	}
	
	public AdminInfoDAO getAdminInfoDAO(){
		return adminInfo;
	}
}
