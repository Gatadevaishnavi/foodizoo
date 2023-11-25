package com.zosh.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zosh.Exception.MenuItemException;
import com.zosh.Exception.RestaurantException;
import com.zosh.model.Category;
import com.zosh.model.MenuItem;
import com.zosh.model.Restaurant;
import com.zosh.repository.CategoryRepository;
import com.zosh.repository.MenuItemRepository;
import com.zosh.repository.RestaurantRepository;
import com.zosh.request.CreateMenuItemRequest;

@Service
public class MenuItemServiceImplementation implements MenuItemService {
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public MenuItem createMenuItem(CreateMenuItemRequest  req) throws MenuItemException, 
	RestaurantException {
		if(true)
//		return new MenuItem();
		if (req!=null) {

			Category newCategory =new Category();
			newCategory.setName(req.getCategory());
			Category savedCategory = categoryRepository.save(newCategory);
			System.out.println("Category object----"+newCategory);
			System.out.println("request object----"+req);
			
			MenuItem menuItem=new MenuItem();
			menuItem.setCategory(savedCategory);
			menuItem.setCreationDate(new Date());
			menuItem.setDescription(req.getDescription());
			menuItem.setGlutenFree(req.isGlutenFree());
			menuItem.setAvailabilityStatus(req.isAvailabilityStatus());
			menuItem.setImageUrl(req.getImageUrl());
			menuItem.setName(req.getName());
			menuItem.setPrice((long) req.getPrice());
			menuItem.setVegan(req.isVegan());
			menuItem.setVegetarian(req.isVegetarian());
			
			Long restaurantId=req.getRestaurantId();
			
			Optional<Restaurant> opt = restaurantRepository.findById(restaurantId);
//			System.out.println("Restaurant object--"+restaurantId);
			if(opt.isPresent()) {
				menuItem.setRestaurant(opt.get());
				
			}else {
				throw new RestaurantException("Restaurant not found with id "+restaurantId);
			}
			
			return menuItemRepository.save(menuItem);
		}

		throw new MenuItemException("Item not saved");
	}

	@Override
	public void deleteMenuItem(Long itemId) {

	}

	public MenuItem findMenuItemById(Long id) throws MenuItemException {
		Optional<MenuItem> menuItem = menuItemRepository.findById(id);
		if (menuItem.isPresent()) {
			return menuItem.get();
		}
		throw new MenuItemException("MenuItem with id" + id + "not found");
	}

	@Override
	public MenuItem updateMenuItem(Long itemId, MenuItem updatedMenuItem) throws MenuItemException {
		MenuItem menuItem = findMenuItemById(itemId);
		if (menuItem.getCategory() != null) {
			menuItem.setCategory(updatedMenuItem.getCategory());
		}
		if (menuItem.getDescription() != null) {
			menuItem.setDescription(updatedMenuItem.getDescription());
		}
		return menuItemRepository.save(menuItem);
	}

	@Override
	public List<MenuItem> getMenuItemsByCategory(Category category) throws MenuItemException {
		Optional<List<MenuItem>> menuItems = menuItemRepository.findByCategory(category);
		if (menuItems.isPresent()) {
			return menuItems.get();
		}
		throw new MenuItemException("no items in the category" + category);
	}

	@Override
	public List<MenuItem> getMenuItemsByCriteria(boolean isVegetarian, boolean isVegan, boolean isGlutenFree)
			throws MenuItemException {
		Optional<List<MenuItem>> menuItems = menuItemRepository.getMenuItemsByFilter(isVegetarian, isVegan,
				isGlutenFree);
		if (menuItems.isPresent()) {
			return menuItems.get();
		}
		throw new MenuItemException("no items in the filter");
	}

	@Override
	public MenuItem findMenuItemByName(String name) throws MenuItemException {
		Optional<MenuItem> menuItem=menuItemRepository.findByName(name);
		if(menuItem.isPresent()) {
			return menuItem.get();
		}
		throw new MenuItemException("Menu item Not found");
	}

	@Override
	public List<MenuItem> getRestaurantMenuItems(Long restaurantId) throws MenuItemException {
		Optional<List<MenuItem>> menuItems = menuItemRepository.findByRestaurantId(restaurantId);
		if(menuItems.isPresent()) {
			return menuItems.get();
		}
		throw new MenuItemException("Menu item Not found");
		
	}

	@Override
	public List<MenuItem> searchMenuItem(String keyword) {
		List<MenuItem> items=new ArrayList<>();
		
		if(keyword!="") {
			System.out.println("keyword -- "+keyword);
			items=menuItemRepository.searchByNameOrCategory(keyword);
		}
		
		return items;
	}

}
