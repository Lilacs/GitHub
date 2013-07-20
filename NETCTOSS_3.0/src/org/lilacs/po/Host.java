package org.lilacs.po;

/**
 * ZpHost entity. @author MyEclipse Persistence Tools
 */

public class Host implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String location;

	// Constructors

	/** default constructor */
	public Host() {
	}

	/** minimal constructor */
	public Host(String id) {
		this.id = id;
	}

	/** full constructor */
	public Host(String id, String name, String location) {
		this.id = id;
		this.name = name;
		this.location = location;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}