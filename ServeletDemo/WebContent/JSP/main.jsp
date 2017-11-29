<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>Including a file</title></head>
<body>
This is the included content: <hr/>
<!-- include指令，引入外围的jspf文件，内部是静态html -->
<!-- 注意区分后续的include指令和include动作的区别 -->
<%@ include file="copyright.jspf"%>
</body>
</html>
