<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/common.jsp" />
	<link rel="stylesheet" href="/resources/css/portfolio/portfolio_list.css">
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<meta name="author" content="freeserver.zone">
	<meta name="keywords" content="포트폴리오, 웹개발자 스킬리스트 개발기술 자바개발자 서버개발자 mysql 데이터베이스">
	<meta name="description" content="웹개발자 포트폴리오 리스트입니다. 포트폴리오를 업로드해주세요.">
	
	<meta property="fb:app_id" content=""/>
	
	<meta property="og:title" content="웹 개발자 포트폴리오 리스트" />
	<meta property="og:type" content="website" />
	<meta property="og:description" content="웹개발자 포트폴리오 리스트입니다. 포트폴리오를 업로드해주세요.">
	<meta property="og:image" content="http://portfolio.freeserver.zone/resources/img/001.jpg"/>
	<meta property="og:site_name" content="포트폴리오"/> 
	<meta property="og:url" content="http://portfolio.freeserver.zone/portfolios/list"/>
	
	<title>웹 개발자 포트폴리오 리스트</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="gal">
					<c:forEach items="${ portfolio }" var="pf">
						<c:if test="${ pf.image != '' }">
							<div class="image-div">
								<a href="/portfolios/view/${ pf.pfNumber }">
									<div class="over-div">
										<p>
											<b>${ pf.title }</b><br><br>
											${ pf.simpleContent }
										</p>
									</div>
								</a>
								
								<a href="#">
									<img src="/picture/portfolio${ pf.image }" alt="${ pf.title }" class="over-image">
								</a>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
					<ul class="pagination">
						<li>
							<c:if test="${ member != null }">
								<a href="/portfolios/register">글쓰기</a>
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
								<a href="${ pageMaker.makeQuer(pageMaker.startPage - 1) }">&lt;</a>
							</li>
						</c:if>
						
						<c:forEach begin="${ pageMaker.startPage }" end="${ pageMaker.endPage }" var="idx">
							 <c:if test="${ pageMaker.cri.page == idx }">
							 	<li class="active">
							 		<a href="${ pageMaker.makeQuery( idx ) }">${ idx }</a>
							 	</li>
							 </c:if>
							 <c:if test="${ pageMaker.cri.page != idx }">
							 	<li>
							 		<a href="${ pageMaker.makeQuery( idx ) }">${ idx }</a>
							 	</li>
							 </c:if>
						</c:forEach>
						
						<c:if test="${ pageMaker.next && pageMaker.endPage > 0 }">
							<li>
								<a href="${ pageMaker.makeQuery(pageMaker.endPage + 1) }">&gt;</a>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<script src="/resources/js/portfolio/portfolio_list.js"></script>

	<jsp:include page="../common/footer.jsp" />
</body>
</html>