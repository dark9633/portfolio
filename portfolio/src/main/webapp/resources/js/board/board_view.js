//게시물 삭제 메소드
$(document).on("click", "#deleteBtn", function(e){
	e.preventDefault();
	var c = confirm("정말로 삭제하시겠습니까?");
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
				
				if(entry.nickName == nickName){
					html += "<a href='#' class='btn btn-default btn-sm replyMod' data-toggle='modal' data-target='#reply-mod-modal' data='"+entry.reNumber+"'><i class='fa fa-reply'></i> 수정</a>";
					html += "<a href='#' class='btn btn-default btn-sm replyDel' data='"+entry.reNumber+"'><i class='fa fa-reply'></i> 삭제</a>";
				}
				
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

//댓글 리스트 더보기 페이징처리
$(document).on("click", "#more", function(e){
	e.preventDefault();
	var limit = $(this).attr("data");
	limit = Number(limit) + 5;
	getReply(limit);
});

//댓글 등록
$(document).on("click", "#replyBtn", function(e){
	var content = $("#content").val();
	$.ajax({
		type : 'post',
		url : '/reply',
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		dataType : 'text',
		data : JSON.stringify({
			bNumber : bNumber,
			nickName : nickName,
			content : content
		}),
		success : function(result){
			if(result == "succ"){
				$(".reCount").html(Number($(".reCount").html()) + 1);
				$('#content').val("");
				getReply(5);
			}else{
				alert("댓글 등록에 실패했습니다.");
			}
		}
	});
});

//댓글삭제
$(document).on("click", ".replyDel", function(e){
	e.preventDefault();
	var reNumber = $(this).attr("data");
	var c = confirm("정말로 삭제하시겠습니까?");
	if(c){
		$.ajax({
			type : 'delete',
			url : '/reply/'+reNumber,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			dataType : 'text',
			success : function(result){
				if(result == "succ"){
					$(".reCount").html(Number($(".reCount").html()) - 1);
					getReply(5);
				}else{
					alert("댓글 삭제에 실패했습니다.");
				}
			},
			error : function(e){
				console.log(e);
			}
		});
	}
});

//댓글 수정 정보
$(document).on("click", ".replyMod", function(e){
	e.preventDefault();
	var reNumber = $(this).attr("data");
	var content = $(".content_"+reNumber).html();
	
	$("#mod_reNumber").val(reNumber);
	$("#mod_content").val(content);
	
	$("#reply-mod-modal").modal("show");
});

//댓글 수정
$(document).on("click", "#replyMod", function(){
var reNumber = $("#mod_reNumber").val();
var content = $("#mod_content").val();

$.ajax({
	type : 'put',
	url : '/reply',
	headers : {
		"Content-Type" : "application/json",
		"X-HTTP-Method-Override" : "PUT"
	},
	data : JSON.stringify({content:content, reNumber:reNumber}),
	dataType : 'text',
	success : function(result){
		if(result == "succ"){
			getReply(5);
		}else{
			alert("댓글 수정에 실패했습니다.");
			}
		},
		error : function(e){
			console.log(e);
		}
	});
});