package com.infosys.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infosys.product.model.ProductModel;
import com.infosys.product.service.IProductService;

@RestController
@RequestMapping(value="/api/product")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	private Logger log=LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value="/")
	public ResponseEntity<List<ProductModel>> getAllProducts(){
		
		try {
			return new ResponseEntity<List<ProductModel>>(productService.getAllProducts(), HttpStatus.OK);
		}
		catch(Exception e) {
			
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
		
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ProductModel> getById(@PathVariable Long id){
		
		try {
			return new ResponseEntity<ProductModel>(productService.getById(id), HttpStatus.OK);
		}
		catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
		
	}
	
	@GetMapping(value="productname/{productName}")
	public ResponseEntity<List<ProductModel>> getByName(@PathVariable String productName){
		
		try {
			return new ResponseEntity<>(productService.getByProductName(productName), HttpStatus.OK);
		}
		catch(Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	@GetMapping(value="category/{category}")
	public ResponseEntity<List<ProductModel>> getByCategory(@PathVariable String category){
		
		try {
			return new ResponseEntity<>(productService.getByCategory(category), HttpStatus.OK);
		}
		catch(Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	@GetMapping(value="/updateStock/{id}/{quantity}")
	public ResponseEntity<String> updateStock(@PathVariable Long id,@PathVariable Long quantity){
		
		try {
			productService.updateStock(id, quantity);
			return new ResponseEntity<String>("Stock Updated",HttpStatus.OK);
		}
		catch(Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	@PostMapping(value="/add")
	public ResponseEntity<String> addProduct(@RequestBody ProductModel productModel){
		
		try {
			productService.addProduct(productModel);
			return new ResponseEntity<String>("Product Added Successfully", HttpStatus.OK);
		}
		catch(Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<String> removeProduct(@PathVariable Long id){
		
		try {
			productService.removeStock(id);
			return new ResponseEntity<String>("Product deleted successfully", HttpStatus.OK);
		}
		catch(Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
		}
	}
	
}

