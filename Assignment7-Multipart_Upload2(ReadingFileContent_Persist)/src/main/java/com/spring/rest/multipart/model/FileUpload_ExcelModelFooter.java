package com.spring.rest.multipart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "companylists")
public class FileUpload_ExcelModelFooter {

	@Id
	private String companylists;
	private double companycount;

	public FileUpload_ExcelModelFooter() {

	}

	public FileUpload_ExcelModelFooter(String companylists, double companycount) {

		this.companylists = companylists;
		this.companycount = companycount;
	}

	public String getCompanylists() {
		return companylists;
	}

	public void setCompanylists(String companylists) {
		this.companylists = companylists;
	}

	public double getCompanycount() {
		return companycount;
	}

	public void setCompanycount(double companycount) {
		this.companycount = companycount;
	}

}
