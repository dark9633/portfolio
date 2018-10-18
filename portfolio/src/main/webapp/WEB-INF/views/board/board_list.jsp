<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
	
	<title>웹 개발자 포트폴리오 | 게시판 리스트</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<form class="navbar-form navbar-right hidden-xs">
					<div class="form-group">
						<!-- 회원 생성 전까지는 임시 닉네임 사용 -->
						<a href="/board/register/${pageMaker.cri.category}" class="btn btn-default search-bth">글쓰기</a>
					</div>
				</form>
			</div>
		</div>
		<!-- table line start -->
		<div class="row">
		
			<!-- list mobile line -->
			<div class="col-xs-12 hidden-sm hidden-md hidden-lg">
				
				<div class="col-sm-12 col-md-12 col-lg-12">
					<table class="table">
						<tbody class="list">
						
							<c:forEach items="${ board }" var="board">
								<tr>
									<td>
									
										<a href="/board/view/${ board.bNumber }${pageMaker.makeQuery(pageMaker.cri.page)}">
											<div>
												${ board.title }
											</div>
											<div style="font-size: 10px;">
												${ board.nickName } | 
												<fmt:formatDate pattern="MM-dd" value="${ board.regDate }"/> | 
												조회 : ${board.viewCount } | 
												댓글 : ${board.reCount }
											</div>
										</a>
										
									</td>
								</tr>
							</c:forEach>
						</tbody>
						
						<!-- list pagenation line -->
						<tbody class="page">
							<tr>
								<td colspan="5" style="text-align: center;">
									<ul class="pagination">
										<c:if test="${ pageMaker.prev }">
											<li>
												<a href="list${ pageMaker.makeQuery(pageMaker.startPage - 1) }">&lt;</a>
											</li>
										</c:if>
										
										<c:forEach begin="${ pageMaker.startPage }" end="${ pageMaker.endPage }" var="idx">
											 <c:if test="${ pageMaker.cri.page == idx }">
											 	<li class="active">
											 		<a href="list${ pageMaker.makeQuery( idx ) }">${ idx }</a>
											 	</li>
											 </c:if>
											 <c:if test="${ pageMaker.cri.page != idx }">
											 	<li>
											 		<a href="list${ pageMaker.makeQuery( idx ) }">${ idx }</a>
											 	</li>
											 </c:if>
										</c:forEach>
										
										<c:if test="${ pageMaker.next && pageMaker.endPage > 0 }">
											<li>
												<a href="list${ pageMaker.makeQuery(pageMaker.endPage + 1) }">&gt;</a>
											</li>
										</c:if>
										
									</ul>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- list mobile line -->
		
			<!-- list pc and tablet line -->
			<div class="hidden-xs col-sm-12 col-md-12 col-lg-12">
				<div class="row">
					<div class="col-sm-12 col-md-12 col-lg-12">
						<table class="table">
							<!-- main list header line -->
							<thead class="head">
								<tr>
									<th width="12%">번호</th>
									<th width="44%">제목</th>
									<th width="12%">작성자</th>
									<th width="12%">날짜</th>
									<th width="10%">조회</th>
									<th width="10%">댓글</th>
								</tr>
							</thead>
							
							<!-- main list line -->
							<tbody class="list">
							
								<c:set value="${ ( pageMaker.totalCount - ( (pageMaker.cri.page - 1) * pageMaker.cri.perPageNum ) )   + 1 }" var="cnt"/>
								
								<c:forEach items="${ board }" var="board">
									<c:set value="${ cnt - 1 }" var="cnt"/>
									<tr>
										<td>${ cnt }</td>
										<td>
											<a href="/board/view/${ board.bNumber }${pageMaker.makeQuery(pageMaker.cri.page)}">
												${ board.title }
											</a>
										</td>
										<td>
											${ board.nickName }
										</td>
										<td class="visible-lg"><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate }"/></td>
										<td class="visible-md"><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate }"/></td>
										<td class="visible-sm"><fmt:formatDate pattern="MM-dd" value="${board.regDate }"/></td>
										<td class="visible-xs"><fmt:formatDate pattern="MM-dd" value="${board.regDate }"/></td>
										<td>
											&nbsp;&nbsp;&nbsp;&nbsp;${ board.viewCount }
										</td>
										<td>
											&nbsp;&nbsp;&nbsp;&nbsp;${ board.reCount }
										</td>
									</tr>
								</c:forEach>
							</tbody>
							
							<!-- list pagenation line -->
							<tbody class="page">
								<tr>
									<td colspan="5" style="text-align: center;">
										<ul class="pagination">
											<c:if test="${ pageMaker.prev }">
												<li>
													<a href="list${ pageMaker.makeQuery(pageMaker.startPage - 1) }">&lt;</a>
												</li>
											</c:if>
											
											<c:forEach begin="${ pageMaker.startPage }" end="${ pageMaker.endPage }" var="idx">
												 <c:if test="${ pageMaker.cri.page == idx }">
												 	<li class="active">
												 		<a href="list${ pageMaker.makeQuery( idx ) }">${ idx }</a>
												 	</li>
												 </c:if>
												 <c:if test="${ pageMaker.cri.page != idx }">
												 	<li>
												 		<a href="list${ pageMaker.makeQuery( idx ) }">${ idx }</a>
												 	</li>
												 </c:if>
											</c:forEach>
											
											<c:if test="${ pageMaker.next && pageMaker.endPage > 0 }">
												<li>
													<a href="list${ pageMaker.makeQuery(pageMaker.endPage + 1) }">&gt;</a>
												</li>
											</c:if>
											
										</ul>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- list pc and tablet line -->
			
		</div>
		<!-- table line end -->
		
	</div>

	<jsp:include page="../common/footer.jsp" />
</body>
</html>