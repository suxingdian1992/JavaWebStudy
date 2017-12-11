<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">  
<title>模态窗口DIV</title>  
<style type="text/css">  
<!--  
.black_overlay{  
            display: none;  
            position: absolute;  
            top: 0%;  
            left: 0%;  
            width: 100%;  
            height: 100%;  
            background-color: black;  
            z-index:1001;  
            -moz-opacity: 0.8;  
            opacity:.80;  
            filter: alpha(opacity=80);  
}  
.white_content {  
            display: none;  
            position: absolute;  
            top: 25%;  
            left: 25%;  
            width: 50%;  
            height: 50%;  
            padding: 16px;  
            border: 16px solid orange;  
            background-color: white;  
            z-index:1002;  
            overflow: auto;  
}  
-->  
</style>  
<script language="javascript">  
function opendiv(lixing){  
document.getElementById('light').style.display='block';  
if(lixing==0)  
    document.getElementById('fade').style.display='block';  
}  
function colsediv(lixing){  
document.getElementById('light').style.display='none';  
if(lixing==0)  
    document.getElementById('fade').style.display='none';  
}  
</script>  
  
  
</head>  
  
<body>  
<p>可以根据自己要求修改css样式<a href = "javascript:void(0)" onclick = "opendiv()">点击这里打开窗口(不充许)</a></p>  
  
<div id="light" class="white_content">This is the lightbox content. 
<a href = "javascript:void(0)" onclick = "colsediv()">Close</a>
<iframe src="http://localhost:8080/ServeletDemo/index.jsp">
</div>  
<div id="fade" class="black_overlay"></div>  
  
  
</body>  
</html>