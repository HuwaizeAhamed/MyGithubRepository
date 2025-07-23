package com.spring.rest.jpa.crud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MobileModel {
	@Id
	private long modelId;
	private String cameraQuality;
	private String modelName;
	private String gamingPerformance;
	private String chipSet;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(referencedColumnName = "mId", name = "tech_mid")
	@JsonIgnore
	private TechProduct techProduct;

	@ManyToOne
	@JoinColumn(name = "product_pid")
	@JsonIgnore
	private Product product;
	
	
	

	public MobileModel() {
	}

	
	
	public MobileModel(long modelId, String cameraQuality, String modelName, String gamingPerformance, String chipSet,
			TechProduct techProduct, Product product) {
		this.modelId = modelId;
		this.cameraQuality = cameraQuality;
		this.modelName = modelName;
		this.gamingPerformance = gamingPerformance;
		this.chipSet = chipSet;
		this.techProduct = techProduct;
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public long getModelId() {
		return modelId;
	}

	public void setModelId(long modelId) {
		this.modelId = modelId;
	}

	public String getCameraQuality() {
		return cameraQuality;
	}

	public void setCameraQuality(String cameraQuality) {
		this.cameraQuality = cameraQuality;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getGamingPerformance() {
		return gamingPerformance;
	}

	public void setGamingPerformance(String gamingPerformance) {
		this.gamingPerformance = gamingPerformance;
	}

	public String getChipSet() {
		return chipSet;
	}

	public void setChipSet(String chipSet) {
		this.chipSet = chipSet;
	}

	public TechProduct getTechProduct() {
		return techProduct;
	}

	public void setTechProduct(TechProduct techProduct) {
		this.techProduct = techProduct;
	}

}
