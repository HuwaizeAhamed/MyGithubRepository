package com.spring.rest.jpa.crud.model;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Product {
	@Id
	private long productId;
	private String chocolates;
	private String juice;
	private String cheese;
	private String panner;
	private String iceCream;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<TechProduct> techProducts;

	@Transient
	private String message;
//	@Transient
//	private List<Long> modelId;
	
	
	

	public Product() {
	}
	
	public Product(String message) {
		this.message=message;
	}
	

	public Product(long productId, String chocolates, String juice, String cheese, String panner, String iceCream,
			List<TechProduct> techProducts) {
		this.productId = productId;
		this.chocolates = chocolates;
		this.juice = juice;
		this.cheese = cheese;
		this.panner = panner;
		this.iceCream = iceCream;
		this.techProducts = techProducts;
	}

//	public List<Long> getmId() {
//		return mId;
//	}
//
//	public void setmId(List<Long> mId) {
//		this.mId = mId;
//	}
//
//	public List<Long> getModelId() {
//		return modelId;
//	}
//
//	public void setModelId(List<Long> modelId) {
//		this.modelId = modelId;
//	}
	
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getProductId() {
		return productId;
	}


	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getChocolates() {
		return chocolates;
	}

	public void setChocolates(String chocolates) {
		this.chocolates = chocolates;
	}

	public String getJuice() {
		return juice;
	}

	public void setJuice(String juice) {
		this.juice = juice;
	}

	public String getCheese() {
		return cheese;
	}

	public void setCheese(String cheese) {
		this.cheese = cheese;
	}

	public String getPanner() {
		return panner;
	}

	public void setPanner(String panner) {
		this.panner = panner;
	}

	public String getIceCream() {
		return iceCream;
	}

	public void setIceCream(String iceCream) {
		this.iceCream = iceCream;
	}

	public List<TechProduct> getTechProducts() {
		return techProducts;
	}

	public void setTechProducts(List<TechProduct> techProducts) {
		this.techProducts = techProducts;
	}

}
