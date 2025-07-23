package com.spring.rest.multipart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fileuploadimgmodel")
public class FileUpload_ImageModel {

	@Id
	private String ID;
	private String Name;
	private String Gender;
	private long Mobile;
	private String Address;
	private String Bank;
	private String Type;
	private String Company;
	private String Signature;
	private String place;

	public FileUpload_ImageModel() {

	}

	public FileUpload_ImageModel(String iD, String name, String gender, long mobile, String address, String bank,
			String type, String company, String signature, String place) {

		ID = iD;
		Name = name;
		Gender = gender;
		Mobile = mobile;
		Address = address;
		Bank = bank;
		Type = type;
		Company = company;
		Signature = signature;
		this.place = place;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public long getMobile() {
		return Mobile;
	}

	public void setMobile(long mobile) {
		Mobile = mobile;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getBank() {
		return Bank;
	}

	public void setBank(String bank) {
		Bank = bank;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}

	public String getSignature() {
		return Signature;
	}

	public void setSignature(String signature) {
		Signature = signature;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

}
