package org.lilacs.interceptor;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		ActionContext ac = arg0.getInvocationContext();
		//List<Integer> list = (List<Integer>) ac.getSession().get("privilege");
		
		if(ac.getSession().get("user") == null){
			return "login";
		}
		arg0.invoke();
		
		return "error";
	}
	
}
