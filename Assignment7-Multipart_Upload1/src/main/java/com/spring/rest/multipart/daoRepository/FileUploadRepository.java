package com.spring.rest.multipart.daoRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.multipart.model.FileUploadModel;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUploadModel, Long> {

}
