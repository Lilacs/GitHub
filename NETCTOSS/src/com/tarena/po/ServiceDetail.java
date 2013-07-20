package com.tarena.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ServiceDetail implements Serializable{
	private Integer id;
	private Integer service_id;
	private String client_host;
	private String os_username;
	private Integer pid;
	private Date login_time;
	private Date logout_time;
	private Integer duration;
	private BigDecimal cost;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getService_id() {
		return service_id;
	}

	public void setService_id(Integer service_id) {
		this.service_id = service_id;
	}

	public String getClient_host() {
		return client_host;
	}

	public void setClient_host(String client_host) {
		this.client_host = client_host;
	}

	public String getOs_username() {
		return os_username;
	}

	public void setOs_username(String os_username) {
		this.os_username = os_username;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Date getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}

	public Date getLogout_time() {
		return logout_time;
	}

	public void setLogout_time(Date logout_time) {
		this.logout_time = logout_time;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String toString() {
		return "ServiceDetail [id=" + id + ", service_id=" + service_id
				+ ", client_host=" + client_host + ", os_username="
				+ os_username + ", pid=" + pid + ", login_time=" + login_time
				+ ", logout_time=" + logout_time + ", duration=" + duration
				+ ", cost=" + cost + "]";
	}

}
