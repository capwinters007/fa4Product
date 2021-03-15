package com.infosys.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.product.entity.Product;
import com.infosys.product.exceptions.ProductException;
import com.infosys.product.model.ProductModel;
import com.infosys.product.repository.IProductRepository;
import com.infosys.product.validator.ProductValidator;

@Service
public class ProductService implements IProductService{
	
	@Autowired
	private IProductRepository productRepo;
	
	private Logger log=LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void addProduct(ProductModel productModel) throws ProductException{
		
		Product product=productModel.toEntity();
		
		log.info("Adding product of product ID: "+product.getProductId());
		
		ProductValidator.validateProduct(ProductModel.toModel(product));
		productRepo.save(product);
		
		log.info("Product added successfully");
	}
	
	@Override
	public List<ProductModel> getAllProducts() throws ProductException{
		
		List<Product>list=productRepo.findAll();
		
		if(list.isEmpty())
			throw new ProductException("No product record exists in the database");
		else {
			List<ProductModel>modelList=new ArrayList<>();
			
			log.info("Fetching all product list...");
			
			for(Product product:list) {
				modelList.add(ProductModel.toModel(product));
			}
			return modelList;
		}
		
	}
	
	@Override
	public ProductModel getById(Long id) throws ProductException{
		
		log.info("Fetching product of ID:"+id);
		
		Optional<Product>productOpt=productRepo.findById(id);
		
		if(productOpt.isPresent())
			return ProductModel.toModel(productOpt.get());
		else
			throw new ProductException("No record found matching product ID: "+id);
		
	}
	
	@Override
	public List<ProductModel> getByProductName(String productName) throws ProductException{
		
		log.info("Fetching product of name: "+productName);
		
		List<Product>list=productRepo.findAllByProductName(productName);
		List<ProductModel>list2=new ArrayList<>();
		
		if(!list.isEmpty()) {
			
			for(Product product:list)
				list2.add(ProductModel.toModel(product));
			
			return list2;
		}
		else
			throw new ProductException("No record found matching Product Name: "+productName);
	}
	
	@Override
	public List<ProductModel> getByCategory(String category) throws ProductException{
		
		log.info("Fetching product of category: "+category);
		
		List<Product>list=productRepo.findAllByCategory(category);
		List<ProductModel>list2=new ArrayList<>();
		
		if(!list.isEmpty()) {
			
			for(Product product:list)
				list2.add(ProductModel.toModel(product));
			
			return list2;
		}
		else
			throw new ProductException("No record found matching Product Category: "+category);
	}
	
	@Override
	public void updateStock(Long id,Long stock) throws ProductException{
		
		log.info("Checking if product exists...");
		
		Optional<Product>productOpt=productRepo.findById(id);
		
		if(productOpt.isPresent()) {
			
			log.info("Updating...");
			
			Product product=productOpt.get();
			product.setStock(product.getStock()-stock);
			
			ProductValidator.validateProduct(ProductModel.toModel(product));
			productRepo.save(product);
			
			log.info("Updated record of ID: "+id);
		}
		else
			throw new ProductException("No such record of ID "+id+" exists!!");
	}
	
	@Override
	public void removeStock(Long id) throws ProductException{
		
		log.info("Checking if product exists...");
		
		Optional<Product>productOpt=productRepo.findById(id);
		
		if(productOpt.isPresent()) {
			log.info("Deleting...");
			
			productRepo.deleteById(id);
			
			log.info("Deleted record of ID: "+id);
		}
		else
			throw new ProductException("No record found matching ID: "+id);
	}

}
