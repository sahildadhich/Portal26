package com.portal26.webhook.pojo.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories_1")
public class CategoryDao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String domain;
	private String category;
	
	
	public CategoryDao() {
		
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}


	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}


	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}


	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "CategoryDao [id=" + id + ", " + (domain != null ? "domain=" + domain + ", " : "")
				+ (category != null ? "category=" + category : "") + "]";
	}

	
}
