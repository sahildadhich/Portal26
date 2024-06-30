package com.portal26.webhook.pojo;

import java.util.List;

public class WhoisXmlApi {
	
	private String domainName;
	private List<Categories> categories;
	/**
	 * @return the domainName
	 */
	public String getDomainName() {
		return domainName;
	}
	/**
	 * @param domainName the domainName to set
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	/**
	 * @return the categories
	 */
	public List<Categories> getCategories() {
		return categories;
	}
	/**
	 * @param categories the categories to set
	 */
	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}
	
	
	@Override
	public String toString() {
		return "WhoisXmlApi [" + (domainName != null ? "domainName=" + domainName + ", " : "")
				+ (categories != null ? "categories=" + categories : "") + "]";
	}
	
	
	

}
