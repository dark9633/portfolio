//ckeditor load
editor = CKEDITOR.replace("editor", {
	width:'100%',
	height:'350px',
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
        url: "/skills/imageUpload",
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
		url : '/skills/imageDelete',
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "GET"
		},
		dataType : 'text'
	});
};

//스킬 등록
$(document).on("click", "#submitBtn", function(e){
	e.preventDefault();
	var title = $("#title");
	var subCategory = $("#subCategory");
	var form = $("#form");
	
	if(title.val() == ""){
		alert("제목을 입력하세요.");
		title.focus();
		return;
	}else if(subCategory.val() == ""){
		alert("카테고리를 입력하세요.");
		subCategory.focus();
		return;
	}
	window.onbeforeunload = null;
	form.submit();
});