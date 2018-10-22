<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/common.jsp" />
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<meta name="author" content="">
	<meta name="keywords" content="">
	<meta name="description" content="">
	
	<meta property="og:title" content="" />
	<meta property="fb:app_id" content=""/>
	<meta property="og:type" content="" />
	<meta property="og:description" content="">
	<meta property="og:image" content=""/>
	<meta property="og:site_name" content=""/> 
	<meta property="og:url" content=""/>
	
	<style type="text/css">
		.card {
		    font-size: 1em;
		    overflow: hidden;
		    padding: 0;
		    margin-top: 30px;
		    border: none;
		    border-radius: .28571429rem;
		    box-shadow: 0 1px 3px 0 #d4d4d5, 0 0 0 1px #d4d4d5;
		}
		
		.card-block {
		    font-size: 1em;
		    position: relative;
		    margin: 0;
		    padding: 1em;
		    border: none;
		    border-top: 1px solid rgba(34, 36, 38, .1);
		    box-shadow: none;
		}
		
		.card-title {
		    font-size: 1.28571429em;
		    font-weight: 700;
		    line-height: 1.2857em;
		}
		
		.card-text {
		    clear: both;
		    margin-top: .5em;
		    color: rgba(0, 0, 0, .68);
		    max-height: 170px;
		    min-height: 170px;
		    overflow-y: scroll;
		}
		
		::-webkit-scrollbar {
		    width: 0px;
		    background: transparent;
		}
		
		.card-text > ul {
			list-style: none;
			padding-top: 5px;
			padding-bottom: 5px;
			padding-left: 10px;
			padding-right: 10px;
			margin-left: 10px;
			border-left: 1px solid #ddd;
		}
		
		.card-footer {
		    font-size: 1em;
		    position: static;
		    top: 0;
		    left: 0;
		    max-width: 100%;
		    padding: .75em 1em;
		    color: rgba(0, 0, 0, .4);
		    border-top: 1px solid rgba(0, 0, 0, .05) !important;
		    background: #fff;
		}
	</style>
	
	<title>웹 개발자 포트폴리오 | 스킬 리스트</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />

	<div class="container">
	
		<div class="row">
			
			<c:forEach items="${ list }" var="skill">
				<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	                <div class="card">
	                    <div class="card-block">
	                        <h4 class="card-title">${ fn:toUpperCase(skill.category) }</h4>
	                        <div class="meta">
	                            <a>${ skill.nickName }</a>
	                        </div>
	                        <div class="card-text">
	                        	<ul>
	                        		<c:forEach items="${ skill.skills }" var="skills">
		                        		<li><a href="/skills/list/${ skills.nickName }/${ skills.category }?subCategory=${ skills.subCategory }">${ skills.subCategory }</a></li>
	                        		</c:forEach>
	                        	</ul>
	                        </div>
	                    </div>
	                    <div class="card-footer">
	                    	<div class="row">
		                    	<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
			                        <small>Last updated <fmt:formatDate pattern="MM-dd HH:mm" value="${ skill.regDate }"/></small>
		                    	</div>
		                    	<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right">
			                        <a href="/skills/list/${ skill.nickName }/${ skill.category }" class="btn btn-default btn-sm">더보기</a>
		                    	</div>
	                    	</div>
	                    </div>
	                </div>
	            </div>
			</c:forEach>
			
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<ul class="pagination">
							<li>
								<c:if test="${ member != null }">
									<a href="/skills/register">글쓰기</a>
								</c:if>
								<c:if test="${ member == null }">
									<a href="#" onclick="alert('로그인 후 이용하실 수 있습니다.')">글쓰기</a>
								</c:if>
							</li>
						</ul>
					</div>
					
					<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8 text-right">
						<ul class="pagination">
							<c:if test="${ pageMaker.prev }">
								<li>
									<a href="${ pageMaker.makeQuerySkills2(pageMaker.startPage - 1) }">&lt;</a>
								</li>
							</c:if>
							
							<c:forEach begin="${ pageMaker.startPage }" end="${ pageMaker.endPage }" var="idx">
								 <c:if test="${ pageMaker.cri.page == idx }">
								 	<li class="active">
								 		<a href="${ pageMaker.makeQuerySkills2( idx ) }">${ idx }</a>
								 	</li>
								 </c:if>
								 <c:if test="${ pageMaker.cri.page != idx }">
								 	<li>
								 		<a href="${ pageMaker.makeQuerySkills2( idx ) }">${ idx }</a>
								 	</li>
								 </c:if>
							</c:forEach>
							
							<c:if test="${ pageMaker.next && pageMaker.endPage > 0 }">
								<li>
									<a href="${ pageMaker.makeQuerySkills2(pageMaker.endPage + 1) }">&gt;</a>
								</li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
			
		</div>
	</div>

	<jsp:include page="../common/footer.jsp" />
</body>
</html>