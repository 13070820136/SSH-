package com.edianxun.adp.action;
/**
 * @author lhr
 * @date 2014-4-30
 */
import java.util.Map;

import com.edianxun.adp.pojo.Customer;
import com.edianxun.adp.service.CustomerService;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.edianxun.adp.pojo.Manager;
import com.edianxun.adp.service.ManagerService;
import com.opensymphony.xwork2.ModelDriven;

@Results({@Result(name="main",location="/jsp/main.jsp",type="redirect")})
public class CustomerAction extends BaseAction
		implements ModelDriven<Customer>{

	private static final long serialVersionUID = -1040088992400395429L;
	private static final Logger logger = Logger.getLogger(ManagerAction.class);
	private Customer customer = new Customer();
	private CustomerService customerService;
	private String name;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}


	@Action("customerList")
	public void list(){
		logger.debug("query all manager");
		try {
			this.outJson(customerService.customerList());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*@Action("mgradd")
	public void add(){
		try {
			manager.setStatus("NORMAL");
			int managerId = managerService.addManager(manager);
			logger.debug("managerId : " + managerId);
			if (managerId <= 0) {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Action("mgrupdate")
	public void update(){
		logger.debug("manager update password ...");
		try {
			Map<String, Object> session = getSession();
			int id = (Integer) session.get("managerId");
			Manager man = managerService.getManager(id);
			man.setPassword(manager.getPassword());
			managerService.update(man);
			this.outJson(man.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Action("mgrUpdateInfo")
	public void updateInfo(){
		logger.debug("manager update info ...");
		try {
			System.out.println("####" + manager.getDescrition());
			managerService.update(manager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Action("mgrdel")
	public void del(){
		logger.debug("manager delete");
		try {
			managerService.del(manager.getManagerId());
			this.outJson("ok");
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson("nok");
		}
	}*/

	@Override
	public Customer getModel() {
		return customer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
