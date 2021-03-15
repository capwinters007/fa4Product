package com.infosys.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.product.entity.Product;


@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{
	
	public List<Product> findAllByProductName(String productName);
	
	public List<Product> findAllByCategory(String category);

}
