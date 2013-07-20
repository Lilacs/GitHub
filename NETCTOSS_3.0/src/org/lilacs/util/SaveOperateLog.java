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
		// ִ��ǰ�ķ���
		Object obj = pjp.proceed();// ִ��Ŀ�����
		Object target = pjp.getTarget();// ���Ŀ���������
		String targetName = target.getClass().getName();// ��ȡĿ��������������
		String method = pjp.getSignature().getName();// ��ȡĿ�����Ҫִ�еķ���
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
