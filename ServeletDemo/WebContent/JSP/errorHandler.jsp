<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page isErrorPage="true"%>
<!-- 此处表示此页面为出错时展示 -->
<html>
<head><title>ErrorSuxingdianCustomerize</title></head>
<body>
An error has occurred. <br/>
Error message: 
<%
    out.println(exception.toString());
%>
</body>
</html>