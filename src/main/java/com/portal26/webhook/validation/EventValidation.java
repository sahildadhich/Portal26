package com.portal26.webhook.validation;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.portal26.webhook.pojo.EventRequest;

@Component
public class EventValidation {

	public void validateWebhookRequest(EventRequest request) {
		if(Objects.isNull(request)) {
			
		}
		
		String tenant = request.getTenant();
		if(Objects.isNull(tenant)) {
			
		}
		
		String userId = request.getUser_id();
		String body = request.getBody();
		String eventTime = request.getEvent_timestamp();
		
		if(Objects.isNull(request) && Objects.isNull(body) && Objects.isNull(eventTime)) {
			
		}
	}
	
	
	
}
