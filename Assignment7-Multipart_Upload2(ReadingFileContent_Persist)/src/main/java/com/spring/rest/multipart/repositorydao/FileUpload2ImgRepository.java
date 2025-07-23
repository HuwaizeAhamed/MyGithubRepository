package com.spring.rest.multipart.repositorydao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.multipart.model.FileUpload_ImageModel;

@Repository
public interface FileUpload2ImgRepository extends JpaRepository<FileUpload_ImageModel, String> {

}
