package com.spring.rest.multipart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fileuploadexcelmodel")
public class FileUpload_ExcelModel {

	@Id
	private double no;
	private String name;
	private String company;
	private double totalexperience;
	private double salary;

	public FileUpload_ExcelModel() {
	}

	public FileUpload_ExcelModel(double no, String name, String company, double totalexperience, double salary) {

		this.no = no;
		this.name = name;
		this.company = company;
		this.totalexperience = totalexperience;
		this.salary = salary;
	}

	public double getNo() {
		return no;
	}

	public void setNo(double no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getTotalexperience() {
		return totalexperience;
	}

	public void setTotalexperience(double totalexperience) {
		this.totalexperience = totalexperience;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
