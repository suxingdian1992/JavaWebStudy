<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>测试索引页面</title>
</head>
<body>
<!-- 此处的action为注解中的urlPatterns -->
<form action="myhttp" method="GET">
网址名：<input type="text" name="name">
<br />
网址：<input type="text" name="url" />
<input type="submit" value="提交" />
</form>
<script type="text/javascript"> 
//小型弹窗示例
function openWin(src, width, height, showScroll){ 
window.showModalDialog (src,"","location:No;status:No;help:No;dialogWidth:"+width+";dialogHeight:"+height+";scroll:"+showScroll+";"); 
}
openWin("http://localhost:8080/ServeletDemo/main.jsp","100px","200px",true);
</script> 
</body>
</html>