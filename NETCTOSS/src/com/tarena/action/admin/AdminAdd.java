package com.tarena.action.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tarena.UtilBag.DAOFactory;
import com.tarena.po.Admin_info;
import com.tarena.po.Role_info;

public class AdminAdd {
	private List<Role_info> checkBoxList;
	private Admin_info adminInfo;
	private int[] num;
	private int id;
	public String view(){
		try {
			checkBoxList = new DAOFactory().getRole_infoDAO().getAllInfo();
			return "truntoaddview";
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}
	public String create(){
		try {
			
			boolean b = new DAOFactory().getAdmin_infoDAO().createInfo(adminInfo);
			boolean c = false;
			
			int id = new DAOFactory().getAdmin_infoDAO().findID(adminInfo.getName());
			System.out.println("Õâ¸öÊÇid"+id);
			if(b){
				c = new DAOFactory().getAdmin_roleDAO().createAdminRole(id, num);
			}
			if(b && c ){
				com.tarena.UtilBag.BaseDAO.commit();
				com.tarena.UtilBag.BaseDAO.close();
				return "backtolist";
			}else{
				com.tarena.UtilBag.BaseDAO.rollback();
				com.tarena.UtilBag.BaseDAO.close();
				return "fail";
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	public String modi(){
		List<Integer> list = new ArrayList<Integer>();
		try {
			checkBoxList = new DAOFactory().getRole_infoDAO().getAllInfo();
			adminInfo = new DAOFactory().getAdmin_infoDAO().getAdminInfoById(id);
			list = new DAOFactory().getAdmin_roleDAO().findRoleIdByAdminID(id);
			num = new int[list.size()];
			for(int i=0;i<list.size();i++){
				num[i] = list.get(i);
			}
			return "turntomodi";
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}	
	}
	public String update(){
		adminInfo.setId(id);
		try {
			boolean flag1 = new DAOFactory().getAdmin_infoDAO().updateInfo(adminInfo);
			boolean flag2 = new DAOFactory().getAdmin_roleDAO().deleteRole(id);
			boolean flag3 = new DAOFactory().getAdmin_roleDAO().createAdminRole(id, num); 
			if(flag1 && flag2 && flag3){
				com.tarena.UtilBag.BaseDAO.commit();
				com.tarena.UtilBag.BaseDAO.close();
				return "backtolist";
			}else{
				com.tarena.UtilBag.BaseDAO.rollback();
				com.tarena.UtilBag.BaseDAO.close();
				return "fail";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		} 
	}
	
	public String delete(){
		try{
		boolean flag1 = new DAOFactory().getAdmin_infoDAO().delete(id);
		boolean flag2 = new DAOFactory().getAdmin_roleDAO().deleteRole(id);
		if(flag1 && flag2){
			com.tarena.UtilBag.BaseDAO.commit();
			com.tarena.UtilBag.BaseDAO.close();
			return "backtolist";
		}else{
			com.tarena.UtilBag.BaseDAO.rollback();
			com.tarena.UtilBag.BaseDAO.close();
			return "fail";
		}
		}catch(SQLException e){
			e.printStackTrace();
			return "fail";
		}
	}
	
	public List<Role_info> getCheckBoxList() {
		return checkBoxList;
	}
	public Admin_info getAdminInfo() {
		return adminInfo;
	}
	public void setAdminInfo(Admin_info adminInfo) {
		this.adminInfo = adminInfo;
	}
	public int[] getNum() {
		return num;
	}
	public void setNum(int[] num) {
		this.num = num;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCheckBoxList(List<Role_info> checkBoxList) {
		this.checkBoxList = checkBoxList;
	}

	
	
}
