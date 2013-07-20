package org.lilacs.util;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.lilacs.po.AdminInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;

@Component
@Scope("prototype")
@Aspect
public class SaveOperateLog {
	private Logger logger = Logger.getLogger(SaveOperateLog.class);

	@Around("execution(* willLogger*(..))")
	public Object operateLog(ProceedingJoinPoint pjp) throws Throwable {
		// 执行前的方法
		Object obj = pjp.proceed();// 执行目标对象
		Object target = pjp.getTarget();// 获得目标组件对象
		String targetName = target.getClass().getName();// 获取目标组件对象的名称
		String method = pjp.getSignature().getName();// 获取目标对象要执行的方法
		String key = targetName + "." + method;
		String operate = org.lilacs.util.LoadConf.getResult(key);
		ActionContext ac = ActionContext.getContext();
		Map<String, Object> session = ac.getSession();
		AdminInfo adminInfo = (AdminInfo) session.get("user");
		String theMan = adminInfo.getAdminCode();
		logger.info(theMan + "," + new Date() + "," + operate);
		// System.out.println(operate);
		return obj;
	}
}
