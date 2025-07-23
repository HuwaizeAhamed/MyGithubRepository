package com.spring.rest.multipart.repositorydao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.multipart.model.FileUpload_PdfModel;

@Repository
public interface FileUpload2PdfRepository extends JpaRepository<FileUpload_PdfModel, String> {

}
