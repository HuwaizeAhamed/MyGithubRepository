package com.spring.crud.assign5.hibernate.model;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "hibernatecrud1")
public class HibernateModel {

	@Id
	private int empId;
	private String name;
	private int age;
	private String company;
	private String aadhaarCard;
	private String panCard;
	private String experience;
	private String designation;
	private double salary;
	private String bankName;
	private String bankAccountNo;
	private String ifscCode;
	private String branch;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(referencedColumnName = "empId")
	@JacksonXmlElementWrapper(useWrapping = false)
	@Embedded
	public List<Address> address;

	public HibernateModel() {

	}
	
	public HibernateModel(HibernateModel model) {
		model=model;

	}

	public HibernateModel(int empId, String name, int age, String company, String aadhaarCard, String panCard,
			String experience, String designation, double salary, String bankName, String bankAccountNo,
			String ifscCode, String branch) {
		this.empId = empId;
		this.name = name;
		this.age = age;
		this.company = company;
		this.aadhaarCard = aadhaarCard;
		this.panCard = panCard;
		this.experience = experience;
		this.designation = designation;
		this.salary = salary;
		this.bankName = bankName;
		this.bankAccountNo = bankAccountNo;
		this.ifscCode = ifscCode;
		this.branch = branch;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAadhaarCard() {
		return aadhaarCard;
	}

	public void setAadhaarCard(String aadhaarCard) {
		this.aadhaarCard = aadhaarCard;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

}
