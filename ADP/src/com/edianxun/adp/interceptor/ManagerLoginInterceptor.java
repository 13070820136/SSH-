package com.edianxun.adp.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ManagerLoginInterceptor extends AbstractInterceptor{

	/**
	 * 登录拦截器
	 * @author lhr
	 * @date 2014-4-23
	 */
	private static final long serialVersionUID = 9004199373355771950L;

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String uri = request.getRequestURI();
			if ("/ADP/mgrlogin".equals(uri) || uri.startsWith("/ADP/relogin") || uri.startsWith("/ADP/error")) {
				return actionInvocation.invoke();
			}
			Map<String, Object> session = ActionContext.getContext().getSession();
			String manager = (String) session.get("manager");
			if (manager == null) {
				return Action.LOGIN;
			}
			return actionInvocation.invoke();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
