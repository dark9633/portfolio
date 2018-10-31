<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	
	<title>웹 개발자 포트폴리오 | 스킬 리스트 카테고리</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />

	<section>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-4 col-md-3 col-lg-3">
					<div class="list-group">
						<c:forEach items="${ subCategory }" var="sc">
							<c:choose>
								<c:when test="${ sc.subCategory == pageMaker.cri.subCategory }">
									<a class="list-group-item active" href="/skills/list/${ sc.nickName }/${ sc.category }?subCategory=${ sc.subCategory }">${ sc.subCategory }</a>
								</c:when>
								<c:otherwise>
									<a class="list-group-item" href="/skills/list/${ sc.nickName }/${ sc.category }?subCategory=${ sc.subCategory }">${ sc.subCategory }</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
	            </div>
	            <div class="col-xs-12 hidden-sm hidden-md hidden-lg">
	            	<div class="row">
		            	<div class="col-sm-12 col-md-12 col-lg-12">
							<table class="table">
								<tbody class="list">
									<c:forEach items="${ skills }" var="skill">
										<tr>
											<td>
												<a href="/skills/view/${ skill.skNumber }">
													<div>
														${ skill.title }
													</div>
													<div style="font-size: 10px;">
														${ skill.nickName } | 
														<fmt:formatDate pattern="MM-dd" value="${ skill.regDate }"/> | 
														조회 : ${skill.viewCount }
													</div>
												</a>
												
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
	            	</div>
				</div>
	            <div class="hidden-xs col-sm-8 col-md-9 col-lg-9">
	            	<div class="row">
	            		<div class="col-sm-12 col-md-12 col-lg-12">
							<table class="table table-hover">
								<thead class="head">
									<tr>
										<th width="62%">제목</th>
										<th width="15%">작성자</th>
										<th width="13%">날짜</th>
										<th width="10%">조회</th>
									</tr>
								</thead>
								<!-- main list line -->
								<tbody class="list">
									<c:forEach items="${ skills }" var="skill">
										<tr>
											<td>
												<a href="/skills/view/${ skill.skNumber }">
													${ skill.title }
												</a>
											</td>
											<td>
												${ skill.nickName }
											</td>
											<td class="visible-lg"><fmt:formatDate pattern="yyyy-MM-dd" value="${ skill.regDate }"/></td>
											<td class="visible-md"><fmt:formatDate pattern="yyyy-MM-dd" value="${ skill.regDate }"/></td>
											<td class="visible-sm"><fmt:formatDate pattern="MM-dd" value="${ skill.regDate }"/></td>
											<td class="visible-xs"><fmt:formatDate pattern="MM-dd" value="${ skill.regDate }"/></td>
											<td>
												&nbsp;&nbsp;&nbsp;&nbsp;${ skill.viewCount }
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
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
						<div class="col-sm-8 col-md-8 col-lg-8 text-right">
							<ul class="pagination">
								<c:if test="${ pageMaker.prev }">
									<li>
										<a href="${ cri.subCategory }${ pageMaker.makeQuerySkills(pageMaker.startPage - 1) }">&lt;</a>
									</li>
								</c:if>
								<c:forEach begin="${ pageMaker.startPage }" end="${ pageMaker.endPage }" var="idx">
									 <c:if test="${ pageMaker.cri.page == idx }">
									 	<li class="active">
									 		<a href="${ cri.subCategory }${ pageMaker.makeQuerySkills( idx ) }">${ idx }</a>
									 	</li>
									 </c:if>
									 <c:if test="${ pageMaker.cri.page != idx }">
									 	<li>
									 		<a href="${ cri.subCategory }${ pageMaker.makeQuerySkills( idx ) }">${ idx }</a>
									 	</li>
									 </c:if>
								</c:forEach>
								<c:if test="${ pageMaker.next && pageMaker.endPage > 0 }">
									<li>
										<a href="${ cri.subCategory }${ pageMaker.makeQuerySkills(pageMaker.endPage + 1) }">&gt;</a>
									</li>
								</c:if>
							</ul>
						</div>
	            	</div>
	            </div>
			</div>
		</div>
	</section>

	<jsp:include page="../common/footer.jsp" />
</body>
</html>