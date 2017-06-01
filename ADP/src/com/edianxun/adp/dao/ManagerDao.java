package com.edianxun.adp.dao;

import com.edianxun.adp.pojo.Manager;
/**
 * 管理员操作接口
 * @author lhr
 * @ date 2014-4-21
 */
public interface ManagerDao extends BaseDao<Manager> {

	Manager findManagerByNameAndPwd(String name, String pwd);
	
	Manager findManagerByName(String name);
}
