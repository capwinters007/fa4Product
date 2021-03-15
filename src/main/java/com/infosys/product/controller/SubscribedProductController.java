package com.infosys.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.infosys.product.model.SubscribedProductModel;
import com.infosys.product.service.ISubscribedProductService;

@RestController
@RequestMapping(value="/api/subscriptions")
public class SubscribedProductController {
	
	@Autowired
	ISubscribedProductService subProduct;
	
	private Logger log=LoggerFactory.getLogger(this.getClass());
	
	@PostMapping(value="/add")
	public String addSubscription(@RequestBody SubscribedProductModel subProductModel) {
		
		Boolean isPriviledged=new RestTemplate().getForObject(
				"http://localhost:8000/buyer/privilege/"+subProductModel.getBuyerId(), Boolean.class);
		if(isPriviledged) {
			subProduct.subscribe(subProductModel);
			return "Subscribbed Successfully";
		}
		else
			return "Not a priviledged user!!";
	}
	
	@DeleteMapping(value="/remove/{id}")
	public String removeSubsription(@PathVariable Long id) {
		
		try {
			subProduct.remove(id);
			return "Subscription removed successfully";
		}
		catch(Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

}
