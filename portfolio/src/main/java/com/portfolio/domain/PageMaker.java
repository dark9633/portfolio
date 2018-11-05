package com.portfolio.domain;

import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/*
 * 페이지 처리 클래스 객체
 * 
 * */

public class PageMaker {

	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 5;
	
	private Criteria cri;
	
	public void setCri(Criteria cri){
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount){
		this.totalCount = totalCount;
		
		calcData();
	}
	
	private void calcData(){
		
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
		
		startPage = (endPage - displayPageNum) + 1;
		
		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum() ));
		
		if(endPage > tempEndPage){
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public Criteria getCri() {
		return cri;
	}
	
	/* default UriComponents */
	public String makeQuery(int page) throws Exception{
		UriComponents uriComponents = null;
		if(cri.getSearch() == null || cri.getSearch().equals("")){
			uriComponents = UriComponentsBuilder
					.newInstance()
					.queryParam("category", cri.getCategory())
					.queryParam("page", page)
					.queryParam("perPageNum", cri.getPerPageNum())
					.build();
		}else{
			uriComponents = UriComponentsBuilder
					.newInstance()
					.queryParam("category", cri.getCategory())
					.queryParam("search", URLEncoder.encode(cri.getSearch(), "UTF-8" ))
					.queryParam("page", page)
					.queryParam("perPageNum", cri.getPerPageNum())
					.build();
		}
		return uriComponents.toString();
	}
	
	/* skills UriComponents */
	public String makeQuerySkills(int page) throws Exception{
		UriComponents uriComponents = null;
		uriComponents = UriComponentsBuilder
				.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("subCategory", cri.getSubCategory())
				.build();
		return uriComponents.toString();
	}
	
	/* skills UriComponents */
	public String makeQuerySkills2(int page) throws Exception{
		UriComponents uriComponents = null;
		uriComponents = UriComponentsBuilder
				.newInstance()
				.queryParam("category", cri.getCategory())
				.queryParam("search", cri.getSearch())
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();
		return uriComponents.toString();
	}
	
	/* board UriComponents */
	public String makeQueryBoard(int page) throws Exception{
		UriComponents uriComponents = null;
		uriComponents = UriComponentsBuilder
				.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();
		return uriComponents.toString();
	}
	
}
