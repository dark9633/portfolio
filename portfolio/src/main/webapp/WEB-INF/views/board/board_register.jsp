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
	
	<title>웹 개발자 포트폴리오 | 게시글 등록</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<form action="/board/register" id="form" method="post">
	
					<div class="form-group">
						<input type="text" id="title" name="title" class="form-control" placeholder="제목을 입력하세요" maxlength="50">
					</div>
					<div class="form-group text-right">
						<textarea id="editor" name="content"></textarea>
					</div>
					
					<div class="form-group row">
						<div class="col-xs-12 text-right">
							<input type="hidden" id="nickName" name="nickName" value="${ member.nickName }">
							<input type="hidden" id="category" name="category" value="${ cri.category }">
							<input type="file" id="file" style="display: none;" multiple="multiple">
							<input type="submit" id="submitBtn" class="btn btn-default" value="등록">
							<input type="button" id="cancelBtn" class="btn btn-default" value="취소">
						</div>
					</div>
					
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		
		//ckeditor load
		editor = CKEDITOR.replace("editor", {
			width:'100%',
			height:'450px',
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
		
		//다중 이미지 업로드
		$(document).on("change","#file",function(){
			var fileCount = document.getElementById("file").files.length;
			var files = document.getElementById("file").files;
			
			var formData = new FormData();
			for(var i = 0; i < fileCount; i++){
				formData.append("file"+i, files[i]);
			}
			
			$.ajax({
		        url: "/board/imageUpload",
		        enctype: "multipart/form-data",
		        data: formData,
		        dataType: "text",
		        processData: false,
		        contentType: false,
		        type: "POST",
		        success: function(data){
		           var url = data.split("123456789----------");
		           var content = CKEDITOR.instances.editor.getData();
		           var text = "";
		           $.each(url,function(index,entry){
		              if(entry != ""){
		                 text += '<p><img src="/picture/portfolio'+entry+' " /></p>';
		              }
		           });
		           CKEDITOR.instances.editor.setData(content + text);
		        },
		        error:function(e){
		           console.log(e);
		        }
		     });
		});
		
		//글 작성 도중 나갈때 이미지 삭제
		window.onbeforeunload = function (e) {
			$.ajax({
				type : 'get',
				url : '/board/imageDelete',
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "GET"
				},
				dataType : 'text'
			});
		};
		
		//게시글 등록
		$(document).on("click", "#submitBtn", function(e){
			e.preventDefault();
			var title = $("#title");
			var form = $("#form");
			
			if(title.val() == ""){
				alert("제목을 입력하세요.");
				title.focus();
				return;
			}
			window.onbeforeunload = null;
			form.submit();
		});
		
	</script>
						
	<jsp:include page="../common/footer.jsp" />
	
</body>
</html>