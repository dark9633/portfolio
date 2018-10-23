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
	
	<style type="text/css">
		.content-my-list{
			margin-bottom: 0;
			padding: 10px 10px 10px 10px;
		}
		
		.mainContentAll{
			min-height:400px; 
			border: 1px solid #dddddd;
		}
		
		.mainContent{
			min-height: 100px;
			padding: 30px;
		}
		
		.mainContent p img, .mainContent div img, .mainContent img{
			max-width: 100%;
			height: auto;
		}
		
		.comment-post img{
			max-width: 100%;
			height: auto;
			padding: 15px 15px 15px 15px;
		}
		
		.title-icon{
			padding: 3px 3px 3px 3px;
		}
		
		.in-add-foot{
			padding-top: 50px;
			padding-bottom: 50px;
		}
		
		#bReplyContent{
			resize: vertical;
		}
	</style>
	
	<title>웹 개발자 포트폴리오 | 포트폴리오 상세</title>
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
								<a href="/portfolio/modify/${ view.pfNumber }" class="btn btn-default btn-lg">수정</a>
								<a href="/portfolio/delete/${ view.pfNumber }" class="btn btn-default btn-lg" id="deleteBtn">삭제</a>
							</c:if>
							<a href="" class="btn btn-default btn-lg">목록보기</a>
						</div>
						<div class="col-md-1"></div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		//스킬 삭제 메소드
		$(document).on("click", "#deleteBtn", function(e){
			e.preventDefault();
			var c = confirm("정말로 삭제하시겠습니까?");
			if(c){
				location.href = $("#deleteBtn").attr("href");
			}
		});
	</script>

	<jsp:include page="../common/footer.jsp" />
</body>
</html>