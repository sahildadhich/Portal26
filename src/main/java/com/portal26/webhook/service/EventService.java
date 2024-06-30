package com.portal26.webhook.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.portal26.webhook.pojo.EventQueryRequest;
import com.portal26.webhook.pojo.EventRequest;
import com.portal26.webhook.pojo.EventResponse;
import com.portal26.webhook.pojo.dao.EventDao;
import com.portal26.webhook.repository.EventRepository;
import com.portal26.webhook.util.Dateutility;
import com.portal26.webhook.util.StringUtility;

@Service
public class EventService {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private EventRepository eventRepository;
	
	
	public EventResponse saveEvents(EventRequest request) {
		
		String domain = StringUtility.getDomain(request.getUrl()).trim().toLowerCase();
		Timestamp eventTimeStamp = Dateutility.convertToTimestamp(request.getEvent_timestamp());
		
		EventDao webhook = new EventDao(request, domain, eventTimeStamp);
		eventRepository.save(webhook);
		
		/*
		 * fetching categories for the domain in separate thread
		 */
		new Thread(new Runnable() {
            @Override
            public void run() {
            	categoryService.saveCategories(domain);
            }
        }).start();
		
		EventResponse response = new EventResponse();
		response.setStatus("success");
		return response;
	}
	
	public List<EventResponse> getEvents(EventQueryRequest request) {
		
		
		Timestamp toDateTime = Dateutility.getToDateTime(request.getTo_date());
		Timestamp fromDateTime = Dateutility.getFromTimestamp(request.getFrom_date(), toDateTime);
		String tenant = request.getTenant();
		
		String userId = request.getUser_id();
		String domain = request.getDomain();
		
		String category = request.getCategory();
		
		HashMap<String, String> domainMap = null;
		if(!Objects.isNull(category)) {
			domainMap = categoryService.findDomainsByCategory(category);
			if(CollectionUtils.isEmpty(domainMap)) {
				return null;
			}
			
		}
		
		
		List<EventDao> eventList = null;
		
		eventList = eventRepository.findEventDaos(tenant, domain, userId, fromDateTime, toDateTime);
		
		if(CollectionUtils.isEmpty(eventList)) {
			return null;
		}
		
		List<EventResponse> returnList = new ArrayList<EventResponse>();
		for(EventDao event : eventList) {
			if((Objects.nonNull(category) && Objects.nonNull(domainMap.get(event.getDomain()))) || Objects.isNull(category)) {
				returnList.add(new EventResponse(event));
			}
		}
		
		return returnList;
		
	}
	

}
