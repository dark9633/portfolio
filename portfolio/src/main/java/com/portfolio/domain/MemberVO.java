package com.portfolio.domain;

import java.io.Serializable;
import java.util.Date;


public class MemberVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer memNumber;
	private String nickName;
	private String email;
	private String pwd;
	private Date regDate;
	
	private String naverId;
	
	public Integer getMemNumber() {
		return memNumber;
	}
	public void setMemNumber(Integer memNumber) {
		this.memNumber = memNumber;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getNaverId() {
		return naverId;
	}
	public void setNaverId(String naverId) {
		this.naverId = naverId;
	}
	
}
