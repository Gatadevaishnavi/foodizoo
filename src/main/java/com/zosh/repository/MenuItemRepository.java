package com.zosh.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zosh.model.Category;
import com.zosh.model.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

	Optional<List<MenuItem>> findByCategory(Category category);

	@Query("SELECT m FROM MenuItem m " + "WHERE (:isVegetarian IS NULL OR m.isVegetarian = :isVegetarian) "
			+ "AND (:isVegan IS NULL OR m.isVegan = :isVegan) "
			+ "AND (:isGlutenFree IS NULL OR m.isGlutenFree = :isGlutenFree)")
	Optional<List<MenuItem>> getMenuItemsByFilter(boolean isVegetarian, boolean isVegan, boolean isGlutenFree);

	Optional<MenuItem> findByName(String name);
	
	@Query("SELECT mi FROM MenuItem mi WHERE mi.restaurant.id = :restaurantId")
	Optional<List<MenuItem>> findByRestaurantId(@Param("restaurantId") Long restaurantId);
	
	@Query("SELECT m FROM MenuItem m WHERE m.name LIKE %:keyword% OR m.category.name LIKE %:keyword%")
	List<MenuItem> searchByNameOrCategory(@Param("keyword") String keyword);


	

}
