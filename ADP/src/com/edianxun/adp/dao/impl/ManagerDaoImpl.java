package com.edianxun.adp.dao.impl;

import java.util.List;

import com.edianxun.adp.dao.ManagerDao;
import com.edianxun.adp.pojo.Manager;
/**
 * @author lhr
 * @ date 2014-4-21
 */
public class ManagerDaoImpl extends BaseDaoImpl<Manager> implements ManagerDao {

	@Override
	public Manager findManagerByNameAndPwd(String name, String pwd) {
		List manager = find("select m from Manager m where m.name=?0 and m.password=?1", name, pwd);
		if (manager != null && manager.size() >= 1) {
			return (Manager) manager.get(0);
		}
		return null;
	}

	@Override
	public Manager findManagerByName(String name) {
		List manager = find("select m from Manager m where m.name=?0", name);
		if (manager != null && manager.size() >= 1) {
			return (Manager) manager.get(0);
		}
		return null;
	}

}
