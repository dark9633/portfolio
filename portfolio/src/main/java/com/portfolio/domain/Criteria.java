package com.portfolio.domain;


/*
 * 페이지 처리를 위한 객체 클래스
 * 
 * */
public class Criteria {

	private int page;
	private int perPageNum;
	private String category;
	private String subCategory;
	private String nickName;
	private String search;
	private String type;
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 20;
		this.category = null;
		this.subCategory = "";
		this.nickName = "";
		this.search = "";
		this.type = "";
	}
	
	public void setPage(int page){
		if(page <= 0){
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	public void setPerPageNum(int perPageNum){
		if(perPageNum <= 0){
			this.perPageNum = 10;
			return;
		}else if(perPageNum > 100){
			this.perPageNum = 100;
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	public int getPage(){
		return page;
	}
	
	// method for MyBatis SQL Mapper
	public int getPageStart(){
		return (this.page -1) * perPageNum;
	}
	
	public int getPerPageNum(){
		return this.perPageNum;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString(){
		return "Criteria [page=" + page + ", " + "perPageNum=" + perPageNum + ", boardCategory = "+category+", search= "+search+"]";
	}
}
