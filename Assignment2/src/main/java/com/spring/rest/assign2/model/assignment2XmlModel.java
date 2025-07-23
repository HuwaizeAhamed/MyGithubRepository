package com.spring.rest.assign2.model;

import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import jakarta.xml.bind.annotation.XmlElement;

@Configuration
public class assignment2XmlModel {

	@JsonProperty(value = "emp_Name")
	@XmlElement(name = "empName")
	private String name;

	@JsonProperty(value = "employee_Id")
	@XmlElement(name = "employeeId")
	private String empid;

	@JsonProperty(value = "organization_Name")
	@XmlElement(name = "organizationName")
	private String company;

	@JsonProperty(value = "emp_Mobile")
	@XmlElement(name = "empMobile")
	private int phoneNumber;

	@JsonProperty(value = "emp_Department")
	@XmlElement(name = "empDepartment")
	private String department;

	@JsonProperty(value = "emp_Address")
	@XmlElement(name = "empAddress")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XmlAddress> address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<XmlAddress> getAddress() {
		return address;
	}

	public void setAddress(List<XmlAddress> address) {
		this.address = address;
	}

}

class XmlAddress {

	@JsonProperty(value = "emp_Door_No")
	@XmlElement(name = "empDoorNo")
	private String doorNo;

	@JsonProperty(value = "emp_Street")
	@XmlElement(name = "empStreet")
	private String street;

	@JsonProperty(value = "emp_Area")
	@XmlElement(name = "empArea")
	private String area;

	@JsonProperty(value = "emp_City")
	@XmlElement(name = "empCity")
	private String city;

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
