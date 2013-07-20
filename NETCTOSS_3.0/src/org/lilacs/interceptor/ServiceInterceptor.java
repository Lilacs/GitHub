package org.lilacs.interceptor;

import java.util.HashSet;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ServiceInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		ActionContext ac = arg0.getInvocationContext();
		HashSet<Integer> abilitys = (HashSet<Integer>) ac.getSession().get(
				"abilitys");
		boolean flag = abilitys.contains(5);
		if (flag) {
			arg0.invoke();
			return "success";
		} else
			return "nopower";
	}

}
