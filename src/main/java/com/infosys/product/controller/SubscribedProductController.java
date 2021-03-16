package com.infosys.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.infosys.product.exceptions.SubscribbedException;
import com.infosys.product.model.SubscribedProductModel;
import com.infosys.product.service.ISubscribedProductService;

@RestController
@RequestMapping(value="/api/subscriptions")
public class SubscribedProductController {
	
	@Autowired
	ISubscribedProductService subProduct;
	
	@Value("${buyerURI}")
	String buyerURI;
	
	private Logger log=LoggerFactory.getLogger(this.getClass());
	
	@PostMapping(value="/add")
	public String addSubscription(@RequestBody SubscribedProductModel subProductModel) {
				
		Boolean isPriviledged=new RestTemplate().getForObject(buyerURI+subProductModel.getBuyerId(), Boolean.class);
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
	
	@GetMapping(value="/view/{buyerId}")
	public List<SubscribedProductModel> viewSubscription(@PathVariable Long buyerId){
		
		try {
			List<SubscribedProductModel>list=subProduct.viewSubsciptions(buyerId);
			return list;
		}
		catch(SubscribbedException se) {
			log.error(se.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, se.getMessage(), se);
		}
	}

}
