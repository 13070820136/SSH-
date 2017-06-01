package com.edianxun.adp.action;

import org.apache.struts2.convention.annotation.Result;

@Result(name="success", location="/404/error.jsp",type="redirect")
public class ErrorAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String execute(){
		return SUCCESS;
	}

}
