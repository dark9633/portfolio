//메인 이미지 파일 오픈 이벤트
$(document).on("click", "#file_btn", function(e){
	e.preventDefault();
	$("#file").click();
});

//메인 이미지 등록 이벤트
$(document).on("change","#file",function(){
	$("#file_name").val($(this).val());
});

//ckeditor load
editor = CKEDITOR.replace("editor", {
	width:'100%',
	height:'350px',
});

//버튼 이벤트
editor.addCommand("mySimpleCommand", {
    exec: function(edt) {
    	$("#image_file").click();
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
$(document).on("change","#image_file",function(){
	var fileCount = document.getElementById("image_file").files.length;
	var files = document.getElementById("image_file").files;
	
	var formData = new FormData();
	for(var i = 0; i < fileCount; i++){
		formData.append("file"+i, files[i]);
	}
	
	$.ajax({
        url: "/portfolios/imageUpload",
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
		url : '/portfolios/imageDelete',
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "GET"
		},
		dataType : 'text'
	});
};

//포트폴리오 수정
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