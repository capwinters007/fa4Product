package com.infosys.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.product.entity.SubscribedProduct;

@Repository
public interface ISubscribedProductRepository extends JpaRepository<SubscribedProduct, Long> {
	
	public List<SubscribedProduct> findAllByBuyerId(Long buyerId);

}
