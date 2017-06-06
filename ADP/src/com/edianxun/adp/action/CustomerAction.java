package com.edianxun.adp.action;
/**
 * @author lhr
 * @date 2014-4-30
 */

import java.io.PrintWriter;
import java.util.Date;
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

@Results({@Result(name = "main", location = "/jsp/main.jsp", type = "redirect")})
public class CustomerAction extends BaseAction
        implements ModelDriven<Customer> {

    private static final long serialVersionUID = -1040088992400395429L;
    private static final Logger logger = Logger.getLogger(ManagerAction.class);
    private Customer customer = new Customer();
    private CustomerService customerService;
    private String name;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Action("customerList")
    public void list() {
        logger.debug("query all customer");
        try {
            this.outJson(customerService.customerList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Action("customerAdd")
    public void add() {
        try {
            customer.setStatus("NORMAL");
            Date now = new Date();
            customer.setCreateTime((int)now.getTime());
            int customerId = customerService.addCustomer(customer);
            logger.debug("customerId : " + customerId);
        } catch (Exception e) {
            e.printStackTrace();
            this.outJson("102");
        }
    }

    @Action("customerUpdateInfo")
    public void updateInfo() {
        logger.debug("customer update info ...");
        try {
            customerService.update(customer);
        } catch (Exception e) {
            e.printStackTrace();
            this.outJson("102");
        }
    }

    @Action("customerDel")
    public void del() {
        logger.debug("manager delete");
        try {
            customerService.delCustomer(customer.getCustomerid());
            this.outJson("ok");
        } catch (Exception e) {
            e.printStackTrace();
            this.outJson("nok");
        }
    }

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
