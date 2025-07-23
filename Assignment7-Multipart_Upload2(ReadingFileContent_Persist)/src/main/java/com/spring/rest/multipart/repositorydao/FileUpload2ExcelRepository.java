package com.spring.rest.multipart.repositorydao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.multipart.model.FileUpload_ExcelModel;

@Repository
public interface FileUpload2ExcelRepository extends JpaRepository<FileUpload_ExcelModel, Double> {

}
