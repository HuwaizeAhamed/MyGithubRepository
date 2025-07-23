package com.example.test.spring.demo.Model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xmlRoot")
public class xmlRoot {
	
	String message;
	String description;
	
	public xmlRoot(){
		
	}
	
	public xmlRoot(String msg,String description){
		this.message=msg;
		this.description=description;
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		message = msg;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String desc) {
		description = desc;
	}

	 
}
