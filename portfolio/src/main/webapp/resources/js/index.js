$(document).on("mouseover", ".over-image", function(){
	$(".over-div").css("display", "none");
	$(this).parent().prev().children().css("display", "block");
});
$(document).on("mouseout", ".over-div", function(){
	$(this).css("display", "none");
});