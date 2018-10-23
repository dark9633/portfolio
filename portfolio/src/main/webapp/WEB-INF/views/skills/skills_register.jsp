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
	
	<title>웹 개발자 포트폴리오 | 스킬 등록</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<form action="/skills/register" id="form" method="post">
					<div class="row">
						<div class="form-group col-lg-4 col-md-4 col-sm-4 col-xs-4">
							<select class="form-control" id="category" name="category">
								<option value="linux">리눅스</option>
								<option value="web">웹서버</option>
								<option value="bootstrap">부트스트랩</option>
								<option value="android">안드로이드</option>
								<option value="database">데이터베이스</option>
								<option value="api">API</option>
							</select>
						</div>
						<div class="form-group col-lg-8 col-md-8 col-sm-8 col-xs-8">
							<input type="text" id="subCategory" name="subCategory" class="form-control" placeholder="카테고리를 입력하세요" maxlength="50">
						</div>
					</div>
					<div class="form-group">
						<input type="text" id="title" name="title" class="form-control" placeholder="제목을 입력하세요" maxlength="50">
					</div>
					<div class="form-group text-right">
						<textarea id="editor" name="content"></textarea>
					</div>
					
					<div class="form-group row">
						<div class="col-xs-12 text-right">
							<input type="hidden" id="nickName" name="nickName" value="${ member.nickName }">
							<input type="file" id="file" style="display: none;" multiple="multiple">
							<input type="submit" id="submitBtn" class="btn btn-default" value="등록">
							<input type="button" id="cancelBtn" class="btn btn-default" value="취소">
						</div>
					</div>
					
				</form>
			</div>
		</div>
	</div>
	
	<script src="/resources/js/skills/skills_register.js"></script>

	<jsp:include page="../common/footer.jsp" />
</body>
</html>