package org.lilacs.po;

import java.util.Set;

/**
 * ZpRoleInfo entity. @author MyEclipse Persistence Tools
 */

public class RoleInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Set<Ability> abilitys;
	// Constructors

	/** default constructor */
	public RoleInfo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Ability> getAbilitys() {
		return abilitys;
	}

	public void setAbilitys(Set<Ability> ability) {
		this.abilitys = ability;
	}
	
	
	
}