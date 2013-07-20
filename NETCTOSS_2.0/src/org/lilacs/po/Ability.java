package org.lilacs.po;

/**
 * ZpAbility entity. @author MyEclipse Persistence Tools
 */

public class Ability implements java.io.Serializable {

	// Fields

	private Integer id;
	private String abilityType;

	public Ability() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAbilityType() {
		return abilityType;
	}

	public void setAbilityType(String abilityType) {
		this.abilityType = abilityType;
	}

	

}