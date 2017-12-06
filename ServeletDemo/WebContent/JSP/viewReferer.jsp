<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ViewReference</title>
</head>
<body>
<!-- 从隐式对象session中取出之前在searchengine中存入的变量referer -->
The referer header of the previous page is ${sessionScope.referer},及从seesion中取得了是从哪个搜索引擎跳转过来的信息
</body>
</html>