<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- nabvar line Start -->
	<nav class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/">HOME</a>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="/">개인이력</a></li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
							포트폴리오<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li><a href="/">서버개발</a></li>
							<li><a href="/">플레이스24</a></li>
							<li><a href="/">피그미웹</a></li>
							<li><a href="/">푸시미웹</a></li>
							<li><a href="/">관리자앱</a></li>
							<li><a href="/">피그미앱</a></li>
							<li><a href="/">자동화</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
							기술개발<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li><a href="/">LINUX</a></li>
							<li><a href="/">WEB</a></li>
							<li><a href="/">BOOTSTRAP</a></li>
							<li><a href="/">ANDROID</a></li>
							<li><a href="/">API</a></li>
						</ul>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
					<c:if test="${ member == null }">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
							aria-haspopup="true" aria-expanded="false">로그인 <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#" data-toggle="modal" data-target="#login-modal">로그인</a></li>
								<!-- <li><a href="/member/register">회원가입</a></li> -->
							</ul>
					</c:if>
					<c:if test="${ member != null }">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
							aria-haspopup="true" aria-expanded="false">${ member.memNickName } <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="/category/list">마이페이지</a></li>
								<li><a href="/logout">로그아웃</a></li>
							</ul>
					</c:if>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	
	<!-- login Modal line -->	
	<div class="modal fade" id="login-modal" role="dialog" style="padding-top: 100px;">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">로그인</h4>
				</div>
				<div class="modal-body">
					<form action="/login" method="post" role="form" id="login-form">
						<fieldset>
							<div class="form-group">
								<input class="form-control" placeholder="E-mail" name="id" id="id" type="text">
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="Password" name="pw" id="pw" type="password">
							</div>
							<div class="checkbox">
								<label> 
									<input name="remember" id="remember" type="checkbox" value="Remember Me"> 아이디 저장하기
								</label>
							</div>
							<input class="btn btn-lg btn-default btn-block" type="submit" id="login-submit" value="Login">
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- login Modal line -->
