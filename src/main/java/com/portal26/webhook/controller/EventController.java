package com.portal26.webhook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portal26.webhook.pojo.EventQueryRequest;
import com.portal26.webhook.pojo.EventRequest;
import com.portal26.webhook.pojo.EventResponse;
import com.portal26.webhook.service.EventService;

@RestController
public class EventController {
	
	@Autowired
	private EventService service;
	
	@PostMapping("/v1/webhooks/{tenant}/events")
	public EventResponse saveEvent(@PathVariable String tenant, @RequestBody EventRequest request) {
		
		request.setTenant(tenant);
		EventResponse response = service.saveEvents(request);
		return response;
		
		
	}
	
	@PostMapping("/v1/{tenant}/query")
	public List<EventResponse> getEvent(@PathVariable String tenant, @RequestBody EventQueryRequest request) {
		
		request.setTenant(tenant);
		List<EventResponse> responseList = service.getEvents(request);
		return responseList;
	}

}
