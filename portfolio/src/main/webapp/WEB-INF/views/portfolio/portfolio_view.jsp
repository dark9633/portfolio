<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/common.jsp" />
	<link rel="stylesheet" href="/resources/css/portfolio/portfolio_view.css">
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<meta name="author" content="freeserver.zone">
	<meta name="keywords" content="${ view.category } 웹개발자 자바개발자 서버개발자 mysql 데이터베이스, 포트폴리오">
	<meta name="description" content="${ view.simpleContent }">
	
	<meta property="fb:app_id" content=""/>
	
	<meta property="og:title" content="웹 개발자 포트폴리오 | ${ view.title }" />
	<meta property="og:type" content="website" />
	<meta property="og:description" content="${ view.simpleContent }">
	<meta property="og:image" content="http://portfolio.freeserver.zone/picture/portfolio${ view.image }"/>
	<meta property="og:site_name" content="포트폴리오"/> 
	<meta property="og:url" content="http://portfolio.freeserver.zone/portfolios/view/${ view.pfNumber }"/>
	
	<title>웹 개발자 포트폴리오 | ${ view.title }</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				
				<!-- main content view line -->
				<div class="mainContentAll">
					
					<div class="mainTitle">
						<div class="row">
						 
							<div class="col-md-9 col-sm-12">
								<h4>
									<b style="padding: 10px;">&nbsp;${ view.title }</b>
								</h4>
							</div>
							<div class="col-md-3 col-sm-12 text-right">
								<h5 style="padding-right: 10px;">${ view.nickName }</h5>
							</div>
							
							<div class="col-md-12 col-sm-12 col-xs-12"><hr></div>
							
							<div class="col-md-12 text-right">
								<span class="glyphicon glyphicon-eye-open title-icon"> <b>${ view.viewCount }</b></span>
								<i style="padding: 10px;">
									<fmt:formatDate pattern="yyyy-MM-dd" value="${view.regDate }"/>
								</i>
							</div>
						</div>
					</div>
				
					<div class="mainContent">
						 ${ view.content }
					</div>
					
					<div class="row">
						<div class="col-md-1"></div>
						<div class="col-md-10 text-right">
							<br>
							<c:if test="${ view.nickName == member.nickName }">
								<a href="/portfolios/modify/${ view.pfNumber }" class="btn btn-default btn-lg">수정</a>
								<a href="/portfolios/delete/${ view.pfNumber }" class="btn btn-default btn-lg" id="deleteBtn">삭제</a>
							</c:if>
							<a href="" class="btn btn-default btn-lg">목록보기</a>
						</div>
						<div class="col-md-1"></div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	
	<script src="/resources/js/portfolio/portfolio_view.js"></script>

	<jsp:include page="../common/footer.jsp" />
</body>
</html>