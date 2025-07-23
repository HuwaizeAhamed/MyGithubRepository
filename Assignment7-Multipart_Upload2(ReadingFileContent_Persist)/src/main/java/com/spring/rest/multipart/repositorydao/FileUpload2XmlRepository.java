package com.spring.rest.multipart.repositorydao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.multipart.model.FileUpload_XmlModel;

@Repository
public interface FileUpload2XmlRepository extends JpaRepository<FileUpload_XmlModel, String> {

}
