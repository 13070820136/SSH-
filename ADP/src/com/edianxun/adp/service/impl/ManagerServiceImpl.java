package com.edianxun.adp.service.impl;

import java.util.List;

import com.edianxun.adp.dao.GateDao;
import com.edianxun.adp.pojo.GateCard;
import net.sf.json.JSONArray;

import com.edianxun.adp.dao.ManagerDao;
import com.edianxun.adp.pojo.Manager;
import com.edianxun.adp.service.ManagerService;

public class ManagerServiceImpl implements ManagerService {
	
	private ManagerDao managerDao; //spring 注入
	public void setManagerDao(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}
	private GateDao gateDao; //spring 注入
	public void setGateDao(GateDao gateDao) {
		this.gateDao = gateDao;
	}

	@Override
	public int addManager(Manager manager) throws Exception {
		try {
			managerDao.save(manager);
			return manager.getManagerId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("add manager exception");
		}
	}

	@Override
	public int loginValidate(Manager manager) throws Exception {
		try {
			Manager man = managerDao.findManagerByNameAndPwd(manager.getName(), 
					manager.getPassword());
			if (man != null) {
				return man.getManagerId();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("login validate exception");
		}
		return -1;
	}

	@Override
	public boolean validateName(String name) throws Exception {
		try {
			Manager manager = managerDao.findManagerByName(name);
			if (manager != null) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("validate manager name exception");
		}
	}
	
	@Override
	public String managerList() throws Exception {
		String listJson = null;
		try {
			List<Manager> list = managerDao.findAll(Manager.class);
			if (list.size() != 0) {
				String lJson = JSONArray.fromObject(list).toString();
				Long total = managerDao.allTotal(Manager.class);
				listJson = "{\"total\":"+total+",\"rows\":"+lJson+"}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("query all manager exception");
		}
		return listJson;
	}
	@Override
	public void update(Manager manager) throws Exception {
		try {
			managerDao.update(manager);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("update manager exception");
		}
		
	}
	@Override
	public Manager getManager(Integer managerId) throws Exception {
		try {
			Manager manager = managerDao.get(Manager.class, managerId);
			return manager;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("get manager exception");
		}
	}
	@Override
	public void del(Integer id) throws Exception {
		try {
			managerDao.delete(Manager.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("delete manager exception");
		}
		
	}

	@Override
	public String gateList() throws Exception {
		String listJson = null;
		try {
			List<GateCard> list = gateDao.findAllOrderbyDay();
			if (list.size() != 0) {
				String lJson = JSONArray.fromObject(list).toString();
				Long total = gateDao.allTotal(GateCard.class);
				listJson = "{\"total\":"+total+",\"rows\":"+lJson+"}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("query all gate card exception");
		}
		return listJson;
	}

}
