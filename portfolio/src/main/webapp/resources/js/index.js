var webSocket = new WebSocket("ws://"+location.host+"/talk/");

webSocket.onerror = function(event) {onError(event)};
webSocket.onopen = function(event) {onOpen(event)};
webSocket.onmessage = function(event) {onMessage(event)};

function onMessage(event) {
	var message = event.data;
	var json = JSON.parse(event.data);
	if(json.result == "message"){
		$("#chatDiv").append(json.message);
		$("#chatDiv").scrollTop(30000);
	}else if(json.result == "connect"){
		$("#chatCount").html(json.count);
	}
}

function onOpen(event) {
	$.getJSON("/ws/list", function(data){
		var html = "";
		$.each(data.reverse(), function(index, entry){
			html += entry.nickName + " : " + entry.content + "<br>";
		});
		$("#chatDiv").append(html);
		$("#chatDiv").scrollTop(30000);
	});
}

function onError(event) {
	alert("대화창 연결에 실패했습니다. 지속적으로 문제가 생길경우 문의주세요.");
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
					html += decodeURIComponent(json.nickName) + " : " + decodeURIComponent(json.content).replace(/\+/g, ' ') + "<br>";
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