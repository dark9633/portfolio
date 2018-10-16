package com.portfolio.domain;

import java.util.Date;


public class PortfolioVO {
	
	private Integer pfNumber;
	private String nickName;
	private String category;
	private String title;
	private String content;
	private int viewCount;
	private Date regDate;
	
	public Integer getPfNumber() {
		return pfNumber;
	}
	public void setPfNumber(Integer pfNumber) {
		this.pfNumber = pfNumber;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
