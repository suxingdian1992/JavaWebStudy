<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<!-- scriptlet区块，可以允许编码人员在jsp页面上书写java代码动态生成页面 -->
<%
	DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
	String s = dateFormat.format(new Date());
	out.println("Today is " + s);
%>
</body>
</html>