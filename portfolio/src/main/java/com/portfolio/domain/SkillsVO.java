package com.portfolio.domain;

import java.util.Date;


public class SkillsVO {
	
	private Integer skNumber;
	private String nickName;
	private String category;
	private String title;
	private String content;
	private int viewCount;
	private Date regDate;
	
	/* 추가 */
	private String subCategory;
	
	public Integer getSkNumber() {
		return skNumber;
	}
	public void setSkNumber(Integer skNumber) {
		this.skNumber = skNumber;
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
	
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	
}
