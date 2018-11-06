var webSocket = new WebSocket("ws://127.0.0.1:8080/talk/");
//var webSocket = new WebSocket("ws://portfolio.freeserver.zone/talk/");

webSocket.onerror = function(event) {onError(event)};
webSocket.onopen = function(event) {onOpen(event)};
webSocket.onmessage = function(event) {onMessage(event)};

function onMessage(event) {
	var message = event.data;
	$("#chatDiv").append(message);
	$("#chatDiv").scrollTop(30000);
}

function onOpen(event) {
	$("#chatDiv").append("건강한 커뮤니티를 만들어갑시다.<br>");
}

function onError(event) {
	console.log("연결 에러 "+event.data);
}

$(document).on("keyup", "#chatText", function(e){
	if(e.keyCode == 13){
		$.ajax({
			type : 'post',
			url : '/ws/send',
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : 'text',
			data : JSON.stringify({
				content : $(this).val()
			}),
			success : function(data){
				var json = JSON.parse(data);
				if(json.result == "succ"){
					var html = "";
					html += decodeURIComponent(json.nickName) + " : " + decodeURIComponent(json.content) + "<br>";
					webSocket.send(html);
		    		$("#chatDiv").append(html);
		    		$("#chatDiv").scrollTop(30000);
		    		$("#chatText").val("");
		    		$("#chatText").focus();
				}else if(json.result == "login"){
					alert("로그인이 필요한 기능입니다.");
				}
			}
		});
	}
});

$(document).on("mouseover", ".over-image", function(){
	$(".over-div").css("display", "none");
	$(this).parent().prev().children().css("display", "block");
});
$(document).on("mouseout", ".over-div", function(){
	$(this).css("display", "none");
});