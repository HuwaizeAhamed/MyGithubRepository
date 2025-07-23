package com.spring.rest.multipart.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.rest.multipart.daoRepository.FileUploadRepository;
import com.spring.rest.multipart.exception.GenericException;
import com.spring.rest.multipart.model.FileUploadModel;

@Service
public class FileUploadService {

	@Autowired
	FileUploadModel fileuplmodel;
	@Autowired
	FileUploadRepository fileRepo;

	public FileUploadModel parseUploadedFile(MultipartFile file) {

		try {
			byte[] bytes = file.getBytes();
			String contentType = file.getContentType();
			String name = file.getName();
			String fileName = file.getOriginalFilename();
//			Resource resource=file.getResource();
			long size = file.getSize();
			long fileId = System.currentTimeMillis();
			String fileContent = new String(file.getBytes(), StandardCharsets.UTF_8);
//			System.out.println(fileContent);

			fileuplmodel = new FileUploadModel(fileId, bytes, contentType, name, fileName, size);
			
			fileRepo.save(fileuplmodel);

		} 
		
		catch (IOException e) {
//			System.out.println("Service Error caught " + e.getLocalizedMessage() + "  " + e.getMessage());
			
			fileuplmodel = new FileUploadModel("Error is service : file upload failure");

			e.printStackTrace();
			return fileuplmodel;
		}
		

		return fileuplmodel;
	}

}
