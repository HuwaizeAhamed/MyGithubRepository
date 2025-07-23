package com.Hibernate.Demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Model_Pojo {
	
	private String name;
	private String bloodGrp;
	private int phoneNumber;
	@Id
	private int id;
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected String getBloodGrp() {
		return bloodGrp;
	}
	protected void setBloodGrp(String bloodGrp) {
		this.bloodGrp = bloodGrp;
	}
	protected int getPhoneNumber() {
		return phoneNumber;
	}
	protected void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	protected int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	
	
	
	
	

}
