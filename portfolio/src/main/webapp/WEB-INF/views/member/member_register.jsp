<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/common.jsp" />
	<title>웹 개발자 포트폴리오 | 회원가입</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />

	<section>
		<div class="container">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<form action="/member/register" method="post" id="register">
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="이메일 : " name="email" id="email">
								<div class="input-group-btn">
									<button class="btn btn-default" type="button" id="mailCheckBtn">메일검증</button>
								</div>
							</div>
						</div>
						<div class="form-group display-none" style="display: none;">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="코드 : " name="mailCode" id="mailCode">
								<div class="input-group-btn">
									<button class="btn btn-default" type="button" id="codeCheckBtn">코드확인</button>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="닉네임 : " name="nickName" id="nickName" maxlength="10">
								<div class="input-group-btn">
									<button class="btn btn-default" type="button" id="nickCheckBtn">중복체크</button>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<input type="password" class="form-control" placeholder="비밀번호 : " name="pwd" id="pwd">
						</div>
						
						<div class="form-group">
							<input type="password" class="form-control" placeholder="비밀번호 확인 : " name="pwdCheck" id="pwdCheck">
						</div>
						
						<div class="form-group text-right">
							<input type="checkbox" id="registerCheck" name="registerCheck">
							<label for="registerCheck"> 약관을 모두 읽었으며 동의합니다. </label>
						</div>
							
						<div class="col-md-12">
							<hr>
						</div>
						
						<div class="text-right">
							<input type="submit" class="btn btn-default btn-md" id="submitBtn" value="가입하기">
						</div>
					</form>
					
				</div>
				<div class="col-md-3"></div>
			</div>
		</div>
	</section>

	<script type="text/javascript">
		//회원 가입을 위한 메일검증
		$(document).on("click", "#mailCheckBtn", function(e){
			e.preventDefault();
			
			var email = $("#email").val();
			var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	
			if(email == ""){
				alert("메일을 입력하세요.");
				return false;
			}
			
			if (email.match(regExp) != null) {
				$.ajax({
					type : 'get',
					url : '/member/mailCheck?email='+email,
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "GET"
					},
					dataType : 'text',
					success : function(data){
						if(data == "succ"){
							$(".display-none").css("display", "");
							$("#email").attr("readonly", "");
							$("#mailCheckBtn").attr("disabled", "");
							$("#mailCode").focus();
						}else{
							alert("메일 전송에 실패했습니다. 이미 가입한 계정일 수 있습니다.");
						}
					},
					error : function(e){
						console.log(e);
					}
				});
			} else {
			   alert("메일 형식이 잘못되었습니다.");
			}
		});
		
		//코드 인증
		$(document).on("click", "#codeCheckBtn", function(e){
			e.preventDefault();
			
			var mailCode = $("#mailCode").val();
			
			if(mailCode == ""){
				alert("코드를 입력하세요.");
				return false;
			}
			
			$.ajax({
				type : 'get',
				url : '/member/codeCheck?code='+mailCode,
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "GET"
				},
				dataType : 'text',
				success : function(data){
					if(data == "succ"){
						$("#mailCode").attr("readonly", "");
						$("#codeCheckBtn").attr("disabled", "");
						$(".display-none").css("display", "none");
						$("#nickName").focus();
					}else{
						alert("확인에 실패했습니다.");
					}
				},
				error : function(e){
					console.log(e);
				}
			});
		});
		
		//닉네임 중복여부 검증
		$(document).on("click", "#nickCheckBtn", function(e){
			e.preventDefault();
			
			var nickName = $("#nickName").val();
			nickName = nickName.replace(" ", "");
			
			if(nickName == ""){
				alert("닉네임을 입력하세요.");
				return false;
			}else if(nickName.length > 10){
				alert("닉네임은 10자 이하만 가능합니다.");
				return false;
			}
			$.ajax({
				type : 'get',
				url : '/member/nickCheck?nickName='+encodeURI(nickName),
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "GET"
				},
				dataType : 'text',
				success : function(data){
					if(data == "succ"){
						var c = confirm("["+nickName+"]은 사용가능 합니다. 사용하시겠습니까?");
						if(c == true){
							$("#nickName").attr("readonly", "");
							$("#nickName").val(nickName);
							$("#nickCheckBtn").attr("disabled", "");
						}
					}else{
						alert("사용할 수 없는 닉네임입니다.");
					}
				},
				error : function(e){
					console.log(e);
				}
			});
		});
		
		//회원가입 마지막 검증
		$(document).on("click", "#submitBtn", function(e){
			e.preventDefault();
			var pwd = $("#pwd").val();
			var pwdCheck = $("#pwdCheck").val();
			var mailCheck = $("#email").attr("readonly");
			var codeCheck = $("#mailCode").attr("readonly");
			var nickCheck = $("#nickName").attr("readonly");
			var registerCheck = $("#registerCheck").is(':checked');
			
			if(!mailCheck){
				alert("메일 검증을 완료 해주세요.");
				$("#email").focus();
				return;
			}else if(!codeCheck){
				alert("코드 인증이 되지 않았습니다.");
				$("#mailCode").focus();
				return;
			}else if(!nickCheck){
				alert("중복 체크를 완료 해주세요");
				$("#nickName").focus();
				return;
			}else if(pwd.length < 8 || pwd.length > 16){
				alert("비밀번호는 최소 8자에서 16자 이내입니다.");
				return;
			}else if(pwd != pwdCheck){
				alert("비밀번호가 틀립니다.");
				return;
			}else if(!registerCheck){
				alert("회원가입을 위해서는 이용약관에 동의해주시기 바랍니다.");
				return;
			}
			$("#register").submit();
		});
	</script>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>