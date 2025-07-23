package com.spring.rest.multipart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fileuploadjsonmodel")
public class FileUploadJsonModel {

	@Id
	protected long productid;

	protected String name;
	protected String processor;
	protected int ram;
	protected String color;
	protected double price;
	protected long filegrpid;

	public FileUploadJsonModel() {

	}

	public FileUploadJsonModel(long productid, String name, String processor, int ram, String color, double price,
			long filegrpid) {

		this.productid = productid;
		this.name = name;
		this.processor = processor;
		this.ram = ram;
		this.color = color;
		this.price = price;
		this.filegrpid = filegrpid;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getFilegrpid() {
		return filegrpid;
	}

	public void setFilegrpid(long filegrpid) {
		this.filegrpid = filegrpid;
	}

}
