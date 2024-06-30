package com.portal26.webhook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class WebhookApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(WebhookApplication.class, args);
		
	}
	
	


}
