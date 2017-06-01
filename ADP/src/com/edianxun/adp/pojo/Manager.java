package com.edianxun.adp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="manager")
public class Manager {
	
	@Id
	@Column(name="managerId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int managerId;
	private String name;
	private String password;
	private String role;
	private String status;
	private String descrition;
	
	public Manager(){}
	
	public Manager(String name, String password, String role){
		this.name = name;
		this.password = password;
		this.role = role;
	}
	
	public Manager(String name, String password, String role, String status, String descrition){
		this.name = name;
		this.password = password;
		this.role = role;
		this.status = status;
		this.descrition = descrition;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescrition() {
		return descrition;
	}

	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}
	
}
