package com.spring.rest.jpa.crud.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class TechProduct {
	@Id
	private long mId;
	private String mobileName;
	private String mobileBrand;
	private int ram;
	private int rom;
	private double price;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "techProduct")
//	@JsonManagedReference
	private List<MobileModel> mobileModels;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(referencedColumnName = "productId", name = "p_id")
	@JsonIgnore
	public Product product;

	
	
	public TechProduct() {
	}

	
	
	public TechProduct(long mId, String mobileName, String mobileBrand, int ram, int rom, double price,
			List<MobileModel> mobileModels, Product product) {
		this.mId = mId;
		this.mobileName = mobileName;
		this.mobileBrand = mobileBrand;
		this.ram = ram;
		this.rom = rom;
		this.price = price;
		this.mobileModels = mobileModels;
		this.product = product;
	}

	public long getmId() {
		return mId;
	}

	public void setmId(long mId) {
		this.mId = mId;
	}

	public String getMobileName() {
		return mobileName;
	}

	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}

	public String getMobileBrand() {
		return mobileBrand;
	}

	public void setMobileBrand(String mobileBrand) {
		this.mobileBrand = mobileBrand;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public int getRom() {
		return rom;
	}

	public void setRom(int rom) {
		this.rom = rom;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<MobileModel> getMobileModels() {
		return mobileModels;
	}

	public void setMobileModels(List<MobileModel> mobileModels) {
		this.mobileModels = mobileModels;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
