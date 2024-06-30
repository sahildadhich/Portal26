package com.portal26.webhook.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portal26.webhook.pojo.dao.EventDao;

public interface EventRepository extends JpaRepository<EventDao, Integer>{
	
	//List<WebhookDao> findByTenantAndEvent_timestampGreaterThanEqualAndEvent_timestampLessThanEqual(String tenant, Timestamp fromdate, Timestamp toDate);
	
	
	List<EventDao> findByTenantAndTimestampBetween(String tenant, Timestamp fromdate, Timestamp toDate);
	
	List<EventDao> findByTenantAndTimestampBetweenAndDomain(String tenant, Timestamp fromdate, Timestamp toDate, String domain);
	
	List<EventDao> findByTenantAndTimestampBetweenAndUserid(String tenant, Timestamp fromdate, Timestamp toDate, String userId);
	
	List<EventDao> findByTenantAndTimestampBetweenAndDomainAndUserId(String tenant, Timestamp fromdate, Timestamp toDate, String domain, String userId);
	
	
}
