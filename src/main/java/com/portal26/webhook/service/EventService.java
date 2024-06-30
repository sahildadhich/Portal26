package com.portal26.webhook.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

@Service
public class EventService {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private EventRepository eventRepository;
	
	
	public EventResponse saveEvents(EventRequest request) {
		
		String domain = getDomain(request.getUrl());
		Timestamp eventTimeStamp = convertToTimestamp(request.getEvent_timestamp());
		
		EventDao webhook = new EventDao(request, domain, eventTimeStamp);
		eventRepository.save(webhook);
		
		categoryService.saveCategories(domain, request.getTenant(), request.getUser_id());
		
		EventResponse response = new EventResponse();
		response.setStatus("success");
		return response;
	}
	
	public List<EventResponse> getEvents(EventQueryRequest request) {
		
		
		Timestamp toDateTime = getToDateTime(request.getTo_date());
		Timestamp fromDateTime = getFromTimestamp(request.getFrom_date(), toDateTime);
		String tenant = request.getTenant();
		
		String userId = request.getUser_id();
		String domain = request.getDomain();
		
		List<EventDao> eventList = null;
		
		
		if(Objects.nonNull(userId) && Objects.nonNull(domain)) {
			eventList = eventRepository.findByTenantAndTimestampBetweenAndDomainAndUserId(tenant, fromDateTime, toDateTime, domain, userId);
		}else if(Objects.nonNull(userId)) {
			eventList = eventRepository.findByTenantAndTimestampBetweenAndUserid(tenant, fromDateTime, toDateTime, userId);
		}else if(Objects.nonNull(domain)) {
			eventList = eventRepository.findByTenantAndTimestampBetweenAndDomain(tenant, fromDateTime, toDateTime, domain);
		}else {
			eventList = eventRepository.findByTenantAndTimestampBetween(tenant, fromDateTime, toDateTime);
		}
		
		
		List<EventResponse> returnList = new ArrayList<EventResponse>();
		if(!CollectionUtils.isEmpty(eventList)) {
			for(EventDao event : eventList) {
				returnList.add(new EventResponse(event));
			}
		}
		
		return returnList;
		
	}
	
	private String getDomain(String url) {
		
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
	
	private Timestamp convertToTimestamp(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateString, formatter);
        return Timestamp.from(offsetDateTime.toInstant());
    }
	
	private Timestamp getToDateTime(String fromDate) {
		Timestamp fromTimestamp = null;
		
		if(Objects.isNull(fromDate)) {
			Long millis = System.currentTimeMillis();
			fromTimestamp = new Timestamp(millis);
		}else {
			LocalDate localDate = LocalDate.parse(fromDate);
			LocalDateTime endOfDay = localDate.atTime(LocalTime.MAX);
			fromTimestamp = Timestamp.valueOf(endOfDay);
		}
		
		return fromTimestamp;
	}
	
	
	private Timestamp getFromTimestamp(String fromDate, Timestamp toTimestamp) {
		if(Objects.isNull(fromDate)) {
			LocalDateTime currentDateTime = toTimestamp.toLocalDateTime();

	        // Subtract 30 days
	        LocalDateTime dateTimeMinus30Days = currentDateTime.minus(180, ChronoUnit.DAYS);

	        // Convert LocalDateTime back to SQL Timestamp
	        return Timestamp.valueOf(dateTimeMinus30Days);
		}else {
			LocalDate localDate = LocalDate.parse(fromDate);
			LocalDateTime localDateTime = localDate.atStartOfDay();
			return Timestamp.valueOf(localDateTime);
		}
	}

}
