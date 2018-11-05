var address = "광주 광역시 북구 두암동 859-22";
$(function(){
	$.getJSON("/daum/location/" + address, function(data){
		getMap(data.x, data.y, 'map');
	});
});
function getMap(x, y, map){
	var mapContainer = document.getElementById(map), // 지도를 표시할 div 
	mapOption = {
		center : new daum.maps.LatLng(y,x), // 지도의 중심좌표
		level : 3
	};
	var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	var markerPosition = new daum.maps.LatLng(y, x);
	var marker = new daum.maps.Marker({
		position : markerPosition
	});
	marker.setMap(map);
}