package com.portal26.webhook.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.portal26.webhook.pojo.dao.EventDao;

public interface EventRepository extends JpaRepository<EventDao, Integer>{
	
	@Query("SELECT e FROM EventDao e WHERE (e.tenant = :tenant) AND (:domain IS NULL OR e.domain = :domain) AND (:userid IS NULL OR e.userid = :userid) AND (e.timestamp BETWEEN :fromDate AND :endDate)")
	List<EventDao> findEventDaos(@Param("tenant") String tenant, @Param("domain") String domain,  @Param("userid") String userid,  @Param("fromDate") Timestamp fromDate,  @Param("endDate") Timestamp endDate);
	
	
}
