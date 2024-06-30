package com.portal26.webhook.util;

public class StringUtility {
	
	/*
	 * method to extract domain name from the given url,
	 * it works by removing prefix http://, https://, www
	 * and will return the domain
	 */
	public static String getDomain(String url) {
		
		String domain = url;
		if(domain.startsWith("https") || domain.startsWith("http")) {
			domain = domain.substring(domain.indexOf("//")+2);
		}
		
		if(domain.startsWith("www.")) {
			domain = domain.substring(domain.indexOf("www.")+4);
		}
		
		if(-1 != domain.indexOf("/")) {
			domain = domain.substring(0, domain.indexOf("/"));
		}
		
		
		return domain;
		
	}

}
