package com.infosys.product.validator;

import com.infosys.product.exceptions.ProductException;
import com.infosys.product.model.ProductModel;

public class ProductValidator {
	
	public static String validate(ProductModel productModel) {
		
		String nameRegex="([A-za-z\\']+([\\s][A-Za-z\\'])*){1,100}";
		String descRegex=".{1,500}";
		String imageRegex=".*(png\"|jpeg\")";
		
		if(!productModel.getProductName().matches(nameRegex))
			return "Invalid product name!! Should have only characters, apostrophe sign and spaces between words.";
		if(!productModel.getDescription().matches(descRegex))
			return "Invalid Description!! Should not be more than 500 characters.";
		if(!(productModel.getPrice()>=200))
			return "Invalid price!! Should be greater than or equal to 200.";
		if(!(productModel.getStock()>=10))
			return "Invalid Stock!! Should be greater than or equal to 10.";
		if(!productModel.getImage().matches(imageRegex))
			return "Invalid image extension "+productModel.getImage()+"!! Should be in .png or .jpg.";
		return "valid";
	}
	
	public static void validateProduct(ProductModel productModel) throws ProductException{
		
		String validateReturn=validate(productModel);
		
		if(!validateReturn.equals("valid"))
			throw new ProductException(validateReturn);	
	}

}
