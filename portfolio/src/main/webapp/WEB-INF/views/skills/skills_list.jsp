<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/common.jsp" />
	<link rel="stylesheet" href="/resources/css/skills/skills_list.css">
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<meta name="author" content="freeserver.zone">
	<meta name="keywords" content="웹개발자 스킬리스트 개발기술 자바개발자 서버개발자 mysql 데이터베이스">
	<meta name="description" content="웹개발자 스킬 향상을 위한 스킬리스트입니다.">
	
	<meta property="fb:app_id" content=""/>
	
	<meta property="og:title" content="웹 개발자 스킬 리스트" />
	<meta property="og:type" content="website" />
	<meta property="og:description" content="웹개발자 스킬 향상을 위한 스킬리스트입니다.">
	<meta property="og:image" content="http://portfolio.freeserver.zone/resources/img/001.jpg"/>
	<meta property="og:site_name" content="포트폴리오"/> 
	<meta property="og:url" content="http://portfolio.freeserver.zone/skills/list"/>
	
	<title>웹 개발자 스킬 리스트</title>
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
	
	<script type="text/javascript">
		$(function(){ $("#header-li").css("display", ""); });
		$(document).on("keyup", "#header-search", function(e){
			if(e.keyCode == 13){
				location.href = "/skills/list?search="+$(this).val();
			}
		});
		$(function(){
			var param = getUrlParams();
			$.each(param, function(index, entry){
				if(index == 'search'){
					$("#header-search").val(decodeURI(entry));
				}
			});
		});
		function getUrlParams() {
			var params = {};
			window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str, key, value) { params[key] = value; });
			return params;
		}
	</script>

	<jsp:include page="../common/footer.jsp" />
</body>
</html>