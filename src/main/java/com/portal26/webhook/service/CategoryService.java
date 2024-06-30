package com.portal26.webhook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	public void saveCategories(String domain, String tenant, String userId) {
		
		WhoisXmlApi apiResponse = null;
		try {
			apiResponse = restService.getCategories(domain);
		}catch (Exception e) {
			
		}
		
		
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
			dao.setTenant(tenant);
			dao.setUser_id(userId);
			dao.setDomain(domain);
			dao.setCategory(category.getName());
			daoList.add(dao);
		}
		
		categoryRepository.saveAll(daoList);
		
	}

}
