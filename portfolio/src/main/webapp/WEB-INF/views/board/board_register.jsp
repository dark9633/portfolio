<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/common.jsp" />
	<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
	
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
	
	<style>
		.cke_button__superbutton_icon { background-position: 0 -360px !important; }
	</style>
	
	<title>웹 개발자 포트폴리오 | 게시판 리스트</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />

	<div class="container">
		<div class="row">
		
			<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
	
			<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
				<form action="/board/register/${ cri.category }" id="form" method="post">
	
					<div class="form-group">
						<input type="text" id="title" name="title" class="form-control" placeholder="제목을 입력하세요" maxlength="50">
					</div>
					<div class="form-group text-right">
						<textarea id="editor" name="content"></textarea>
					</div>
					
					<div class="form-group row">
						<div class="col-xs-12 text-right">
							<input type="hidden" id="category" name="category" value="${ cri.category }">
							<input type="file" id="file" style="display: none;" multiple="multiple">
							<input type="submit" id="submitBtn" class="btn btn-default" value="등록">
							<input type="button" id="cancelBtn" class="btn btn-default" value="취소">
						</div>
					</div>
					
				</form>
			</div>
			<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
			
		</div>
	</div>

	<script type="text/javascript">
						
		editor = CKEDITOR.replace("editor", {
			width:'100%',
			height:'450px',
			filebrowserUploadUrl :"/board/image"
		});

		//버튼 이벤트
		editor.addCommand("mySimpleCommand", {
		    exec: function(edt) {
		    	$("#file").click();
		    }
		});

		//ckeditor 이미지 버튼 추가
		editor.ui.addButton('SuperButton', {
		    label: "이미지",
		    command: 'mySimpleCommand',
		    toolbar: 'insert',
		    icon: '/resources/ckeditor/plugins/icons.png?t=HBDD'
		});
		
	</script>
						
	<jsp:include page="../common/footer.jsp" />
	
</body>
</html>