<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Polar Chart Example</title>
<!-- 引入 ECharts 文件 -->
<script src="/ServeletDemo/js/echarts.min.js"></script>
</head>
<body>
	<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	<div id="main" style="width: 600px; height: 400px;"></div>
	<script type="text/javascript">
		var data = [];

		for (var i = 0; i <= 360; i++) {
			var t = i / 180 * Math.PI;
			var r = Math.sin(2 * t) * Math.cos(2 * t);
			data.push([ r, i ]);
		}

		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));

		var option = {
			title : {
				text : '极坐标双数值轴'
			},
			legend : {
				data : [ 'line' ]
			},
			polar : {
				center : [ '50%', '54%' ]
			},
			tooltip : {
				trigger : 'axis',
				axisPointer : {
					type : 'cross'
				}
			},
			angleAxis : {
				type : 'value',
				startAngle : 0
			},
			radiusAxis : {
				min : 0
			},
			series : [ {
				coordinateSystem : 'polar',
				name : 'line',
				type : 'line',
				showSymbol : false,
				data : data
			} ],
			animationDuration : 2000,
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	</script>
</body>
</html>