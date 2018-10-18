
$(function(){
	//이메일 쿠키가 있는경우 로그인 이메일을 작성한다.
	var email = getCookie("email");
	if(email != ""){
		$("#remember").attr("checked", true);
		$("#login_email").val(email);
	}
});

//로그인 이메일 저장을 위한 검증
$(document).on("click", "#login-submit", function(e){
	e.preventDefault();
	
	var email = $("#login_email");
	var pwd = $("#login_pwd");
	var remember = $("#remember");
	
	if(email.val().length == 0){
		alert("이메일을 입력해주세요.");
		email.focus();
		return;
	}else if(pwd.val().length == 0){
		alert("비밀번호를 입력해주세요.");
		pwd.focus();
		return;
	}
	if(remember.is(":checked")){
		setCookie("email", email.val(), 30);
	}else{
		setCookie("email", email.val(), 0);
	}
	
	$("#login-form").submit();
});

//쿠키 출력용 자바스크립트
function getCookie(cname){
	var name = cname + "=";
	var ca = document.cookie.split(';');
	
	for(var i = 0; i < ca.length; i++){
		var c = ca[i];
		while(c.charAt(0) == ' ') c = c.substring(1);
		if(c.indexOf(name) == 0) return c.substring(name.length, c.length); 
	}
	return "";
}

//쿠키 저장용 스크립트
function setCookie(cname, cvalue, exdays){
	var d = new Date();
	d.setDate(d.getDate() + exdays);
	var expires = "expires=" + d.toGMTString();
	document.cookie = cname + "=" + cvalue + "; " + expires;
}

//날짜 포맷용 자바스크립트
function javaDateFormat(date){
	var result;
	
	var dateObj = new Date(date);
	var year = dateObj.getFullYear();
	var month = dateObj.getMonth() + 1;
	var date = dateObj.getDate();
	
	if( month < 10){
		month = "0" + month;
	}
	if( date < 10){
		date = "0" + date;
	}
	result = year + "-" + month + "-" + date;
	return result;
}