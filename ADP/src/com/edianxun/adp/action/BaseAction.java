package com.edianxun.adp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{

	/**
	 * @author lhr
	 * @date  2014-4-29
	 */
	private static final long serialVersionUID = 1893151512645757572L;

	/**
	 * get session
	 */
	public Map<String, Object> getSession(){
		return ActionContext.getContext().getSession();
	}
	
	/**
	 * get response by ServletActionContext
	 * @return response
	 */
	public HttpServletResponse getResponse(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		return response;
	}
	
	/**
	 * get request by ServletActionContext
	 * @return request
	 */
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	/**
	 * open PrintWrite
	 * @return pw
	 */
	public PrintWriter getWriter(){
		HttpServletResponse response = this.getResponse();
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pw;
	}
	
	/**
	 * close PrintWrite
	 * @param pw
	 */
	public void close(PrintWriter pw){
		if (pw != null) {
			pw.close();
		}
	}
	
	/**
	 * write json string
	 * @param json
	 */
	public void outJson(String json){
		PrintWriter pw = this.getWriter();
		pw.write(json);
		this.close(pw);
	}
	
}
