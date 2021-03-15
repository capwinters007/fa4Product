package com.infosys.product.service;

import java.util.List;

import com.infosys.product.exceptions.ProductException;
import com.infosys.product.model.ProductModel;

public interface IProductService {
	
	public void addProduct(ProductModel productModel) throws ProductException;
	public List<ProductModel> getAllProducts() throws ProductException;
	public ProductModel getById(Long id) throws ProductException;
	public List<ProductModel> getByProductName(String productName) throws ProductException;
	public List<ProductModel> getByCategory(String category) throws ProductException;
	public void updateStock(Long id,Long stock) throws ProductException;
	public void removeStock(Long id) throws ProductException;

}
