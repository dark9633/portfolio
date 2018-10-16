package com.portfolio.domain;

import java.util.Date;


public class BoardVO {
	
	private Integer bNumber;
	private String nickName;
	private String category;
	private String title;
	private String content;
	private int reCount;
	private int viewCount;
	private Date regDate;
	
	public Integer getbNumber() {
		return bNumber;
	}
	public void setbNumber(Integer bNumber) {
		this.bNumber = bNumber;
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
	public int getReCount() {
		return reCount;
	}
	public void setReCount(int reCount) {
		this.reCount = reCount;
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
