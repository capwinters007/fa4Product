package com.infosys.product.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.product.entity.SubscribedProduct;
import com.infosys.product.exceptions.SubscribbedException;
import com.infosys.product.model.SubscribedProductModel;
import com.infosys.product.repository.ISubscribedProductRepository;

@Service
public class SubscribedProductService implements ISubscribedProductService {
	
	@Autowired
	private ISubscribedProductRepository subscribeRepo;
	
	private Logger log=LoggerFactory.getLogger(this.getClass());	
	
	@Override
	public void subscribe(SubscribedProductModel subscribeModel) {
		
		log.info("Subscribing to product of ID: "+subscribeModel.getProdId()+" by Buyer: "+subscribeModel.getBuyerId());
		SubscribedProduct subscribeProduct=subscribeModel.toEntity();
		subscribeRepo.save(subscribeProduct);
		log.info("Subscribed");
	}
	
	@Override
	public void remove(Long id) throws SubscribbedException{
		
		log.info("Checking subscription if exists for ID: "+id);
		
		Optional<SubscribedProduct>subProductOpt=subscribeRepo.findById(id);
		if(!subProductOpt.isPresent())
			throw new SubscribbedException("No Subscription for such ID exists");
		
		log.info("Deleting subscribed product of ID: "+id);
		
		subscribeRepo.deleteById(id);
		
		log.info("Deleted");
		
		
	}

}
