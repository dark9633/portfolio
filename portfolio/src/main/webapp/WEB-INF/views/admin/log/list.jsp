<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>

<jsp:include page="../common/common.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Log List</title>
</head>
<body>

	<jsp:include page="../common/header.jsp" />
	
	<div class="container-fluid">
		<div class="row">
			
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="row">
					<div class="col-lg-2 col-md-2 col-sm-4 col-xs-6">
						<div class="form-group">
							<input type="button" value="모두보기" class="form-control" id="all-button">
						</div>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-4 col-xs-6">
						<div class="form-group">
							<input type="button" value="사이트 로그" class="form-control log-button btn-info" id="log-button">
						</div>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-4 col-xs-6">
						<div class="form-group">
							<input type="button" value="브라우저 로그" class="form-control log-button" id="browser-button">
						</div>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-4 col-xs-6">
						<div class="form-group">
							<input type="button" value="많이본 페이지" class="form-control log-button" id="url-button">
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 log-division" id="log-division">
				<h3>접속 로그별 카운트</h3>
				<div id='userLog' style="width:100%; height: 450px;"></div>
			</div>
			
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 log-division" id="browser-division" style="display: none;">
				<h3>접속 브라우저별 카운트</h3>
				<div id='userBrowser' class="col-xs-6" style="height: 550px;"></div>
				<div class="col-xs-6">
					<table class="table table-hover">
						<thead>
							<tr>
								<td>디테일</td>
								<td>카운트</td>
							</tr>
						</thead>
						<tbody id="userDevice"></tbody>
					</table>
				</div>
			</div>
			
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 log-division" id="url-division" style="display: none;">
				<h3>가장 많이본 페이지</h3>
				<div id='userUrl' style="width:100%; height: 450px;"></div>
			</div>
			
		</div>
	</div>
	
	<script type="text/javascript" src="/resources/chartjs/amcharts.js"></script>
	<script type="text/javascript" src="/resources/chartjs/serial.js"></script>
	<script type="text/javascript" src="/resources/chartjs/pie.js"></script>
	<script type="text/javascript" src="/resources/chartjs/light.js"></script>

	<script type="text/javascript">
		var userLogData = [];
		var browserLogData = [];
		var urlLogData = [];
		var chartCursor;
		
		$(function(){
			resultLog();
			resultBrowser();
			resultDevice();
			resultUrl();
		});
		
		function resultUrl(){
			$.getJSON("/admin/log/url", function(data){
				$.each(data, function(ind, ent){
					if(ent.url.length > 25){
						urlLogData.push({
							country : ent.url.substring(0, 25) + "...",
							visits : ent.cnt 
						});
					}else{
						urlLogData.push({
							country : ent.url,
							visits : ent.cnt 
						});
					}
				});
			}).done(function(){
				resultUrlList(urlLogData);
			});
		}
		
		function resultUrlList(data){
			var chart = AmCharts.makeChart("userUrl", {
				  "type": "serial",
				  "theme": "light",
				  "marginRight": 70,
				  "dataProvider": data,
				  "startDuration": 1,
				  "graphs": [{
				    "balloonText": "<b>[[category]]: [[value]]</b>",
				    "fillColorsField": "color",
				    "fillAlphas": 0.9,
				    "lineAlpha": 0.2,
				    "type": "column",
				    "valueField": "visits"
				  }],
				  "chartCursor": {
				    "categoryBalloonEnabled": false,
				    "cursorAlpha": 0,
				    "zoomable": false
				  },
				  "categoryField": "country",
				  "categoryAxis": {
				    "gridPosition": "start",
				    "labelRotation": 45
				  },
				  "export": {
				    "enabled": true
				  }

				});
		}
		
		function resultDevice(){
			$.getJSON("/admin/log/device", function(data){
				var html = "";
				$.each(data, function(index, entry){
					html += "<tr>";
					html += "<td>"+entry.device+"</td>";
					html += "<td>"+entry.cnt+" 건</td>";
					html += "</tr>";
				});
				$("#userDevice").empty();
				$("#userDevice").append(html);
			});
		}
		
		function resultBrowser(){
			$.getJSON("/admin/log/browser", function(data){
				$.each(data, function(ind, ent){
					browserLogData.push({
						country : ent.browser,
						litres : ent.cnt 
					});
				});
			}).done(function(){
				resultBrowserList(browserLogData);
			});
		}
		
		function resultBrowserList(data){
			var chart = AmCharts.makeChart("userBrowser", {
				  "type": "pie",
				  "theme": "light",
				  "dataProvider": data,
				  "valueField": "litres",
				  "titleField": "country",
				   "balloon":{
				   "fixedPosition":true
				  },
				  "export": {
				    "enabled": true
				  }
				});
		}
		
		function resultLog(){
			$.getJSON("/admin/log/log", function(data){
				$.each(data, function(ind, ent){
					userLogData.push({
						date : ent.date,
						visits : ent.cnt 
					});
				});
			}).done(function(){
				resultLogList(userLogData);
			});
		}
		
		function resultLogList(data){
			var html = "";
			var chart = AmCharts.makeChart("userLog", {
				"autoDisplay" : "true",
				"autoResize" : "true",
			    "type": "serial",
			    "theme": "light",
			    "marginRight": 80,
			    "autoMarginOffset": 20,
			    "marginTop": 7,
			    "dataProvider": userLogData,
			    "valueAxes": [{
			        "axisAlpha": 0.2,
			        "dashLength": 1,
			        "position": "left"
			    }],
			    "mouseWheelZoomEnabled": true,
			    "graphs": [{
			        "id": "g1",
			        "balloonText": "[[value]]",
			        "bullet": "round",
			        "bulletBorderAlpha": 1,
			        "bulletColor": "#FFFFFF",
			        "hideBulletsCount": 50,
			        "title": "red line",
			        "valueField": "visits",
			        "useLineColorForBulletBorder": true,
			        "balloon":{
			            "drop":true
			        }
			    }],
			    "chartScrollbar": {
			        "autoGridCount": true,
			        "graph": "g1",
			        "scrollbarHeight": 40
			    },
			    "chartCursor": {
			       "limitToGraph":"g1"
			    },
			    "categoryField": "date",
			    "categoryAxis": {
			        "parseDates": true,
			        "axisColor": "#DADADA",
			        "dashLength": 1,
			        "minorGridEnabled": true
			    },
			    "export": {
			        "enabled": true
			    }
			});
			
			chart.addListener("rendered", zoomChart);
			zoomChart();
			
			function zoomChart() {
			    chart.zoomToIndexes(userLogData.length - 8, userLogData.length - 1);
			}
	
			// changes cursor mode from pan to select
			function setPanSelect() {
			    if (document.getElementById("rb1").checked) {
			        chartCursor.pan = false;
			        chartCursor.zoomable = true;
			    } else {
			        chartCursor.pan = true;
			    }
			    chart.validateNow();
			}
		}
		
		$(document).on("click", ".log-button", function(){
			var id = $(this).attr("id");
			var type = id.split("-")[0];
			$(".log-division").css("display", "none");
			$("#"+type+"-division").css("display", "");
			$(".log-button").attr("class", "form-control log-button");
			$(this).attr("class", "form-control log-button btn-info");
		});
		
		$(document).on("click", "#all-button", function(){
			$(".log-button").attr("class", "form-control log-button");
			$(".log-division").css("display", "");
		});
	</script>
	

	<jsp:include page="../common/footer.jsp" />

</body>
</html>