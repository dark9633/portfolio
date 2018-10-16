package com.portfolio.domain;

import java.util.Date;


public class ReplyVO {
	
	private Integer reNumber;
	private Integer bNumber;
	private String nickName;
	private String content;
	private Date regDate;
	
	public Integer getReNumber() {
		return reNumber;
	}
	public void setReNumber(Integer reNumber) {
		this.reNumber = reNumber;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
