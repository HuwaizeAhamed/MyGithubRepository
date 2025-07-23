package com.spring.rest.assign2.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

//import jakarta.xml.bind.annotation.XmlElement;

@JacksonXmlRootElement(localName="Xml_Root_Response")
public class assignment2RequestModel {

	@JsonProperty(value="emp_Name")
	@JacksonXmlProperty(localName = "empName")
	private String emp_Name;

	@JsonProperty(value="employee_Id")
	@JacksonXmlProperty(localName = "employeeId")
	private String employee_Id;

	@JsonProperty(value="organization_Name")
	@JacksonXmlProperty(localName = "organizationName")
	private String organization_Name;

	@JsonProperty(value="emp_Mobile")
	@JacksonXmlProperty(localName = "empMobile")
	private int emp_Mobile;

	@JsonProperty(value="emp_Department")
	@JacksonXmlProperty(localName = "empDepartment")
	private String emp_Department;

	@JsonProperty(value="emp_Address")
	@JacksonXmlProperty(localName = "empAddress")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Address> emp_Address;

	public String getEmp_Name() {
		return emp_Name;
	}

	public void setEmp_Name(String emp_Name) {
		this.emp_Name = emp_Name;
	}

	public String getEmployee_Id() {
		return employee_Id;
	}

	public void setEmployee_Id(String employee_Id) {
		this.employee_Id = employee_Id;
	}

	public String getOrganization_Name() {
		return organization_Name;
	}

	public void setOrganization_Name(String organization_Name) {
		this.organization_Name = organization_Name;
	}

	public int getEmp_Mobile() {
		return emp_Mobile;
	}

	public void setEmp_Mobile(int emp_Mobile) {
		this.emp_Mobile = emp_Mobile;
	}

	public String getEmp_Department() {
		return emp_Department;
	}

	public void setEmp_Department(String emp_Department) {
		this.emp_Department = emp_Department;
	}

	public List<Address> getEmp_Address() {
		return emp_Address;
	}

	public void setEmp_Address(List<Address> emp_Address) {
		this.emp_Address = emp_Address;
	}

	

}

class Address {

	@JsonProperty(value="emp_Door_No")
	@JacksonXmlProperty(localName = "empDoorNo")
	private String emp_Door_No;

	@JsonProperty(value="emp_Street")
	@JacksonXmlProperty(localName = "empStreet")
	private String emp_Street;

	@JsonProperty(value="emp_Area")
	@JacksonXmlProperty(localName = "empArea")
	private String emp_Area;

	@JsonProperty(value="emp_City")
	@JacksonXmlProperty(localName = "empCity")
	private String emp_City;

	public String getEmp_Door_No() {
		return emp_Door_No;
	}

	public void setEmp_Door_No(String emp_Door_No) {
		this.emp_Door_No = emp_Door_No;
	}

	public String getEmp_Street() {
		return emp_Street;
	}

	public void setEmp_Street(String emp_Street) {
		this.emp_Street = emp_Street;
	}

	public String getEmp_Area() {
		return emp_Area;
	}

	public void setEmp_Area(String emp_Area) {
		this.emp_Area = emp_Area;
	}

	public String getEmp_City() {
		return emp_City;
	}

	public void setEmp_City(String emp_City) {
		this.emp_City = emp_City;
	}

	
	
}
