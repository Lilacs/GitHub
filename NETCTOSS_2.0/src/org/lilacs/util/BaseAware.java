package org.lilacs.util;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

public class BaseAware implements RequestAware, SessionAware, ApplicationAware,
		ServletRequestAware, ServletResponseAware, ServletContextAware {
	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;
	protected HttpServletRequest httpServletRequest;
	protected HttpServletResponse httpServletResponse;
	protected ServletContext servletContext;
		
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;

	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;

	}

	public void setApplication(Map<String, Object> arg0) {
		this.application = arg0;

	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.httpServletRequest = arg0;

	}

	public void setServletResponse(HttpServletResponse arg0) {
		this.httpServletResponse = arg0;

	}

	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;

	}

}
