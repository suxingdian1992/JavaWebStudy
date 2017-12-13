<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>echarts-JSON请求数据</title>
		<!-- <link rel="shortcut icon" href="../js/echarts-2.2.7/doc/asset/ico/favicon.png"> -->
		<script type="text/javascript" src="/ServeletDemo/js/jquery-3.2.1.min.js" ></script>
		<script type="text/javascript" src="/ServeletDemo/js/echarts.min.js"></script>
		<script>
			$(document).ready(function(){
				var chart = document.getElementById('chart');
				var chartData = echarts.init(chart);
	
			    chartData.setOption({
			        title: {
			            text: '异步数据加载示例'
			        },
			        tooltip: {},
			        legend: {
			            data:['销量']
			        },
			        xAxis: {
			            data: []
			        },
			        yAxis: {},
			        series: [{
			            name: '销量',
			            type: 'bar',
			            data: []
			        }]
			    });
			    /* 用jquery请求json文件 */
				$.get('/ServeletDemo/js/data.json').done(function (data) {
					console.dir(data);
					// 填入数据
				    chartData.setOption({
				        xAxis: {
				            data: data.categories
				        },
				        series: [{
				            name: '销量',
				            data: data.data
				        }]
				    });
				
				});
			});

		</script>
	</head>
	<body>
		<div id="chart" style="width: 600px; height: 400px;"></div>
	</body>
</html>
