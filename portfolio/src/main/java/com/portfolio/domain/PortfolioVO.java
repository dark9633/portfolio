package com.portfolio.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;


public class PortfolioVO {
	
	private Integer pfNumber;
	private String nickName;
	private String category;
	private String title;
	private String content;
	private int viewCount;
	private Date regDate;
	
	/* 추가 */
	private String image;
	private String simpleContent;
	private MultipartFile file;
	
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
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSimpleContent() {
		return simpleContent;
	}
	public void setSimpleContent(String simpleContent) {
		this.simpleContent = simpleContent;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
