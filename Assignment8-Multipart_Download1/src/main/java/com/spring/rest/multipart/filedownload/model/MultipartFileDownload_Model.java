package com.spring.rest.multipart.filedownload.model;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MultipartFileDownload_Model {

	private String id;
	private String name;
	private String age;
	private String company;
	private String phone;
	private String department;
	private String salary;
	private String location;

	public MultipartFileDownload_Model() {

	}

	public MultipartFileDownload_Model(String id, String name, String age, String company, String phone,
			String department, String salary, String location) {

		this.id = id;
		this.name = name;
		this.age = age;
		this.company = company;
		this.phone = phone;
		this.department = department;
		this.salary = salary;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
