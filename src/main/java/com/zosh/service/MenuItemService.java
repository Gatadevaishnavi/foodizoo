package com.zosh.service;

import java.util.List;

import com.zosh.Exception.MenuItemException;
import com.zosh.Exception.RestaurantException;
import com.zosh.model.Category;
import com.zosh.model.MenuItem;
import com.zosh.request.CreateMenuItemRequest;

public interface MenuItemService {

	public MenuItem createMenuItem(CreateMenuItemRequest req) throws MenuItemException, RestaurantException;

	public MenuItem updateMenuItem(Long itemId, MenuItem updatedMenuItem) throws MenuItemException;

	void deleteMenuItem(Long itemId);

	public List<MenuItem> getMenuItemsByCategory(Category category) throws MenuItemException;

	public List<MenuItem> getMenuItemsByCriteria(boolean isVegetarian, boolean isVegan, boolean isGlutenFree)
			throws MenuItemException;

	public MenuItem findMenuItemByName(String string) throws MenuItemException;
	
	public List<MenuItem> getRestaurantMenuItems(Long restaurantId) throws MenuItemException;
	
	public List<MenuItem> searchMenuItem(String keyword);

}
