<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	Your referer header: ${header.referer}
	<br />
	<tags:doBodyDemo>
   		${header.referer}
	</tags:doBodyDemo>
	<a href="JSP/viewReferer.jsp">View</a> the referer as a Session attribute.
</body>
</html>