package com.spring.crud.assign5.hibernate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Address {

	private String doorNo;
	private String street;
	private String area;
	private String district;
	@Id
	private long phone;
	private String city;
	private int pinCode;

	public Address(String doorNo, String street, String area, String district, long phone, String city, int pinCode,
			HibernateModel hibernateModel) {

		this.doorNo = doorNo;
		this.street = street;
		this.area = area;
		this.district = district;
		this.phone = phone;
		this.city = city;
		this.pinCode = pinCode;
	}

	public Address() {
	}

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

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

}
