package com.spring.rest.multipart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fileuploadpdfmodel")
public class FileUpload_PdfModel {

	private String name;

	@Id
	private String empid;

	private String location;
	private long productid;
	private String orderid;
	private long invoiceno;
	private long phone;
	private String address;
	private String productname;
	private double price;
	private String discount;
	private String gst;
	private double total;
	private String modeofpayment;

	public FileUpload_PdfModel() {

	}

	public FileUpload_PdfModel(String name, String empid, String location, long productid, String orderid,
			long invoiceno, long phone, String address, String productname, double price, String discount, String gst,
			double total, String modeofpayment) {

		this.name = name;
		this.empid = empid;
		this.location = location;
		this.productid = productid;
		this.orderid = orderid;
		this.invoiceno = invoiceno;
		this.phone = phone;
		this.address = address;
		this.productname = productname;
		this.price = price;
		this.discount = discount;
		this.gst = gst;
		this.total = total;
		this.modeofpayment = modeofpayment;
	}

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public long getInvoiceno() {
		return invoiceno;
	}

	public void setInvoiceno(long invoiceno) {
		this.invoiceno = invoiceno;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getModeofpayment() {
		return modeofpayment;
	}

	public void setModeofpayment(String modeofpayment) {
		this.modeofpayment = modeofpayment;
	}

}
