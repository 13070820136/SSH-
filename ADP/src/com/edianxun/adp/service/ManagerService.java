package com.edianxun.adp.service;

import com.edianxun.adp.pojo.Manager;

public interface ManagerService {

	/**
	 * 新增管理员
	 * @param manager
	 * @return 返回新用户主键
	 * @throws Exception
	 */
	int addManager(Manager manager) throws Exception;
	
	/**
	 * 验证用户登录
	 * @param manager
	 * @return
	 * @throws Exception
	 */
	int loginValidate(Manager manager) throws Exception;
	
	/**
	 * 验证用户名是否存在
	 * @param name
	 * @return
	 * @throws Exception
	 */
	boolean validateName(String name) throws Exception;
	
	String managerList() throws Exception;
	
	Manager getManager(Integer managerId) throws Exception;
	
	void update(Manager manager) throws Exception;
	
	void del(Integer id) throws Exception;

	String gateList() throws Exception;

}
