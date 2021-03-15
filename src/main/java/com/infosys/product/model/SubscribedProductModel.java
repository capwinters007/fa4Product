package com.infosys.product.model;

import com.infosys.product.entity.SubscribedProduct;

public class SubscribedProductModel {
	
	private Long subId;
	private Long buyerId;
	private Long prodId;
	private Long quantity;
	
	public SubscribedProductModel() {
		super();
	}

	public SubscribedProductModel(long subId, Long buyerId, Long prodId, Long quantity) {
		super();
		this.subId = subId;
		this.buyerId = buyerId;
		this.prodId = prodId;
		this.quantity = quantity;
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "SubscribedProductModel [subId=" + subId + ", buyerId=" + buyerId + ", prodId=" + prodId + ", quantity="
				+ quantity + "]";
	}
	
	//convert model to entity
	public SubscribedProduct toEntity() {
		
		SubscribedProduct subscribe=new SubscribedProduct();
		
		subscribe.setSubId(this.getSubId());
		subscribe.setBuyerId(this.getBuyerId());
		subscribe.setProdId(this.getProdId());
		subscribe.setQuantity(this.getQuantity());
		
		return subscribe;
	}
	
	//convert entity to model
	public static SubscribedProductModel toModel(SubscribedProduct subscribe) {
		
		SubscribedProductModel subscribeModel=new SubscribedProductModel();
		
		subscribeModel.setSubId(subscribe.getSubId());
		subscribeModel.setBuyerId(subscribe.getBuyerId());
		subscribeModel.setProdId(subscribe.getProdId());
		subscribeModel.setQuantity(subscribe.getQuantity());
		
		return subscribeModel;
	}

}
