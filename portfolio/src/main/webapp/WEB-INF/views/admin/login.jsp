<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<jsp:include page="./common/common.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
	body{padding-top: 100px;}
</style>

<title>Admin Login</title>
</head>
<body>

<div class="container">
    <div class="row">
		<div class="col-md-4 col-md-offset-4">
    		<div class="panel panel-default">
			  	<div class="panel-heading">
			    	<h3 class="panel-title">관리자 로그인</h3>
			 	</div>
			  	<div class="panel-body">
			    	<form action="/admin/login" method="post">
                    <fieldset>
			    	  	<div class="form-group">
			    		    <input class="form-control" placeholder="ID는 아무거나 입력하세요." id="id" name="id" type="text">
			    		</div>
			    		<div class="form-group">
			    			<input class="form-control" placeholder="Password는 아무거나 입력하세요." name="pwd" type="password">
			    		</div>
			    		<input class="btn btn-lg btn-success btn-block" type="submit" value="로그인">
			    	</fieldset>
			      	</form>
			    </div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		$("#admId").focus();
	});
</script>

</body>
</html>