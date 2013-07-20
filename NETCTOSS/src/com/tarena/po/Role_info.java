package com.tarena.po;

import java.io.Serializable;
import java.util.List;

public class Role_info implements Serializable{
	//½ÇÉ«±í
	private int id;
	private String name;
	private List<String> privilege;
	
	

	public List<String> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
