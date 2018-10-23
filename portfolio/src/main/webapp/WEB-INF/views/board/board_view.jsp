<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/common.jsp" />
	<link rel="stylesheet" href="/resources/css/board/board_view.css">
	
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
	
	<title>웹 개발자 포트폴리오 | 게시글 상세</title>
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
								<span class="glyphicon glyphicon glyphicon-comment title-icon"> <b class="reCount">${ view.reCount }</b></span><!-- 게시판에서는 미사용 -->
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
								<a href="/board/modify/${ view.category }/${ view.bNumber }" class="btn btn-default btn-lg">수정</a>
								<a href="/board/delete/${ view.category }/${ view.bNumber }" class="btn btn-default btn-lg" id="deleteBtn">삭제</a>
							</c:if>
							<a href="/board/list/${ view.category }" class="btn btn-default btn-lg">목록보기</a>
						</div>
						<div class="col-md-1"></div>
					</div>
					
				</div>
				
				<div class="row">
					<div class="col-lg-12">
						<h2 class="page-header">댓글</h2>
						<section class="comment-list" id="replyList"></section>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-12">
						<form method="post" onsubmit="return false;">
							<div class="row">
								<!-- 비로그인 처리 -->
								<c:if test="${ member == null }">
									<div class="col-md-10 form-group">
										<textarea class="form-control" rows="5" placeholder="댓글을 작성하시려면 로그인을 해주세요" disabled="disabled"></textarea>
									</div>
									<div class="col-md-2 form-group">
										<input type="submit" class="btn btn-default" value="등록" disabled="disabled">
									</div>
								</c:if>
								
								<!-- 로그인 처리 및 댓글 처리 -->
								<c:if test="${ member != null }">
									<div class="col-lg-10 form-group">
										<textarea class="form-control" name="content" id="content" rows="5"></textarea>
									</div>
									<div class="col-lg-2 form-group">
										<input type="submit" id="replyBtn" class="btn btn-default" value="등록">
									</div>
								</c:if>
							
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	
	<!-- modify Modal line -->
	<div class="modal fade" id="reply-mod-modal" role="dialog" style="padding-top: 100px;">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">댓글 수정</h4>
				</div>
				<div class="modal-body">
					<div style="border: 1px solid #e5e5e5;">
						<input type="hidden" id="mod_reNumber" value="">
						<textarea class="form-control" id="mod_content" rows="6"></textarea>	
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" id="replyMod" data-dismiss="modal">수정</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				</div>
			</div>
		</div>
	</div>
	<!-- modify Modal line -->
	
	<script type="text/javascript">
		var bNumber = ${ bNumber };
		var nickName = "${ member.nickName }";	//회원 생성전까지 임시적으로 사용
	</script>
	<script src="/resources/js/board/board_view.js"></script>

	<jsp:include page="../common/footer.jsp" />
	
</body>
</html>