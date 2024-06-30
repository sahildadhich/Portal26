package com.portal26.webhook.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.portal26.webhook.pojo.dao.EventDao;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EventResponse {
	
	private String event_timestamp;
	private String user_id;
	private String body;
	
	private String status;
	
	public EventResponse() {
		
	}
	
	public EventResponse(EventDao dao) {
		super();
		this.event_timestamp = dao.getTimestamp() != null ? dao.getTimestamp().toString() : "";
		this.user_id = dao.getUserid();
		this.body = dao.getBody();
	}
	
	
	/**
	 * @return the event_timestamp
	 */
	public String getEvent_timestamp() {
		return event_timestamp;
	}
	/**
	 * @param event_timestamp the event_timestamp to set
	 */
	public void setEvent_timestamp(String event_timestamp) {
		this.event_timestamp = event_timestamp;
	}
	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return "EventResponse [" + (event_timestamp != null ? "event_timestamp=" + event_timestamp + ", " : "")
				+ (user_id != null ? "user_id=" + user_id + ", " : "") + (body != null ? "body=" + body + ", " : "")
				+ (status != null ? "status=" + status : "") + "]";
	}

}
