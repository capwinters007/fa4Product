package com.infosys.product;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.infosys.product.entity.Product;
import com.infosys.product.model.ProductModel;
import com.infosys.product.repository.IProductRepository;
import com.infosys.product.service.IProductService;

@SpringBootTest
public class ProductServiceTest {
	
	@Autowired
	IProductService productService;
	
	@MockBean
	IProductRepository productRepo;
	
	ProductModel productModel=new ProductModel(1L, "Alisha","Clothing", "Key Features of Alisha Solid Women's Cycling Shorts Cotton Lycra Navy, Red, Navy,Specifications of Alisha Solid Women's Cycling Shorts Shorts",
			"\"http://img5a.flixcart.com/image/short/u/4/a/altht-3p-21-alisha-38-original-imaeh2d5vm5zbtgg.jpeg\"",
			(float) 500.0, "Alisha Solid Women's Cycling Shorts", null, 7L, 46L, "Women's Clothing");
	
	List<Product>list=new ArrayList<>();
	List<Product>list2=new ArrayList<>();
	
	@SuppressWarnings("deprecation")
	@Test
	public void addProductTest() {
		
	Mockito.when(productRepo.save(Mockito.anyObject())).thenReturn(null);
	String message;
	
	try {
		productService.addProduct(productModel);
		message="Verified";
	}
	catch(Exception e) {
		message=e.getMessage();
	}
	
	assertEquals("Verified", message);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void addProductTestN() {
		
		Mockito.when(productRepo.save(Mockito.anyObject())).thenReturn(null);
		String message;
		productModel.setProductName("Alisha Solid Women's Cycling Shorts ");
		try {
			productService.addProduct(productModel);
			message="Verified";
		}
		catch(Exception e) {
			message=e.getMessage();
		}
		System.out.println(message);
		assertEquals("Invalid product name!! Should have only characters, apostrophe sign and spaces between words.", message);
	}
	
	@Test
	public void getById() {
		
		Mockito.when(productRepo.findById(1L)).thenReturn(Optional.of(productModel.toEntity()));
		String message;
		try {
			message=productService.getById(1L).getProductName();
		}
		catch(Exception e) {
			message=e.getMessage();
		}
		assertEquals("Alisha Solid Women's Cycling Shorts", message);
	}
	
	@Test
	public void getByIdN() {
		
		Mockito.when(productRepo.findById(1L)).thenReturn(Optional.empty());
		String message;
		try {
			message=productService.getById(1L).getProductName();
		}
		catch(Exception e) {
			message=e.getMessage();
		}
		System.out.println(message);
		assertEquals("No record found matching product ID: "+1, message);
	}
	
	@Test
	public void getByName() {
		
		list2.add(productModel.toEntity());
		Mockito.when(productRepo.findAllByProductName("Alisha Solid Women's Cycling Shorts")).thenReturn(list2);
		String message;
		try {
			message=productService.getByProductName("Alisha Solid Women's Cycling Shorts").get(0).getProductName();
		}
		catch(Exception e) {
			message=e.getMessage();
		}
		assertEquals("Alisha Solid Women's Cycling Shorts", message);
	}
	
	@Test
	public void getByNameN() {
		
		Mockito.when(productRepo.findAllByProductName("Alisha Solid Women's Cycling Shorts")).thenReturn(list);
		String message;
		try {
			message=productService.getByProductName("Alisha Solid Women's Cycling Shorts").get(0).getProductName();
		}
		catch(Exception e) {
			message=e.getMessage();
		}
		assertEquals("No record found matching Product Name: "+"Alisha Solid Women's Cycling Shorts", message);
	}
	
	@Test
	public void getByCategory() {
		
		list2.add(productModel.toEntity());
		Mockito.when(productRepo.findAllByCategory("clothing")).thenReturn(list2);
		String message;
		try {
			message=productService.getByCategory("clothing").get(0).getProductName();
		}
		catch(Exception e) {
			message=e.getMessage();
		}
		System.out.println(message);
		assertEquals("Alisha Solid Women's Cycling Shorts", message);
	}
	
	@Test
	public void getByCategoryN() {
		
		Mockito.when(productRepo.findAllByCategory("clothing")).thenReturn(list);
		String message;
		try {
			message=productService.getByCategory("clothing").get(0).getProductName();
		}
		catch(Exception e) {
			message=e.getMessage();
		}
		assertEquals("No record found matching Product Category: "+"clothing", message);
	}
	
	@Test
	public void updateTest() {
		
		Mockito.when(productRepo.findById(1L)).thenReturn(Optional.of(productModel.toEntity()));
		Mockito.when(productRepo.save(productModel.toEntity())).thenReturn(null);
		String message;
		try {
			productService.updateStock(1L, 2L);
			message="updated";
		}
		catch(Exception e) {
			message=e.getMessage();
		}
		
		assertEquals("updated", message);
	}
	
	@Test
	public void updateTestN() {
		
		productModel.setStock(10L);
		Mockito.when(productRepo.findById(1L)).thenReturn(Optional.of(productModel.toEntity()));
		Mockito.when(productRepo.save(productModel.toEntity())).thenReturn(null);
		String message;
		try {
			productService.updateStock(1L, 2L);
			message="updated";
		}
		catch(Exception e) {
			message=e.getMessage();
		}
		
		assertEquals("Invalid Stock!! Should be greater than or equal to 10.", message);
	}
	
	@Test
	public void deleteTest() {
		
		Mockito.when(productRepo.findById(1L)).thenReturn(Optional.of(productModel.toEntity()));
		String message;
		try {
			productService.removeStock(1L);
			message="deleted";
		}
		catch(Exception e) {
			message=e.getMessage();
		}
		
		assertEquals("deleted", message);
	}
	
	@Test
	public void deleteTestN() {
		
		Mockito.when(productRepo.findById(1L)).thenReturn(Optional.empty());
		String message;
		try {
			productService.removeStock(1L);
			message="deleted";
		}
		catch(Exception e) {
			message=e.getMessage();
		}
		
		assertEquals("No record found matching ID: "+1, message);
	}

}
