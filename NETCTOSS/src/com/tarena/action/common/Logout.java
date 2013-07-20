package com.tarena.action.common;

import com.tarena.UtilBag.BaseAware;

public class Logout extends BaseAware{
	
	
	public String execute(){
		httpServletRequest.getSession().invalidate();
		return "logout";
		
	}
}
