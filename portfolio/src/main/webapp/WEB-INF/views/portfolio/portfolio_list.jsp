<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
		.gal {
			-webkit-column-count: 3; /* Chrome, Safari, Opera */
			-moz-column-count: 3; /* Firefox */
			column-count: 3;
		}
		@media (max-width: 768px) {
			.gal {
				-webkit-column-count: 2; /* Chrome, Safari, Opera */
				-moz-column-count: 2; /* Firefox */
				column-count: 2;
			}
		}
	
		/* .gal img{ width: 100%; padding: 7px 0;} */
		.gal .image-div img { width: 100%; padding: 7px 0;}
		
		.image-div { position: relative; }
		
		.over-div { 
			display: none;
			width: 100%; 
			height: 100%; 
			position: absolute;
	
			background-color: rgba(0, 0, 0, 0.6);
			background-clip: content-box;
			
			padding-top: 7px;
			padding-bottom: 7px;
		}
		
		.over-div p {
		    color: white;
		    font-size: 15px;
		    width: 100%;
		    
		    font-weight: 100;
		    margin-left: auto;
		    margin-right: auto;
		    
		    padding: 12px;
		}
		.over-div p b {
			font-size: 20px;
		}
	</style>
	
	<title>웹 개발자 포트폴리오 | 포트폴리오 리스트</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />

	<section>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="gal">
						<c:forEach items="${ portfolio }" var="pf">
							<c:if test="${ pf.image != '' }">
								<div class="image-div">
									<a href="/portfolio/view/${ pf.pfNumber }">
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
									<a href="/portfolio/register">글쓰기</a>
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
	</section>

	<script type="text/javascript">
	
		$(document).on("mouseover", ".over-image", function(){
			$(".over-div").css("display", "none");
			$(this).parent().prev().children().css("display", "block");
		});
		
		$(document).on("mouseout", ".over-div", function(){
			$(this).css("display", "none");
		});
		
	</script>

	<jsp:include page="../common/footer.jsp" />
</body>
</html>