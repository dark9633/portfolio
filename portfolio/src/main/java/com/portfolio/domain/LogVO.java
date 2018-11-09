package com.portfolio.domain;

import java.util.Date;


public class LogVO {
	
	private Integer logNumber;
	private Integer memNumber;
	private String ip;
	private String url;
	private String browser;
	private String device;
	private String userAgent;
	private Date regDate;
	
	public Integer getLogNumber() {
		return logNumber;
	}
	public void setLogNumber(Integer logNumber) {
		this.logNumber = logNumber;
	}
	public Integer getMemNumber() {
		return memNumber;
	}
	public void setMemNumber(Integer memNumber) {
		this.memNumber = memNumber;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
