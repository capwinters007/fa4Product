package com.infosys.product.service;

import com.infosys.product.exceptions.SubscribbedException;
import com.infosys.product.model.SubscribedProductModel;

public interface ISubscribedProductService {
	
	public void subscribe(SubscribedProductModel subscribeModel);
	public void remove(Long id) throws SubscribbedException;

}
