package com.tarena.po;

import java.io.Serializable;
import java.util.Date;

public class Cost implements Serializable{
	private Integer id;
	private String name;
	private Integer base_duration;
	private double base_cost;
	private double unit_cost;
	private String status;
	private String descr;
	private Integer costtype;
	private Date creatime;
	private Date startime;
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
	public Integer getBase_duration() {
		return base_duration;
	}
	public void setBase_duration(Integer base_duration) {
		this.base_duration = base_duration;
	}
	public double getBase_cost() {
		return base_cost;
	}
	public void setBase_cost(double base_cost) {
		this.base_cost = base_cost;
	}
	public double getUnit_cost() {
		return unit_cost;
	}
	public void setUnit_cost(double unit_cost) {
		this.unit_cost = unit_cost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Date getCreatime() {
		return creatime;
	}
	public void setCreatime(Date creatime) {
		this.creatime = creatime;
	}
	public Date getStartime() {
		return startime;
	}
	public void setStartime(Date startime) {
		this.startime = startime;
	}
	public Integer getCosttype() {
		return costtype;
	}
	public void setCosttype(Integer costtype) {
		this.costtype = costtype;
	}
	
}
