package com.spring.rest.jpa.crud.daorepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.jpa.crud.model.TechProduct;

@Repository
public interface TechRepository extends JpaRepository<TechProduct, Long> {

}
