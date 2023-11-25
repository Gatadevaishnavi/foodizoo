package com.zosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
