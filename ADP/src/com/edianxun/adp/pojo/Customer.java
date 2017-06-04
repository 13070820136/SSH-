package com.edianxun.adp.pojo;

import javax.persistence.*;

/**
 * Created by yu on 2017/6/3.
 */
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "managerId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerid;
    private String account;
    private String password;
    private String name;
    private int age;
    private int size;
    private int phone;
    private int createTime;

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public Customer() {
    }
}
