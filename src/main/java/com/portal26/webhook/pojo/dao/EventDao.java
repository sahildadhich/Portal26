package com.portal26.webhook.pojo.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import com.portal26.webhook.pojo.EventRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity    
@Table(name = "webhook_1")
public class EventDao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	private String tenant;
	private String userid;
	private String url;
	private String domain;
	private String body;
	private Timestamp timestamp;
	
	public EventDao() {
		
	}
	
	public EventDao(EventRequest request, String domain, Timestamp eventTimestamp) {
		super();
		this.tenant = request.getTenant().trim().toLowerCase();
		this.userid = request.getUser_id().trim().toLowerCase();
		this.url = request.getUrl();
		this.domain = domain;
		this.body = request.getBody();
		this.timestamp = eventTimestamp;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @return the tenant
	 */
	public String getTenant() {
		return tenant;
	}

	/**
	 * @param tenant the tenant to set
	 */
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "EventDao [" + (tenant != null ? "tenant=" + tenant + ", " : "")
				+ (userid != null ? "userid=" + userid + ", " : "") + (url != null ? "url=" + url + ", " : "")
				+ (domain != null ? "domain=" + domain + ", " : "") + (body != null ? "body=" + body + ", " : "")
				+ (timestamp != null ? "timestamp=" + timestamp : "") + "]";
	}

	
}
