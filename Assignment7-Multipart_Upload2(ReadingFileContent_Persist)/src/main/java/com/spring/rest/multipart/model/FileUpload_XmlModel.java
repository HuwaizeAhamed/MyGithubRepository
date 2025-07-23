package com.spring.rest.multipart.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fileuploadxmlmodel")
@JacksonXmlRootElement(localName = "CoffeeShop")
public class FileUpload_XmlModel {

	@Id
	private String name;
	private String location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public FileUpload_XmlModel() {

	}

	public FileUpload_XmlModel(String name, String location) {

		this.name = name;
		this.location = location;
	}

	@Override
	public String toString() {
		return "FileUpload_XmlModel [name=" + name + ", location=" + location + "]";
	}

}
