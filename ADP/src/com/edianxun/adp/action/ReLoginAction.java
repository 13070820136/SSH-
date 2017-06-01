package com.edianxun.adp.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

@Result(name="success", location="/jsp/index.jsp",type="redirect")
public class ReLoginAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Action("relogin")
	public String execute(){
		return SUCCESS;
	}

}
