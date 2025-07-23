package com.spring.rest.multipart.repositorydao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.multipart.model.FileUploadJsonModel;

@Repository
public interface FileUpload2JsonRepository extends JpaRepository<FileUploadJsonModel, Long> {

//	@Modifying
//	@Transactional
//	@Query(value="INSERT INTO fileuploadjsonmodel (name,processor,ram,color,price) "+
//			 "VALUES(:Name,:Processor,:RAM,:Color,:Price)",nativeQuery = true)
//	void insertJsonData(@Param("Name") String name,@Param("Processor") String processor,@Param("RAM") 
//	int ram,@Param("Color") String color,@Param("Price") double price);

}
