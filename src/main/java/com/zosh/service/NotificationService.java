package com.zosh.service;

import com.zosh.model.Order;
import com.zosh.model.Restaurant;
import com.zosh.model.User;

public interface NotificationService {
	
	public void sendOrderStatusNotification(User user, Order order);
	public void sendRestaurantNotification(Restaurant restaurant, String message);
	public void sendPromotionalNotification(User user, String message);

}
