package com.spring.rest.multipart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fileuploadcsvmodel")
public class FileUpload_CsvModel {

	@Id
	private long sid;
	private String bank_name;
	private String bank_acc_no;
	private String acc_type;
	private String bank_location;
	private String company;

	public FileUpload_CsvModel() {

	}

	public FileUpload_CsvModel(long sid, String bank_name, String bank_acc_no, String acc_type, String bank_location,
			String company) {

		this.sid = sid;
		this.bank_name = bank_name;
		this.bank_acc_no = bank_acc_no;
		this.acc_type = acc_type;
		this.bank_location = bank_location;
		this.company = company;
	}

	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_acc_no() {
		return bank_acc_no;
	}

	public void setBank_acc_no(String bank_acc_no) {
		this.bank_acc_no = bank_acc_no;
	}

	public String getAcc_type() {
		return acc_type;
	}

	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}

	public String getBank_location() {
		return bank_location;
	}

	public void setBank_location(String bank_location) {
		this.bank_location = bank_location;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
