<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/common.jsp" />
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<meta name="author" content="freeserver.zone">
	<meta name="keywords" content="개발자 게시판, ${pageMaker.cri.category}게시판, 개발에 관하여, 웹개발관련문의">
	<meta name="description" content="개발자 게시판입니다. 다양한 게시물을 등록해주세요.">
	
	<meta property="fb:app_id" content=""/>
	
	<meta property="og:title" content="웹 개발자 포트폴리오 | 게시판 리스트" />
	<meta property="og:type" content="website" />
	<meta property="og:description" content="개발자 ${pageMaker.cri.category} 게시판입니다. 다양한 게시물을 등록해주세요.">
	<meta property="og:image" content="http://portfolio.freeserver.zone/resources/img/001.jpg"/>
	<meta property="og:site_name" content="포트폴리오"/> 
	<meta property="og:url" content="http://portfolio.freeserver.zone/board/list/${pageMaker.cri.category}"/>
	
	<title>웹 개발자 포트폴리오 | 게시판 리스트</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-xs-12 hidden-sm hidden-md hidden-lg">
				<div class="row">
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
						</table>
					</div>
				</div>
			</div>
		
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
						</table>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
				<ul class="pagination">
					<li>
						<c:if test="${ member != null }">
							<a href="/board/register/${pageMaker.cri.category}" class="btn btn-default">글쓰기</a>
						</c:if>
						<c:if test="${ member == null }">
							<a href="#" onclick="alert('로그인 후 이용하실 수 있습니다.')" class="btn btn-default">글쓰기</a>
						</c:if>
					</li>
				</ul>
			</div>
			<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8 text-right">
				<ul class="pagination">
					<c:if test="${ pageMaker.prev }">
						<li>
							<a href="${ pageMaker.makeQueryBoard(pageMaker.startPage - 1) }">&lt;</a>
						</li>
					</c:if>
					<c:forEach begin="${ pageMaker.startPage }" end="${ pageMaker.endPage }" var="idx">
						 <c:if test="${ pageMaker.cri.page == idx }">
						 	<li class="active">
						 		<a href="${ pageMaker.makeQueryBoard( idx ) }">${ idx }</a>
						 	</li>
						 </c:if>
						 <c:if test="${ pageMaker.cri.page != idx }">
						 	<li>
						 		<a href="${ pageMaker.makeQueryBoard( idx ) }">${ idx }</a>
						 	</li>
						 </c:if>
					</c:forEach>
					<c:if test="${ pageMaker.next && pageMaker.endPage > 0 }">
						<li>
							<a href="${ pageMaker.makeQueryBoard(pageMaker.endPage + 1) }">&gt;</a>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp" />
</body>
</html>