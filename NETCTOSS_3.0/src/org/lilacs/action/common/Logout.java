package org.lilacs.action.common;

import org.lilacs.util.BaseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
@Controller
@Scope("prototype")
public class Logout extends BaseAware{
	public String execute(){
		this.session.clear();
		return "success";
	}
}
