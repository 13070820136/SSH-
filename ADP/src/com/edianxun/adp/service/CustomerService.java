package com.edianxun.adp.service;

import com.edianxun.adp.pojo.Customer;

public interface CustomerService {

	/**
	 * 新增客户
	 * @param customer
	 * @return 返回新用户主键
	 * @throws Exception
	 */
	int addCustomer(Customer customer) throws Exception;

	String customerList() throws Exception;

	Customer getCustomer(Integer customerid) throws Exception;
	
	void update(Customer customer) throws Exception;
	
	void delCustomer(Integer customerid) throws Exception;
}
