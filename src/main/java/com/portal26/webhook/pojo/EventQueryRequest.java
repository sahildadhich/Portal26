package com.portal26.webhook.pojo;

public class EventQueryRequest {
	
	private String tenant;

	private String from_date;
	private String to_date;
	
	private String user_id;
	private String domain;
	private String category;
	
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
	 * @return the from_date
	 */
	public String getFrom_date() {
		return from_date;
	}
	/**
	 * @param from_date the from_date to set
	 */
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	/**
	 * @return the to_date
	 */
	public String getTo_date() {
		return to_date;
	}
	/**
	 * @param to_date the to_date to set
	 */
	public void setTo_date(String to_date) {
		this.to_date = to_date;
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
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "EventQueryRequest [" + (tenant != null ? "tenant=" + tenant + ", " : "")
				+ (from_date != null ? "from_date=" + from_date + ", " : "")
				+ (to_date != null ? "to_date=" + to_date + ", " : "")
				+ (user_id != null ? "user_id=" + user_id + ", " : "")
				+ (domain != null ? "domain=" + domain + ", " : "") + (category != null ? "category=" + category : "")
				+ "]";
	}
	
	
	
	


}
