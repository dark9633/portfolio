//스킬 삭제 메소드
$(document).on("click", "#deleteBtn", function(e){
	e.preventDefault();
	var c = confirm("정말로 삭제하시겠습니까?");
	if(c){
		location.href = $("#deleteBtn").attr("href");
	}
});