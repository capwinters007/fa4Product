package com.infosys.product.service;

import java.util.List;

import com.infosys.product.exceptions.SubscribbedException;
import com.infosys.product.model.SubscribedProductModel;

public interface ISubscribedProductService {
	
	public void subscribe(SubscribedProductModel subscribeModel);
	public void remove(Long id) throws SubscribbedException;
	public List<SubscribedProductModel> viewSubsciptions(Long buyerId) throws SubscribbedException;

}
