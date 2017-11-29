<%@page import="org.apache.jasper.tagplugins.jstl.core.Param"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Loging Test</title>
</head>
<body>
	<h2>This is forward page test</h2>
	<%
/* 取得跳转中传递的参数 */
	out.println(request.getParameter("ParmeterTest"));
%>
</body>
</html>