package com.edianxun.adp.service.impl;

import com.edianxun.adp.dao.CustomerDao;
import com.edianxun.adp.pojo.Customer;
import com.edianxun.adp.pojo.Manager;
import com.edianxun.adp.service.CustomerService;
import net.sf.json.JSONArray;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao; //spring 注入

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public int addCustomer(Customer customer) throws Exception {
        customerDao.save(customer);
        return customer.getCustomerid();
    }

    @Override
    public String customerList() throws Exception {
        String listJson = null;
        try {
            List<Customer> list = customerDao.findAll(Customer.class);
            if (list.size() != 0) {
                String lJson = JSONArray.fromObject(list).toString();
                Long total = customerDao.allTotal(Customer.class);
                listJson = "{\"total\":" + total + ",\"rows\":" + lJson + "}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("query all customer exception");
        }
        return listJson;
    }

    @Override
    public Customer getCustomer(Integer customerid) throws Exception {
        return null;
    }

    @Override
    public void update(Customer customer) throws Exception {
        try {
            customerDao.update(customer);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("update customer exception");
        }
    }

    @Override
    public void delCustomer(Integer customerid) throws Exception {
        try {
            customerDao.delete(Customer.class, customerid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("delete manager exception");
        }
    }
}
