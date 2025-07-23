package com.spring.rest.multipart.repositorydao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.multipart.model.FileUpload_ExcelModelFooter;

@Repository
public interface FileUpload2ExcelFooterRepository extends JpaRepository<FileUpload_ExcelModelFooter, String> {

}
