package com.spring.rest.multipart.model;

import org.springframework.context.annotation.Configuration;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Configuration
@Entity
@Table(name = "fileUpload1")
public class FileUploadModel {

	@Id
	private long fileId;

	@Lob
	private byte[] bytes;
	private String contentType;
//	private InputStream input;
	private String name;
	private String fileName;
//	private Resource resource;
	private long fileSize;

	public FileUploadModel() {

	}

	public FileUploadModel(String name) {
		this.name = name;
	}

	public FileUploadModel(long file_Id, byte[] bytes, String contentType, String name, String fileName, long size) {
		super();
		this.fileId = file_Id;
		this.bytes = bytes;
		this.contentType = contentType;
		this.name = name;
		this.fileName = fileName;
		this.fileSize = size;
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getSize() {
		return fileSize;
	}

	public void setSize(long size) {
		this.fileSize = size;
	}

}
