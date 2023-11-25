package com.zosh.request;

import com.zosh.model.Address;

import lombok.Data;

@Data
public class CreateOrderRequest {
 
	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	private Long restaurantId;
	
	private Address deliveryAddress;
	
    
}
