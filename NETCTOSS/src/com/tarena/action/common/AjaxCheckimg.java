package com.tarena.action.common;

import com.tarena.UtilBag.BaseAware;

public class AjaxCheckimg extends BaseAware{
	private String checkcode;
	private boolean flag;
	public String execute(){
		String checkcode2 = (String) session.get("checkcode");
		if(checkcode.equalsIgnoreCase(checkcode2)){
			flag = true;
			return "success";
		}else{
			flag = false;
			return "error";
			
		}
		
	}
	
	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}
