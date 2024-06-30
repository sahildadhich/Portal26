package com.portal26.webhook.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.portal26.webhook.pojo.Categories;
import com.portal26.webhook.pojo.WhoisXmlApi;
import com.portal26.webhook.pojo.dao.CategoryDao;
import com.portal26.webhook.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RestService restService;
	
	
	public void saveCategories(String domain) {
		
		domain = domain.trim().toLowerCase();
		
		/*
		 * Categories are already saved for domain
		 * hence nothing to do
		 */
		List<CategoryDao> savedCategories = categoryRepository.findByDomain(domain);
		if(!CollectionUtils.isEmpty(savedCategories)) {
			return;
		}
		
		/*
		 * Making API call to third party to fetch the categories list for the given domain. 
		 */
		WhoisXmlApi apiResponse = null;
		try {
			apiResponse = restService.getCategories(domain);
		}catch (Exception e) {
			
		}
		
		/*
		 * If API response is null or empty nothing to do here, simply return
		 */
		if(Objects.isNull(apiResponse)) {
			return;
		}
			
		List<Categories> categories = apiResponse.getCategories();
		if(CollectionUtils.isEmpty(categories)) {
			return;
		}
		
		
		List<CategoryDao> daoList = new ArrayList<CategoryDao>();
		CategoryDao dao = null;
		
		for(Categories category : categories) {
			dao = new CategoryDao();
			dao.setDomain(domain);
			dao.setCategory(category.getName().trim().toLowerCase());
			daoList.add(dao);
		}
		
		categoryRepository.saveAll(daoList);
		
	}
	
	public HashMap<String, String> findDomainsByCategory(String category){
		
		List<CategoryDao> categories = categoryRepository.findByCategory(category);
		if(CollectionUtils.isEmpty(categories)) {
			return null;
		}
		
		HashMap<String, String> domainMap = new HashMap<String, String>();
		for(CategoryDao categorydao : categories) {
			domainMap.put(categorydao.getDomain(), category);
		}
		return domainMap;
	}
}
