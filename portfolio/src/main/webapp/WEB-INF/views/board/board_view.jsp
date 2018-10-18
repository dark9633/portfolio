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
	
	<title>웹 개발자 포트폴리오 | 게시글 상세</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />

	<div class="container">
		<div class="row">
			
			<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
			
			<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
				
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
								<span class="glyphicon glyphicon-eye-open title-icon"> ${ view.viewCount }</span>
								<span class="glyphicon glyphicon glyphicon-comment title-icon"> ${ view.reCount }</span><!-- 게시판에서는 미사용 -->
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
							<!-- 임시적으로 모두 허용 -->
							<a href="/board/modify/${ view.category }/${ view.bNumber }" class="btn btn-default btn-lg">수정</a>
							<a href="/board/delete/${ view.category }/${ view.bNumber }" class="btn btn-default btn-lg" id="deleteBtn">삭제</a>
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
								<!-- 임시적으로 모두 허용 -->
								<div class="col-lg-10 form-group">
									<textarea class="form-control" name="content" id="content" rows="5"></textarea>
								</div>
								<div class="col-lg-2 form-group">
									<input type="hidden" id="bNumber" name="bNumber" value="${ view.bNumber }">
									<input type="hidden" id="nickName" name="nickName" value="김남">
									<input type="submit" id="replyBtn" class="btn btn-default" value="등록">
								</div>
							
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
			
		</div>
		
	</div>
	
	<script type="text/javascript">
		var bNumber = ${ bNumber };
	
		//게시물 삭제 메소드
		$(document).on("click", "#deleteBtn", function(e){
			e.preventDefault();
			var c = confirm("정말로 삭제하시겠습니까?" + $("#deleteBtn").attr("href"));
			if(c){
				location.href = $("#deleteBtn").attr("href");
			}
		});
		
		//시작 실행 메소드
		$(document).ready(function(){
			getReply(5);
		});
		
		//댓글 리스트 ajax 통신후 html 생성
		function getReply(limit){
			$.getJSON("/reply/list/"+bNumber, function(data){
				var html = "";

				$.each(data, function(index, entry){
					
					if(index < limit){
						html += "<article class='row'>";
						
						html += "<div class='col-md-2 col-sm-2 hidden-xs'>";
						html += "<figure class='thumbnail'>";
						html += "<img class='img-responsive img-circle img-thumbnail' width = '70px;' height='70px;' src='/resources/img/none-image.jpg'/>";
						
						html += "<figcaption class='text-center'>";
						html += "<div class='dropdown'>";
						html += "<a href='#' class='dropdown-toggle' data-toggle='dropdown'>"+entry.nickName+"</a>";
						html += "<ul class='dropdown-menu'>";
						html += "<li><a href='#' data='"+entry.nickName+"' class='mCommunity'>게시글</a></li>";
						html += "<li><a href='#' data='"+entry.nickName+"' class='mReply'>댓글</a></li>";
						html += "</ul>";
						html += "</div>";
						html += "</figcaption>";
						
						html += "</figure>";
						html += "</div>"; 
						
						html += "<div class='col-md-10 col-sm-10'>";
						html += "<div class='panel panel-default arrow left'>";			//right : left
						html += "<div class='panel-body'>";
						html += "<header class='text-left'>";							//right : left
						
						html += "<div class='row'>";
						html += "<div class='col-md-12'>";
						html += "<p class='text-right'>";
						
						html += javaDateFormat(entry.regDate);
						
						html += "</p></div></div></header>";
						html += "<div class='comment-post'>";
						html += "<p class='content_"+entry.reNumber+"'>";
						
						html += entry.content;
						
						html += "</p>";
						html += "</div>";
						html += "<p class='text-right'>";
						// 임시적으로 모두 허용
						html += "<a href='#' class='btn btn-default btn-sm replyMod' data-toggle='modal' data-target='#reply-mod-modal' data='"+entry.reNumber+"'><i class='fa fa-reply'></i> 수정</a>";
						html += "<a href='#' class='btn btn-default btn-sm replyDel' data='"+entry.reNumber+"'><i class='fa fa-reply'></i> 삭제</a>";
						
						html += "</p></div></div></div>";
						
						html += "</article>";
					}
				});
				
				if(data.length > limit){
					html += "<input type='button' id='more' data='"+limit+"' class='form-control' value='더보기 (+"+Number(data.length-limit)+")'><br>";
				}
				
				$('#replyList').empty();
				$('#replyList').append(html);
			});
		}
		
		$(document).on("click", "#more", function(e){
			e.preventDefault();
			var limit = $(this).attr("data");
			limit = Number(limit) + 5;
			getReply(limit);
		});
		
	</script>

	<jsp:include page="../common/footer.jsp" />
	
</body>
</html>