<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page errorPage="errorHandler.jsp"%>
<!-- 此处为出错展示页面，出错之后会被导向之前设定好的错误展示页面：errorhadler -->
<%
    Integer.parseInt("Throw me");
%>
<html>
<head><title>errorHandlertest</title></head>
<body>
errorHandler test
</body>
</html>