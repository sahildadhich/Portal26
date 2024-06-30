package com.portal26.webhook.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.portal26.webhook.pojo.WhoisXmlApi;

@Service
public class RestService {
	
	private final RestClient restClient;
	
	public RestService() {
		restClient = RestClient.builder()
						.baseUrl("https://website-categorization.whoisxmlapi.com")
						.build();
	}
	
	
	public WhoisXmlApi getCategories(String domain) {
		return restClient.get()
				.uri("/api/v3?apiKey=at_mt9ou3pj5WzJY1KoSUIjku37QKpfg&url="+domain)
				.retrieve()
				.body(WhoisXmlApi.class);
	}

}
