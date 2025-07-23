package com.spring.rest.jpa.crud.daorepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.jpa.crud.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
