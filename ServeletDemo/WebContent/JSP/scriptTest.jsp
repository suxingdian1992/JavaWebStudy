<%@page import="sun.security.jca.GetInstance"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Scriptlet example</title>
</head>
<body>
	<b>Http headers:</b>
	<br />
	<%-- first scriptlet --%>
	<!-- scriptlet即为jsp中的脚本区块，这里的区块中写java代码可以动态生成页面，能使用jsp的隐式对象 -->
	<%
		for (Enumeration<String> e = request.getHeaderNames(); e.hasMoreElements();) {
			String header = e.nextElement();
			out.println(header + ": " + request.getHeader(header) + "<br/>");
		}
		String message = "Thank you.";
	%>
	<hr />
	<%-- second scriptlet --%>
	<%
		out.println(message);
	%>
	ServletName:"<%=config.getServletName()%>"
</body>
</html>