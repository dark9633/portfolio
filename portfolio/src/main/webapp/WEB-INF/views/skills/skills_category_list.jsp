<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
		.body-nav{
			border: 1px solid #ddd;
		}
		.body-content{
			border: 1px solid #ddd;
		}
	</style>
	
	<title>웹 개발자 포트폴리오 | 스킬 리스트 카테고리</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />

	<section>
		<div class="container">
			<div class="row">
				
				<div class="col-xs-4 col-sm-4 col-md-3 col-lg-3 body-nav">
					<ul>
						<li>리눅스</li>
						<li>마스터</li>
						<li>어쩌다</li>
					</ul>
	            </div>
	            
	            <div class="col-xs-8 col-sm-8 col-md-9 col-lg-9 body-content">
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
									
									<c:forEach items="${ skills }" var="skill">
										<c:set value="${ cnt - 1 }" var="cnt"/>
										<tr>
											<td>${ cnt }</td>
											<td>
												<a href="">
													title
												</a>
											</td>
											<td>
												nickName
											</td>
											<td class="visible-lg"><fmt:formatDate pattern="yyyy-MM-dd" value="2018-10-18 11:41:45"/></td>
											<td class="visible-md"><fmt:formatDate pattern="yyyy-MM-dd" value="2018-10-18 11:41:45"/></td>
											<td class="visible-sm"><fmt:formatDate pattern="MM-dd" value="2018-10-18 11:41:45"/></td>
											<td class="visible-xs"><fmt:formatDate pattern="MM-dd" value="2018-10-18 11:41:45"/></td>
											<td>
												&nbsp;&nbsp;&nbsp;&nbsp;0
											</td>
											<td>
												&nbsp;&nbsp;&nbsp;&nbsp;1
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
	            
			</div>
		</div>
	</section>

	<jsp:include page="../common/footer.jsp" />
</body>
</html>