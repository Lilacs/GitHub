package com.tarena.po;

import java.io.Serializable;

public class Ability implements Serializable{
	//È¨ÏÞ±í
	private int id;
	private String ability_type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAbility_type() {
		return ability_type;
	}
	public void setAbility_type(String ability_type) {
		this.ability_type = ability_type;
	}
	
	
}
