package com.portal26.webhook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portal26.webhook.pojo.dao.CategoryDao;


public interface CategoryRepository extends JpaRepository<CategoryDao, Integer>{
	
	
	List<CategoryDao> findByCategory(String category);
	
	List<CategoryDao> findByDomain(String category);
	

}
