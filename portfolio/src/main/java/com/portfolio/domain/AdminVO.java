package com.portfolio.domain;

import java.io.Serializable;


/* 
 * 임시 데이터 용도로 사용하기 위해 추가했다. 
 * 추후 관리자 데이터베이스 추가여부는 미정
 * */
public class AdminVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String pwd;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
