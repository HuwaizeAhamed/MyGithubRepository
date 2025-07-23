package com.spring.rest.multipart.repositorydao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.multipart.model.FileUpload_CsvModel;

@Repository
public interface FileUpload2CsvRepository extends JpaRepository<FileUpload_CsvModel, Long> {

}
