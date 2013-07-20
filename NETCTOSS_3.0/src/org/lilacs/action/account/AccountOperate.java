package org.lilacs.action.account;

import java.util.Date;

import javax.annotation.Resource;

import org.lilacs.DAO.AccountDAO;
import org.lilacs.po.Account;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Scope("prototype")
@Transactional
public class AccountOperate {
	private Account account;
	private String birthdate;
	private int id;
	@Resource
	private AccountDAO accountDAO;

	// ��ת������ҳ��
	public String addview() {
		return "toaddview";
	}

	// ����һ���ʷ��˻�
	public String willLoggertoadd() throws Exception {
		Date date = org.lilacs.util.UtilBag.strToDate(birthdate);
		account.setBirthdate(date);
		try {
			boolean flag = accountDAO.create(account);
			if (flag)
				return "toaccountlist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// ��ת���鿴ҳ��
	@Transactional(readOnly=true)
	public String todetail() throws Exception {
		try {
			account = accountDAO.getAccountById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "todetail";
	}

	// ��ת���޸Ľ���
	@Transactional(readOnly=true)
	public String tomodi() throws Exception {
		try {
			account = accountDAO.getAccountById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "tomodi";
	}

	// ����һ����Ϣ
	public String willLoggertoupdate() throws Exception {
		try {
			boolean flag = accountDAO.update(account);
			if (flag)
				return "toaccountlist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// ɾ��һ����Ϣ
	public String willLoggertodelete() throws Exception {
		try {
			boolean flag = accountDAO.delete(id);
			if (flag)
				return "toaccountlist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// ��ͣ�ʷ��˺�
	public String willLoggertopause() throws Exception {
		try {
			boolean flag = accountDAO.pauseStatus(id);
			if (flag)
				return "toaccountlist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	// ��ͨ�ʷ��˺�
	public String willLoggertoopen() throws Exception {
		System.out.println("����");
		try {
			boolean flag = accountDAO.openStatus(id);
			if (flag)
				return "toaccountlist";
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
