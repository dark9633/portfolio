<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="./common/common.jsp" />
	<link rel="stylesheet" href="/resources/css/index.css">
	
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
	
	<title>웹 개발자 포트폴리오 | 메인</title>
</head>
<body>
	<jsp:include page="./common/header.jsp" />

	<div class="container">
		<div class="row">
			
			<div class="col-xs-12 col-sm-12 col-md-8 col-lg-9">
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<h2>기술개발</h2>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-right">
						<h2><a href="/skills/list">+</a></h2>
					</div>
				</div>
				<div class="row">
					<c:forEach items="${ skillsList }" var="skill">
						<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
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
				</div>
				
				<div class="row"><div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"><hr></div></div>
				
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<h2>포트폴리오</h2>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-right">
						<h2><a href="/portfolios/list">+</a></h2>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="overflow-y: scroll; max-height: 600px;">
						<div class="gal">
							<c:forEach items="${ portfolioList }" var="pf">
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
			</div>
			
			<div class="col-xs-12 col-sm-12 col-md-4 col-lg-3">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<h2>실시간 톡</h2>
					</div>
				</div>
				<div style="width: 100%; height: 203px; overflow-y: scroll; border: 1px solid #ddd; padding: 5px; margin-top: 30px;" id="chatDiv" class="form-group"></div>
				<div class="form-group">
					<c:if test="${ member != null }">
						<input type="text" id="chatText" placeholder="입력후 엔터를 치세요." class="form-control">
					</c:if>
					<c:if test="${ member == null }">
						<input type="text" id="chatText" class="form-control" disabled="disabled" value="로그인 후 이용할 수 있습니다.">
					</c:if>
					
				</div>
				
				<table class="table">
					<thead>
						<tr>
							<th>
								<div class="row">
									<div class="col-xs-8">신규 게시물</div>
									<div class="col-xs-4 text-right"><a href="/board/list/질문">+</a></div>
								</div>
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ boardList }" var="board">
							<tr>
								<td><a href="/board/view/${ board.bNumber }"><span>${ board.nickName }</span> 님이 [${ board.category }] 에 게시물 등록 <b>${ board.title }</b></a></td>
							</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</div>
			
		</div>
	</div>
	
	<script src="/resources/js/index.js"></script>

	<jsp:include page="./common/footer.jsp" />
</body>
</html>