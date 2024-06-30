package com.portal26.webhook.pojo;

public class EventRequest {
	
	
	private String tenant;
	private String event_timestamp;
	private String user_id;
	private String url;
	private String body;
	
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
	
	
	@Override
	public String toString() {
		return "EventRequest [" + (tenant != null ? "tenant=" + tenant + ", " : "")
				+ (event_timestamp != null ? "event_timestamp=" + event_timestamp + ", " : "")
				+ (user_id != null ? "user_id=" + user_id + ", " : "") + (url != null ? "url=" + url + ", " : "")
				+ (body != null ? "body=" + body : "") + "]";
	}
	
	
	
}
